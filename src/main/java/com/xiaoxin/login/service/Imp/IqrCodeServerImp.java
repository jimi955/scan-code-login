package com.xiaoxin.login.service.Imp;

import com.xiaoxin.login.service.IqrCodeServer;
import com.xiaoxin.login.utils.QrCodeUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class IqrCodeServerImp implements IqrCodeServer {

    @Override
    public void createCode(String uuid, HttpServletResponse response) {
        String signal = "login";
        String s = "http://192.168.1.104:8080/app/scanlogin" + "?uuid=" + uuid + "&sign=" + signal;
        System.out.println(s);
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(s, "", os, true);
            System.out.println("返回二维码");

//            QrCodeUtils.encode(requestUrl, "/static/images/logo.png", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
