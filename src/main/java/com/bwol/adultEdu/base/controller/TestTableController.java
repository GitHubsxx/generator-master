/****************************************************
 * Description: Controller for 测试管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-03-14 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.base.controller;

import com.bwol.adultEdu.base.entity.TestTable;
import com.bwol.adultEdu.base.service.TestTableService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bwol.framework.BfsuolConstants;
import com.bwol.framework.controller.LoginInfo;
import com.bwol.framework.controller.SpringControllerSupport;
import com.bwol.framework.controller.json.MessageJson;
import com.bwol.framework.controller.support.DefaultQueryParam;
import com.bwol.framework.controller.support.Pagination;
import com.bwol.framework.controller.support.QueryParam;
import com.bwol.framework.exception.ValidationException;
import com.bwol.framework.spring.LocalizedTextContext;
import com.bwol.framework.spring.QueryParameter;
import com.bwol.framework.support.log.BfsuolLogger;
import com.bwol.security.annotations.SecCreate;
import com.bwol.security.annotations.SecDelete;
import com.bwol.security.annotations.SecEdit;
import com.bwol.security.annotations.SecList;
import com.bwol.security.annotations.SecPrivilege;
import java.util.List;

/**
 * Author sxx
 * Date  2019-03-14
 */
@Controller
@RequestMapping(value = "/base/testTable")
public class TestTableController extends SpringControllerSupport{
    @Autowired
    private TestTableService testTableService;

    @SecPrivilege(title="测试管理")
    @RequestMapping("/index")
    public String index(Model model){
        return getViewNamedPath("index");
    }
    @SecList
    @RequestMapping("/list")
    public String list(
            @QueryParameter("query") QueryParam query,
            @ModelAttribute("page") Pagination page){

        page = testTableService.getPage(query, page);

        return getViewNamedPath("list");
    }
    @SecCreate(title="添加测试")
    @RequestMapping("/input")
    public String create(@ModelAttribute TestTable testTable, Model model){

        return getViewNamedPath("input");
    }
    @SecEdit(title="修改测试")
    @RequestMapping("/input/{id}")
    public String edit(@PathVariable("id") Long id, Model model){

                TestTable testTable = this.testTableService.getById(id);
                model.addAttribute(testTable);

            return getViewNamedPath("input");
    }
    @RequestMapping("/save")
    public @ResponseBody MessageJson save(@ModelAttribute TestTable testTable){
            LoginInfo loginInfo = super.getLoginInfo();
            //校验
            validateSave(testTable);
            this.testTableService.saveOrUpdatePro(testTable,loginInfo);

        return MessageJson.information("保存成功");
    }

    /**
    * 数据校验
    **/
    private void validateSave(TestTable testTable){

            //必填项校验
            if(testTable.getId() == null){
                throw new ValidationException("请选择测试");
            }
            QueryParam query = new DefaultQueryParam();
            if(testTable.getId()!=null && testTable.getId() > 0){
            query.addQuery("id!ne@l", testTable.getId());
            }
            query.addQuery("title!eq@s", testTable.getTitle());
            List<TestTable> testTableList = this.testTableService.findAll(query);
                if(CollectionUtils.isNotEmpty(testTableList)){
                throw new ValidationException("测试名称已经存在");
            }

    }
    @SecDelete
    @RequestMapping("/delete/{id}")
    public @ResponseBody MessageJson delete(@PathVariable("id") Long id){
        if(testTableService.isDeletable(id)){
            this.testTableService.delete(id);
            BfsuolLogger.data("测试删除，","测试id:"+id);
        }
        return MessageJson.information("成功删除1条");
    }

    @SecDelete
    @RequestMapping("/delete")
    public @ResponseBody MessageJson delete(@RequestParam("ids") Long[] ids){
        if(ids == null || ids.length == 0){
            return MessageJson.error("没有删除");
        }
        for(Long id : ids){
            if(testTableService.isDeletable(id)){
                testTableService.delete(id);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<ids.length;i++) {
            if(i==0) {
                sb.append(ids[i]);
            }else {
                sb.append(","+ids[i]);
            }
        }
        BfsuolLogger.data("批量删除测试，","测试ids:"+sb.toString()+"，数量:"+ids.length);
        return MessageJson.information("成功删除"+ids.length+"条");
    }
}
