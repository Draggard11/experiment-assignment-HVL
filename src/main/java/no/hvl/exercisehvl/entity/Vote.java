package no.hvl.exercisehvl.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private final User voter;
    @ManyToOne
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
