package com.sxx.generator.utils;

import com.sxx.generator.entity.ColumnInfo;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Author sxx
 * Date   2018/4/19
 */
public class GeneratorUtil {

    /**
     * 时间
     *
     * @param infos
     * @return
     */
    public static String createTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return  sdf.format(date);
    }
    /**
     * 生成实体类属性字段（基本数据类型，用于单表关系）
     *
     * @param infos 表结构
     * @return
     */
    public static String generateEntityProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("    ");
            }
            //主键就id
            if(infos.get(i).isPrimaryKey())
                sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append("id").append(";")
                        .append("//").append(infos.get(i).getComment()).append("\n");
           else
                sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(";")
                        .append("//").append(infos.get(i).getComment()).append("\n");
        }
        return sb.toString();
    }


    /**
     * 生成实体类属性字段（列表，用于多对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @return
     */
    public static String generateEntityProperties(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder(generateEntityProperties(infos));
        sb.append("    ").append("private List<").append(parentClassName).append(">").append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("s; \n");
        return sb.toString();
    }

    /**
     * 生成实体类属性字段（引用，用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateEntityProperties(String parentClassName, List<ColumnInfo> infos, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                //主键就id
                if(infos.get(i).isPrimaryKey())
                    sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append("id").append(";")
                            .append("//").append(infos.get(i).getComment()).append("\n");
                else
                sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(";")
                        .append("//").append(infos.get(i).getComment()).append("\n");
            }
        }
        // 外键为父表实体引用
        sb.append("    ").append("private ").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("; \n");
        return sb.toString();
    }


    /**
     * 生成实体类存取方法（用于单表关系）
     *
     * @param infos 表结构
     * @return
     */
    public static String generateEntityMethods(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("    ");
            }
            //主键id
            if (infos.get(i).isPrimaryKey()) {
                sb.append(" /**\n" +
                        "     * @param 主键\n" +
                        "     */");
                sb.append("\n");
                sb.append("    ");
                sb.append("public void setId").append(" (").append("Long").append(" ").append("id").append(") {this.").append("id").append(" = ").append("id").append(";} \n\n");
                sb.append("    ")
                        .append(" /**\n" +
                                "     * @return 主键\n" +
                                "     */");
                sb.append("\n");
                sb.append("    ");
                sb.append("public ").append("Long").append(" getId").append("(){ return ").append("id").append(";} \n\n");
            } else{
                sb.append(" /**\n" +
                        "     * 设置"+infos.get(i).getComment()+"\n" +
                        "     * @param ("+infos.get(i).getPropertyName()+") ("+infos.get(i).getComment()+")\n" +
                        "     */");
                sb.append("\n");
                sb.append("    ");
                sb.append("public void set").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append(" (").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(") {this.").append(infos.get(i).getPropertyName()).append(" = ").append(infos.get(i).getPropertyName()).append(";} \n\n");
            if (infos.get(i).getType() == Types.BIT || infos.get(i).getType() == Types.TINYINT) {
                sb.append(" \t/**\n" +
                            "     * 返回"+infos.get(i).getComment()+"\n" +
                            "     * @return "+infos.get(i).getComment()+"\n" +
                            "     */");
                    sb.append("\n");
                sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" is").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n\n");
            } else {
                sb.append("\t/**\n" +
                            "     * 返回"+infos.get(i).getComment()+"\n" +
                            "     * @return "+infos.get(i).getComment()+"\n" +
                            "     */");
                    sb.append("\n");
                sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" get").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n\n");
            }
            }
        }
        return sb.toString();
    }

    /**
     * 生成实体类存取方法（用于多对多关系）
     *
     * @param parentClassName
     * @param infos
     * @return
     */
    public static String generateEntityMethods(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder(generateEntityMethods(infos));
        sb.append("    ").append("public void set" + parentClassName + "s (List<" + parentClassName + "> " + StringUtil.firstToLowerCase(parentClassName) + "s) { \n this." + StringUtil.firstToLowerCase(parentClassName) + "s = " + StringUtil.firstToLowerCase(parentClassName) + "s;\n} \n");
        sb.append("    ").append("public List<" + parentClassName + "> get" + parentClassName + "s(){ return this." + StringUtil.firstToLowerCase(parentClassName) + "s;} \n");
        return sb.toString();
    }

    /**
     * 生成实体类存取方法（用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateEntityMethods(String parentClassName, List<ColumnInfo> infos, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                //主键id
                if (infos.get(i).isPrimaryKey()) {
                    sb.append(" /**\n" +
                            "     * @param 主键\n" +
                            "     */");
                    sb.append("\n");
                    sb.append("    ");
                    sb.append("public void setId").append(" (").append("Long").append(" ").append("id").append(") {this.").append("id").append(" = ").append("id").append(";} \n");
                    sb.append("    ")
                            .append(" /**\n" +
                                    "     * @return 主键\n" +
                                    "     */");
                    sb.append("\n");
                    sb.append("    ");
                    sb.append("public ").append("Long").append(" getId").append("(){ return ").append("id").append(";} \n");
                }else {
                    sb.append(" /**\n" +
                            "     * 设置"+infos.get(i).getComment()+"\n" +
                            "     * @param ("+infos.get(i).getPropertyName()+") ("+infos.get(i).getComment()+")\n" +
                            "     */");
                    sb.append("\n");
                    sb.append("    ");
                    sb.append("public void set").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append(" (").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(") {this.").append(infos.get(i).getPropertyName()).append(" = ").append(infos.get(i).getPropertyName()).append(";} \n");
                    if (infos.get(i).getType() == Types.BIT || infos.get(i).getType() == Types.TINYINT) {
                        sb.append(" \t/**\n" +
                                "     * 返回"+infos.get(i).getComment()+"\n" +
                                "     * @return "+infos.get(i).getComment()+"\n" +
                                "     */");
                        sb.append("\n");
                        sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" is").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
                    } else {
                        sb.append("\t/**\n" +
                                "     * 返回"+infos.get(i).getComment()+"\n" +
                                "     * @return "+infos.get(i).getComment()+"\n" +
                                "     */");
                        sb.append("\n");
                        sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" get").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
                    }
                }
            }
        }
        // 外键为存取父表实体引用
        sb.append("\t/**\n" +
                "     * @param 外键\n" +
                "     */");
        sb.append("\n");
        sb.append("    ").append("public void set").append(parentClassName).append(" (").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append(") {this.").append(StringUtil.firstToLowerCase(parentClassName)).append(" = ").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        sb.append("    ")
                .append(" /**\n" +
                        "     * @return 外键\n" +
                        "     */");
        sb.append("\n");
        sb.append("    ").append("public ").append(parentClassName).append(" get").append(parentClassName).append("(){ return this.").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        return sb.toString();
    }

    /**
     * 生成Mapper ColumnMap字段，单表
     */
    public static String generateMapperColumnMap(String tableName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("        ");
            }
            sb.append(tableName).append(".").append(infos.get(i).getColumnName()).append(" AS ").append("\"").append(infos.get(i).getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper ColumnMap字段，一对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(tableName).append(".").append(infos.get(i).getColumnName()).append(" AS ").append("\"").append(infos.get(i).getPropertyName()).append("\",\n");
            }
        }
        for (ColumnInfo info : parentInfos) {
            sb.append("        ").append(parentTableName).append(".").append(info.getColumnName()).append(" AS ").append("\"").append(parentEntityName).append(".").append(info.getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper ColumnMap字段，多对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName) {
        StringBuilder sb = new StringBuilder(generateMapperColumnMap(tableName, infos));
        sb.append(",\n");
        for (ColumnInfo info : parentInfos) {
            sb.append("        ").append(parentTableName).append(".").append(info.getColumnName()).append(" AS ").append("\"").append(parentEntityName).append("s.").append(info.getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }
    /**
     * 对应模板文件${ResultMap}字段 用于 single、one2many、many2many
     *
     * @param infos
     * @return
     */
    public static String generateMapperResultMap(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.isPrimaryKey()) {
                sb.append("<id column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("        ").append("<result column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }

    /**
     * 对应模板文件${ResultMap}字段 用于 single、one2many、many2many
     *
     * @param infos
     * @return
     */
    public static String generateMapperResultMap(List<ColumnInfo> infos,String parentTableName,String parentClassName,String foreignKey,Map<String, String> mapperData) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.isPrimaryKey()) {
                sb.append("\t\t<id name=\"id\" type=\"" + TypeUntilHibernateXml.parseTypeFormSqlType(info.getType()) + "\">\n");
                sb.append("\t\t\t<column name=\"" + generateHibernateName(info.getPropertyName()) + "\" precision=\"16\" scale=\"0\"/>\n");
                sb.append("\t\t\t<generator class=\"native\" />\n");
                sb.append("\t\t</id>\n\n");
                //sb.append("<id column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else if (info.getColumnName().equalsIgnoreCase(foreignKey)){
                sb.append("\t\t<many-to-one name=\""+StringUtil.firstToLowerCase(parentClassName)+"\"  class=\""+mapperData.get("BasePackageName")+mapperData.get("EntityPackageName")+"."+parentClassName+"\"  fetch=\"select\">\n");
                sb.append("\t\t\t<column name=\""+generateHibernateName(info.getPropertyName())+"\" precision=\"16\" scale=\"0\" />\n");
                sb.append("\t\t</many-to-one>\n");

            }else {
                sb.append("\t\t<property generated=\"never\" lazy=\"false\" name=\"" + info.getPropertyName() + "\" type=\"" + TypeUntilHibernateXml.parseTypeFormSqlType(info.getType()) + "\">\n");
                sb.append("\t\t\t<column length=\""+info.getPrecision()+"\" name=\"" + generateHibernateName(info.getPropertyName()) + "\" />\n");
                sb.append("\t\t</property>\n\n");
                //sb.append("        ").append("<result column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }

    /**
     * 对应模板文件${Association}字段
     * 用于 one2many
     *
     * @param parentInfos
     * @param parentClassName
     * @param parentClassPackage
     * @return
     */
    public static String generateMapperAssociation(List<ColumnInfo> parentInfos, String parentClassName, String parentClassPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<association property=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("\" javaType=\"").append(parentClassPackage).append(".").append(parentClassName).append("\">\n");
        for (ColumnInfo info : parentInfos) {
            if (info.isPrimaryKey()) {
                sb.append("            ").append("<id column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("            ").append("<result column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        sb.append("        ").append("</association>");
        return sb.toString();
    }


    /**
     * 对应模板文件${Collection}字段
     * 用于 many2many
     *
     * @param parentInfos
     * @param parentClassName
     * @param parentClassPackage
     * @return
     */
    public static String generateMapperCollection(List<ColumnInfo> parentInfos, String parentClassName, String parentClassPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<collection property=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s\" ofType=\"").append(parentClassPackage).append(".").append(parentClassName).append("\">\n");
        for (ColumnInfo info : parentInfos) {
            if (info.isPrimaryKey()) {
                sb.append("            ").append("<id column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s").append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("            ").append("<result column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s").append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        sb.append("        ").append("</collection>");
        return sb.toString();
    }


    /**
     * 生成Mapper Joins字段（一对多关系）
     *
     * @param tableName
     * @param parentTableName
     * @param foreignKey
     * @param parentPrimaryKey
     * @return
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String foreignKey, String parentPrimaryKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".").append(parentPrimaryKey).append(" = ").append(tableName).append(".").append(foreignKey);
        return sb.toString();
    }


    /**
     * 生成Mapper Joins字段（多对多关系）
     *
     * @param tableName
     * @param parentTableName
     * @param relationTableName
     * @param foreignKey
     * @param parentForeignKey
     * @param primaryKey
     * @param parentPrimaryKey
     * @return
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String relationTableName, String foreignKey, String parentForeignKey, String primaryKey, String parentPrimaryKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(relationTableName).append(" on ").append(relationTableName).append(".").append(foreignKey).append(" = ").append(tableName).append(".").append(primaryKey).append(" \n")
                .append("        ").append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".").append(parentPrimaryKey).append(" = ").append(relationTableName).append(".").append(parentForeignKey);
        return sb.toString();
    }


    /**
     * 生成Mapper 插入列名字段（所有关系皆用）
     *
     * @param infos
     * @return
     */
    public static String generateMapperInsertProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append(infos.get(i).getColumnName() + ",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 插入属性字段（单表，多对多）
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append("#{").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 批量插入属性字段（单表，多对多）
     */
    public static String generateMapperInsertBatchValues(List<ColumnInfo> infos, String entityName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append("#{").append(entityName).append(".").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 插入属性字段（一对多）
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(parentEntityName).append(".").append(primaryKey).append("},\n"); // 此处id需要修改为primarykey
            } else {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 批量插入属性字段（一对多）
     */
    public static String generateMapperInsertBatchValues(List<ColumnInfo> infos, String entityName, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(entityName).append(".").append(parentEntityName).append(".").append(primaryKey).append("},\n"); // 此处id需要修改为primarykey
            } else {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(entityName).append(".").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("        ");
            }
            sb.append(infos.get(i).getColumnName()).append(" = #{").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(infos.get(i).getColumnName()).append(" = #{").append(parentEntityName).append(".").append(primaryKey).append("},\n");
            } else {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(infos.get(i).getColumnName()).append(" = #{").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }
    /**
     * hibernate
     * 对应模板文件${ResultMap}字段 用于 single、one2many、many2many
     *
     * @param infos
     * @return
     */
    public static String generateHibernateResultMap(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.isPrimaryKey()) {
                sb.append("\t\t<id name=\"id\" type=\"" + TypeUntilHibernateXml.parseTypeFormSqlType(info.getType()) + "\">\n");
                sb.append("\t\t\t<column name=\"" + generateHibernateName(info.getPropertyName()) + "\" precision=\"16\" scale=\"0\"/>\n");
                sb.append("\t\t\t<generator class=\"native\" />\n");
                sb.append("\t\t</id>\n\n");
                //sb.append("<id column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("\t\t<property generated=\"never\" lazy=\"false\" name=\"" + info.getPropertyName() + "\" type=\"" + TypeUntilHibernateXml.parseTypeFormSqlType(info.getType()) + "\">\n");
                sb.append("\t\t\t<column length=\""+info.getPrecision()+"\" name=\"" + generateHibernateName(info.getPropertyName()) + "\" />\n");
                sb.append("\t\t</property>\n\n");
                //sb.append("        ").append("<result column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }


    /**
     * 以驼峰命名法生成类名，用于未指定类名时自动生成类名，如sys_user自动生成类名SysUser
     * @author sxx
     * @param tableName
     * @return
     */
    public static String generateClassName(String tableName) {
        String[] nameStrs = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String string : nameStrs) {
            sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        }
        return sb.toString();
    }
    /**
     * 以驼峰命名法生成类名，用于未指定类名时自动生成hibernate列表名，如sysUser自动生成类名SYS_USER
     * @author sxx
     * @param name
     * @return
     */
    public static String generateHibernateName(String name) {
        StringBuilder sb=new StringBuilder(name);
        int temp=0;//定位
        for(int i=0;i<name.length();i++){
            if(Character.isUpperCase(name.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toUpperCase();
    }
    /**
     * index页面
     * @param entityData
     * @param entityData
     * @return
     */
    public static String createBaseData(Map<String, String> entityData) {
        StringBuilder sb = new StringBuilder();
        String time = createTime();
        String s = time.split("-")[0];
        sb.append("<#--\n" +
                "/****************************************************\n" +
                " * Description: 列表页面\n" +
                " * Copyright:   Copyright (c) "+s+"\n" +
                " * Company:     beiwaionline\n" +
                " * @author      bfsu\n" +
                " * @version     1.0\n" +
                " * @see\n" +
                "\tHISTORY\n" +
                "    *  "+time+" bfsu Create File\n" +
                "**************************************************/" +
                "-->\n");
        sb.append("<#include \"/templates/ace/ace-inc.ftl\">\n\n").append("<@html>\n\n").append("<@head>\n\n")
                .append("</@head>\n\n").append("<@body>\n\n").append("<@nav '"+entityData.get("Title")+"'/>\n\n")
                .append("<@content url=\"${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/list\" >\n\n")
                .append("\t\t<@query queryUrl=\"${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/list\">\n\n")
                .append("\t\t\t<@querygroup  title='"+entityData.get("Title")+"名称'><input type='text' name='query.title!lk@s'  class=\"form-control\" placeholder=\"请输入"+entityData.get("Title")+"名称\"></@querygroup>\n\n")
                .append("\t\t\t<@querygroup  title='状态'><@select list=BfsuolConstants.GLOBAL_YESNO name=\"query.isEnroll!eq@i\" listKey='' listValue='' id=\"statusSelect\" multi=false search=false/></@querygroup>\n\n")
                .append("\t\t</@query>\n")
                .append("\t\t<@sec pcode=\""+entityData.get("ParentPath")+"."+entityData.get("EntityName")+"\" fcode=\"create\">\n")
                .append("\t\t\t<@button icon=\"pencil\" type=\"primary\" size=\"sm\" onclick=\"bfsu.add('${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/input','添加"+entityData.get("Title")+"');\">添加"+entityData.get("Title")+"</@button>\n")
                .append("\t\t</@sec>\n")
                .append("</@content>\n\n").append("</@body>\n\n").append("</@html>");

        return sb.toString();
    }
    /**
     * input页面
     * @param entityData
     * @param entityData
     * @return
     */
    public static String createBaseDataInput(Map<String,String> entityData) {
        StringBuilder sb = new StringBuilder();
        String time = createTime();
        String s = time.split("-")[0];
        sb.append("<#--\n" +
                "/****************************************************\n" +
                " * Description: 输入页面，包括添加和修改\n" +
                " * Copyright:   Copyright (c) "+s+"\n" +
                " * Company:     beiwaionline\n" +
                " * @author      bfsu\n" +
                " * @version     1.0\n" +
                " * @see\n" +
                "\tHISTORY\n" +
                "    *  "+time+" bfsu Create File\n" +
                "**************************************************/" +
                "-->\n");
        sb.append("<#include \"/templates/ace/ace-inc.ftl\">\n\n")
                .append("<@input url=\"${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/save\">\n")
                .append("\t\t<input type=\"hidden\" name=\"id\" value=\"${"+entityData.get("EntityName")+".id}\"/>\n")
                .append("\t\t<@formgroup title='"+entityData.get("Title")+"名称'>\n")
                .append("\t\t\t<input type=\"text\" name=\"title\" value=\"${"+entityData.get("EntityName")+".title}\" placeholder=\"例：2018\"  check-type=\"required\" maxlength=\"100\">\n")
                .append("\t\t</@formgroup>\n\n").append("\t\t<@formgroup title='是否有效'>\n")
                .append("\t\t\t<@swichButton name='isEnroll' title='是否' val=\"${"+entityData.get("EntityName")+".status}\" onVal=BfsuolConstants.GLOBAL_YESNO_YES offVal=BfsuolConstants.GLOBAL_YESNO_NO></@swichButton>\n")
                .append("\t\t\t<b><font style=\"color:red;\">说明："+entityData.get("Title")+"只能有一个为 “是”</font></b>\n")
                .append("\t\t</@formgroup>\n\n")
                .append("\t\t<@formgroup title=\"层次\">\n" +
                        "\t\t\t<@select name=\"levelIds\" list=levelList value=\"${"+entityData.get("EntityName")+"?if_exists.levelIds}\" listKey=\"title\" listValue=\"id\" id=\"levelIdInput\" headerKey='' headerValue='' emptyOption=false multi=true />\n" +
                        "\t\t</@formgroup>\n\n")
                .append("\t\t<@formgroup title=\"专业\">\n" +
                        "\t\t\t<@ajaxselect id=\"specialtyIds\" name=\"specialtyIds\" value=\"${"+entityData.get("EntityName")+"?if_exists.specialtyIds}\" pid=\"levelIdInput\"  url=\"${base}/base/baseObjectJson/getInputSpecialtyJSON/[levelIdInput]\" multi=true />\n" +
                        "\t\t</@formgroup>\n")
                .append("</@input>");
        return sb.toString();
    }
    /**
     * list页面
     * @param entityData
     * @param tableInfos
     * @return
     */
    public static String createBaseDataList(Map<String, String> entityData, List<ColumnInfo> tableInfos) {
        StringBuilder sb = new StringBuilder();
        String time = createTime();
        String s = time.split("-")[0];
        sb.append("<#--\n" +
                "/****************************************************\n" +
                " * Description: 简单列表页面，没有编辑功能\n" +
                " * Copyright:   Copyright (c) "+s+"\n" +
                " * Company:     beiwaionline\n" +
                " * @author      bfsu\n" +
                " * @version     1.0\n" +
                " * @see\n" +
                "\tHISTORY\n" +
                "    *  "+time+" bfsu Create File\n" +
                "**************************************************/" +
                "-->\n");
        sb.append("<#include \"/templates/ace/ace-inc.ftl\">\n\n")
                .append("<@list>\n")
                .append("\t<thead>\n")
                .append("\t\t<tr>\n")
                .append("\t\t\t<th><input type=\"checkbox\" class=\"bscheckall\"></th>\n");
                for(int i=0;i<tableInfos.size();i++){
                    if(tableInfos.get(i).getPropertyName().equalsIgnoreCase("createUserId")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("createUserName")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("createTime")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("updateUserId")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("updateUserName")){
                        continue;
                    }
                    sb.append("\t\t\t<th>"+tableInfos.get(i).getComment()+"</th>\n");
                }
                sb.append("\t\t</tr>\n").append("\t</thead>\n")
                .append("\t<tbody>\n")
                .append("\t\t<#list page.items?if_exists as item>\n")
                .append("\t\t\t<tr>\n")
                .append("\t\t\t\t<td><input type=\"checkbox\" class=\"bscheck\" data=\"id:${item.id}\"></td>\n");
                for(int i=0;i<tableInfos.size();i++){
                    if(tableInfos.get(i).getPropertyName().equalsIgnoreCase("createUserId")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("createUserName")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("createTime")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("updateUserId")
                            || tableInfos.get(i).getPropertyName().equalsIgnoreCase("updateUserName")){
                        continue;
                    }
                  sb .append("\t\t\t\t<td>\n").append("\t\t\t\t\t${item."+tableInfos.get(i).getPropertyName()+"}\n").append("\t\t\t\t</td>\n");
                }
                 sb.append("\t\t\t\t\t<td>\n")
                .append("\t\t\t\t\t<@sec pcode=\""+entityData.get("ParentPath")+"."+entityData.get("EntityName")+"\" fcode=\"edit\">\n")
                .append("\t\t\t\t\t\t<@button icon=\"pencil\" type=\"primary\" size=\"sm\" onclick=\"bfsu.add('${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/input/${item.id}','修改"+entityData.get("Title")+"');\">修改"+entityData.get("Title")+"</@button>\n")
                .append("\t\t\t\t\t</@sec>\n")
                .append("\t\t\t\t\t<@sec pcode=\""+entityData.get("ParentPath")+"."+entityData.get("EntityName")+"\" fcode=\"delete\">\n")
                .append("\t\t\t\t\t\t<@button icon=\"remove\" \t type=\"primary\" onclick=\"bfsu.del('${base}/"+entityData.get("ParentPath")+"/"+entityData.get("EntityName")+"/delete/${item.id}','从列表中删除？')\">删除</@button>\n")
                .append("\t\t\t\t\t</@sec>\n")
                .append("\t\t\t\t</td>\n")
                .append("\t\t\t</tr>\n").append("\t\t</#list>\n").append("\t</tbody>\n").append("</@list>\n");

        return sb.toString();
    }
}
