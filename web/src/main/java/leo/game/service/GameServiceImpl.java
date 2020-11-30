package leo.game.service;

import leo.game.Game;
import leo.game.MessageGenerator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class GameServiceImpl implements GameService {


    private final Game game;
    private final MessageGenerator messageGenerator;


    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @PostConstruct
    public void init(){
        log.info("animalName = {}",game.getAnimalName()[game.getRound()]);
        log.info("mainMessage = {}",messageGenerator.getMainMessage());
    }

    @Override
    public boolean isGameOver() {
        return game.isGameOver();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(String guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }





}
