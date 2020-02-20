package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameScreen;

public class PlayerCard{

    private String league;
    private String club;
    private String firstName;
    private String surname;
    private int overallValue;
    private String cardPortraitPath;

    public PlayerCard(int overallValue, String league, String club, String firstName, String surname, String cardPortraitPath){
        this.overallValue = overallValue;
        this.league = league;
        this.club = club;
        this.firstName = firstName;
        this.surname = surname;
        this.cardPortraitPath = cardPortraitPath;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getOverallValue() {
        return overallValue;
    }

    public void setOverallValue(int overallValue) {
        this.overallValue = overallValue;
    }

    public String getCardPortraitPath() {
        return cardPortraitPath;
    }

    public void setCardPortraitPath(String cardPortraitPath) {
        this.cardPortraitPath = cardPortraitPath;
    }
}
