<#--
/****************************************************
 * Description: 简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
	HISTORY
    *  2019-01-13 bfsu Create File
**************************************************/-->
<#include "/templates/ace/ace-inc.ftl">

<@list>
		<thead>

		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
			<th>学员名称</th>
			<th>是否有效</th>
			<th>创建人姓名</th>
			<th>创建时间</th>
			<th>更新人姓名</th>
			<th>更新时间</th>
			<th>操作</th>
		</tr>

		</thead>

		<tbody>
				<#list page.items?if_exists as item>
				<tr>
					<td><input type="checkbox" class="bscheck" data="id:${item.id}"></td>
					<td>
						${item.title}
					</td>
					<td>
						${item.status}
					</td>
					<td>
						${item.createUserName}
					</td>
					<td>
						${item.createTime}
					</td>
					<td>
						${item.updateUserName}
					</td>
					<td>
						${item.updateTime}
					</td>
					<td>
						<@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/learner/input/${item.id}','修改学员');">修改学员</@button>
						<@button icon="remove" 	 type="primary" onclick="bfsu.del('${base}/base/learner/delete/${item.id}','从列表中删除？')">删除</@button>
					</td>
				</tr>
				</#list>
			</tbody>
</@list>

