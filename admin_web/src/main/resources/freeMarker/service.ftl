package ${packageName};

import com.cn.model.${beanName};
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
/**
* @author ${author}
* @desc
* @date ${date}
*/
public interface ${upBeanName}Service {
    PageBean<${upBeanName}> queryListByPage(PageRequstParams pageRequstParams);

    void save(${upBeanName} ${beanName});

    void update(${upBeanName} ${beanName});

${upBeanName} queryById(String rId);

    void delete(String rId);
}
