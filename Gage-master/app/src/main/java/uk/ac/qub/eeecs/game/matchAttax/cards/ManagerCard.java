package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameScreen;

public class ManagerCard extends Card {

    private final int amountOfCardsInDatabase = 7;
    private Bitmap cardPortrait;
    private int overallValue;
    private String firstName;
    private String surname;
    private boolean type; //Addition card or a multiplier (true = add // false = multiply)
    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];

    public ManagerCard(GameScreen gameScreen, int overallValue, String firstName, String surname, Bitmap cardPortrait){
        super(gameScreen, overallValue, firstName, surname, cardPortrait);


    }
}
