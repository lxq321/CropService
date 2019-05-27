package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:53 2019/4/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

 /*   @RequestMapping("list")
    public List<Product> list(){
        return productRepository.findAll();
    }*/
    @RequestMapping("listPage")
    public List<Product> list(Integer currentPage,Integer pagesize){

        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Product> models = productRepository.findAll(pageable);
        return models.getContent();
    }
    @RequestMapping("save")
    public void save(String product){
        Product product1 = JSON.parseObject(product, Product.class);
        productRepository.save(product1);
    }
    @RequestMapping("deleteById")
    public void deleteById(Integer id){
       productRepository.deleteById(id);
    }
    @RequestMapping("update")
    public void update(Product product){
        productRepository.save(product);
    }
    @RequestMapping("findById")
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }
    @RequestMapping("findByCompanyId")
    public List<Product> findByCompanyId(Integer company_id) {
        return productRepository.findByCompanyId(company_id);
    }
}
