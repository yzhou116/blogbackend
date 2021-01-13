package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/image")
public class ImageApi {

    @PostMapping
    public ResponseResult uploadImage() {
        return null;

    }

    @DeleteMapping("/{imageId}")
    public ResponseResult deleteImage(@PathVariable("imageId") String imageId) {
        return null;

    }

    @PostMapping("/{imageId}")
    public ResponseResult updateImage(@PathVariable("imageId") String imageId) {
        return null;

    }

    @GetMapping("/{imageId}")
    public ResponseResult getImage(@PathVariable("imageId") String imageId) {
        return null;

    }

    @GetMapping("/list")
    public ResponseResult listImage(@RequestParam("page") int page,
                                    @RequestParam("size") int size) {
        return null;

    }


}
