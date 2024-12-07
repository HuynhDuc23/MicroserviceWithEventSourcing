package vn.com.haibazo.BorrowwingService.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingCreatedRequestModel {
    private String bookId ;
    private String employeeId ;
    private Date borrowingDate ;
}
