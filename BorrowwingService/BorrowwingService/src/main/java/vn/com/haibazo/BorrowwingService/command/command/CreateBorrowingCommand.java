package vn.com.haibazo.BorrowwingService.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBorrowingCommand {
    @TargetAggregateIdentifier
    private String id ;
    private String bookId ;
    private String employeeId ;
    private Date borrowingDate ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
