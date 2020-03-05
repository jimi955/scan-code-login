package com.xiaoxin.login.service;

import javax.servlet.http.HttpServletResponse;

public interface IqrCodeServer {
    void createCode(String uuid, HttpServletResponse response);
}
