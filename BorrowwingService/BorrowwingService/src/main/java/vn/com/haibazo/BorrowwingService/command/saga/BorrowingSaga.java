package vn.com.haibazo.BorrowwingService.command.saga;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import vn.com.haibazo.BorrowwingService.command.command.DeleteBorrowingCommand;
import vn.com.haibazo.BorrowwingService.command.event.BorrowingCreatedEvent;
import vn.com.haibazo.Commonservice.command.UpdateStatusBookCommand;
import vn.com.haibazo.Commonservice.event.UpdateStatusBookEvent;
import vn.com.haibazo.Commonservice.model.BookResponseCommonModel;
import vn.com.haibazo.Commonservice.queries.GetBookDetailQuery;

@Slf4j
@Saga
public class BorrowingSaga {
    @Autowired
    private transient CommandGateway commandGateway ;
    @Autowired
    private transient QueryGateway queryGateway ;

    @StartSaga // bat dau hanh trinh voi su kien + saga
    @SagaEventHandler(associationProperty = "id") // lien ket id nay voi id trong borrowingCratedEvent la duy nhat
    private void handle(BorrowingCreatedEvent event){
        System.out.println("Start saga" +  event.getId() + "employment" + event.getEmployeeId());
        try {
            GetBookDetailQuery getBookDetailQuery = new GetBookDetailQuery(event.getBookId());
            BookResponseCommonModel bookResponseCommonModel = queryGateway.query(getBookDetailQuery, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();

            if(!bookResponseCommonModel.getIsReady()){
                throw new Exception("BookResponseCommonModel is not ready");
            }else {
                // san san cho muon
                SagaLifecycle.associateWith("bookId", event.getBookId());
                UpdateStatusBookCommand command = new UpdateStatusBookCommand();
                command.setBookId(event.getBookId());
                command.setIsReady(false);
                command.setEmployeeId(event.getEmployeeId());
                command.setBorrowingId(event.getId());
                commandGateway.sendAndWait(command);
            }
        }catch(Exception e){
            // tai day can xoa record khi no da luu vao db
            rollbackBorrowingRecord(event.getId());
            System.out.println(e.getMessage());
        }
    }

    private void rollbackBorrowingRecord(String id) {
        DeleteBorrowingCommand command = new DeleteBorrowingCommand() ;
        command.setId(id);
        commandGateway.sendAndWait(command);
        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "bookId")
    public void handler(UpdateStatusBookEvent event){
        System.out.println("BookUpdatedStatusEvent in Saga for BookId " + event.getBookId());
        SagaLifecycle.end();
    }
}
