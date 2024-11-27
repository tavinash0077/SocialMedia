package com.social.media;

import com.social.media.model.Post;
import com.social.media.model.SocialGroup;
import com.social.media.model.SocialProfile;
import com.social.media.model.SocialUser;
import com.social.media.repository.PostRepository;
import com.social.media.repository.SocialGroupRepository;
import com.social.media.repository.SocialProfileRepository;
import com.social.media.repository.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;


    public DataInitializer(SocialUserRepository userRepository, SocialGroupRepository groupRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }
    @Bean
    public CommandLineRunner initializeData(){
       return args -> {

            //create some users
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            //save users to database
           userRepository.save(user1);
           userRepository.save(user2);
           userRepository.save(user3);

           //create Some group
           SocialGroup group1 = new SocialGroup();
           SocialGroup group2 = new SocialGroup();

           //add users to group
           group1.getSocialUser().add(user1);
           group1.getSocialUser().add(user2);

           group2.getSocialUser().add(user3);
           group2.getSocialUser().add(user2);

           groupRepository.save(group1);
           groupRepository.save(group2);

           //associate user with groups
           user1.getGroups().add(group1);
           user2.getGroups().add(group2);
           user2.getGroups().add(group1);
           user3.getGroups().add(group2);

           // Save users back to database to update associations
           userRepository.save(user1);
           userRepository.save(user2);
           userRepository.save(user3);

           //create some posts

           Post post1 = new Post();
           Post post2 = new Post();
           Post post3 = new Post();

           //Associate Post with users

           post1.setUser(user1);
           post2.setUser(user2);
           post3.setUser(user3);

           // Save posts to the database (assuming you have a PostRepository)
           postRepository.save(post1);
           postRepository.save(post2);
           postRepository.save(post3);

           //create some profile

           SocialProfile profile1 = new SocialProfile();
           SocialProfile profile2 = new SocialProfile();
           SocialProfile profile3 = new SocialProfile();

           //Associate profile with users
           profile1.setUser(user1);
           profile2.setUser(user2);
           profile3.setUser(user3);

           //save profile to the database
           socialProfileRepository.save(profile1);
           socialProfileRepository.save(profile2);
           socialProfileRepository.save(profile3);


       };
    }
}
