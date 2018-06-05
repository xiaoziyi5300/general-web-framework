package com.cn.freeMarker;

import com.cn.liu.util.StrUtil;

import javax.management.Attribute;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzf
 * @date 2018-06-05
 * @desc 模板工具类
 */
public class FreeMarkerUtil {

    /***
     * 格式化 mysql 字段类型到java字段类型
     * @param str
     * @return
     */
    public static String formatSqlColumType(String str) {
        String ms = "";
        switch (str) {
            case "INT":
                ms = "Integer";
                break;
            case "VARCHAR":
                ms = "String";
                break;
            case "DATETIME":
                ms = "Date";
                break;
            case "BLOB":
                ms = "byte[]";
                break;
            case "BIT":
                ms = "boolean";
                break;
            case "BIGINT":
                ms = "long";
                break;
            default:
                ms = "";
                break;
        }
        return ms;
    }

    /***
     * 把sql字段格式化java驼峰命名方式
     * @param cloumName
     * @return
     */
    public static String formatSqlColumName(String cloumName) {
        StringBuilder sb = new StringBuilder();
        if (cloumName.indexOf("_") > -1) {
            sb.append(StrUtil.columnToProperty(cloumName));
        } else {
            sb.append(cloumName);
        }
        return sb.toString();
    }

    //从数据库获取表字段
    public static ResultBean getResultBean(String driverName, String jdbcUrl, String name, String passWord, String table) throws Exception {
        ResultBean resultBean = new ResultBean();
        List<Attribute> attrList = new ArrayList<Attribute>();
        List<XmlAssistBean> xmlAssistBeanList = new ArrayList<>();
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(jdbcUrl, name, passWord);
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, "%", table, new String[]{"TABLE"});
            XmlAssistBean xmlAssistBean;
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (tableName.equals(table)) {
                    ResultSet rs = connection.getMetaData().getColumns(null, "", tableName.toUpperCase(), "%");
                    while (rs.next()) {
                        xmlAssistBean = new XmlAssistBean();
                        attrList.add(new javax.management.Attribute(formatSqlColumName(rs.getString("COLUMN_NAME")), formatSqlColumType(rs.getString("TYPE_NAME"))));
                        xmlAssistBean.setCloumName(rs.getString("COLUMN_NAME"));
                        xmlAssistBean.setJavaTypeName(FreeMarkerUtil.formatSqlColumName(rs.getString("COLUMN_NAME")));
                        xmlAssistBean.setSqlCloumType(rs.getString("TYPE_NAME"));
                        xmlAssistBeanList.add(xmlAssistBean);
                    }
                }
            }
            resultBean.setAttributeList(attrList);
            resultBean.setXmlAssistBeanList(xmlAssistBeanList);
            return resultBean;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
