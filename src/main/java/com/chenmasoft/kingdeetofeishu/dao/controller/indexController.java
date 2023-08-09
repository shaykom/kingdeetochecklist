package com.chenmasoft.kingdeetofeishu.dao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("index")
@CrossOrigin(origins = "*", maxAge = 3600)
public class indexController {

    @RequestMapping("/login")
    public String index() {
        return "login";
    }

    @RequestMapping("/toPage")//, method = RequestMethod.GET)
    public synchronized String toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        return url;
    }
}
