package com.social.media.repository;

import com.social.media.model.SocialGroup;
import com.social.media.model.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialGroupRepository extends JpaRepository<SocialGroup,Long>  {
}
