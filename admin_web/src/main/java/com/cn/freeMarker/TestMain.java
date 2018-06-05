package com.cn.freeMarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @author lzf
 * @date 2018-06-04
 * @desc
 */
public class TestMain {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/my_shop?autoReconnect=true&useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String NAME = "root";
    public static final String PASSWORD = "111111";

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

        ResultBean resultBean = FreeMarkerUtil.getResultBean(JDBC_DRIVER, JDBC_URL, NAME, PASSWORD, "t_role");

        testMain.generateController(root, cfg, "role", filePath);
        testMain.generateApiController(root, cfg, "role", filePath + "api");
        testMain.generateService(root, cfg, "role", filePath + "service");
        testMain.generateServiceImpl(root, cfg, "role", filePath + "service/impl", "用户已经被使用了");
        testMain.generateModel(root, cfg, "role", filePath + "model", resultBean);
        testMain.generateMapper(root, cfg, "role", filePath + "mapper");
        testMain.generateMapperXml(root, cfg, "role", filePath + "mapper/xml", resultBean, "t_role");
        testMain.generateJs(root, cfg, "role", filePath + "jsp/js");
        testMain.generateJsp(root, cfg, "role", filePath + "jsp");
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
    public void generateModel(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath, ResultBean resultBean) throws Exception {
        Template temp = configuration.getTemplate("/model.ftl");
        root.put("packageName", "com.cn.model");
        root.put("className", returnUppercase(tagertStr));
        root.put("attrs", resultBean.getAttributeList());
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

    //生成mapper 模板
    public void generateMapper(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/mapper.ftl");
        root.put("upBeanName", returnUppercase(tagertStr));
        root.put("packageName", "com.cn.mapper");
        root.put("beanName", tagertStr);
        root.put("model_packageName", "com.cn.model");
        printResources(root, temp, tagertStr, filePath);
    }

    //生成mapper.xml模板
    public void generateMapperXml(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath, ResultBean resultBean, String tableName) throws Exception {
        Template temp = configuration.getTemplate("/mapperXml.ftl");
        root.put("mapper_packageName", "com.cn.mapper");
        root.put("upBeanName", returnUppercase(tagertStr));
        root.put("model_packageName", "com.cn.model");
        root.put("attrs", resultBean.getXmlAssistBeanList());
        root.put("baseColumList", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("baseColumList").toString());
        root.put("selectByPrimaryKey", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("selectByPrimaryKey").toString());
        root.put("deleteByPrimaryKey", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("deleteByPrimaryKey").toString());
        root.put("insert", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("insert").toString());
        root.put("insertSelective", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("insertSelective").toString());
        root.put("updateByPrimaryKeySelective", SqlAssistBean.getSqlMap(resultBean.getXmlAssistBeanList(), tableName).get("updateByPrimaryKeySelective").toString());
        root.put("table_name", tableName);
        root.put("xmlAssistBean", resultBean.getXmlAssistBeanList().get(0));
        printResources(root, temp, tagertStr, filePath);
    }

    //生成jsp模板
    public void generateJsp(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/jsp.ftl");
        root.put("beanName", tagertStr);
        printResources(root, temp, tagertStr, filePath);
    }

    //生成js模板
    public void generateJs(Map<String, Object> root, Configuration configuration, String tagertStr, String filePath) throws Exception {
        Template temp = configuration.getTemplate("/js.ftl");
        root.put("beanName", tagertStr);
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
            if (filePath.endsWith("api")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "ApiController.java"));
            } else if (filePath.endsWith("impl")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "ServiceImpl.java"));
            } else if (filePath.endsWith("service")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "Service.java"));
            } else if (filePath.endsWith("model")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + ".java"));
            } else if (filePath.endsWith("xml")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "Mapper.xml"));
            } else if (filePath.endsWith("mapper")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + "Mapper.java"));
            } else if (filePath.endsWith("js")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + ".js"));
            } else if (filePath.endsWith("jsp")) {
                fos = new FileOutputStream(new File(dir, returnUppercase(tagertStr) + ".jsp"));
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
}
