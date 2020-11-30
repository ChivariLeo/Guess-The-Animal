package leo.game.console;


import leo.game.Game;
import leo.game.MessageGenerator;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class Console {


    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == constructor ==

    public Console(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        Scanner scanner =new Scanner(System.in);
        while (game.getRound()<=game.getAnimalName().length){
            System.out.println(messageGenerator.getMainMessage());

                String guess = scanner.next();
                scanner.nextLine();
                game.setGuess(guess);

                game.check();

            System.out.println(messageGenerator.getResultMessage());

            if(game.isGameOver())
            {
                System.out.println("Play again y/n?");
                String playAgain=scanner.nextLine().trim();
                if(!playAgain.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
