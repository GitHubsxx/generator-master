/****************************************************
 * Description: Controller for 学员管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-01-06 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.service;

import com.bwol.adultEdu.enroll.dao.LearnerDAO;
import com.bwol.adultEdu.enroll.entity.Learner;
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
* Date  2019-01-06
*/
@Service
public class LearnerServiceImpl extends ServiceSupport<Learner> implements LearnerService{
    private final  org.slf4j.Logger logger = LoggerFactory.getLogger(LearnerServiceImpl.class);
    @Autowired
    private LearnerDAO learnerDAO;

    @Override
	public LearnerDAO getDao() {
	    return learnerDAO;
	}
    /**
	 * 是否可以删除
	 * @param learnerId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long learnerId){
		return true;
	}

    /**
	 * 保存或修改
	 * @param learner
	 * @param loginInfo
	 */
    @Override
	public void saveOrUpdatePro(Learner learner, LoginInfo loginInfo){
            try{
                if(learner.isNew()){
                    learner.setCreateUserId(loginInfo.getUserId());
                    learner.setCreateUserName(loginInfo.getLoginName());
                    learner.setCreateTime(new Date());
                    this.learnerDAO.save(learner);
                }else {
                    Learner learner_ = this.learnerDAO.getById(learner.getId());
                    if (learner_ == null){
                        throw new ValidationException("程序未知错误");
                    }
                    learner_.setTitle(learner.getTitle());
                    learner_.setUpdateUserId(loginInfo.getUserId());
                    learner_.setUpdateUserName(loginInfo.getUserName());
                    learner_.setUpdateTime(new Date());
                    this.learnerDAO.update(learner_);
                }

            }catch (Exception e){
                logger.info("learner param:",JSON.toJSONString(learner),e);
                throw new ValidationException("保存失败");
             }
    };

}
