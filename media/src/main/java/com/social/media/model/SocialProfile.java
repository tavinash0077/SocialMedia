package com.social.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    @JsonIgnore
    private SocialUser user;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialUser socialUser){
        this.user  = socialUser;
        if(user.getSocialProfile() != this){
            user.setSocialProfile(this);
        }
    }
}
