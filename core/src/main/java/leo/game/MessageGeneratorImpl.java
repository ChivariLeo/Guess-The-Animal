package leo.game;


import org.springframework.stereotype.Component;


@Component
public class MessageGeneratorImpl implements MessageGenerator {


    private final Game game;

    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }


    @Override
    public String getMainMessage() {
        String message = "";
        System.out.println();
        for (int i = 0; i < game.getAnimalDescription().length; i++) {
            if (game.getRound() == i)
                message = game.getAnimalDescription()[i];
        }
        return message;
    }

    @Override
    public String getResultMessage() {
        if (game.isGameOver()) {
            return " Nice! You got  " + game.getPoints() + " points out of "+ game.getAnimalName().length ;
        }else if (!game.isValidString()) {
            return "Invalid string. Must be at least 2 characters " + "\n! You have " + game.getRemainingGuesses() + " guesses left";
        }else if (game.isRoundWon()) {
            return "You guessed it! The animal is " + game.getAnimalName()[game.getRound()==0 ? game.getRound():game.getRound()-1];
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "Can you guess the animal?";
        } else {
            char hint = 'b';
            char hint2 = 'e';

            if (!game.getGuess().equals(game.getAnimalName()[game.getRound()])) {
                hint = game.getAnimalName()[game.getRound()].charAt(0);
                hint2 = game.getAnimalName()[game.getRound()].toUpperCase().charAt(game.getAnimalName()[game.getRound()].length() - 1);
            }
            return "Animal name begins with the letter: " + hint + " and ends with: " + hint2 + "\n! You have " + game.getRemainingGuesses() + " guesses left";
        }
    }
}
