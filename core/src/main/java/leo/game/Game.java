package leo.game;

public interface Game {
        String[] getAnimalName();
        String[] getAnimalDescription();
        String getGuess();
        int getRemainingGuesses();
        int getGuessCount();
        void reset();
        void check();
        boolean isValidString();
        boolean isRoundWon();
        boolean isGameOver();
        void setGuess(String guess);
        int getRound();
        int getPoints();
        int guessSimilarityIndex(String guess, String animalName);
}
