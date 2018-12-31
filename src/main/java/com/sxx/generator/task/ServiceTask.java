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
public class ServiceTask extends BaseTask {

    public ServiceTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        System.out.println("Generating " + className + "Service.java");
        Map<String, String> serviceData = new HashMap<>();
        serviceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        serviceData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        serviceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        //serviceData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        serviceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        serviceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        serviceData.put("ClassName", className);
        serviceData.put("EntityName", StringUtil.firstToLowerCase(className));
        serviceData.put("Title",ConfigUtil.getConfiguration().getTitle());
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());
        String fileName = className + "Service.java";
        // 生成Service文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_SERVICE, serviceData, filePath + fileName);
    }
}
