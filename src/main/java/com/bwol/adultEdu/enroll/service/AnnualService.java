/****************************************************
 * Description: Controller for 年度管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-01-01 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.service;

import com.bwol.adultEdu.enroll.entity.Annual;
import com.bwol.framework.service.Service;
import java.util.List;
import com.bwol.framework.controller.LoginInfo;

/**
* Author sxx
* Date  2019-01-01
*/
public interface AnnualService extends Service<Annual>{

    /**
	 * 是否可以删除
	 * @param annualId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long annualId);

    /**
	 * 保存或修改
	 * @param annual
	 * @param loginInfo
	 */
	void saveOrUpdatePro(Annual annual, LoginInfo loginInfo);
}
