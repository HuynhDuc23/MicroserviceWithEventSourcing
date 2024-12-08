package vn.com.haibazo.BorrowwingService.command.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import vn.com.haibazo.BorrowwingService.command.command.CreateBorrowingCommand;
import vn.com.haibazo.BorrowwingService.command.command.DeleteBorrowingCommand;
import vn.com.haibazo.BorrowwingService.command.event.BorrowingCreatedEvent;
import vn.com.haibazo.BorrowwingService.command.event.BorrowingDeletedEvent;
import vn.com.haibazo.Commonservice.command.UpdateStatusBookCommand;

import java.util.Date;

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowingAggregate {
   @AggregateIdentifier
    private String id ;
    private String bookId ;
    private String employeeId ;
    private Date borrowingDate ;
    private Date returnDate ;

    @CommandHandler
    public BorrowingAggregate (CreateBorrowingCommand command){
      BorrowingCreatedEvent event= new BorrowingCreatedEvent();
     BeanUtils.copyProperties(command,event);
     AggregateLifecycle.apply(event); // tao event tu command
    }




    // xu ly event
    @EventSourcingHandler
    public void on(BorrowingCreatedEvent event){
     this.id = event.getId();
     this.bookId = event.getBookId();
     this.employeeId = event.getEmployeeId();
     this.borrowingDate = event.getBorrowingDate();
     // tai day no se cap lai  trang thai event -> tren code gio can cap nhat vo csdl
    }

 @CommandHandler
 public void handler(DeleteBorrowingCommand command){
  BorrowingDeletedEvent event = new BorrowingDeletedEvent() ;
  BeanUtils.copyProperties(command , event);
  AggregateLifecycle.apply(event); // tao event tu command
 }
 @EventSourcingHandler
 public void on (BorrowingDeletedEvent event){
     this.id = event.getId();
     // tai day no se cap lai  trang thai event -> tren code gio can cap nhat vo csdl
 }
}
