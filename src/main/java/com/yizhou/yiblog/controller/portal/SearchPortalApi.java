package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal/search")
public class SearchPortalApi {
    @GetMapping
    public ResponseResult doSearch(@RequestParam("keyword") String keyword,
                                   @RequestParam("page") int page,
                                   @RequestParam("size") int size,
                                   @RequestParam(value = "categoryId", required = false) String categoryId,
                                   @RequestParam(value = "sort", required = false) Integer sort) {
        /**
         * Need to learn solr, will finish this part after.
         */
        return null;
    }

}
