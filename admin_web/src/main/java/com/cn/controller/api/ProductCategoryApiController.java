package com.cn.controller.api;

import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.ProductCategory;
import com.cn.liu.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc
 */
@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryApiController {

    @Autowired(required = false)
    private ProductCategoryService productCategoryService;

    /***
     * 商品类目分页显示
     * @param pageRequstParams
     * @return
     */
    @RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
    public PageBean<ProductCategory> queryListByPage(PageRequstParams pageRequstParams) {
        return productCategoryService.queryListByPage(pageRequstParams);
    }

    /****
     * 保存
     * @param productCategory
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReponseDto save(ProductCategory productCategory) {
        ReponseDto reponseDto = new ReponseDto();
        if (productCategory.getId() == null) {
            productCategoryService.save(productCategory);
        } else {
            productCategoryService.update(productCategory);
        }
        reponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        reponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return reponseDto;
    }

}
