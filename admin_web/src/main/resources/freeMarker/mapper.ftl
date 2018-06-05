package ${packageName};

import ${model_packageName}.${upBeanName}
/**
* @author ${author}
* @desc
* @date ${date}
*/
public interface ${upBeanName}Mapper{

    int insert(${upBeanName} ${beanName});

    int insertSelective (${upBeanName} ${beanName});

${upBeanName} selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(${upBeanName} ${beanName});

    int totalCount();

    List<${upBeanName}> selectByList();
}