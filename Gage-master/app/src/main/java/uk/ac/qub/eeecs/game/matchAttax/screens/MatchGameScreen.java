package uk.ac.qub.eeecs.game.matchAttax.screens;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.Game;

public class MatchGameScreen extends GameScreen {

    private int playerScore;
    private int opponentScore;

    public int getPlayerScore(){ return playerScore;}
    public int getOpponentScore(){return opponentScore;}

    public MatchGameScreen(Game game){
        super("MatchGameScreen", game);

    }

    public void update(ElapsedTime elapsedTime){

    }

    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){

    }

}
