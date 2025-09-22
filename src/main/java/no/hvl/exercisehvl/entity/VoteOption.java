package no.hvl.exercisehvl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class VoteOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String caption;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "votesOn", cascade =  CascadeType.ALL)
    private Set<Vote> votes;

    @Setter
    private int presentationOrder;

    public VoteOption() {
        // Default constructor for Jackson
        this.votes = new HashSet<>();
        this.poll = new Poll();
    }

    public VoteOption(Poll poll, String caption) {
        this.poll = poll;
        this.caption = caption;
        this.votes = new HashSet<>();
    }

    public VoteOption(Poll poll, String caption, Set<Vote> votes) {
        this.poll = poll;
        this.caption = caption;
        this.votes = votes;
    }

    public Vote addVote(User voter) {
        Vote vote = new Vote(voter, this);
        votes.add(vote);
        return vote;
    }
}
