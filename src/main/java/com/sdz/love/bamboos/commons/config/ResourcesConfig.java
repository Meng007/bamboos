package com.sdz.love.bamboos.commons.config;

import com.sdz.love.bamboos.commons.constant.ResponseConstants;
import com.sdz.love.bamboos.commons.util.SpringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        SDZConfig config = SpringUtils.getBean(SDZConfig.class);
        /** 本地文件上传路径 */
        registry.addResourceHandler(ResponseConstants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + config.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
