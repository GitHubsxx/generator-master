<#--
/****************************************************
 * Description: 年度的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2014
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  2014-01-13 bfsu Create File
**************************************************/
-->
<#include "/templates/ace/ace-inc.ftl">
<@input url="${base}/base/annual/save">
    <input type="hidden" name="id" value="${annual.id}"/>
    <@formgroup title='年度名称'>
    	<input type="text" name="title" value="${annual.title}" placeholder="例：2018  或  2018年度  或  18年度"  check-type="required" maxlength="100">
    </@formgroup>
    <@formgroup title='简称'>
    	<input type="text" name="simpleTitle" value="${annual.simpleTitle}" placeholder="例：18"  check-type="required number" maxlength="2">
    	<b><font style="color:red;">说明：生成学籍号、创建班级时要用到简称</font></b>
    </@formgroup>
    <@formgroup title='是否为招生年度'>
        <@swichButton name='isEnroll' title='是否' val="${annual.isEnroll}" onVal=BfsuolConstants.GLOBAL_YESNO_YES offVal=BfsuolConstants.GLOBAL_YESNO_NO></@swichButton>
    	<b><font style="color:red;">说明：招生年度只能有一个为 “是”</font></b>
    </@formgroup>
</@input>