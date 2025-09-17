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
public class VoteOption {
    private String id;

    @Setter
    private String caption;
    @Setter
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
