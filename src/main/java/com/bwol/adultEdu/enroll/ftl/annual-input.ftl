<#--
/****************************************************
 * Description: 输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  2019-01-13 bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@input url="${base}/base/annual/save">
		<input type="hidden" name="id" value="${annual.id}"/>
		<@formgroup title='年度名称'>
			<input type="text" name="title" value="${annual.title}" placeholder="例：2018"  check-type="required" maxlength="100">
		</@formgroup>

		<@formgroup title='是否有效'>

			<@swichButton name='isEnroll' title='是否' val="${annual.status}" onVal=BfsuolConstants.GLOBAL_YESNO_YES offVal=BfsuolConstants.GLOBAL_YESNO_NO></@swichButton>
			<b><font style="color:red;">说明：年度只能有一个为 “是”</font></b>

		</@formgroup>
</@input>
