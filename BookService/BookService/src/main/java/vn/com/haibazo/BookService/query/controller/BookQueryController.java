package vn.com.haibazo.BookService.query.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.haibazo.BookService.query.model.BookResponseModel;
import vn.com.haibazo.BookService.query.queries.GetAllBookQuery;
import vn.com.haibazo.BookService.query.queries.GetBookDetailQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name="Book Query")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;
    @GetMapping
    @Operation(
            summary = "Get all books",
            description = "Returns a list of all books."
    )
    public List<BookResponseModel> getAllBooks() {
        GetAllBookQuery getAllBookQuery = new GetAllBookQuery();
        //        CompletableFuture<List<BookResponseModel>> bookFuture=queryGateway.query(getAllBookQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class));
        // lang nghe ket qua tra ve khong nhat thiet phai tra ve ngay lap tuc
        return queryGateway.query(getAllBookQuery,ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        // dung join de cho cho nhan ket qua chu khong dung bat dong bo
        // tai day da dispatch query

    }
    @GetMapping("/{bookId}")
    @Operation(
            summary = "Get book by ID",
            description = "Returns a book by ID."
    )
    public BookResponseModel getBookById(@PathVariable String bookId) {
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
        // tai day tao dispatch
    }
}
