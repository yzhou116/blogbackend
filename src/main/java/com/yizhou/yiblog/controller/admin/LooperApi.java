package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/loop")
public class LooperApi {


    @PostMapping
    public ResponseResult addLoop(@RequestBody Looper looper) {
        return null;

    }

    @DeleteMapping("/{loopId}")
    public ResponseResult deleteLoop(@PathVariable("loopId") String loopId) {
        return null;

    }

    @PostMapping("/{loopId}")
    public ResponseResult updateLoop(@PathVariable("loopId") String loopId) {
        return null;

    }

    @GetMapping("/{loopId}")
    public ResponseResult getLoop(@PathVariable("loopId") String loopId) {
        return null;

    }

    @GetMapping("/list")
    public ResponseResult listLoop(@RequestParam("page") int page,
                                   @RequestParam("size") int size) {
        return null;

    }

}
