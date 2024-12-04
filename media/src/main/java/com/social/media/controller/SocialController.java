package com.social.media.controller;

import com.social.media.model.SocialUser;
import com.social.media.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {
    @Autowired
    private SocialService socialService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getAllUser(){
        return new ResponseEntity<>(socialService.getAllUser(), HttpStatus.OK);

    }
    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.CREATED);

    }
    @DeleteMapping("/social/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        socialService.deleteUser(userId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
