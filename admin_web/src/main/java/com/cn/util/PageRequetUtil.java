package com.cn.util;

import com.cn.liu.base.PageRequstParams;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc
 */
public class PageRequetUtil {

    /***
     * 获取boot-strap table 页面传来的参数
     * @param request
     * @return
     */
    public static PageRequstParams getPageRequest(HttpServletRequest request) {
        PageRequstParams pageRequstParams = new PageRequstParams();
        pageRequstParams.setPage(Integer.valueOf(request.getParameter("page")));
        pageRequstParams.setRows(Integer.valueOf(request.getParameter("rows")));
        pageRequstParams.setSortOrder(request.getParameter("sortOrder"));
        pageRequstParams.setSort(request.getParameter("sort"));
        return pageRequstParams;
    }
}
