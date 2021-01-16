package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICategoryService;
import com.yizhou.yiblog.service.IFriendsLinkService;
import com.yizhou.yiblog.service.ILooperService;
import com.yizhou.yiblog.service.impl.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal/web_size_info")
public class WebSizeInfoApi {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IFriendsLinkService iFriendsLinkService;
    @Autowired
    private ILooperService iLooperService;

    @Autowired
    private IWebsiteService iWebsiteService;


    @GetMapping("/categories/{page}/{size}")
    public ResponseResult getCategory(@PathVariable("page") int page,
                                      @PathVariable("size") int size) {
        return iCategoryService.ListCategory(page, size);
    }

    @GetMapping("/title")
    public ResponseResult getWebSizeTitle() {
        return iWebsiteService.getWebsiteTitle();
    }

    @GetMapping("/view_count")
    public ResponseResult getWebSizeViewCount() {
        return iWebsiteService.getSizeViewCount();
    }

    @GetMapping("/seo")
    public ResponseResult getWebSizeSeoInfo() {
        return iWebsiteService.getWebsiteSeoInfo();
    }

    @GetMapping("/loop/{page}/{size}")
    public ResponseResult getLoops(@PathVariable("page") int page,
                                   @PathVariable("size") int size) {
        return iLooperService.ListLoops(page, size);
    }

    @GetMapping("/friend_link/{page}/{size}")
    public ResponseResult getLinks(@PathVariable("page") int page,
                                   @PathVariable("size") int size) {
        return iFriendsLinkService.ListAllFriends(page, size);
    }

    @PutMapping("/view_count")
    public void updateViewCount() {
        iWebsiteService.updateViewCount();
    }

}
