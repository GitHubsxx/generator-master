<#--
/****************************************************
 * Description: 列表页面
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  2019-01-13 bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@html>

<@head>

</@head>

<@body>

<@nav '学员'/>

<@content url="${base}/base/learner/list" >

		<@query queryUrl="${base}/base/learner/list">

			<@querygroup  title='学员名称'><input type='text' name='query.title!lk@s'  class="form-control" placeholder="请输入学员名称"></@querygroup>

			<@querygroup  title='状态'><@select list=BfsuolConstants.GLOBAL_YESNO name="query.isEnroll!eq@i" listKey='' listValue='' id="statusSelect" multi=false search=false/></@querygroup>

		</@query>
		<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/learner/input','添加学员');">添加学员</@button>
</@content>

</@body>

</@html>
