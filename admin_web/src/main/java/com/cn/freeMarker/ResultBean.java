package com.cn.freeMarker;

import javax.management.Attribute;
import java.util.List;

/**
 * @author lzf
 * @date 2018-06-05
 * @desc
 */
public class ResultBean {

    List<Attribute> attributeList;
    List<XmlAssistBean> xmlAssistBeanList;

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<XmlAssistBean> getXmlAssistBeanList() {
        return xmlAssistBeanList;
    }

    public void setXmlAssistBeanList(List<XmlAssistBean> xmlAssistBeanList) {
        this.xmlAssistBeanList = xmlAssistBeanList;
    }
}
