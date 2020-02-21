package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.world.GameObject;
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
    private GameObject background;

    private ScreenViewport mScreenViewport;
    private LayerViewport mLayerViewport;

    private String playerName;
    private String aiName;

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

        loadAssets();

        mScreenViewport = new ScreenViewport( 0,0, game.getScreenWidth(), game.getScreenHeight());
        mLayerViewport = new LayerViewport(mScreenViewport.centerX(),
                mScreenViewport.centerY(),
                mScreenViewport.width / 2,
                mScreenViewport.height / 2);

        gameBackground = "menuBackground";
        background = new GameObject(game.getScreenWidth() / 2.0f,
                game.getScreenHeight() / 2.0f,
                game.getScreenWidth(),
                game.getScreenHeight(),
                getGame().getAssetManager().getBitmap(gameBackground),
                this);

        addCards("txt/assets/Players.json");

        playerName = "Player1";
        aiName = "Player2";

        playerDeck = new Deck(this);
        aiDeck = new Deck(this);

        humanPlayer = new Player(playerName, playerDeck);
        aiPlayer = new PlayerAI(aiName, aiDeck, this);

        playerScore = 0;
        aiScore = 0;
        roundNum = 1;

        humanPlayer.setEndTurn(false);

    }
    //Pauric Donnelly
    public void addCards(String jsonFilePath)
    {
        FileIO mFileIO = mGame.getFileIO();
        String loadedJSON = "";
        try
        {
            loadedJSON = mFileIO.loadJSON(jsonFilePath);
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx.getMessage());
        }

        try
        {
            JSONObject cards = new JSONObject(loadedJSON);
            addPlayerCards(cards, this);
        }
        catch(JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }
    //Pauric Donnelly
    public void addPlayerCards(JSONObject cards, GameScreen gameScreen)
    {
        try {
            JSONArray players = cards.getJSONArray("players");
            for (int i = 0; i < players.length(); i++) {
                JSONObject player = players.getJSONObject(i);
                PlayerCard playerCard = new PlayerCard(
                        gameScreen,
                        player.getInt("overall"),
                        player.getString("league"),
                        player.getString("team"),
                        player.getString("fname"),
                        player.getString("sname"),
                        player.getString("cardPortrait"));
                mPlayerCards.add(playerCard);
            }
        }
        catch (JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }

    public List<PlayerCard> getPlayerCardList()
    {
        return mPlayerCards;
    }


    public Player getHumanPlayer(){ return humanPlayer; }
    public Player getAiPlayer(){ return aiPlayer; }


    public PlayerCard getRandomPlayerCard(){
        Random random = new Random();
        PlayerCard card = mPlayerCards.get(random.nextInt(AMOUNT_OF_PLAYER_CARDS));

        return card;
    }

    public Card getRandomManagerCard(){
        Random random = new Random();
        Card card = mManagerCards.get(random.nextInt(AMOUNT_OF_MANAGER_CARDS));

        return card;
    }

    //by Bronach
    private void loadAssets(){
        loadBitmaps("menuBackground", "img/menuBackground.png");

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

        Input input = mGame.getInput();
        //Prevents more than 1 card from being dragged (Reference - CardDefence)
        boolean dragged =false;
        for (Card card: getHumanPlayer().getDeck().getCardsInDeck()) {
            if (card.getBeingDragged()) {
                dragged=true;
            }
        }

        // drag card (Reference - Card Defence)
        for (int i = 0; i<getHumanPlayer().getDeck().getCardsInDeck().size(); i++ ){
            Card card = getHumanPlayer().getDeck().getCardsInDeck().get(i);
            if(!card.getPlaced()) {
                if(!dragged || card.getBeingDragged()) {
                    card.update(elapsedTime);
                    if (card.getBeingDragged())
                    {
                        dragged = true;
                    }
                }
            }
            else{
                card.setBeingDragged(false);
            }
        }
    }

    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        background.draw(elapsedTime, graphics2D);

        Input input = this.getGame().getInput();
        int n = -1;
        // draw cards not being dragged first
        for(int i = 0; i< getHumanPlayer().getDeck().getCardsInDeck().size(); i++) {
            if (!getHumanPlayer().getDeck().getCardsInDeck().get(i).getPlaced()) {
                if (!getHumanPlayer().getDeck().getCardsInDeck().get(i).getBeingDragged()) {
                    getHumanPlayer().getDeck().getCardsInDeck().get(i).draw(elapsedTime, graphics2D);
                } else {

                }
            }
        }
    }

}
