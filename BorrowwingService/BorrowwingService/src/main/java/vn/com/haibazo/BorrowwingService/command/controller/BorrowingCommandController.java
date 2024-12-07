package vn.com.haibazo.BorrowwingService.command.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.haibazo.BorrowwingService.command.command.CreateBorrowingCommand;
import vn.com.haibazo.BorrowwingService.command.model.BorrowingCreatedRequestModel;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingCommandController {
    @Autowired
    private CommandGateway commandGateway ;
    /**
     * createBorrowing this return string
     * @param model this request dto
     *
     * */
    @PostMapping
    public String createBorrowing(@RequestBody BorrowingCreatedRequestModel model){
        CreateBorrowingCommand command = new CreateBorrowingCommand();
        command.setId(UUID.randomUUID().toString());
        command.setBookId(model.getBookId());
        command.setEmployeeId(model.getEmployeeId());
        command.setBorrowingDate(new Date());
        return this.commandGateway.sendAndWait(command);
    }

}
