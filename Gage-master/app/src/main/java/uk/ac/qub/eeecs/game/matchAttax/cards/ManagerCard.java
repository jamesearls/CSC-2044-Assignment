package uk.ac.qub.eeecs.game.matchAttax.cards;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameScreen;

//David Mackenzie 40238376
public class ManagerCard extends Card {

    private boolean type; //Addition card or a multiplier (true = add // false = multiply)

    public ManagerCard(GameScreen gameScreen, int overallValue, String firstName, String surname, boolean type, String cardPortraitPath) {
        super(gameScreen, overallValue, firstName, surname, cardPortraitPath);
        this.type = type;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
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