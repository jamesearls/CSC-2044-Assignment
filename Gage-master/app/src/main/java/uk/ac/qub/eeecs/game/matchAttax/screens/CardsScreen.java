package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class CardsScreen extends GameScreen
{
    private float screenWidth = getGame().getScreenWidth(),
            screenHeight = getGame().getScreenHeight(),
            symbolXOffset = screenWidth * 0.125f,
            symbolYOffset = screenHeight * 0.125f;
    private AssetManager assetStore = getGame().getAssetManager();
    private Bitmap background;
    private GameObject mBackground;
    private ScreenManager screenManager = getGame().getScreenManager();

    int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
    int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

    public CardsScreen(Game game)
    {
        super("CardsScreen", game);


        assetStore.loadAndAddBitmap("menuBackground", "img/menuBackground.png");
        background = assetStore.getBitmap("CardsScreenBackground");

        //Test card
        assetStore.loadAndAddBitmap("rightArrow", "img/Cards/Aguero.png");


        mBackground = new GameObject(game.getScreenWidth()/2, game.getScreenHeight()/2, game.getScreenWidth(),
                game.getScreenHeight(), getGame().getAssetManager().getBitmap("menuBackground"), this);

    }

    @Override
    public void update(ElapsedTime elapsedTime)
    {
        Input input = mGame.getInput();
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D)
    {
        graphics2D.drawBitmap(
                background,
                null,
                new Rect(0, 0, (int) screenWidth, (int) screenHeight),
                new Paint());
    }
}
