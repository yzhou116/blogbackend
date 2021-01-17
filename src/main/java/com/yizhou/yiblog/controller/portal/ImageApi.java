package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/portal/image")
public class ImageApi {

    @Autowired
    private IImageService iImageService;

    @GetMapping("/{imageId}")
    public ResponseResult getImage(HttpServletResponse response,
                                   @PathVariable("imageId") String imageId) {
        try {
            return iImageService.viewImage(response, imageId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.FAIL("Can't read file");

    }
}
