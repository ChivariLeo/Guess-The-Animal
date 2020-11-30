package leo.game.service;

public interface GameService {
    boolean isGameOver();
    String getMainMessage();
    String getResultMessage();
    void checkGuess(String guess);
    void reset();



}
