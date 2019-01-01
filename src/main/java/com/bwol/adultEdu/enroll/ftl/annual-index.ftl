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

<@nav '年度'/>

<@content url="${base}/base/annual/list" >

			<@query queryUrl="${base}/base/annual/list">

				<@querygroup  title='年度名称'><input type='text' name='query.title!lk@s'  class="form-control" placeholder="请输入年度名称"></@querygroup>

			</@query>

</@content>

</@body>

</@html>
