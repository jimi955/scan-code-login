package com.xiaoxin.login.controller;

import com.xiaoxin.login.service.Imp.IqrCodeServerImp;
import com.xiaoxin.login.utils.QrCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Controller
public class WebServerController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IqrCodeServerImp iqrCodeServerImp;

    @RequestMapping("/qrcode/create")
    public void qrcode(@RequestParam("uuid") String uuid, HttpServletResponse response) throws IOException {
        if (uuid == null || uuid.equals("")) {
            // 第一次前端没有uuid
            uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, "0", 180, TimeUnit.SECONDS);
        }
        String res = redisTemplate.opsForValue().get(uuid);
        if (res == null) {
            System.out.println("二维码失效");

            // uuid失效了 需要重新生成图片
            uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, "0", 180, TimeUnit.SECONDS);
            iqrCodeServerImp.createCode(uuid, response);

        } else {
            if (res.equals("0")) {
                // 没有登录
                System.out.println("没有登录");
                iqrCodeServerImp.createCode(uuid, response);
            } else {
                // 登录成功 返回userId
                System.out.println("登录成功 userid:" + res);

                response.getWriter().write("userid:" + res);
            }
        }
    }
}