package vn.com.haibazo.BorrowwingService.command.event;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.BorrowwingService.command.data.Borrowing;
import vn.com.haibazo.BorrowwingService.command.data.BorrowingRepository;

@Slf4j
@Component
public class BorrowingEventsHandler {
    @Autowired
    private BorrowingRepository borrowingRepository ;

    @EventHandler
    public void handler(BorrowingCreatedEvent borrowingCreatedEvent){
        Borrowing model = new Borrowing();
        model.setId(borrowingCreatedEvent.getId());
        model.setBookId(borrowingCreatedEvent.getBookId());
        model.setEmployeeId(borrowingCreatedEvent.getEmployeeId());
        model.setBorrowingDate(borrowingCreatedEvent.getBorrowingDate());
        this.borrowingRepository.save(model);
        System.out.println("created");
    }
    @EventHandler
    public void handler(BorrowingDeletedEvent event){
        this.borrowingRepository.deleteById(event.getId());
        System.out.println("deleted");
    }

}
