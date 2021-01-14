package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IFriendsLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/friend_link")
public class FriendsLinkApi {

    @Autowired
    private IFriendsLinkService iFriendsLinkService;


    @PreAuthorize("@permission.admin()")
    @PostMapping
    public ResponseResult addFriend(@RequestBody Friends friends) {
        return iFriendsLinkService.addFriendLink(friends);

    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/{friendsId}")
    public ResponseResult deleteFriends(@PathVariable("friendsId") String friendsId) {
        return iFriendsLinkService.deleteFriend(friendsId);

    }

    @PreAuthorize("@permission.admin()")
    @PostMapping("/{friendsId}")
    public ResponseResult updateFriends(@PathVariable("friendsId") String friendsId,
                                        @RequestBody Friends friends) {
        return iFriendsLinkService.updateFriendInfo(friendsId, friends);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/{friendsId}")
    public ResponseResult getFriends(@PathVariable("friendsId") String friendsId) {
        return iFriendsLinkService.getFriend(friendsId);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/list/{page}/{size}")
    public ResponseResult listFriends(@PathVariable("page") int page,
                                      @PathVariable("size") int size) {
        return iFriendsLinkService.ListAllFriends(page, size);

    }

}
