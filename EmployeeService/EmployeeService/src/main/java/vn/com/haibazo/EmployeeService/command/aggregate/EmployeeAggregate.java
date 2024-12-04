package vn.com.haibazo.EmployeeService.command.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import vn.com.haibazo.EmployeeService.command.command.CreateEmployeeCommand;
import vn.com.haibazo.EmployeeService.command.command.DeleteEmployeeCommand;
import vn.com.haibazo.EmployeeService.command.command.UpdateEmployeeCommand;
import vn.com.haibazo.EmployeeService.command.event.EmployeeCreatedEvent;
import vn.com.haibazo.EmployeeService.command.event.EmployeeDeleteEvent;
import vn.com.haibazo.EmployeeService.command.event.EmployeeUpdatedEvent;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    private String id ;
    private String firstName ;
    private String lastName ;
    private String Kin ;
    private Boolean isDisciplined ;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command){
        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command , event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateEmployeeCommand command){
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on (EmployeeUpdatedEvent event){
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.Kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }


    @EventSourcingHandler
    public void on (EmployeeCreatedEvent event){
        // tại đây ập nhật lại trạng thái của aggregate , và lưu event vào trong event store
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.Kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();

        System.out.println(firstName);
    }
    @CommandHandler
    public void handle(DeleteEmployeeCommand command){
        EmployeeDeleteEvent event = new EmployeeDeleteEvent() ;
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on (EmployeeDeleteEvent event){
        this.id = event.getId();
    }


}
