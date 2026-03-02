package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private Long carId;
    private Long customerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private LocalDateTime createdAt;

    /* sa vad de ce imi pare null la invoice la amount sa rezolv problema
    * sa incep sa lucrez la generarea pdf-ului
    * sa revizuiesc logica din spatele tuturor entitatilor si claselor care sunt sa vad daca sunt totate corect!!
    *
    */
}
