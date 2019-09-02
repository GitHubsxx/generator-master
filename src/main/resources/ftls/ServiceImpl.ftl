/****************************************************
 * Description: Controller for ${Title}管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  ${Date} bfsu Create File
 **************************************************/
package ${BasePackageName}${ServicePackageName};

import ${BasePackageName}${DaoPackageName}.${ClassName}DAO;
import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwol.framework.service.ServiceSupport;
import com.bwol.framework.exception.ValidationException;
import org.slf4j.LoggerFactory;
import com.bwol.framework.controller.LoginInfo;
import java.util.Date;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
* Author ${Author}
* Date  ${Date}
*/
@Service
public class ${ClassName}ServiceImpl extends ServiceSupport<${ClassName}> implements ${ClassName}Service{
    private final  org.slf4j.Logger logger = LoggerFactory.getLogger(${ClassName}ServiceImpl.class);
    @Autowired
    private ${ClassName}DAO ${EntityName}DAO;

    @Override
	public ${ClassName}DAO getDao() {
	    return ${EntityName}DAO;
	}
    /**
	 * 是否可以删除
	 * @param ${EntityName}Id 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long ${EntityName}Id){
		return true;
	}

    /**
	 * 保存或修改
	 * @param ${EntityName}
	 * @param loginInfo
	 */
    @Override
	public void saveOrUpdatePro(${ClassName} ${EntityName}, LoginInfo loginInfo){
            try{
                if(${EntityName}.isNew()){
                    ${EntityName}.setCreateUserId(loginInfo.getUserId());
                    ${EntityName}.setCreateUserName(loginInfo.getUserName());
                    ${EntityName}.setCreateTime(new Date());
                    this.${EntityName}DAO.save(${EntityName});
                }else {
                    ${ClassName} ${EntityName}_ = this.${EntityName}DAO.getById(${EntityName}.getId());
                    if (${EntityName}_ == null){
                        throw new ValidationException("程序未知错误");
                    }
                    ${EntityName}_.setTitle(${EntityName}.getTitle());
                    ${EntityName}_.setUpdateUserId(loginInfo.getUserId());
                    ${EntityName}_.setUpdateUserName(loginInfo.getUserName());
                    ${EntityName}_.setUpdateTime(new Date());
                    this.${EntityName}DAO.update(${EntityName}_);
                }

            }catch (Exception e){
                logger.info("${EntityName} param:",JSON.toJSONString(${EntityName}),e);
                throw new ValidationException("保存失败");
             }
        }

}
