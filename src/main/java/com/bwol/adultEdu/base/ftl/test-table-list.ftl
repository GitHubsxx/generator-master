<#--
/****************************************************
 * Description: 简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) (2019)
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  (2019-03-14) bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@list>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
			<th>主键</th>
			<th>主键2</th>
			<th>名称</th>
			<th>大小</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
			<tr>
				<td><input type="checkbox" class="bscheck" data="id:${item.id}"></td>
				<td>
					${item.id}
				</td>
				<td>
					${item.schoolId}
				</td>
				<td>
					${item.name}
				</td>
				<td>
					${item.size}
				</td>
					<td>
					<@sec pcode="base.testTable" fcode="edit">
						<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/testTable/input/${item.id}','修改测试');">修改测试</@button>
					</@sec>
					<@sec pcode="base.testTable" fcode="delete">
						<@button icon="remove" 	 type="primary" onclick="bfsu.del('${base}/base/testTable/delete/${item.id}','从列表中删除？')">删除</@button>
					</@sec>
				</td>
			</tr>
		</#list>
	</tbody>
</@list>

