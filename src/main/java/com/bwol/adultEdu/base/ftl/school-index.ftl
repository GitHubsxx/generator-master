<#--
/****************************************************
 * Description: 列表页面
 * Copyright:   Copyright (c) (2019)
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  (2019-03-13) bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@html>

<@head>

</@head>

<@body>

<@nav '学校'/>

<@content url="${base}/base/school/list" >

		<@query queryUrl="${base}/base/school/list">

			<@querygroup  title='学校名称'><input type='text' name='query.title!lk@s'  class="form-control" placeholder="请输入学校名称"></@querygroup>

			<@querygroup  title='状态'><@select list=BfsuolConstants.GLOBAL_YESNO name="query.isEnroll!eq@i" listKey='' listValue='' id="statusSelect" multi=false search=false/></@querygroup>

		</@query>
		<@sec pcode="base.school" fcode="create">
			<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/school/input','添加学校');">添加学校</@button>
		</@sec>
</@content>

</@body>

</@html>
