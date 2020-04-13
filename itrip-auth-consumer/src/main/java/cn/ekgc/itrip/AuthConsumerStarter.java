package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//`#registerFeignClients` -- 注册各个`feign`客户端及其配置
@EnableFeignClients
//@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。
@EnableEurekaClient
@SpringBootApplication
public class AuthConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(AuthConsumerStarter.class,args);
	}
}
