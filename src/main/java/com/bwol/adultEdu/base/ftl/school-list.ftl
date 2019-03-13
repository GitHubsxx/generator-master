<#--
/****************************************************
 * Description: 简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) (2019)
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  (2019-03-13) bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@list>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
			<th>主键</th>
			<th>学校名称</th>
			<th>学校所在地</th>
			<th>办学形式</th>
			<th>办学类型</th>
			<th>批准文号</th>
			<th>计划属性</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
			<tr>
				<td><input type="checkbox" class="bscheck" data="id:${item.id}"></td>
				<td>
					${item.schoolId}
				</td>
				<td>
					${item.title}
				</td>
				<td>
					${item.address}
				</td>
				<td>
					${item.runSchoolForm}
				</td>
				<td>
					${item.runSchoolType}
				</td>
				<td>
					${item.approvalNumber}
				</td>
				<td>
					${item.planProperties}
				</td>
					<td>
					<@sec pcode="base.school" fcode="edit">
						<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/school/input/${item.id}','修改学校');">修改学校</@button>
					</@sec>
					<@sec pcode="base.school" fcode="delete">
						<@button icon="remove" 	 type="primary" onclick="bfsu.del('${base}/base/school/delete/${item.id}','从列表中删除？')">删除</@button>
					</@sec>
				</td>
			</tr>
		</#list>
	</tbody>
</@list>

