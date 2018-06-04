package ${packageName};

import com.cn.controller.BaseController;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cn.model.${upController_name};
/**
* @author ${author}
* @desc
* @date ${date}
*/
@RestController
@RequestMapping("/api/${controller_name}")
public class ${upController_name}ApiController extends BaseController{

        @Autowired
        private ${upController_name}Service ${controller_name}Service

        /***
         * ${controller_name}分页查询
         * @param pageRequstParams
         * @return
         */
        @RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
        public PageBean<${upController_name}>queryListByPage(PageRequstParams pageRequstParams){
            return ${controller_name}Service.queryListByPage(pageRequstParams);
        }
        /***
         * 保存或修改
         * @param request
         * @param  ${controller_name}
         * @return
         */
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public ReponseDto save(HttpServletRequest request, ${upController_name} ${controller_name}){
            ReponseDto dto = new ReponseDto();
            User user = getUserInfo(request);
            Date now = new Date();
            if (${controller_name}.getId() == null) {
${controller_name}.setCreateDate(now);
${controller_name}.setCreateUserId(user.getId());
${controller_name}.setCreateUserName(user.getUserName());
${controller_name}Service.save(${controller_name});
            } else {
${controller_name}.setModifyDate(now);
${controller_name}.setModifyUserId(user.getId());
${controller_name}.setModifyUserName(user.getUserName());
${controller_name}Service.update(${controller_name});
            }
            dto.setStatus(CommonConstant.SUCCESS_STATUS);
            dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
            return dto;
        }
         /***
         * @根据ID获取数据
         * @param id
         * @return
         */
        @RequestMapping(value = "/queryById", method = RequestMethod.POST)
        public ${upController_name}ReponseDto queryById(String id) {
${upController_name}ReponseDto dto = new ${upController_name}ReponseDto();
            dto.setRole(${controller_name}Service.queryById(id));
            dto.setStatus(CommonConstant.SUCCESS_STATUS);
            dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
            return dto;
        }
         /***
         * 根据ID删除
         * @param id
         * @return
         */
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public ReponseDto delete(String id) {
            RoleReponseDto dto = new RoleReponseDto();
${controller_name}Service.delete(id);
            dto.setStatus(CommonConstant.SUCCESS_STATUS);
            dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
            return dto;
        }
}
