package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameScreen;

public class PlayerCard extends Card{

    private String league;
    private String club;

    public PlayerCard(GameScreen gameScreen, int overallValue, String league, String club, String firstName, String surname, String cardPortraitPath){
        super(gameScreen, overallValue, firstName, surname, cardPortraitPath);
        this.league = league;
        this.club = club;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

}
