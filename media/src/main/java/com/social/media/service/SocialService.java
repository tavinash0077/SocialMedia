package com.social.media.service;

import com.social.media.model.SocialUser;
import com.social.media.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialUserRepository socialUserRepository;


    public List<SocialUser> getAllUser() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    public SocialUser deleteUser(Long id) {
        SocialUser user  = socialUserRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user not found"));
         socialUserRepository.delete(user);
         return user;
    }
}
