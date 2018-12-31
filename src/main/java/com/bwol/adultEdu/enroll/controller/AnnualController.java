/****************************************************
 * Description: Controller for 年度管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2018-12-31 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.controller;

import com.bwol.adultEdu.enroll.entity.Annual;
import com.bwol.adultEdu.enroll.service.AnnualService;
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
 * Date  2018-12-31
 */
@Controller
@RequestMapping(value = "/annual")
public class AnnualController extends SpringControllerSupport{
    @Autowired
    private AnnualService annualService;

    @SecPrivilege(title="年度管理",code="enr.annual")
    @RequestMapping("/index")
    public String index(Model model){
        return getViewNamedPath("index");
    }
    @SecList
    @RequestMapping("/list")
    public String list(
            @QueryParameter("query") QueryParam query,
            @ModelAttribute("page") Pagination page){

        page = annualService.getPage(query, page);

        return getViewNamedPath("list");
    }
    @SecCreate
    @RequestMapping("/input")
    public String create(@ModelAttribute Annual annual, Model model){

        return getViewNamedPath("input");
    }
    @SecEdit
    @RequestMapping("/input/{id}")
    public String edit(@PathVariable("id") Long id, Model model){

                Annual annual = this.annualService.getById(id);
                model.addAttribute(annual);

            return getViewNamedPath("input");
    }
    @RequestMapping("/save")
    public @ResponseBody MessageJson save(@ModelAttribute Annual annual){
            LoginInfo loginInfo = super.getLoginInfo();
            //校验
            validateSave(annual);
            this.annualService.saveOrUpdatePro(annual,loginInfo);

        return MessageJson.information("保存成功");
    }

    /**
    * 数据校验
    **/
    private void validateSave(Annual annual){

            //必填项校验
            if(annual.getId() == null){
                throw new ValidationException("请选择年度");
            }
            QueryParam query = new DefaultQueryParam();
            if(annual.getId()!=null && annual.getId() > 0){
            query.addQuery("id!ne@l", annual.getId());
            }
            query.addQuery("title!eq@s", annual.getTitle());
            List<Annual> annualList = this.annualService.findAll(query);
                if(CollectionUtils.isNotEmpty(annualList)){
                throw new ValidationException("年度名称已经存在");
            }

    }
    @SecDelete
    @RequestMapping("/delete/{id}")
    public @ResponseBody MessageJson delete(@PathVariable("id") Long id){
        if(annualService.isDeletable(id)){
            this.annualService.delete(id);
            BfsuolLogger.data("年度删除，","年度id:"+id);
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
            if(annualService.isDeletable(id)){
                annualService.delete(id);
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
        BfsuolLogger.data("批量删除年度，","年度ids:"+sb.toString()+"，数量:"+ids.length);
        return MessageJson.information("成功删除"+ids.length+"条");
    }
}
