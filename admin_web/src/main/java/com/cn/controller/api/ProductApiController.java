package com.cn.controller.api;

import com.cn.controller.BaseController;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.Product;
import com.cn.liu.dto.User;
import com.cn.liu.service.ProductService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lzf
 * desc
 * date 2018/6/3-16:14
 */
@RestController
@RequestMapping("/api/product")
public class ProductApiController extends BaseController {


    @Autowired(required = false)
    private ProductService productService;

    /***
     * 商品分页查询
     * @param PpgeRequstParams
     * @return
     */
    @RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
    public Page<Product> queryListByPage(PageRequstParams PpgeRequstParams) {
        return productService.queryListByPage(PpgeRequstParams);
    }

    /***
     * 保存或修改
     * @param product
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReponseDto save(Product product, HttpServletRequest request) {
        ReponseDto dto = new ReponseDto();
        User user = getUserInfo(request);
        Date now = new Date();
        if (product.getId() == null) {
            product.setCreateDate(now);
            product.setCreateUserId(String.valueOf(user.getId()));
            product.setCreateUserName(user.getUserName());
            productService.save(product);
        } else {
            product.setModifyDate(now);
            product.setModifyUserName(user.getUserName());
            product.setModifyUserId(String.valueOf(user.getId()));
            productService.update(product);
        }
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }
}
