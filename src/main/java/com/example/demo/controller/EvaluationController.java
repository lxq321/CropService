package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.EvaluationRepository;
import com.example.demo.entity.Evaluation;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;


    @GetMapping("list")
    public List<Evaluation> list() {
        return evaluationRepository.findAll();
    }

    @RequestMapping("listPage")
    public List<Evaluation> list(Integer currentPage, Integer pagesize) {
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Evaluation> models = evaluationRepository.findAll(pageable);
        return models.getContent();
    }

    @RequestMapping("save")
    public Evaluation save(String evaluation) {
        Evaluation model = JSON.parseObject(evaluation, Evaluation.class);
        return evaluationRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(int id) {
        evaluationRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }

    @RequestMapping("findById")
    public Evaluation findById(Integer id) {
        return evaluationRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<Evaluation> findByUserId(Integer id) {
        return evaluationRepository.findByUserId(id);
    }

    @RequestMapping("findByCropName")
    public List<Evaluation> findByCropName(String cropName) {
        return evaluationRepository.findByCropName(cropName);
    }

    @RequestMapping("findByCropNameLike")
    public List<Evaluation> findByCropNameLike(String cropName) {
        return evaluationRepository.findByCropNameLike("%" + cropName + "%");
    }

    /**
     * 动态条件查询
     *
     * @param cropName    作物名称
     * @param sort        排序字段
     * @param currentPage 第几页
     * @param pagesize    每页个数
     * @return 帖子集合
     */
    @RequestMapping("findByExample")
    public List<Evaluation> findByExample(String cropName, Integer currentPage, Integer pagesize, String sort) {
        Evaluation evaluation = new Evaluation();
        evaluation.setCropName(cropName);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//                .withStringMatcher(StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
//                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("cropName", ExampleMatcher.GenericPropertyMatchers.contains()); //地址采用“开始匹配”的方式查询
//                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<Evaluation> example = Example.of(evaluation, matcher);

        //分页
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, sort);
        return evaluationRepository.findAll(example, pageable).getContent();
    }

    @PostMapping("addScan")
    public void addScan(Integer evaluationId) {
        Evaluation evaluation = findById(evaluationId);
        evaluation.setScan(evaluation.getScan() + 1);
        evaluationRepository.save(evaluation);
    }

    @PostMapping("findByContentLike")
    public List<Evaluation> findByContentLike(String content) {
        return evaluationRepository.findByContentLike("%" + content + "%");
    }
}
