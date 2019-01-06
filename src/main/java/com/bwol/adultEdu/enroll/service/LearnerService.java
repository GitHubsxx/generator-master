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

import com.bwol.adultEdu.enroll.entity.Learner;
import com.bwol.framework.service.Service;
import java.util.List;
import com.bwol.framework.controller.LoginInfo;

/**
* Author sxx
* Date  2019-01-06
*/
public interface LearnerService extends Service<Learner>{

    /**
	 * 是否可以删除
	 * @param learnerId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long learnerId);

    /**
	 * 保存或修改
	 * @param learner
	 * @param loginInfo
	 */
	void saveOrUpdatePro(Learner learner, LoginInfo loginInfo);
}
