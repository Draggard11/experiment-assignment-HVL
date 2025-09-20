package no.hvl.exercisehvl.helpers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User createdBy;

    @Setter
    private String question;

    @Setter
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private Set<VoteOption> options;

    @Setter
    private States availability;

    private final Instant publishedAt;

    @Setter
    private Instant expiresAt;

    public Poll() {
        // Default constructor for Jackson
        this.availability = States.Public; // Default value
        this.publishedAt = Instant.now();
    }

    public Poll(User createdBy, String question) {
        this.createdBy = createdBy;
        this.question = question;
        this.options = new HashSet<>();
        this.availability = States.Public;
        this.publishedAt = Instant.now();
        this.expiresAt = this.publishedAt.plusSeconds(60*10);
    }

    public Poll(User createdBy, String question, Set<VoteOption> voteOptions, States availability, Instant expiresAt) {
        this.createdBy = createdBy;
        this.question = question;
        this.options = voteOptions;
        this.availability = availability;
        this.publishedAt = Instant.now();
        this.expiresAt = expiresAt;
    }

    /**
     *
     * Adds a new option to this Poll and returns the respective
     * VoteOption object with the given caption.
     * The value of the presentationOrder field gets determined
     * by the size of the currently existing VoteOptions for this Poll.
     * I.e. the first added VoteOption has presentationOrder=0, the secondly
     * registered VoteOption has presentationOrder=1 and so on.
     */
    public VoteOption addVoteOption(String caption) {
        VoteOption option = new VoteOption(this, caption);
        option.setPresentationOrder(options.size());
        options.add(option);
        return option;
    }
}
