package vn.com.haibazo.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	// chỗ này có tác dụng để phân biệt giữa 2 user khi đến web -> sử dụng tên host , phân biệt người dùng
	@Bean
	public KeyResolver customKeyResolver() {
		return exchange-> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}

}
