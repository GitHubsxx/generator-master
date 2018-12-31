/****************************************************
 * Description: Controller for 年度管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2018-12-31 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.service;

import com.bwol.adultEdu.enroll.dao.AnnualDao;
import com.bwol.adultEdu.enroll.entity.Annual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwol.framework.service.ServiceSupport;
import com.bwol.framework.exception.ValidationException;
import org.slf4j.LoggerFactory;
import com.bwol.framework.controller.LoginInfo;

import java.util.List;

/**
* Author sxx
* Date  2018-12-31
*/
@Service
public class AnnualServiceImpl extends ServiceSupport<Annual> implements AnnualService{
    private final  org.slf4j.Logger logger = LoggerFactory.getLogger(AnnualServiceImpl.class);
    @Autowired
    private AnnualDao annualDao;

    @Override
	public AnnualDao getDao() {
	    return annualDao;
	}
    /**
	 * 是否可以删除
	 * @param annualId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long annualId){
		return true;
	}

    /**
	 * 保存或修改
	 * @param annual
	 * @param loginInfo
	 */
    @Override
	void saveOrUpdatePro(Annual annual, LoginInfo loginInfo){
            try{
                if(annual.isNew()){
                    annual.setCreateUserId(loginInfo.getUserId());
                    annual.setCreateUserName(loginInfo.getLoginName());
                    annual.setCreateTime(new Date());
                    annualDAO.save(annual);
                }else {
                    Annual annual_ = annualDAO.getById(annual.getId());
                    if (annual_ == null){
                        throw new ValidationException("程序未知错误");
                    }
                    annual_.setTitle(annual.getTitle());
                    annual_.setUpdateUserId(loginInfo.getUserId());
                    annual_.setUpdateUserName(loginInfo.getUserName());
                    annual_.setUpdateTime(new Date());
                    annualDAO.update(annual_);
                }

            }catch (Exception e){
                logger.info("annual param:",annual.getTitle(),e);
                throw new ValidationException("保存失败");
             }
    };

}
