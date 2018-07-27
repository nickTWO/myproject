package com.hyt.myproject.controller;

import com.hyt.myproject.common.config.TestConfiguration;
import com.hyt.myproject.common.utils.FileUtil;
import com.hyt.myproject.common.utils.PayCloudUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/4/16.
 */
@Controller
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestConfiguration testConfiguration;

    private final ResourceLoader resourceLoader;

    public IndexController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request){
//        System.out.println(redisTemplate);
        System.out.println(testConfiguration.getName() + " " + testConfiguration.getAge());
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "testBody")
    public String testBody(HttpServletRequest request){
        return "{test:test1}";
    }

    /**
     * 文件上传(不落db)
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        try {
            String fileName = file.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            FileUtil.uploadFile(file.getBytes(), testConfiguration.getFilePath(), fileName);
            Map map = new HashMap(8);
            map.put("fileName",fileName);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap(8);
        }
    }

    /**
     * 显示单张图片
     *
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName) {
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            Resource resource = resourceLoader.getResource("file:" + testConfiguration.getFilePath() + fileName);
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
