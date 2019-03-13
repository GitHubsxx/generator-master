<#--
/****************************************************
 * Description: 输入页面，包括添加和修改
 * Copyright:   Copyright (c) (2019)
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  (2019-03-13) bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@input url="${base}/base/school/save">
		<input type="hidden" name="id" value="${school.id}"/>
		<@formgroup title='学校名称'>
			<input type="text" name="title" value="${school.title}" placeholder="例：2018"  check-type="required" maxlength="100">
		</@formgroup>

		<@formgroup title='是否有效'>
			<@swichButton name='isEnroll' title='是否' val="${school.status}" onVal=BfsuolConstants.GLOBAL_YESNO_YES offVal=BfsuolConstants.GLOBAL_YESNO_NO></@swichButton>
			<b><font style="color:red;">说明：学校只能有一个为 “是”</font></b>
		</@formgroup>

		<@formgroup title="层次">
			<@select name="levelIds" list=levelList value="${school?if_exists.levelIds}" listKey="title" listValue="id" id="levelIdInput" headerKey='' headerValue='' emptyOption=false multi=true />
		</@formgroup>

		<@formgroup title="专业">
			<@ajaxselect id="specialtyIds" name="specialtyIds" value="${school?if_exists.specialtyIds}" pid="levelIdInput"  url="${base}/base/baseObjectJson/getInputSpecialtyJSON/[levelIdInput]" multi=true />
		</@formgroup>
</@input>
