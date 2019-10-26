package uk.ac.qub.eeecs.game.spaceDemo;

import java.util.Random;

import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.Sprite;

/**
 * Simple asteroid
 *
 * Note: See the course documentation for extension/refactoring stories
 * for this class.
 *
 * @version 1.0
 */
public class Asteroid extends SpaceEntity {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Default size for the asteroid
     */

    private static final float DEFAULT_RADIUS = 40;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Create an asteroid
     *
     * @param startX     x location of the asteroid
     * @param startY     y location of the asteroid
     * @param gameScreen Gamescreen to which asteroid belongs
     */
    public Asteroid(float startX, float startY, GameScreen gameScreen) {
        super(startX, startY, getBoundaries(), getBoundaries(), null, gameScreen);

        Random random = new Random();

        mBitmap = gameScreen.getGame().getAssetManager()
                .getBitmap(asteroidImg());

        mRadius = DEFAULT_RADIUS;
        mMass = 1000.0f;

        angularVelocity = random.nextFloat() * 240.0f - 20.0f;

    }

    public static float getBoundaries(){
        Random rand = new Random();
        float bound = (rand.nextFloat() * DEFAULT_RADIUS) + 10;
        return bound;
    }

    public String asteroidImg(){
        String img;
        Random random = new Random();
        int num = random.nextInt(4);
        if(num==0) img="Asteroid1";
        else if(num==1) img="Asteroid2";
        else if(num==2) img="Asteroid3";
        else img="Asteroid4";
        return img;
    }
}
