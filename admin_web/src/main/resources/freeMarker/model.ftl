package ${packageName};

import java.util.List;

/**
 *  @author ${author}
 */
public class ${className} {
<#list attrs as attr>

    private ${attr.value} ${attr.name};
</#list>
<#list attrs as attr>
    public void set${attr.name?cap_first}(${attr.value} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }
    public ${attr.value} get${attr.name?cap_first}(){
        return this.${attr.name};
    }
</#list>
}