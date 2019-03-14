<#--
/****************************************************
 * Description: 列表页面
 * Copyright:   Copyright (c) (2019)
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  (2019-03-14) bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@html>

<@head>

</@head>

<@body>

<@nav '测试'/>

<@content url="${base}/base/testTable/list" >

		<@query queryUrl="${base}/base/testTable/list">

			<@querygroup  title='测试名称'><input type='text' name='query.title!lk@s'  class="form-control" placeholder="请输入测试名称"></@querygroup>

			<@querygroup  title='状态'><@select list=BfsuolConstants.GLOBAL_YESNO name="query.isEnroll!eq@i" listKey='' listValue='' id="statusSelect" multi=false search=false/></@querygroup>

		</@query>
		<@sec pcode="base.testTable" fcode="create">
			<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/testTable/input','添加测试');">添加测试</@button>
		</@sec>
</@content>

</@body>

</@html>
