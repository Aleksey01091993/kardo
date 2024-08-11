package com.kardoaward.mobileapp;

import com.kardoaward.mobileapp.files.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class MobileappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileappApplication.class, args);
	}

}
