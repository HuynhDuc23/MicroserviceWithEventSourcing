package vn.com.haibazo.BookService.command.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import vn.com.haibazo.BookService.command.command.CreateBookCommand;
import vn.com.haibazo.BookService.command.command.DeleteBookCommand;
import vn.com.haibazo.BookService.command.command.UpdateBookCommand;
import vn.com.haibazo.BookService.command.event.BookCreatedEvent;
import vn.com.haibazo.BookService.command.event.BookDeletedEvent;
import vn.com.haibazo.BookService.command.event.BookUpdatedEvent;

@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id ;
    private String name ;
    private String author ;
    private Boolean isReady;
    @CommandHandler
    public BookAggregate(CreateBookCommand command){
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(command,bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }


    /**
     * this is method return void
     * @param command this is the command
     */
    @CommandHandler
    public void handle(UpdateBookCommand command){
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(command,bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }
    @CommandHandler
    public void handle(DeleteBookCommand command){
        BookDeletedEvent event = new BookDeletedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event); // apply event vao
    }

    @EventSourcingHandler // sau khi da apply command thanh event tai day thuc su se xu ly event -> dispatch 2 event
    public void on(BookCreatedEvent event){
        // tai day thay doi trang thai
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookUpdatedEvent event){
        // tai day toi se thay doi trang thai
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookDeletedEvent event){
        // cap nhat trang thai khi da event
        this.id = event.getId();
    }

}
