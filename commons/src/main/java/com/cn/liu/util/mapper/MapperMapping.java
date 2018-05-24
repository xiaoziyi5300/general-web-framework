package com.cn.liu.util.mapper;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public abstract class MapperMapping<S, D> {
    private S source;
    private D destination;

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public D getDestination() {
        return destination;
    }

    public void setDestination(D destination) {
        this.destination = destination;
    }

    //适配方法
    public abstract void configure();
}
