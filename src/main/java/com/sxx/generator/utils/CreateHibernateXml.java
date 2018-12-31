package com.sxx.generator.utils;
import com.bwol.adultEdu.enroll.entity.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 根据实体类生成Hibernate映射文件
 * 此类只适用于生成简单的隐射文件，如需根据自己需求生成，则可修改convert方法中的相关JAVA代码
 * @time 2015-03-30
 * @author sxx
 *
 */
public class CreateHibernateXml {

    public static void main(String[] args){
        try {
            String  comments = "任务信息表"; //映射文件注释名称
            String  idName = "id"; //主键名称
            boolean isuuid = true; //是否允许生成UUid
            CreateHibernateXml.convert(Test.class, new File("src/main/java"), idName, isuuid,comments);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param cls 实体类Class
     * @param file 文件生成路径
     * @param id 主键名称
     * @param uuid 是否自动生成序号uuid格式
     * @param comments 映射文件注释
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static boolean convert(Class cls, File file, String id, boolean uuid,String comments) throws IOException {

        if (null == cls || file == null) {
            return false;
        }

        if (file.isDirectory()) {
            file = new File(file.getAbsolutePath() + "//" + cls.getSimpleName() + ".hbm.xml");
        }

        if (file.exists()) {
            file.delete();
        }

        // 输出流
        FileOutputStream fs = new FileOutputStream(file);

        // 写头
        fs.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n".getBytes("UTF-8"));
        fs.write("<!DOCTYPE hibernate-mapping PUBLIC\n".getBytes("UTF-8"));
        fs.write("\"-//Hibernate/Hibernate Mapping DTD//EN\"\n".getBytes("UTF-8"));
        fs.write("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n".getBytes("UTF-8"));
        fs.write(("<!-- "+comments+" -->\n").getBytes("UTF-8"));
        // 正文
        StringBuffer str = new StringBuffer();
        str.append("<hibernate-mapping package=\"" + cls.getPackage().getName() + "\">\n");
        str.append("\t<class name=\"" + cls.getSimpleName() + "\" table=\"t_" + cls.getSimpleName().toLowerCase() + "\">\n\n");
        str.append("\t\t<cache usage=\"read-write\" />\n\n");

        Field[] fls = cls.getDeclaredFields();
        Field[] sfls = cls.getSuperclass().getDeclaredFields();

        // 只加载父类的主键
        for (Field f : sfls) {

            if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
                continue;
            }

            if (f.getName().equals(id)) {
                str.append("\t\t<id name=\"" + id + "\" type=\"" + f.getType().getName() + "\">\n");
                str.append("\t\t\t<column name=\"" + id + "\" />\n");

                if (uuid) {
                    str.append("\t\t\t<generator class=\"uuid\" />\n");
                }

                str.append("\t\t</id>\n\n");
                break;
            }
        }

        // 加载当前类的所有属性
        for (Field f : fls) {

            if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
                continue;
            }

            if (f.getName().equals(id)) {
                str.append("\t\t<id name=\"" + id + "\" type=\"" + f.getType().getName() + "\">\n");
                str.append("\t\t\t<column name=\"" + id + "\" />\n");

                if (uuid) {
                    str.append("\t\t\t<generator class=\"uuid\"></generator>\n");
                }

                str.append("\t\t</id>\n\n");
                continue;
            }

            str.append("\t\t<property name=\"" + f.getName() + "\" type=\"" + f.getType().getName() + "\">\n");
            str.append("\t\t\t<column name=\"" + f.getName() + "\" />\n");
            str.append("\t\t</property>\n\n");
        }

        // 只加载父类的普通属性
        for (Field f : sfls) {

            if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
                continue;
            }

            if (f.getName().equals(id)) {
                continue;
            }

            str.append("\t\t<property name=\"" + f.getName() + "\" type=\"" + f.getType().getName() + "\">\n");
            str.append("\t\t\t<column name=\"" + f.getName() + "\" />\n");
            str.append("\t\t</property>\n\n");
        }

        str.append("\t</class>\n");
        str.append("</hibernate-mapping>");
        fs.write(str.toString().getBytes("UTF-8"));
        return false;
    }
}

