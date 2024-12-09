package vn.com.haibazo.EmployeeService.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service API With Microservice EventSourcing",
                version = "1.0",
                description = "API for document employees - https://springdoc.org/",
                contact = @Contact(
                        name = "Tran Vu Huynh Duc",
                        email = "ductrantad23@gmail.com",
                        url = "null"
                ),
                license = @License( // ban quyen
                        name = "Tran Vu Huynh Duc",
                        url = "null"
                ),
                // dieu khoan
                termsOfService = "null"
        ),
        servers =
                {@Server(
                          description = "LOCAL ENV",
                           url = "http://localhost:8081"
        ),
                 @Server(
                         description = "DEV ENV",
                         url = "http://localhost:8081"
                 )
        }
)
public class OpenApiConfig {
}
