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

import ${BasePackageName}${EntityPackageName}.${ClassName};
import com.bwol.framework.service.Service;
import java.util.List;
import com.bwol.framework.controller.LoginInfo;

/**
* Author ${Author}
* Date  ${Date}
*/
public interface ${ClassName}Service extends Service<${ClassName}>{

    /**
	 * 是否可以删除
	 * @param ${EntityName}Id 主键
	 * @return 是否可以删除
	 */
    public boolean isDeletable(Long ${EntityName}Id);

    /**
	 * 保存或修改
	 * @param ${EntityName}
	 * @param loginInfo
	 */
	void saveOrUpdatePro(${ClassName} ${EntityName}, LoginInfo loginInfo);
}
