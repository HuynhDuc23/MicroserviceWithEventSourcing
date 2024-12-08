package vn.com.haibazo.Commonservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusBookEvent {
    private String bookId ;
    private Boolean isReady;
    private String employeeId ;
    private String borrowingId;
}
