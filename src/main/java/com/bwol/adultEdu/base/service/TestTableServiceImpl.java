/****************************************************
 * Description: Controller for 测试管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-03-14 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.base.service;

import com.bwol.adultEdu.base.dao.TestTableDAO;
import com.bwol.adultEdu.base.entity.TestTable;
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
* Date  2019-03-14
*/
@Service
public class TestTableServiceImpl extends ServiceSupport<TestTable> implements TestTableService{
    private final  org.slf4j.Logger logger = LoggerFactory.getLogger(TestTableServiceImpl.class);
    @Autowired
    private TestTableDAO testTableDAO;

    @Override
	public TestTableDAO getDao() {
	    return testTableDAO;
	}
    /**
	 * 是否可以删除
	 * @param testTableId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long testTableId){
		return true;
	}

    /**
	 * 保存或修改
	 * @param testTable
	 * @param loginInfo
	 */
    @Override
	public void saveOrUpdatePro(TestTable testTable, LoginInfo loginInfo){
            try{
                if(testTable.isNew()){
                    testTable.setCreateUserId(loginInfo.getUserId());
                    testTable.setCreateUserName(loginInfo.getLoginName());
                    testTable.setCreateTime(new Date());
                    this.testTableDAO.save(testTable);
                }else {
                    TestTable testTable_ = this.testTableDAO.getById(testTable.getId());
                    if (testTable_ == null){
                        throw new ValidationException("程序未知错误");
                    }
                    testTable_.setTitle(testTable.getTitle());
                    testTable_.setUpdateUserId(loginInfo.getUserId());
                    testTable_.setUpdateUserName(loginInfo.getUserName());
                    testTable_.setUpdateTime(new Date());
                    this.testTableDAO.update(testTable_);
                }

            }catch (Exception e){
                logger.info("testTable param:",JSON.toJSONString(testTable),e);
                throw new ValidationException("保存失败");
             }
    };

}
