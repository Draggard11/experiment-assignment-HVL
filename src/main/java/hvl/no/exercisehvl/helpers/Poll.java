package hvl.no.exercisehvl.helpers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "id"
)
public class Poll {
    @Getter
    private String id;

    @Getter
    @Setter
    @JsonProperty("owner")
    private User owner;

    @Getter
    @Setter
    @JsonProperty("question")
    private String question;

    @Getter
    @Setter
    @JsonProperty("options")
    private Set<VoteOption> options;

    @Getter
    @JsonProperty("availability")
    private final States availability;

    @Getter
    private Instant publishedAt;

    @Getter
    @Setter
    @JsonProperty("expiresAt")
    private Instant expiresAt;

    public Poll() {
        // Default constructor for Jackson
        this.availability = States.Public; // Default value
        this.publishedAt = Instant.now();
    }
    public Poll(User owner, String question, Set<VoteOption> voteOptions, States availability, Instant expiresAt) {
        this.owner = owner;
        this.question = question;
        this.options = voteOptions;
        this.availability = availability;
        this.publishedAt = Instant.now();
        this.expiresAt = expiresAt;
    }
}
