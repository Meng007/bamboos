package com.sdz.love.bamboos.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@ConfigurationProperties(prefix = "project")
public class SDZConfig {

    private String projectName;
    private String version;
    private String profile;
    private String copyrightYear;
    private String author;
    private String address;
    private String qq;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取头像上传路径
     */
    public  String getAvatarPath()
    {
        return getProfile() + File.separator+"avatar";
    }

    /**
     * 获取上传路径
     */
    public  String getUploadPath()
    {
        return getProfile() + File.separator+ "upload";
    }

    @Override
    public String toString() {
        return "SDZConfig{" +
                "projectName='" + projectName + '\'' +
                ", version='" + version + '\'' +
                ", copyrightYear='" + copyrightYear + '\'' +
                ", author='" + author + '\'' +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String f = "D:/sdz/icon";
        File file = new File(f);
        if (file.exists()){
            file.mkdirs();
        }
        File f1 = new File(f + File.separator + "love.text");
        if (f1.exists()){
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
