package com.arepresas.synchrobank;

import org.springframework.boot.SpringApplication;

public class TestSynchroBankApplication {

	public static void main(String[] args) {
		SpringApplication.from(SynchroBankApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
