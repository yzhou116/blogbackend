package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.impl.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/web_size_info")
public class WebSizeInfoAdminApi {

    @Autowired
    private IWebsiteService iWebsiteService;

    @PreAuthorize("@permission.admin()")
    @GetMapping("/title")
    public ResponseResult getWebsiteTitle() {
        return iWebsiteService.getWebsiteTitle();

    }

    @PreAuthorize("@permission.admin()")
    @PutMapping("/title")
    public ResponseResult updateWebsiteTitle(@RequestParam("title") String title) {
        return iWebsiteService.updateWebsiteTitle(title);
    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/seo")
    public ResponseResult getSeoInfo() {
        return iWebsiteService.getWebsiteSeoInfo();
    }

    @PreAuthorize("@permission.admin()")
    @PutMapping("/seo")
    public ResponseResult putSeoInfo(@RequestParam("keyword") String keyword,
                                     @RequestParam("description") String desc
    ) {
        return iWebsiteService.putSeoInfo(keyword, desc);
    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("view_count")
    public ResponseResult getWebSizeViewCount() {
        return iWebsiteService.getSizeViewCount();

    }


}
