package hvl.no.exercisehvl.helpers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Getter
public class Poll {

    private String id;

    @Setter
    private User owner;

    @Setter
    private String question;

    @Setter
    private Set<VoteOption> options;

    @Setter
    private States availability;

    @Setter
    private LocalDateTime expiresAt;

    private final LocalDateTime publishedAt;

    public Poll() {
        // Default constructor for Jackson
        this.availability = States.Public; // Default value
        this.publishedAt = LocalDateTime.now();
    }

    public Poll(User owner, String question, Set<VoteOption> voteOptions, States availability, LocalDateTime expiresAt) {
        this.owner = owner;
        this.question = question;
        this.options = voteOptions;
        this.availability = availability;
        this.publishedAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
    }
}
