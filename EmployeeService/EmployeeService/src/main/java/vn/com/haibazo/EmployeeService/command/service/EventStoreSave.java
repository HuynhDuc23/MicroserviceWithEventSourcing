package vn.com.haibazo.EmployeeService.command.service;

import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventStoreSave {
    @Autowired
    private  EventStore eventStore ;

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
