package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CompanyRepository;
import com.example.demo.entity.Company;
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
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    private CompanyRepository companyRepository;

    /*   @RequestMapping("list")
       public List<Company> list(){
           return companyRepository.findAll();
       }*/
    @RequestMapping("listPage")
    public List<Company> list(Integer currentPage, Integer pagesize) {
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Company> models = companyRepository.findAll(pageable);
        return models.getContent();
    }

    @RequestMapping("save")
    public void save(String company) {
/*
        Company company1 =new Company();
        company1.setLogo("http://www.bjzgfs.com/images/logo.png");
        company1.setTitle("北京志广富庶农产品有限公司");
        company1.setLinkman("马清明");
        company1.setBile("1234566");
        company1.setPhone("0571-123423234");
        company1.setFax("0571-123423234");
        company1.setAddress("厦门");
        company1.setIntroduce("志广果蔬隶属于北京志广富庶农产品有限公司，成立于2008年，是一家集种植、销售、配送及电商物流建设于一体的综合型企业。以社区连锁便民菜店、社区车载蔬菜直通车，以及农产品配送为主要业务板块，在张家口、承德、海南，内蒙等地总计建立基地6万余亩。目前，在北京公司拥有占地 20000多平米的集配场地，社区直营店300余家，直通车50辆，辐射近700个社区。2017年年销售额达23.5亿元，水果蔬菜销售量达50万吨，年服务首都居民9800多万人次。我公司与全国人大、北京市委、北京市审计局等40多个政府机关单位签订了放心菜长期配送机制。中央电视台、北京电视台、人民日报，北京日报等多次报道了事迹。");
*/
        Company company1 = JSON.parseObject(company, Company.class);
        companyRepository.save(company1);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        companyRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(Company company) {
        companyRepository.save(company);
    }

    @RequestMapping("findById")
    public Company findById(Integer id) {
        return companyRepository.findById(id).get();
    }
    @RequestMapping("findByNameLike")
    public List<Company> findByNameLike(String name) {
        return companyRepository.findByNameLike("%"+name+"%");
    }
}
