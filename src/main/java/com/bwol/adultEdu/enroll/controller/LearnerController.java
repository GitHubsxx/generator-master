/****************************************************
 * Description: Controller for 学员管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-01-06 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.controller;

import com.bwol.adultEdu.enroll.entity.Learner;
import com.bwol.adultEdu.enroll.service.LearnerService;
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
 * Date  2019-01-06
 */
@Controller
@RequestMapping(value = "/learner")
public class LearnerController extends SpringControllerSupport{
    @Autowired
    private LearnerService learnerService;

    @SecPrivilege(title="学员管理",code="enr.learner")
    @RequestMapping("/index")
    public String index(Model model){
        return getViewNamedPath("index");
    }
    @SecList
    @RequestMapping("/list")
    public String list(
            @QueryParameter("query") QueryParam query,
            @ModelAttribute("page") Pagination page){

        page = learnerService.getPage(query, page);

        return getViewNamedPath("list");
    }
    @SecCreate
    @RequestMapping("/input")
    public String create(@ModelAttribute Learner learner, Model model){

        return getViewNamedPath("input");
    }
    @SecEdit
    @RequestMapping("/input/{id}")
    public String edit(@PathVariable("id") Long id, Model model){

                Learner learner = this.learnerService.getById(id);
                model.addAttribute(learner);

            return getViewNamedPath("input");
    }
    @RequestMapping("/save")
    public @ResponseBody MessageJson save(@ModelAttribute Learner learner){
            LoginInfo loginInfo = super.getLoginInfo();
            //校验
            validateSave(learner);
            this.learnerService.saveOrUpdatePro(learner,loginInfo);

        return MessageJson.information("保存成功");
    }

    /**
    * 数据校验
    **/
    private void validateSave(Learner learner){

            //必填项校验
            if(learner.getId() == null){
                throw new ValidationException("请选择学员");
            }
            QueryParam query = new DefaultQueryParam();
            if(learner.getId()!=null && learner.getId() > 0){
            query.addQuery("id!ne@l", learner.getId());
            }
            query.addQuery("title!eq@s", learner.getTitle());
            List<Learner> learnerList = this.learnerService.findAll(query);
                if(CollectionUtils.isNotEmpty(learnerList)){
                throw new ValidationException("学员名称已经存在");
            }

    }
    @SecDelete
    @RequestMapping("/delete/{id}")
    public @ResponseBody MessageJson delete(@PathVariable("id") Long id){
        if(learnerService.isDeletable(id)){
            this.learnerService.delete(id);
            BfsuolLogger.data("学员删除，","学员id:"+id);
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
            if(learnerService.isDeletable(id)){
                learnerService.delete(id);
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
        BfsuolLogger.data("批量删除学员，","学员ids:"+sb.toString()+"，数量:"+ids.length);
        return MessageJson.information("成功删除"+ids.length+"条");
    }
}
