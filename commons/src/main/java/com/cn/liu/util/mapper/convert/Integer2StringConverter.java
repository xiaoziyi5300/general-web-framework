package com.cn.liu.util.mapper.convert;

import org.modelmapper.AbstractConverter;

/**
 * Created by weijun.hu on 2016/4/1.
 */
public class Integer2StringConverter extends AbstractConverter<Integer, String> {
    @Override
    protected String convert(Integer source) {
        return source == null ? "" : source.toString();
    }
}
