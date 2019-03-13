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

import com.bwol.adultEdu.base.entity.School;
import com.bwol.framework.service.Service;
import java.util.List;
import com.bwol.framework.controller.LoginInfo;

/**
* Author sxx
* Date  2019-03-13
*/
public interface SchoolService extends Service<School>{

    /**
	 * 是否可以删除
	 * @param schoolId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long schoolId);

    /**
	 * 保存或修改
	 * @param school
	 * @param loginInfo
	 */
	void saveOrUpdatePro(School school, LoginInfo loginInfo);
}
