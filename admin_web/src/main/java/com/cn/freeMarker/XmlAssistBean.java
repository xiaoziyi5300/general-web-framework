package com.cn.freeMarker;

/**
 * @author lzf
 * @date 2018-06-05
 * @desc mapper.xml生成辅助bean
 */
public class XmlAssistBean {

    //数据库字段
    private String cloumName;
    //java字段名称
    private String javaTypeName;
    //数据库jdbcType
    private String sqlCloumType;

    public String getCloumName() {
        return cloumName;
    }

    public void setCloumName(String cloumName) {
        this.cloumName = cloumName;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public void setJavaTypeName(String javaTypeName) {
        this.javaTypeName = javaTypeName;
    }

    public String getSqlCloumType() {
        return sqlCloumType;
    }

    public void setSqlCloumType(String sqlCloumType) {
        this.sqlCloumType = sqlCloumType;
    }
}
