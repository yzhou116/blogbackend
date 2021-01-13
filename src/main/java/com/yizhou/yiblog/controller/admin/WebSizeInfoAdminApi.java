package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/web_size_info")
public class WebSizeInfoAdminApi {

    @GetMapping("/title")
    public ResponseResult getWebsiteTitle() {
        return null;

    }

    @PutMapping("/title")
    public ResponseResult updateWebsiteTitle(@RequestParam("title") String title) {
        return null;
    }

    @GetMapping("/seo")
    public ResponseResult getSeoInfo() {
        return null;
    }

    @PutMapping("/seo")
    public ResponseResult putSeoInfo(@RequestParam("keyword") String keyword,
                                     @RequestParam("description") String desc
    ) {
        return null;
    }

    @GetMapping("view_count")
    public ResponseResult getWebSizeViewCount() {
        return null;

    }


}
