package mrs.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReservableRoomId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8065580893988778152L;

    private Integer roomId;

    private LocalDate reservedDate;

}
