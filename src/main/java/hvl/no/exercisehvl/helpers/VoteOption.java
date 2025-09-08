package hvl.no.exercisehvl.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class VoteOption {
    @Getter
    @Setter
    @JsonProperty("caption")
    private String caption;
    @Getter
    @Setter
    @JsonProperty("votes")
    private int votes;

    public VoteOption() {
        // Default constructor for Jackson
        this.votes = 0;
    }
    public VoteOption(String caption, int votes) {
        this.caption = caption;
        this.votes = votes;
    }
}
