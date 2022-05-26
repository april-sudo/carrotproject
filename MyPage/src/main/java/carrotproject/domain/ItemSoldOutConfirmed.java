package carrotproject.domain;

import carrotproject.infra.AbstractEvent;
import lombok.Data;
import java.util.Date;

@Data
public class ItemSoldOutConfirmed extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String name;
    private String category;
    private Integer price;
    private String description;
    private String status;
    private Integer score;
}
