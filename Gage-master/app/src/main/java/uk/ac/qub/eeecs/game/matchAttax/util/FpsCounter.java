package uk.ac.qub.eeecs.game.matchAttax.util;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.matchAttax.font.Font;

//James Earls
public class FpsCounter {

    private Game mGame;
    private Font fpsFont;

public FpsCounter(Game game, GameScreen gameScreen) {
    mGame = game;
    fpsFont = new Font(gameScreen);

}

    private Game getGame() {
        return mGame;
    }

    //Method that actually gets the fps and draws it to the screen
    public void drawFPS(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        int fpsInt = (int) mGame.getAverageFramesPerSecond();
        String fps = Integer.toString(fpsInt);
        fpsFont.drawText("FPS: " + fps, "Hemi Head Bold Italic", 72, "Black", (getGame().getScreenWidth() / 1.15f), getGame().getScreenHeight() / 11f, elapsedTime, graphics2D);
    }


}
