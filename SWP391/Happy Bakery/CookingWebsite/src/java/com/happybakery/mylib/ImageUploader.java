/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.mylib;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;

/**
 *
 * @author thinh
 */
public class ImageUploader {
    

    public static String upload(String imgName, Part part, String path) {
        File img = new File(path);
        img.mkdirs();
        String fileName = getFileName(part);

        if (fileName != null && !fileName.isEmpty()) {
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
            imgName = imgName + (ext.isEmpty() ? "" : '.' + ext);
            try {
                part.write(path + File.separator + imgName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgName;
    }
    
    public static boolean delete(String imgPath) {
        File img = new File(imgPath);
        return img.delete();
    }

    public static String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String filePath = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return filePath.substring(filePath.lastIndexOf(File.separator) + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}