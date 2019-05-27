package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 练续强
 * @Description TODO(工具)
 * @Date Create in 23:54 2019/3/23
 * @Modified By:
 */
@RestController
@RequestMapping("/api/util")
public class UtilController {
    @Autowired
    private HttpServletRequest request;
    @Value("${localhost.images}")
    String imagesPath;
    //图片文件接送
    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(MultipartFile file) {
        File dir  = new File(imagesPath);
        if (!dir.exists()){
            dir.mkdir();
        }
        Map<String, Object> r = new HashMap<>();
        String name = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/")
                        + file.getOriginalFilename();
//                name=UUID.randomUUID().toString()+file.getOriginalFilename();

                name = UUID.randomUUID().toString()+file.getOriginalFilename();
                r.put("name", name);
                filePath = imagesPath+File.separator+ name;
                System.out.println(filePath);
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        r.put("flag", 0);
        return r;
    }
}
