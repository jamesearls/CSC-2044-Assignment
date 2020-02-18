package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.Cards;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;

public class MatchGameScreen extends GameScreen {

    private int playerScore;
    private int opponentScore;

    private List<Card> mPlayerCards;
    private AssetManager assetManager;

    public static final int AMOUNT_OF_CARDS_IN_DATABASE = 40;
    private Bitmap cardPortrait;
    private int overallValue;
    private String league;
    private String club;
    private String firstName;
    private String surname;

    public int getPlayerScore(){ return playerScore;}
    public int getOpponentScore(){return opponentScore;}

    public MatchGameScreen(Game game){
        super("MatchGameScreen", game);

        setupCards();

    }

    private void setupCards(){
        String jsonToBeLoaded = "";
        JSONObject players;
        try {
            jsonToBeLoaded = assetManager.getFileIO().loadJSON("txt/assets/Players.json");
            players = new JSONObject(jsonToBeLoaded);

            mGame.getAssetManager().loadAssets("txt/assets/Players.json");
            for (int idx = 0; idx<AMOUNT_OF_CARDS_IN_DATABASE; idx++){
                if(idx == players.getInt("id"))
                {
                    overallValue = players.getInt("overall");
                    league = players.getString("league");
                    club = players.getString("team");
                    firstName = players.getString("fname");
                    surname = players.getString("sname");
                    Card cardToAdd = new PlayerCard(this, overallValue, league, club, firstName, surname, cardPortrait);
                    mPlayerCards.add(cardToAdd);
                    }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public Card getRandomCard(){
        Random random = new Random();
        Card card = mPlayerCards.get(random.nextInt(40));

        return card;
    }

    public void update(ElapsedTime elapsedTime){

    }

    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){

    }

}
