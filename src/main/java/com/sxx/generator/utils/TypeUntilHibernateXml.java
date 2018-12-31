package com.sxx.generator.utils;

import java.sql.Types;

public class TypeUntilHibernateXml {

    /**
     * 将数据库数据类型转换为Java数据类型(实体类)
     *
     * @param sqlType
     * @return
     */
    public static String parseTypeFormSqlType(int sqlType) {
        StringBuilder sb = new StringBuilder();
        switch (sqlType) {
            case Types.BIT:
            case Types.BOOLEAN:
                sb.append("boolean");
                break;
            case Types.TINYINT:
                sb.append("byte");
                break;
            case Types.SMALLINT:
                sb.append("short");
                break;
            case Types.INTEGER:
                sb.append("java.lang.Integer");
                break;
            case Types.BIGINT:
                sb.append("java.lang.Long");
                break;
            case Types.REAL:
                sb.append("float");
                break;
            case Types.FLOAT:
            case Types.DOUBLE:
                sb.append("java.lang.Double");
                break;
            case Types.DECIMAL:
            case Types.NUMERIC:
                sb.append("BigDecimal");
                break;
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.NCHAR:
            case Types.NVARCHAR:
            case Types.LONGVARCHAR:
            case Types.LONGNVARCHAR:
                sb.append("java.lang.String");
                break;
            case Types.DATE:
                sb.append("java.util.Date");
                break;
            case Types.TIME:
                sb.append("Time");
                break;
            case Types.TIMESTAMP:
                sb.append("java.util.Date");
                break;
            case Types.NCLOB:
            case Types.CLOB:
            case Types.BLOB:
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                sb.append("byte[]");
                break;
            case Types.NULL:
            case Types.OTHER:
            case Types.JAVA_OBJECT:
                sb.append("object");
                break;
            default:
                sb.append("object");

        }
        return sb.toString();
    }

}
