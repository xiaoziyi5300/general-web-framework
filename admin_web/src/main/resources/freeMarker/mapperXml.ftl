<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapper_packageName}.${upBeanName}Mapper">
    <resultMap id="BaseResultMap" type="${model_packageName}.${upBeanName}">
        <#list attrs as attr>
            <id column="${attr.cloumName}" property="${attr.javaTypeName}" jdbcType="${attr.sqlCloumType}"/>
        </#list>
    </resultMap>
    <sql id="Base_Column_List">
    ${baseColumList}
    </sql>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Integer">
    ${selectByPrimaryKey}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
    ${deleteByPrimaryKey}
    </delete>
    <insert id="insert" parameterType="${model_packageName}.${upBeanName}">
    ${insert}
    </insert>
    <insert id="insertSelective" parameterType="${model_packageName}.${upBeanName}">
    ${insertSelective}
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="${model_packageName}.${upBeanName}">
    ${updateByPrimaryKeySelective}
    </update>
    <select id="totalCount" resultType="java.lang.Integer">
        select count(1) from ${table_name} where delete_mark = 1
    </select>
    <select id="selectByList" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${table_name}
        where delete_mark =1 order by ${xmlAssistBean.cloumName} desc
    </select>
</mapper>