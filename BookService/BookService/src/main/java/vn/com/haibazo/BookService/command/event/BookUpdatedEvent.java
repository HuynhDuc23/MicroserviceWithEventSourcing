package vn.com.haibazo.BookService.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdatedEvent {
    @TargetAggregateIdentifier
    private String id ;
    private String name ;
    private String author ;
    private Boolean isReady;
}
