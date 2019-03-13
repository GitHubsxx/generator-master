package com.sxx.generator.db;


import com.sxx.generator.entity.ColumnInfo;
import com.sxx.generator.utils.ConfigUtil;
import org.apache.commons.collections.CollectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author sxx
 * Date   2018/4/19
 */
public class ConnectionUtil {
    private Connection connection;
    private Statement statement;
    private Statement statement_2;
    private ResultSet resultSet;
    private ResultSet resultSet_2;

    /**
     * 初始化数据库连接
     *
     * @return
     */
    public boolean initConnection() {
        try {
            Class.forName(DriverFactory.getDriver(ConfigUtil.getConfiguration().getDb().getUrl()));
            String url = ConfigUtil.getConfiguration().getDb().getUrl();
            String username = ConfigUtil.getConfiguration().getDb().getUsername();
            String password = ConfigUtil.getConfiguration().getDb().getPassword();
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取表结构数据
     *
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) throws SQLException {
        ResultSet tempResultSet = connection.getMetaData().getPrimaryKeys(null, null, tableName);
        String primaryKey = null;
        if (tempResultSet.next()) {
            primaryKey = tempResultSet.getObject(4).toString();
        }
        List<ColumnInfo> columnInfos = new ArrayList<>();
        statement = connection.createStatement();
        statement_2 = connection.createStatement();
        String sql = "SELECT * FROM " + tableName + " WHERE 1 != 1";
        String sql_2 = "show full fields from "+tableName;
        resultSet = statement.executeQuery(sql);
        resultSet_2 = statement_2.executeQuery(sql_2);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        while (resultSet_2.next()){
            String columnName = resultSet_2.getString(1);
            String comment = resultSet_2.getString("comment");
            map.put(columnName,comment);
        }
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            ColumnInfo info;
            if (metaData.getColumnName(i).equals(primaryKey)) {
                info = new ColumnInfo(metaData.getColumnName(i), metaData.getColumnType(i), true, metaData.getPrecision(i));
            } else {
                info = new ColumnInfo(metaData.getColumnName(i), metaData.getColumnType(i), false, metaData.getPrecision(i));
            }
            columnInfos.add(info);
        }
        statement.close();
        resultSet.close();
        if(map !=null && map.size()>0 && CollectionUtils.isNotEmpty(columnInfos)){

            Iterator<ColumnInfo> var = columnInfos.iterator();
            while (var.hasNext()){
                ColumnInfo info = var.next();
                Iterator<Map.Entry<String, String>> var2 = map.entrySet().iterator();
                while (var2.hasNext()){
                    Map.Entry<String, String> var3 = var2.next();
                    if(var3.getKey() !=null && var3.getKey().equals(info.getColumnName())){
                        info.setComment(var3.getValue());
                        break;
                    }
                }
            }
        }
        return columnInfos;
    }

    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
