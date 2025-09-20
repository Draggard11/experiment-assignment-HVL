package no.hvl.exercisehvl.helpers;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private final User voter;
    @ManyToOne
    @JoinColumn(name = "option_id")
    private final VoteOption votesOn;

    public Vote() {
        this.voter = null;
        this.votesOn = null;
    }

    public Vote(User votesOn, VoteOption option) {
        this.voter = votesOn;
        this.votesOn = option;
    }
}
