package vn.com.haibazo.BorrowwingService.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBorrowingCommand {
    @TargetAggregateIdentifier
    private String id ;
    private String bookId ;
    private String employeeId ;
    private Date borrowingDate ;
}
