package com.sdz.love.bamboos.controller;

import com.sdz.love.bamboos.commons.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @GetMapping("/hello")
    public String hello(){
        return "你好";
    }
}
