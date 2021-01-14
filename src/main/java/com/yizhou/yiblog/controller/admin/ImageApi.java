package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/admin/image")
public class ImageApi {
    @Autowired
    private IImageService iImageService;

    /**
     * About file upload:
     * use Nginx + fastDFS ==> fastDFS --> fileupload , Nginx --> see file
     *
     * @param file
     * @return
     */
    @PreAuthorize("@permission.admin()")
    @PostMapping
    public ResponseResult uploadImage(@RequestParam("file") MultipartFile file) {
        return iImageService.uploadFile(file);

    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/{imageId}")
    public ResponseResult deleteImage(@PathVariable("imageId") String imageId) {
        return iImageService.deleteById(imageId);

    }

    @PreAuthorize("@permission.admin()")
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

    @GetMapping("/list/{page}/{size}")
    public ResponseResult listImage(@PathVariable("page") int page,
                                    @PathVariable("size") int size) {
        return iImageService.ListImages(page, size);

    }


}
