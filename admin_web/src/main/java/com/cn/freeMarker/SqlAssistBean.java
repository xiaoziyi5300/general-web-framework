package com.cn.freeMarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 * @date 2018-06-05
 * @desc mybatis sql查询语句bean
 */
public class SqlAssistBean {

    public static Map<String, String> getSqlMap(List<XmlAssistBean> list, String table) {
        Map<String, String> sqlMap = new HashMap<>();
        StringBuilder baseColumSb = new StringBuilder();
        StringBuilder javaColumSb = new StringBuilder();
        StringBuilder selectByPrimaryKeySb = new StringBuilder().append("select").append(" <include refid=\"Base_Column_List\"/>").append(" from ").append(table).append(" where");
        StringBuilder deleteByPrimaryKeySb = new StringBuilder().append("delete").append(" from ").append(table).append(" where");
        StringBuilder insert = new StringBuilder().append("insert into " + table + " (");
        StringBuilder insertSelective_1 = new StringBuilder().append("insert into ").append(table).append(" <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        StringBuilder insertSelective_2 = new StringBuilder().append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        StringBuilder updateSelective = new StringBuilder().append("update ").append(table).append(" <set>");
        for (int i = 0; i < list.size(); i++) {
            baseColumSb.append(list.get(i).getCloumName());
            javaColumSb.append("#{").append(list.get(i).getJavaTypeName() + ",jdbcType=" + list.get(i).getSqlCloumType() + "}");
            insertSelective_1.append("<if test=\"").append(list.get(i).getJavaTypeName() + " != null \" >").append(list.get(i).getCloumName());
            insertSelective_2.append("<if test=\"").append(list.get(i).getJavaTypeName() + " != null \" >").append("#{").append(list.get(i).getJavaTypeName() + ",jdbcType=" + list.get(i).getSqlCloumType()).append("}");
            updateSelective.append("<if test=\" ").append(list.get(i).getJavaTypeName() + " != null \" >").append(list.get(i).getCloumName()).append(" = #{").append(list.get(i).getJavaTypeName() + ",jdbcType=" + list.get(i).getSqlCloumType()).append("}").append(",");
            if (i == 0) {
                selectByPrimaryKeySb.append(" " + list.get(0).getCloumName())
                        .append(" =#{").append(" " + list.get(0).getJavaTypeName()).append(",jdbcType=").append(list.get(0).getSqlCloumType()).append("}");
                deleteByPrimaryKeySb.append(" " + list.get(0).getCloumName())
                        .append(" =#{").append(" " + list.get(0).getJavaTypeName()).append(",jdbcType=").append(list.get(0).getSqlCloumType()).append("}");
            }
            if (i < list.size() - 1) {
                baseColumSb.append(",");
                javaColumSb.append(",");
                insertSelective_1.append(",");
                insertSelective_2.append(",");
            }
            insertSelective_1.append(" </if>");
            insertSelective_2.append(" </if>");
            updateSelective.append(" </if>");
        }
        insertSelective_1.append("</trim>");
        insertSelective_2.append("</trim>");
        insert.append(baseColumSb.toString()).append(" ) values ( ").append(javaColumSb.toString() + ")");
        updateSelective.append("</set>  where ").append(list.get(0).getCloumName()).append(" = #{").append(list.get(0).getJavaTypeName() + ",jdbcType=" + list.get(0).getSqlCloumType()).append("}");
        sqlMap.put("baseColumList", baseColumSb.toString());
        sqlMap.put("selectByPrimaryKey", selectByPrimaryKeySb.toString());
        sqlMap.put("deleteByPrimaryKey", deleteByPrimaryKeySb.toString());
        sqlMap.put("insert", insert.toString());
        sqlMap.put("insertSelective", insertSelective_1.toString() + insertSelective_2.toString());
        sqlMap.put("updateByPrimaryKeySelective", updateSelective.toString());
        return sqlMap;
    }
}
