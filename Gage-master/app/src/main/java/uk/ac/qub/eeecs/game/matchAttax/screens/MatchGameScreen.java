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
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.Cards;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;
import uk.ac.qub.eeecs.game.matchAttax.player.Deck;
import uk.ac.qub.eeecs.game.matchAttax.player.Player;
import uk.ac.qub.eeecs.game.matchAttax.player.PlayerAI;

public class MatchGameScreen extends GameScreen {

    private String gameBackground;

    private ScreenViewport mScreenViewport;
    private LayerViewport mLayerViewport;

    private Player humanPlayer;
    private PlayerAI aiPlayer;

    private Deck playerDeck;
    private Deck aiDeck;

    private int playerScore;
    private int aiScore;
    private int roundNum;

    private List<PlayerCard> mPlayerCards;
    private List<ManagerCard> mManagerCards;
    private AssetManager assetManager;

    public static final int AMOUNT_OF_PLAYER_CARDS = 40;
    public static final int AMOUNT_OF_MANAGER_CARDS = 7;

    public int getPlayerScore(){ return playerScore;}
    public int AiScore(){return aiScore;}

    public MatchGameScreen(Game game){
        super("MatchGameScreen", game);

        mScreenViewport = new ScreenViewport( 0,0, game.getScreenWidth(), game.getScreenHeight());
        mLayerViewport = new LayerViewport(mScreenViewport.centerX(), mScreenViewport.centerY(),mScreenViewport.width / 2,mScreenViewport.height / 2);

        setupCards();

        playerDeck = new Deck(this);
        aiDeck = new Deck(this);

        humanPlayer = new Player(playerDeck);
        aiPlayer = new PlayerAI(aiDeck, this);

        playerScore = 0;
        aiScore = 0;
        roundNum = 1;

        humanPlayer.setEndTurn(false);

    }

    public Player getHumanPlayer(){ return humanPlayer; }
    public Player getAiPlayer(){ return aiPlayer; }

    private void setupCards(){
        Bitmap cardPortrait;
        int overallValue;
        String league;
        String club;
        String firstName;
        String surname;
        String imgPath;

        String jsonToBeLoaded = "";
        JSONObject players;
        try {
            jsonToBeLoaded = assetManager.getFileIO().loadJSON("txt/assets/Players.json");
            players = new JSONObject(jsonToBeLoaded);

            mGame.getAssetManager().loadAssets("txt/assets/Players.json");
            for (int idx = 0; idx<AMOUNT_OF_PLAYER_CARDS; idx++){
                if(idx == players.getInt("id"))
                {
                    overallValue = players.getInt("overall");
                    league = players.getString("league");
                    club = players.getString("team");
                    firstName = players.getString("fname");
                    surname = players.getString("sname");
                    imgPath = players.getString("portrait");
                    loadBitmaps("player" + idx, imgPath);

                    cardPortrait = assetManager.getBitmap("player" + idx);
                    PlayerCard cardToAdd = new PlayerCard(this, overallValue, league, club, firstName, surname, cardPortrait);
                    mPlayerCards.add(cardToAdd);
                    }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public Card getRandomPlayerCard(){
        Random random = new Random();
        Card card = mPlayerCards.get(random.nextInt(AMOUNT_OF_PLAYER_CARDS));

        return card;
    }

    public Card getRandomManagerCard(){
        Random random = new Random();
        Card card = mManagerCards.get(random.nextInt(AMOUNT_OF_MANAGER_CARDS));

        return card;
    }

    private void loadAssets(){
        loadMusic("ChelseaDagger", "sound/ChelseaDagger.mp3");
        loadMusic("FluorescentAdolescent", "sound/FluorescentAdolescent.mp3");
        loadMusic("SevenNationArmy", "sound/SevenNationArmy.mp3");
        loadMusic("WavinFlag", "sound/WavinFlag.mp3");
        loadMusic("WhatYouKnow", "sound/WhatYouKnow.mp3");
        loadMusic("GuitarMusic", "sound/GuitarMusic.mp3");
    }

    private void loadMusic(String assetName, String fileName){
        getGame().getAssetManager().loadAndAddMusic(assetName, fileName);
    }

    private void loadBitmaps(String assetName, String fileName){
        getGame().getAssetManager().loadAndAddBitmap(assetName, fileName);
    }

    public void update(ElapsedTime elapsedTime){

    }

    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){

    }

}
