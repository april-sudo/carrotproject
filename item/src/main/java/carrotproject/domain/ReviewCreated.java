package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import lombok.Data;

@Data
public class ReviewCreated extends AbstractEvent {

    private Long reviewId;
    private Long itemId;
    private Integer score;

    public ReviewCreated(){
        super();
    }
}
