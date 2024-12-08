package vn.com.haibazo.BorrowwingService.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowingDeletedEvent {
    private String id ;
}
