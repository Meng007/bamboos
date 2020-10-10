package com.sdz.love.bamboos;

import com.sdz.love.bamboos.commons.config.SDZConfig;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BamboosApplicationTests {

    @Autowired
    SDZConfig sdzConfig;
    @Test
    void contextLoads() {

    }

    @Autowired
    TokenService tokenService;
    @Test
    void jwt(){
        System.out.println(tokenService.getHeader());
    }
}
