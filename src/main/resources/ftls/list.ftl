<#--
/****************************************************
 * Description: 年度的简单列表页面，没有编辑功能
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
<@list>
	<thead>
    <tr>
        <th><input type="checkbox" class="bscheckall"></th>
        <th>年度名称</th>
        <th>简称</th>
        <th>是否为招生年度</th>
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
                ${item.simpleTitle}
            </td>
            <td>
                ${getIntText(item.isEnroll)}
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
            <@button icon="pencil" type="primary" size="sm" onclick="bfsu.add('${base}/base/annual/input/${item.id}','修改年度');">修改年度</@button>
            <@button icon="remove" 	 type="primary" onclick="bfsu.del('${base}/base/annual/delete/${item.id}','从列表中删除？')">删除</@button>
            </td>
        </tr>
        </#list>
    </tbody>
</@list>