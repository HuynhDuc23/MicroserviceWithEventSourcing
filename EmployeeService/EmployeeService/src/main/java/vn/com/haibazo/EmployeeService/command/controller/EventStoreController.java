package vn.com.haibazo.EmployeeService.command.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.haibazo.EmployeeService.command.service.EventStoreSave;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees/event")
@Tag(name = "Event Store ")
public class EventStoreController {
    @Autowired
    private EventStoreSave eventStoreSave ;
    @Operation(
            summary = "Get all events for employee",
            description = "Returns all events for the given employee."
    )
    @GetMapping("/{id}")
    public List<Object> getEventsForProduct(@PathVariable String id){
        return this.eventStoreSave.getAllEvents(id);
    }
}
