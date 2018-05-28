package com.cn.controller.api;

import com.cn.dto.category.CategoryReponseDto;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.ProductCategory;
import com.cn.liu.service.ProductCategoryService;
import com.cn.util.PageRequetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
    public PageBean<ProductCategory> queryListByPage(HttpServletRequest request) {
        return productCategoryService.queryListByPage(PageRequetUtil.getPageRequest(request));
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

    /***
     * 获取类目
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/queryCategoryList", method = RequestMethod.POST)
    public CategoryReponseDto queryCategoryList(int parentId) {
        CategoryReponseDto categoryReponseDto = new CategoryReponseDto();
        categoryReponseDto.setList(productCategoryService.queryCategoryList(parentId));
        categoryReponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        categoryReponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return categoryReponseDto;
    }
}
