package uk.ac.qub.eeecs.game.matchAttax.gameBoard;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class Hand extends GameObject {

    // Defines the types of hand's that will be required

    private enum HandType{
        PLAYER,CPU
    }

    public Hand(float x, float y, float width, float height, Bitmap bitmap, GameScreen gameScreen, HandType handType) {
        super(x, y, bitmap, gameScreen);
    }







}
