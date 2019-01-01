package com.sxx.generator.task;

import com.sxx.generator.task.base.BaseTask;
import com.sxx.generator.utils.ConfigUtil;
import com.sxx.generator.utils.FileUtil;
import com.sxx.generator.utils.FreemarketConfigUtils;
import com.sxx.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author sxx
 * Date   2018/4/20
 */
public class DaoImplTask extends BaseTask {

    public DaoImplTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Dao填充数据
        System.out.println("Generating " + className + "Dao.java");
        Map<String, String> daoData = new HashMap<>();
        daoData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        daoData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        daoData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        daoData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        daoData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        daoData.put("ClassName", className);
        daoData.put("EntityName", StringUtil.firstToLowerCase(className));
        daoData.put("Title",ConfigUtil.getConfiguration().getTitle());
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = className + "DAOImpl.java";
        // 生成dao文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_DAO_IMPL, daoData, filePath + fileName);
    }
}
