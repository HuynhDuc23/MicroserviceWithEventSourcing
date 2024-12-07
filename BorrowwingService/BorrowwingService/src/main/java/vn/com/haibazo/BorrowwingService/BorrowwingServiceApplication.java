package vn.com.haibazo.BorrowwingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"vn.com.haibazo.BorrowwingService","vn.com.haibazo.Commonservice"})
public class BorrowwingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowwingServiceApplication.class, args);
	}

}
