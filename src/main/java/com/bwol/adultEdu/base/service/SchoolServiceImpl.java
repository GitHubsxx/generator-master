/****************************************************
 * Description: Controller for 学校管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-03-13 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.base.service;

import com.bwol.adultEdu.base.dao.SchoolDAO;
import com.bwol.adultEdu.base.entity.School;
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
* Author sxx
* Date  2019-03-13
*/
@Service
public class SchoolServiceImpl extends ServiceSupport<School> implements SchoolService{
    private final  org.slf4j.Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
    @Autowired
    private SchoolDAO schoolDAO;

    @Override
	public SchoolDAO getDao() {
	    return schoolDAO;
	}
    /**
	 * 是否可以删除
	 * @param schoolId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long schoolId){
		return true;
	}

    /**
	 * 保存或修改
	 * @param school
	 * @param loginInfo
	 */
    @Override
	public void saveOrUpdatePro(School school, LoginInfo loginInfo){
            try{
                if(school.isNew()){
                    school.setCreateUserId(loginInfo.getUserId());
                    school.setCreateUserName(loginInfo.getLoginName());
                    school.setCreateTime(new Date());
                    this.schoolDAO.save(school);
                }else {
                    School school_ = this.schoolDAO.getById(school.getId());
                    if (school_ == null){
                        throw new ValidationException("程序未知错误");
                    }
                    school_.setTitle(school.getTitle());
                    school_.setUpdateUserId(loginInfo.getUserId());
                    school_.setUpdateUserName(loginInfo.getUserName());
                    school_.setUpdateTime(new Date());
                    this.schoolDAO.update(school_);
                }

            }catch (Exception e){
                logger.info("school param:",JSON.toJSONString(school),e);
                throw new ValidationException("保存失败");
             }
    };

}
