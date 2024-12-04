package vn.com.haibazo.BookService.query.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.BookService.command.data.Book;
import vn.com.haibazo.BookService.command.data.BookRepository;
import vn.com.haibazo.BookService.query.model.BookResponseModel;
import vn.com.haibazo.BookService.query.queries.GetAllBookQuery;
import vn.com.haibazo.BookService.query.queries.GetBookDetailQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository ;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
       // khi co mot dispatch query -> no se chay then vao day
        List<Book> list = bookRepository.findAll();
        return list.stream().map(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book,bookResponseModel);
            return bookResponseModel;
        }).toList();
    }
    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query) throws Exception {
        BookResponseModel bookResponseModel = new BookResponseModel();
        Book book = this.bookRepository.findById(query.getId()).orElseThrow(() -> new Exception("Not Found"));
        BeanUtils.copyProperties(book,bookResponseModel);
        return bookResponseModel;
    }

}
