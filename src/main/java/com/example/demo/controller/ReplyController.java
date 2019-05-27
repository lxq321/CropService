package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.EvaluationRepository;
import com.example.demo.Jpa.GoodRepository;
import com.example.demo.Jpa.ReplyRepository;
import com.example.demo.Jpa.UserRepository;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.Good;
import com.example.demo.entity.Reply;
import com.example.demo.entity.User;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:53 2019/4/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @RequestMapping("list")
    public List<Reply> list() {
        return replyRepository.findAll();
    }

    @RequestMapping("listPage")
    public List<Reply> list(Integer currentPage, Integer pagesize) {
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Reply> models = replyRepository.findAll(pageable);
        return models.getContent();
    }

    @RequestMapping("save")
    public void save(String reply) {
        Reply entity = JSON.parseObject(reply, Reply.class);
        replyRepository.save(entity);
        //添加评论数
        Integer evaluationId = entity.getEvaluation().getId();
        Evaluation evaluation = evaluationRepository.findById(evaluationId).get();
        evaluation.setCommentCount(evaluation.getCommentCount()+1);
        evaluationRepository.save(evaluation);

//      Reply entity1= replyRepository.save(entity);

    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        replyRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(String reply) {
        replyRepository.save(JSON.parseObject(reply, Reply.class));
    }

    @RequestMapping("findById")
    public Reply findById(Integer id) {
        return replyRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<Reply> findByUserId(Integer id) {
        return replyRepository.findByUserId(id);
    }

    @RequestMapping("findByEvaluationId")
    public Map<String, Object> findByEvaluationId(Integer id,Integer userId) {
        List<Reply> replyList = replyRepository.findByEvaluationId(id);
        User user = userRepository.findById(userId).get();
        List<Boolean> booleans=new ArrayList<>();
        for (int i = 0; i < replyList.size(); i++) {
            Good good=new Good();
            good.setReply(replyList.get(i));
            good.setUser(user);
            //判断是否存在点赞
            boolean exists = goodRepository.exists(Example.of(good));
            booleans.add(exists);
        }
        Map<String, Object> r=new HashMap<>();
        r.put("booleans", JSON.toJSONString(booleans));
        r.put("replyList", JSON.toJSONString(replyList));
        return r;
    }
  /*  @RequestMapping("findDistinctEvaluationIdByUserId")
    public List<Reply> findDistinctEvaluationIdByUserId(String id) {
        return replyRepository.findDistinctEvaluation_IdByUser_Id(Integer.valueOf(id));
    }*/

}
