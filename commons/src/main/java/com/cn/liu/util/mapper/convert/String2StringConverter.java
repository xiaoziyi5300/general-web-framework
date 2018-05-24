package com.cn.liu.util.mapper.convert;

import org.modelmapper.AbstractConverter;

/**
 * Created by weijun.hu on 2016/4/1.
 */
public class String2StringConverter extends AbstractConverter<String, String> {
    @Override
    protected String convert(String source) {
        return source == null ? "" : source;
    }
}
