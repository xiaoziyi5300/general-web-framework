package com.cn.controller.api;

import com.cn.dto.category.CategoryReponseDto;
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
     * @param
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

    /***
     * 获取类目
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/queryCategoryList", method = RequestMethod.POST)
    public CategoryReponseDto queryCategoryList(String parentId) {
        CategoryReponseDto categoryReponseDto = new CategoryReponseDto();
        categoryReponseDto.setList(productCategoryService.queryCategoryList(Integer.parseInt(parentId)));
        categoryReponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        categoryReponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return categoryReponseDto;
    }

    /***
     *
     * @param cId
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public CategoryReponseDto queryById(String cId) {
        CategoryReponseDto dto = new CategoryReponseDto();
        dto.setProductCategory(productCategoryService.queryById(cId));
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        return dto;
    }

    /***
     * 删除
     * @param cId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReponseDto delete(String cId) {
        CategoryReponseDto dto = new CategoryReponseDto();
        productCategoryService.deleteById(Integer.parseInt(cId));
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        return dto;
    }
}
