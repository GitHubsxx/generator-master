/****************************************************
 * Description: Controller for ${Title}管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  ${Date} bfsu Create File
 **************************************************/
package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
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
 * Author ${Author}
 * Date  ${Date}
 */
@Controller
@RequestMapping(value = "/${ParentPath}/${EntityName}")
public class ${ClassName}Controller extends SpringControllerSupport{
    @Autowired
    private ${ClassName}Service ${EntityName}Service;

    @SecPrivilege(title="${Title}管理")
    @RequestMapping("/index")
    public String index(Model model){
        return getViewNamedPath("index");
    }
    @SecList
    @RequestMapping("/list")
    public String list(
            @QueryParameter("query") QueryParam query,
            @ModelAttribute("page") Pagination page){

        page = ${EntityName}Service.getPage(query, page);

        return getViewNamedPath("list");
    }
    @SecCreate(title="添加${Title}")
    @RequestMapping("/input")
    public String create(@ModelAttribute ${ClassName} ${EntityName}, Model model){

        return getViewNamedPath("input");
    }
    @SecEdit(title="修改${Title}")
    @RequestMapping("/input/{id}")
    public String edit(@PathVariable("id") Long id, Model model){

                ${ClassName} ${EntityName} = this.${EntityName}Service.getById(id);
                model.addAttribute(${EntityName});

            return getViewNamedPath("input");
    }
    @RequestMapping("/save")
    public @ResponseBody MessageJson save(@ModelAttribute ${ClassName} ${EntityName}){
            LoginInfo loginInfo = super.getLoginInfo();
            //校验
            validateSave(${EntityName});
            this.${EntityName}Service.saveOrUpdatePro(${EntityName},loginInfo);

        return MessageJson.information("保存成功");
    }

    /**
    * 数据校验
    **/
    private void validateSave(${ClassName} ${EntityName}){

            //必填项校验
            if(${EntityName}.getId() == null){
                throw new ValidationException("请选择${Title}");
            }
            QueryParam query = new DefaultQueryParam();
            if(${EntityName}.getId()!=null && ${EntityName}.getId() > 0){
            query.addQuery("id!ne@l", ${EntityName}.getId());
            }
            query.addQuery("title!eq@s", ${EntityName}.getTitle());
            List<${ClassName}> ${EntityName}List = this.${EntityName}Service.findAll(query);
                if(CollectionUtils.isNotEmpty(${EntityName}List)){
                throw new ValidationException("${Title}名称已经存在");
            }

    }
    @SecDelete
    @RequestMapping("/delete/{id}")
    public @ResponseBody MessageJson delete(@PathVariable("id") Long id){
        if(${EntityName}Service.isDeletable(id)){
            this.${EntityName}Service.delete(id);
            BfsuolLogger.data("${Title}删除，","${Title}id:"+id);
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
            if(${EntityName}Service.isDeletable(id)){
                ${EntityName}Service.delete(id);
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
        BfsuolLogger.data("批量删除${Title}，","${Title}ids:"+sb.toString()+"，数量:"+ids.length);
        return MessageJson.information("成功删除"+ids.length+"条");
    }
    /**
     * 导出
    **/
    @SecFunction(code = "export",title = "导出")
    @RequestMapping("/export")
    public void export(@QueryParameter("query") QueryParam query,
                       HttpServletRequest request, HttpServletResponse response){
        this.${EntityName}Service.export(query,request,response);
        this.${EntityName}Service.getPage(query,null)
        String[] column = {"年级","学号","姓名","函授站","专业","层次","手机号","指导老师"};
        String[] field = {"annual","studentNumber","userName","studyCenter","specialty","level","mobile","thesisAdviser"};
        ExcelUtil.createExcel(response, request,new HSSFWorkbook(),"导出信息",column,field,dtoList,"导出信息");
    }
}
