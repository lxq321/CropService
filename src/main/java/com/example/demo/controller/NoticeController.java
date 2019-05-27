package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.NoticeRepository;
import com.example.demo.entity.Notice;
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
@RequestMapping("/api/notice")
public class NoticeController {


    @Autowired
    private NoticeRepository noticeRepository;

    /*   @RequestMapping("list")
       public List<Notice> list(){
           return noticeRepository.findAll();
       }*/
    @RequestMapping("listPage")
    public List<Notice> list(Integer currentPage, Integer pagesize) {
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Notice> models = noticeRepository.findAll(pageable);
        return models.getContent();
    }

    @RequestMapping("save")
    public void save(Notice notice) {
        noticeRepository.save(notice);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        noticeRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(Notice notice) {
        noticeRepository.save(notice);
    }

    @RequestMapping("findById")
    public Notice findById(Integer id) {
        return noticeRepository.findById(id).get();
    }

    @RequestMapping("saveAll")
    public void saveAll(String notices) {
        List<Notice> models = JSON.parseArray(notices, Notice.class);
        noticeRepository.saveAll(models);
    }

    @RequestMapping("findByUserId")
    public List<Notice> findByUserId(Integer user_id) {
        return noticeRepository.findByExpert_User_Id(user_id);
    }
    @RequestMapping("findByUserIdAndRead")
    public List<Notice> findByUserIdAndRead(Integer user_id,Integer isRead) {
        return noticeRepository.findByExpert_User_IdAndAndIsRead(user_id,isRead);
    }
    @RequestMapping("modifyIsReadById")
    public Integer modifyIsReadById(Integer id,Integer isRead) {
        return noticeRepository.modifyIsReadById(isRead,id);
    }
}
