package hvl.no.exercisehvl.helpers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Getter
public class User {
    private String id;

    @Setter
    private String username;

    @Setter
    private String email;

    public User() {
        // Default constructor for Jackson
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
