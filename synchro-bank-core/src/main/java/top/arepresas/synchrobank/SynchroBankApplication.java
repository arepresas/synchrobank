package top.arepresas.synchrobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SynchroBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynchroBankApplication.class, args);
	}

}
