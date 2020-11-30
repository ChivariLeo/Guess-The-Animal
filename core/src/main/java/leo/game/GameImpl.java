package leo.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@Getter
@Component
public class GameImpl implements Game {


    @Getter(AccessLevel.NONE)
    private final AnimalRepository animalRepository;
    private final int guessCount;
    @Setter
    private String guess;
    private int remainingGuesses;
    private boolean validString = false;
    private int round;
    private int points;
    private boolean validName = false;

    @Autowired
    public GameImpl(AnimalRepository animalRepository, @GuessCount int guessCount) {
        this.animalRepository = animalRepository;
        this.guessCount = guessCount;
    }

    //== init || reset ==
    @PostConstruct
    @Override
    public void reset() {
        guess = "test";
        points = 0;
        round = 0;
        remainingGuesses = guessCount;
    }

    @Override
    public void check() {
        if (isValidString()) {
            if (guessSimilarityIndex(guess, getAnimalName()[round]) <= 3){
                    validName = true;
                    round++;
                    points++;
            } else
                    validName = false;
        }
            remainingGuesses--;
    }

    @Override
    public boolean isRoundWon() {
        if (validName)
            return getAnimalDescription()[round].equals(animalRepository.animalDescriptions()[round]);
        return false;
    }

    @Override
    public boolean isGameOver() {
        return points == getAnimalName().length || remainingGuesses <= 0;
    }

    @Override
    public String[] getAnimalDescription() {
        return animalRepository.animalDescriptions();
    }

    @Override
    public String[] getAnimalName() {
        return animalRepository.animalNames();

    }

    public boolean isValidString() {
        validString = !guess.isEmpty() && guess.length() >= 2;
        return validString;
    }

    public int getRound() {
        return round;
    }



    public int guessSimilarityIndex(String guess, String animalName) {
        int[][] dp = new int[guess.length() + 1][animalName.length() + 1];

        for (int i = 0; i <= guess.length(); i++) {
            for (int j = 0; j <= animalName.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(guess.charAt(i - 1), animalName.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[guess.length()][animalName.length()];
    }



    private  int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private  int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

}
