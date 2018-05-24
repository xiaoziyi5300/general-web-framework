package com.cn.liu.util;

import com.cn.liu.util.mapper.MapperMapping;
import com.cn.liu.util.mapper.convert.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;

import java.util.*;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public class BeanUtil {

    private static ModelMapper modelMapper;
    private static ModelMapper modelMapper2;

    static {
        //带转换空值
        modelMapper = new ModelMapper();
        setDefaultConverter(modelMapper);
        //不带转换空值
        modelMapper2 = new ModelMapper();
        setNoDefaultConverter(modelMapper2);
    }

    private BeanUtil() {

    }

    private static void setDefaultConverter(ModelMapper modelMapper) {
        modelMapper.addConverter(new BigDecimal2StringConverter());
        modelMapper.addConverter(new Boolean2StringConverter());
        modelMapper.addConverter(new Date2StringConverter());
        modelMapper.addConverter(new Integer2StringConverter());
        modelMapper.addConverter(new Long2StringConverter());
        modelMapper.addConverter(new String2StringConverter());
        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
    }

    private static void setNoDefaultConverter(ModelMapper modelMapper) {
        modelMapper.addConverter(new String2DateConverter());
        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
    }

    public static <D> D mapper(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    public static void mapper(Object source, Object destination, MapperMapping propertyMap) {
        if (source == null || destination == null) {
            return;
        }

        ModelMapper modelMapper = new ModelMapper();
        setDefaultConverter(modelMapper);
        modelMapper.map(source, destination);
        if (propertyMap != null) {
            propertyMap.setSource(source);
            propertyMap.setDestination(destination);
            propertyMap.configure();
        }
    }

    public static <D> D mapper(Object source, Class<D> destinationType, MapperMapping propertyMap) {
        if (source == null || destinationType == null) {
            return null;
        }

        D newInstance = null;
        try {
            newInstance = destinationType.newInstance();
        } catch (InstantiationException e) {
            //logger.error("destinationType.newInstance InstantiationException " + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            // logger.error("destinationType.newInstance IllegalAccessException " + e.getMessage(), e);
        }
        mapper(source, newInstance, propertyMap);
        return newInstance;
    }

    public static <D> List<D> mapper(List sources, Class<D> destinationType, MapperMapping propertyMap) {
        List<D> list = new ArrayList<D>();
        if (ListUtils.isNotEmpty(sources)) {
            for (Object obj : sources) {
                list.add(mapper(obj, destinationType, propertyMap));
            }
        }
        return list;
    }


    public static void mapper(Object source, Object destination) {
        if (source == null || destination == null) {
            return;
        }
        modelMapper.map(source, destination);
    }

    public static <D> List<D> mapper(List sources, Class<D> destinationType) {
        List<D> list = new ArrayList<D>();
        if (ListUtils.isNotEmpty(sources)) {
            for (Object obj : sources) {
                list.add(modelMapper.map(obj, destinationType));
            }
        }
        return list;
    }

    public static <D> D mapperNoDefault(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper2.map(source, destinationType);
    }

    public static void mapperNoDefault(Object source, Object destination) {
        if (source == null || destination == null) {
            return;
        }
        modelMapper2.map(source, destination);
    }

    public static <D> List<D> mapperNoDefault(List sources, Class<D> destinationType) {
        List<D> list = new ArrayList<D>();
        if (ListUtils.isNotEmpty(sources)) {
            for (Object obj : sources) {
                list.add(modelMapper2.map(obj, destinationType));
            }
        }
        return list;
    }

}
