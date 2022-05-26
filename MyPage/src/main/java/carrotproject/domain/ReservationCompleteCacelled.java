package carrotproject.domain;

import carrotproject.infra.AbstractEvent;
import lombok.Data;
import java.util.Date;

@Data
public class ReservationCompleteCacelled extends AbstractEvent {

    private Long id;
    private Long rsvId;
    private String rsvStatus;
    private Date rsvDate;
    private String rsvPlace;
    private Long itemId;
    private String buyerId;
}
