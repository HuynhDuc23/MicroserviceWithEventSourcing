package vn.com.haibazo.api_gateway.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

@ComponentScan
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Override
    public GatewayFilter apply(AuthFilter.Config config) {
          return (exchange, chain) -> {
              if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                  throw new RuntimeException("Missing authorization information");
              }
              String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
              String[] parts = authHeader.split(" ");
              if(parts.length != 2 || !"Bearer".equals(parts[0])){
                  throw new RuntimeException("Invalid authorization information");
              }
              Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
              JWTVerifier verifier = JWT.require(algorithm).build();
              DecodedJWT decodedJWT = verifier.verify(parts[1]);
              String userName = decodedJWT.getSubject();
              if(userName == "" || userName==null){
                  throw new RuntimeException("Invalid user");
              }
              ServerHttpRequest request = exchange.getRequest().mutate().header("X-auth-username",userName)
                      .build();
              return chain.filter(exchange.mutate().request(request).build());

          };

    }
    private AuthFilter() {
        super(Config.class);
    }
    public static class Config {
        // Add your custom configuration here
    }
}
