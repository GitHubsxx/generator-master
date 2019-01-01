package com.sxx.generator.task;

import com.sxx.generator.entity.ColumnInfo;
import com.sxx.generator.task.base.BaseTask;
import com.sxx.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * input页面
 * Author sxx
 * Date   2018/4/20
 */
public class InputTask extends BaseTask {
    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public InputTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public InputTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public InputTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", className);
        entityData.put("EntityName", StringUtil.firstToLowerCase(className));
        entityData.put("Title",ConfigUtil.getConfiguration().getTitle());
        entityData.put("base","${base}");
        entityData.put("baseData",GeneratorUtil.createBaseDataInput(entityData));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getFtl());
        String fileName =  LowerCaseUtils.generateFtlName(className)+"-" + "input.ftl";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_INPUT, entityData, filePath + fileName);
    }
}
