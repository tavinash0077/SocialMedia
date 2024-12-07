1. @JsonIgnore:
The @JsonIgnore annotation is used in bidirectional relationships (e.g., a User entity has a SocialProfile, and SocialProfile has a User reference) to prevent infinite recursion during JSON serialization.

Without @JsonIgnore, JSON serializers like Jackson may enter an infinite loop as they serialize both entities back and forth.
Example:

java
Copy code
public class User {
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;
}

public class SocialProfile {
    @JsonIgnore
    @OneToOne
    private User user;
}
This ensures that when serializing a SocialProfile, the User reference is ignored, preventing infinite nesting.

2. The Error: non-transient entity has a null id
This error indicates that Hibernate attempted to persist an entity (SocialProfile in this case) with a null identifier. Hibernate requires an entity to have a valid @Id (Primary Key) before it can be persisted.

3. Solution: Cascading Operations
Cascading allows you to propagate an operation (e.g., persist, remove) performed on one entity to its associated entities.

In your case, you want to save the SocialProfile automatically when saving the User.
What to Do:

Use the cascade attribute in the relationship annotation to specify cascading behavior.
Example with Cascading:

java
Copy code
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
        socialProfile.setUser(this); // Maintain bidirectional consistency
    }
}

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
}
Now, when you save the User entity, Hibernate will automatically save the associated SocialProfile entity as well.

4. Cascading Operations Explained:
Cascading simplifies the management of related entities by automatically propagating actions such as:

PERSIST: Save the related entity when the parent entity is saved.
MERGE: Merge changes from the parent entity to the related entities.
REMOVE: Delete the related entities when the parent entity is deleted.
REFRESH: Refresh the state of related entities when the parent entity is refreshed.
ALL: Apply all cascading operations.



5.    {
          "id": 4,
          "socialProfile": null,
          "posts": [],
          "groups": []
      },

      we are getting social profile as null.
      And why is this custom setter needed.

      So the custom setter method in the user like social user entity is for setting the profile is important

      because it is maintaining.

      It is being used for maintaining the consistency on both the sides of the bidirectional relationship

      in the memory.

 => cascade = CascadeType.PERSIST =>> it handles the saving of entitiees, but it does not update the existing oncee
    -----------------------------



=> fetch type-> fetch type plays a crucial role in defining how and when related entities are loaded from
                Database in relation to parent activity.

FetchType.LAZY -One is lazy and this is also known as lazy loading.

                This is when the entities are not loaded immediately with the parent entity.

                Instead, they are loaded on demand, which means they are only loaded when they are accessed for the

                first time in the code, and this approach is useful in improving the performance, especially when

                the related entities are not needed immediately.
FetchType.EAGER -Eager Fetching:

                 Related entities are loaded simultaneously with the parent entity.
                 Useful when the related entities are immediately required after loading the parent entity.
                 Can cause performance issues if unnecessary related data is loaded.
                 Example: In a one-to-many relationship between Customer and Order, if fetch = FetchType.EAGER is used, all orders related to a customer are loaded along with the customer, even if they are not needed.
                 Lazy Fetching:

                 Related entities are not loaded with the parent entity initially.
                 Data in child entities is loaded only when explicitly accessed.
                 Useful when the related data might not always be required or when you want to load it on demand.
                 Example: In the same one-to-many relationship, if fetch = FetchType.LAZY is used, the orders are not fetched when the customer is retrieved but are loaded only when accessed explicitly.
                 Performance Considerations:

                 Eager fetching can lead to unnecessary memory usage if related data is not required.
                 Lazy fetching avoids loading unnecessary data but might result in additional queries if related data is accessed multiple times.
                 Default Fetch Types:

                 One-to-Many: Default is Lazy.
                 Many-to-One: Default is Eager.
                 Many-to-Many: Default is Lazy.
                 One-to-One: Default is Eager.
                 When to Use:

                 Use Eager Fetching when the related entities are always needed with the parent.
                 Use Lazy Fetching when the related entities are rarely needed or should be loaded only on demand.
                 These points summarize the key aspects of fetch types and their impact on application performance.











