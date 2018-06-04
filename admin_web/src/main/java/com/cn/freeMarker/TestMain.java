package com.cn.freeMarker;

import com.cn.liu.util.StrUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import javax.management.Attribute;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @author lzf
 * @date 2018-06-04
 * @desc
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setTemplateLoader(new ClassTemplateLoader(TestMain.class, "/freeMarker"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("author", "lzf");
        root.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        TestMain testMain = new TestMain();
        String filePath = "F:/Freemarker/src/";
        testMain.generateController(root, cfg, "order", filePath);
        testMain.generateApiController(root, cfg, "order", filePath + "api");
        testMain.generateService(root, cfg, "order", filePath + "service");
        testMain.generateServiceImpl(root, cfg, "order", filePath + "service/impl", "用户已经被使用了");
        testMain.generateModel(root, cfg, "order", filePath + "model");
    }

    //生成controller模板
    public void generateController(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/controller.ftl");
        root.put("packageName", "com.cn.controller");
        root.put("controller_name", tagertStr);
        root.put("controller_view", "index");
        root.put("upController_name", returnUppercase(tagertStr));
        printResources(root, temp, tagertStr, filePath);
    }

    //生成bean模板
    public void generateModel(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/my_shop?autoReconnect=true&useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true";
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String NAME = "root";
        final String PASSWORD = "111111";
        List<Attribute> attrList = getAttributeList(JDBC_DRIVER, JDBC_URL, NAME, PASSWORD, "t_user");
        Template temp = configuration.getTemplate("/model.ftl");
        root.put("packageName", "com.cn.model");
        root.put("className", returnUppercase(tagertStr));
        root.put("attrs", attrList);
        printResources(root, temp, tagertStr, filePath);
    }

    //生成controller api模板
    public void generateApiController(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/controllerApi.ftl");
        root.put("packageName", "com.cn.controller.api");
        root.put("controller_name", tagertStr);
        root.put("upController_name", returnUppercase(tagertStr));
        printResources(root, temp, tagertStr, filePath);
    }

    //生成service模板
    public void generateService(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/service.ftl");
        root.put("packageName", "com.cn.service");
        root.put("upBeanName", returnUppercase(tagertStr));
        root.put("beanName", tagertStr);
        printResources(root, temp, tagertStr, filePath);
    }

    //生成serviceImpl模板
    public void generateServiceImpl(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath, String exceptionMsg) throws Exception {
        Template temp = configuration.getTemplate("/serviceImpl.ftl");
        root.put("packageName", "com.cn.service.impl");
        root.put("upBeanName", returnUppercase(tagertStr));
        root.put("beanName", tagertStr);
        root.put("exceptionMsg", exceptionMsg);
        printResources(root, temp, tagertStr, filePath);
    }

    //替换第一个字母为大写
    private String returnUppercase(String str) {
        char c = str.charAt(0);
        if (!Character.isUpperCase(c)) {
            return str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

    //输出到目标文件夹
    public void printResources(Map<String, Object> map, Template template, String tagertStr, String filePath) {
        OutputStream fos = null;
        File dir = new File(filePath);//F:/Freemarker/src/
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            if (filePath.lastIndexOf("api") > -1) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "ApiController.java"));
            } else if (filePath.lastIndexOf("impl") > -1) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "ServiceImpl.java"));
            } else if (filePath.lastIndexOf("service") > -1) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "Service.java"));
            } else if (filePath.lastIndexOf("model") > -1) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + ".java"));
            } else {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "Controller.java"));
            }
            Writer out = new OutputStreamWriter(fos);
            template.process(map, out);
            fos.flush();
            fos.close();
            System.out.println("gen code success!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //从数据库获取表字段
    public List<javax.management.Attribute> getAttributeList(String driverName, String jdbcUrl, String name, String passWord, String tableName) throws Exception {
        List<Attribute> attrList = new ArrayList<Attribute>();
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(jdbcUrl, name, passWord);
        String sql = "select * from " + tableName;
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                if (attrList.size() != data.getColumnCount()) {
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        attrList.add(new javax.management.Attribute(formatSqlColumName(data.getColumnName(i)), formatSqlColumType(data.getColumnTypeName(i))));
                    }
                }
            }
            return attrList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String formatSqlColumName(String str) {
        StringBuilder sb = new StringBuilder();
        if (str.indexOf("_") > -1) {
            sb.append(StrUtil.columnToProperty(str));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    private String formatSqlColumType(String str) {
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
            default:
                ms = "";
                break;
        }
        return ms;
    }
}
