package ${packageName};

import com.cn.liu.exception.BusinessException;
import com.cn.liu.util.BeanUtil;
import com.cn.liu.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
/**
* @author ${author}
* @desc
* @date ${date}
*/
@Service("${beanName}Service")
public class ${upBeanName}ServiceImpl implements ${upBeanName}Service{

    @Autowired
    private ${upBeanName}Mapper ${beanName}Mapper

    @Override
    public PageBean<${upBeanName}> queryListByPage(PageRequstParams pageRequstParams) {
    PageHelper.startPage(pageRequstParams.getPage(), pageRequstParams.getRows());
        List<${upBeanName}> ${beanName}ModelList = ${beanName}Mapper.selectByList();
        PageBean pageBean = new PageBean();
        pageBean.setRows(BeanUtil.mapper(${beanName}ModelList, ${upBeanName}.class));
        pageBean.setTotal(totalCount());
        return pageBean;
    }
    @Override
    public void save(${upBeanName} ${beanName}) {
${beanName}.setDeleteMark(1);
        if (!check${upBeanName}Name(${beanName}.get${upBeanName}Name())) {
            throw new BusinessException("${exceptionMsg}");
        }
${beanName}Mapper.insertSelective(${beanName});
    }
    @Override
    public void update(${upBeanName} ${beanName}) {
        if (!check${upBeanName}Name(${beanName}.get${upBeanName}Name())) {
            throw new BusinessException("${exceptionMsg}");
        }
${beanName}Mapper.updateByPrimaryKeySelective(${beanName});
    }
    @Override
    public ${upBeanName} queryById(String id) {
${upBeanName} ${beanName} = ${beanName}Mapper.selectByPrimaryKey(Integer.parseInt(rId));
        return ${beanName}Mapper.queryListBy${upBeanName}Id(rId);
    }
    @Override
    public void delete(String rId) {
${beanName}Mapper.deleteByPrimaryKey(Integer.parseInt(rId));
    }
    private int totalCount() {
        return ${beanName}Mapper.totalCount();
    }
    private boolean check${upBeanName}Name(String ${beanName}Name) {
        int count = ${beanName}Mapper.query${upBeanName}ByName(${beanName}Name);
        if (count > 0) {
            return false;
        }
        return true;
    }
}
