package vn.com.haibazo.EmployeeService.command.service;

import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.TrackingEventStream;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.EmployeeService.command.aggregate.EmployeeAggregate;
import vn.com.haibazo.EmployeeService.command.event.EmployeeCreatedEvent;

import java.util.stream.Collectors;

@Component
public class EmployeeService {
    @Autowired
    private EventStore eventStore ;

    public EmployeeAggregate restoreProductToCreationState(String id){
        // Đọc tất cả các sự kiện từ EventStore voi Id truyen vao
        DomainEventStream eventStream = eventStore.readEvents(id);

        // Lọc các sự kiện cần thiết (ở đây là EmployeeCreatedEvent)
        var eventsToReapply = eventStream.asStream()
                .filter(event -> event.getPayload() instanceof EmployeeCreatedEvent)
                .toList();

        // Tạo một đối tượng aggregate mới
        EmployeeAggregate aggregate = new EmployeeAggregate();

        // Áp dụng các sự kiện cần thiết vào aggregate
        for (var event : eventsToReapply) {
            aggregate.on((EmployeeCreatedEvent) event.getPayload());
        }

        return aggregate;
    }
}
