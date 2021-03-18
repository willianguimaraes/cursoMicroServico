package br.com.willian.mspayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "ms-worker")
@EnableFeignClients
@SpringBootApplication
public class MsPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPayrollApplication.class, args);
	}

}
