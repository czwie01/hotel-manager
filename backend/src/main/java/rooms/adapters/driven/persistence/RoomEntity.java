package rooms.adapters.driven.persistence;

import rooms.domain.model.RoomType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "rooms_id_seq", allocationSize = 1, initialValue = 1_000)
    private Long roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private boolean hasMiniBar;

}
