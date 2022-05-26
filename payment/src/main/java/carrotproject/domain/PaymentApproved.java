package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class PaymentApproved extends AbstractEvent {

    private Long payId;
    private Long rsvId;
    private Long itemId;
    private String status;
    private Integer price;

    public PaymentApproved(){
        super();
    }
}
