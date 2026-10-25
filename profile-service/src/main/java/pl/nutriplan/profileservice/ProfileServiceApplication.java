package pl.nutriplan.profileservice;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class ProfileServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);

    }
}
