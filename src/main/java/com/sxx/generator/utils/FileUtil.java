package com.sxx.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * Author sxx
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
        if (file.exists()) {
            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
            return;
        }else{
            int last = filePath.lastIndexOf("\\");
            String newPath = filePath.substring(0,last);
            System.out.print(newPath);
            File file_ = new File(newPath);
            file_.mkdirs();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtils.TYPE_ENTITY:
                return FreemarketConfigUtils.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtils.TYPE_DAO:
                return FreemarketConfigUtils.getInstance().getTemplate("Dao.ftl");
            case FreemarketConfigUtils.TYPE_DAO_IMPL:
                return FreemarketConfigUtils.getInstance().getTemplate("DaoImpl.ftl");
            case FreemarketConfigUtils.TYPE_SERVICE:
                return FreemarketConfigUtils.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtils.TYPE_SERVICE_IMPL:
                return FreemarketConfigUtils.getInstance().getTemplate("ServiceImpl.ftl");
            case FreemarketConfigUtils.TYPE_CONTROLLER:
                return FreemarketConfigUtils.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtils.TYPE_MAPPER:
                return FreemarketConfigUtils.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtils.TYPE_HIBERNATE:
                return FreemarketConfigUtils.getInstance().getTemplate("Hibernate.ftl");
            case FreemarketConfigUtils.TYPE_INDEX:
                return FreemarketConfigUtils.getInstance().getTemplate("Index.ftl");
            case FreemarketConfigUtils.TYPE_INPUT:
                return FreemarketConfigUtils.getInstance().getTemplate("Input.ftl");
            case FreemarketConfigUtils.TYPE_LIST:
                return FreemarketConfigUtils.getInstance().getTemplate("List.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

}
