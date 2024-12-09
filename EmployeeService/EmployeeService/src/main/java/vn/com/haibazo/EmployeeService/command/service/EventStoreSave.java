package vn.com.haibazo.EmployeeService.command.service;
import io.swagger.v3.oas.annotations.Operation;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventStoreSave {
    @Autowired
    private  EventStore eventStore ;

    @Operation(
            summary = "get all event in event store",
            description = "Get all events in event store by Domain EventStream"
    )
    public List<Object> getAllEvents(String Id) {
        List<Object> events = new ArrayList<>();
        DomainEventStream eventStream = eventStore.readEvents(Id);
        while (eventStream.hasNext()) {
            DomainEventMessage<?> eventMessage = eventStream.next();
            events.add(eventMessage.getPayload());
        }
        return events;
    }
}
