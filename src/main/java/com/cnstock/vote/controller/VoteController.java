package com.cnstock.vote.controller;

import com.cnstock.vote.service.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {
    @Autowired
    private VoteUserService voteUserService;
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index.html";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(){
        String userNameByUserId = voteUserService.getUserNameByUserId();
        return userNameByUserId;
    }
}
