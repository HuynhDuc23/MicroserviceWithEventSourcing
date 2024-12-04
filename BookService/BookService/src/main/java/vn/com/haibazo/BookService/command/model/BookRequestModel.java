package vn.com.haibazo.BookService.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id ;
    @NotBlank(message = "please enter name ...")
    @Size(min = 2 , max = 10)
    private String name ;
    @Size(min = 2 , max = 10)
    private String author ;
    private Boolean isReady;
}
