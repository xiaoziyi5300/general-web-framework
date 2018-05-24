package com.cn.liu.util.mapper.convert;

import org.modelmapper.AbstractConverter;

import java.math.BigDecimal;

/**
 * Created by weijun.hu on 2016/4/1.
 */
public class BigDecimal2StringConverter extends AbstractConverter<BigDecimal, String> {
    @Override
    protected String convert(BigDecimal source) {
        return source == null ? "" : source.toString();
    }
}
