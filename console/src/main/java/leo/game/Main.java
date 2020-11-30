package leo.game;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println(" == WELCOME TO THE GUESSING GAME  == ");
        SpringApplication.run(Main.class,args);

    }
}
