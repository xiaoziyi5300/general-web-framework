package com.cn.liu.util.mapper.convert;

import com.cn.liu.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.AbstractConverter;

import java.util.Date;


public class String2DateConverter extends AbstractConverter<String, Date> {
    @Override
    protected Date convert(String source) {
        return StringUtils.isBlank(source) ? null : DateUtils.stringToDate(source, DateUtils.FORMAT);
    }
}
