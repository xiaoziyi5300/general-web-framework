package com.cn.liu.util.mapper.convert;

import org.modelmapper.AbstractConverter;

/**
 * Created by weijun.hu on 2016/4/1.
 */
public class Long2StringConverter extends AbstractConverter<Long, String> {
    @Override
    protected String convert(Long source) {
        return source == null ? "" : source.toString();
    }
}
