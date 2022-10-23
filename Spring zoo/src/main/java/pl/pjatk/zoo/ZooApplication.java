package pl.pjatk.zoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ZooApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
    }

}
//@RestController @Service i Wstrzykiwanie zależności
//localhost:8080/zoo/example
//Czym jest repository, jak zrobic, jak sciagac dane do bazy danych etc
//