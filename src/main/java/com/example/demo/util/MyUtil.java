package com.example.demo.util;

import com.aliyuncs.utils.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 13:55 2019/4/17
 * @Modified By:
 */
public class MyUtil {
    public static Pageable getPageable(Integer currentPage, Integer pagesize) {

        if (currentPage == null) {
            currentPage = 0;
        }
        if (pagesize == null) {
            pagesize = 10;
        }
        //分页
        Pageable pageable = new PageRequest(currentPage, pagesize);
        return pageable;
    }

    public static Pageable getPageable(Integer currentPage, Integer pagesize, Sort.Direction ascOrDesc, String sort) {
        //判断是否为空
        if (currentPage == null) {
            currentPage = 0;
        }
        if (pagesize == null) {
            pagesize = 10;
        }
        if (StringUtils.isEmpty(sort)|| sort.equals("id")) {
            sort = "id";
            ascOrDesc= Sort.Direction.ASC;
        }
        //分页
        Pageable pageable = new PageRequest(currentPage, pagesize, ascOrDesc, sort);
        return pageable;
    }
}
