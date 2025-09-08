package hvl.no.exercisehvl.helpers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "id"
)
public class User {
    @Getter
    private String id;

    @Getter
    @Setter
    @JsonProperty("username")
    private String username;

    @Getter
    @Setter
    @JsonProperty("email")
    private String email;

    public User() {
        // Default constructor for Jackson
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
