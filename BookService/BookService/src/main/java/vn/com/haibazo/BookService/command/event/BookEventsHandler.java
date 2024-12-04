package vn.com.haibazo.BookService.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.BookService.command.data.Book;
import vn.com.haibazo.BookService.command.data.BookRepository;

import java.util.Optional;

@Component
public class BookEventsHandler {
    @Autowired
    private BookRepository bookRepository ;
    @EventHandler
    public void on(BookCreatedEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        this.bookRepository.save(book);
    }
    @EventHandler
    public void on(BookUpdatedEvent event){
        Optional<Book> oldBook = this.bookRepository.findById(event.getId());
        if(oldBook.isPresent()){
            Book book = oldBook.get();
            book.setAuthor(event.getAuthor());
            book.setIsReady(event.getIsReady());
            book.setId(event.getId());
            book.setName(event.getName());
            bookRepository.save(book);
        }
    }
    @EventHandler
    public void on(BookDeletedEvent event){
        Optional<Book> oldBook = this.bookRepository.findById(event.getId());
//        if(oldBook.isPresent()){
//            this.bookRepository.deleteById(event.getId());
//        }
        oldBook.ifPresent(books->this.bookRepository.deleteById(event.getId()));
    }
}
