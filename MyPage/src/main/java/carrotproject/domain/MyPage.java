package carrotproject.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="MyPage_table")
@Data
public class MyPage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long itemId;
        private String itemName;
        private String itemStatus;
        private Long rsvId;
        private String rsvStatus;
        private Long payId;
        private String payStatus;


}