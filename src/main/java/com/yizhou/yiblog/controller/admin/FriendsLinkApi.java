package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/friend_link")
public class FriendsLinkApi {

    @PostMapping
    public ResponseResult addFriend(@RequestBody Friends friends) {
        return null;

    }

    @DeleteMapping("/{friendsId}")
    public ResponseResult deleteFriends(@PathVariable("friendsId") String friendsId) {
        return null;

    }

    @PostMapping("/{friendsId}")
    public ResponseResult updateFriends(@PathVariable("friendsId") String friendsId) {
        return null;

    }

    @GetMapping("/{friendsId}")
    public ResponseResult getFriends(@PathVariable("friendsId") String friendsId) {
        return null;

    }

    @GetMapping("/list")
    public ResponseResult listFriends(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        return null;

    }

}
