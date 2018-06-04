package ${packageName};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* @author ${author}
* @desc
* @date ${date}
*/
@Controller
@RequestMapping("/${controller_name}")
public class ${upController_name}Controller{

     /***
     * 主页
     * @return
     */
    @RequestMapping("/${controller_view}")
    public ModelAndView ${controller_view}(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/${controller_name}/${controller_name}list");
        return mv;
    }
}