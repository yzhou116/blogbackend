package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ILooperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/loop")
public class LooperApi {

    @Autowired
    private ILooperService iLooperService;

    @PreAuthorize("@permission.admin()")
    @PostMapping
    public ResponseResult addLoop(@RequestBody Looper looper) {


        return iLooperService.addLoop(looper);

    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/{loopId}")
    public ResponseResult deleteLoop(@PathVariable("loopId") String loopId) {
        return iLooperService.deleteByLoopId(loopId);

    }

    @PreAuthorize("@permission.admin()")
    @PostMapping("/{loopId}")
    public ResponseResult updateLoop(@PathVariable("loopId") String loopId,
                                     @RequestBody Looper looper) {
        return iLooperService.updateLooper(loopId, looper);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/{loopId}")
    public ResponseResult getLoop(@PathVariable("loopId") String loopId) {
        return iLooperService.getLoop(loopId);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/list")
    public ResponseResult listLoop(@RequestParam("page") int page,
                                   @RequestParam("size") int size) {
        return iLooperService.ListLoops(page, size);

    }

}
