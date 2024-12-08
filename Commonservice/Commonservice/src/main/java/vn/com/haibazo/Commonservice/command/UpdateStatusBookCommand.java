package vn.com.haibazo.Commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusBookCommand {
   @TargetAggregateIdentifier
   private String bookId ;
   private Boolean isReady;
   private String employeeId ;
   private String borrowingId;
}
