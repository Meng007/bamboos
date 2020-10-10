package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.SDZConfig;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.constant.ResponseConstants;
import com.sdz.love.bamboos.commons.util.MimeTypeUtils;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbMemberService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class FileUploadController extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    TbMemberService tbMemberService;
    @Autowired
    private SDZConfig sdzConfig;
    @PostMapping("avatar")
    public ResponseResult uploadAvatar(@RequestParam("file") MultipartFile file){

        System.out.println("文件名:"+file.getOriginalFilename());
        String filename = upload(sdzConfig.getAvatarPath(),file);

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();
        user.setIcon(filename);
        user.setUpdateTime(new Date());
        tbMemberService.updateById(user);
        loginUser.setUser(user);
        tokenService.refreshToken(loginUser);
        Map<String,Object> re = new HashMap<>(2);
        re.put("icon",filename);
        return ResponseResult.success(re);
    }

    @CrossOrigin
    @PostMapping("/goods")
    public ResponseResult uploadGoods(@RequestParam("file") MultipartFile file){
        String filename = upload(sdzConfig.getUploadPath(), file);
        Map<String,Object> re = new HashMap<>(2);
        re.put("icon",filename);
        return ResponseResult.success(re);
    }


    private String upload(String dir,MultipartFile file) {
       String filename = extractFilename(file);
        try {
            File f = getAbsoluteFile(dir,filename);
            file.transferTo(f);
            String pathFileName = getPathFileName(dir, filename);
            return pathFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private File getAbsoluteFile(String dir,String filename) throws IOException {
        File desc = new File(dir + File.separator + filename);
       if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    private String getPathFileName(String uploadDir,String fileName){
        int dirLastIndex = sdzConfig.getProfile().length() + 1;
        String currentDir = uploadDir.substring(dirLastIndex);
        //   /profile/upload/2008/02/11/java.png
        String pathFileName = ResponseConstants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    public static final String extractFilename(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        //获取拓展名
        String extension = getExtension(file);
        // 2019/08/09/Java.png
        fileName = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + UUID.randomUUID().toString().replaceAll("-","") + "." + extension;
        return fileName;
    }

    public static final String getExtension(MultipartFile file)
    {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //判断文件是否拥有拓展名
        if (StringUtils.isEmpty(extension))
        {
            //没有 则从类型路获取
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}
