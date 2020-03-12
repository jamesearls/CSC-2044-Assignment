package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;

public class PlayerCard extends Card{

    private String league;
    private String club;

    public PlayerCard(GameScreen gameScreen, double overallValue, String league, String club, String firstName, String surname, String cardPortraitPath){
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

    @Override
    public void update(ElapsedTime elapsedTime){
        super.update(elapsedTime);
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        super.draw(elapsedTime, graphics2D);

    }

}
