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

import com.bwol.adultEdu.base.entity.TestTable;
import com.bwol.framework.service.Service;
import java.util.List;
import com.bwol.framework.controller.LoginInfo;

/**
* Author sxx
* Date  2019-03-14
*/
public interface TestTableService extends Service<TestTable>{

    /**
	 * 是否可以删除
	 * @param testTableId 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long testTableId);

    /**
	 * 保存或修改
	 * @param testTable
	 * @param loginInfo
	 */
	void saveOrUpdatePro(TestTable testTable, LoginInfo loginInfo);
}
