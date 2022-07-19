package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static sample.Out.printOut;
 
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        printOut();
        SpringApplication.run(Main.class);
    }
}
