package com.xiaoxin.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// https://blog.csdn.net/zl1zl2zl3/article/details/90291849
@CrossOrigin
@Controller
@RequestMapping("/app")
public class AppServerController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/scanlogin")
    public String scanLogin(@RequestParam("uuid") String uuid, @RequestParam("sign") String sign, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (sign.equals("login")) {
            HttpSession session = request.getSession();
            session.setAttribute("uuid", uuid);
            return "confim";
        }
        response.getWriter().write("not web scan login !!!");
        return "";
    }

    @RequestMapping("/confim")
    public String scanLogin(HttpServletRequest request) {
        String userid = "123"; // 这个应该是app端带来的token中解析的userid
        HttpSession session = request.getSession();
        String uuid = (String) session.getAttribute("uuid");
        redisTemplate.opsForValue().set(uuid, userid);
        return "success";
    }
}
