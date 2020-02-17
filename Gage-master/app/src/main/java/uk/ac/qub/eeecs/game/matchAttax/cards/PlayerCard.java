package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameScreen;

public class PlayerCard extends Card {

    private int overallValue;
    private String league;
    private String club;

    public PlayerCard(GameScreen gameScreen, int overallValue, String league, String club, String firstName, String surname, Bitmap cardPortrait){
        super(gameScreen, firstName, surname, cardPortrait);
    }
}
