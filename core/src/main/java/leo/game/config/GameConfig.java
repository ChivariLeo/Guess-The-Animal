package leo.game.config;

import leo.game.GuessCount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages ="leo.game")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.guessCount}")
    private int guessCount;

    // == bean methods ==
    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }


}
