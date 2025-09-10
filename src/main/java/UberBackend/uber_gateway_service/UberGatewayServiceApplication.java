package UberBackend.uber_gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UberGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberGatewayServiceApplication.class, args);
	}

}
