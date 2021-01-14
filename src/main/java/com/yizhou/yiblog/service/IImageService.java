package com.yizhou.yiblog.service;

import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IImageService {
    ResponseResult uploadFile(MultipartFile file);

    ResponseResult viewImage(HttpServletResponse response, String imageId) throws IOException;

    ResponseResult ListImages(int page, int size);

    ResponseResult deleteById(String imageId);
}
