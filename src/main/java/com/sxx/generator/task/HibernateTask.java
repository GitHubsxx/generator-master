package com.sxx.generator.task;

import com.sxx.generator.entity.ColumnInfo;
import com.sxx.generator.task.base.BaseTask;
import com.sxx.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Author sxx
 * Date   2018/4/20
 */
public class HibernateTask extends BaseTask {

    /**
     * 单表Hibernate
     */
    public HibernateTask(String className, String tableName, List<ColumnInfo> infos) {
        this(tableName, className, null, null, null, infos, null);
    }

    /**
     * 一对多Hibernate
     */
    public HibernateTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, null, null, tableInfos, parentTableInfos);
    }

    /**
     * 多对多Hibernate
     */
    public HibernateTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }
    @Override
    public void run() throws IOException, TemplateException {
        // 生成Hibernate填充数据
        System.out.println("Generating " + className + "-"+"hibernate.hbm.xml");
        Map<String, String> mapperData = new HashMap<>();
        mapperData.put("ParentPath",ConfigUtil.getConfiguration().getParentPath());
        mapperData.put("PackageName", ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        mapperData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("TableName", tableName);
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        mapperData.put("PrimaryKey", getPrimaryKeyColumnInfo(tableInfos).getColumnName());
        mapperData.put("WhereId", "#{" + getPrimaryKeyColumnInfo(tableInfos).getPropertyName() + "}");
        mapperData.put("Id", "#{id}");
        mapperData.put("Title",ConfigUtil.getConfiguration().getTitle());
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName)));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("Association", "");
            mapperData.put("Collection", GeneratorUtil.generateMapperCollection(parentTableInfos, parentClassName, ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getEntity()));
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, relationalTableName, foreignKey, parentForeignKey, getPrimaryKeyColumnInfo(tableInfos).getColumnName(), getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else if (!StringUtil.isBlank(foreignKey)) { // 一对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos,parentTableName,parentClassName,foreignKey,mapperData));
        } else { // 单表
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, tableInfos));
            mapperData.put("ResultMap", GeneratorUtil.generateHibernateResultMap(tableInfos));
        }
        String parentPath = ConfigUtil.getConfiguration().getParentPath();
        mapperData.put("TableName",tableName);
        String filePath = FileUtil.getResourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapper());
        String fileName = LowerCaseUtils.firstLower(className) +"-"+"mysql"+ "-"+"hibernate.hbm.xml";
        // 生成Hibernate文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_HIBERNATE, mapperData, filePath + fileName);
    }

    private ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        return null;
    }
}
