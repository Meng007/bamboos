package com.sdz.love.bamboos;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 13557  (scanBasePackages = "com.sdz.love.bamboos",exclude = {MultipartAutoConfiguration.class})
 */
@SpringBootApplication
@MapperScan("com.sdz.love.bamboos.mapper")
public class BamboosApplication {

    public static void main(String[] args) {

        //WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
        SpringApplication.run(BamboosApplication.class, args);
    }

}
