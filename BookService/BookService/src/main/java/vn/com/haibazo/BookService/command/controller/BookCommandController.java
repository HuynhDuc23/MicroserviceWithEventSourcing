package vn.com.haibazo.BookService.command.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.haibazo.BookService.command.command.CreateBookCommand;
import vn.com.haibazo.BookService.command.command.DeleteBookCommand;
import vn.com.haibazo.BookService.command.command.UpdateBookCommand;
import vn.com.haibazo.BookService.command.model.BookRequestModel;
import vn.com.haibazo.Commonservice.services.KafkaService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name="Command Gateway")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway ;

    @Autowired
    private KafkaService kafkaService ;


    @PostMapping
    @Operation(
            summary = "Add Book",
            description = "Them mot book moi vao microservice with eventSourcing"
    )
    public String addBook(@Valid  @RequestBody BookRequestModel bookRequestModel){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), bookRequestModel.getName(), bookRequestModel.getAuthor() , true);
        return commandGateway.sendAndWait(command);
    }
    @PutMapping("/{bookId}")
    @Operation(
            summary = "Update Book",
            description = "Update Book With microservice with eventSourcing "
    )
    public String updateBook(@RequestBody BookRequestModel model , @PathVariable String bookId){
        UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
        // phat ra command
        return commandGateway.sendAndWait(command);
    }
    @DeleteMapping("/{bookId}")
    @Hidden
    @Operation(
            summary = "Delete Book With microservice with eventSourcing",
            description = "Xoa mot book theo id"
    )
    public String deletedBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        // phat ra command
        return commandGateway.sendAndWait(command);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message){
         kafkaService.sendMessage("test",message);
    }
}
