package uk.ac.qub.eeecs.game.matchAttax.screens;

        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.graphics.Typeface;
        import android.text.TextPaint;

        import uk.ac.qub.eeecs.gage.Game;
        import uk.ac.qub.eeecs.gage.engine.AssetManager;
        import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
        import uk.ac.qub.eeecs.gage.engine.ScreenManager;
        import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
        import uk.ac.qub.eeecs.gage.ui.PushButton;
        import uk.ac.qub.eeecs.gage.engine.input.Input;
        import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
        import java.util.List;

        import uk.ac.qub.eeecs.gage.world.GameObject;
        import uk.ac.qub.eeecs.gage.world.GameScreen;
        import uk.ac.qub.eeecs.gage.world.Sprite;

public  class OptionsScreen extends  GameScreen {
    private float screenWidth = getGame().getScreenWidth(),
            screenHeight = getGame().getScreenHeight(),
            symbolXOffset = screenWidth * 0.125f,
            symbolYOffset = screenHeight * 0.125f;
    private double timeAccumulator = 0;
    private AssetManager assetStore = getGame().getAssetManager();
    private ScreenManager screenManager = getGame().getScreenManager();
    private Bitmap background, symbol, muteBitmap, unmuteBitmap, highBitmap, normalBitmap, lowBitmap, returnBitmap;
    private Typeface font;
    private GameObject backgroundObject;
    private TextPaint titlePaint, buttonPaint;
    private Rect muteRect, unmuteRect, highRect, normalRect, lowRect, returnRect;
    private Sprite currentMusicImage;
    private Bitmap songImage1, songImage2, songImage3, songImage4, songImage5;
    private int currentSongNumber = 1;
    private PushButton muteButton, unmuteButton, highButton, normalButton, lowButton, returnButton, rightArrow, leftArrow, homeButton, homeButtonPressed;
    private Canvas buttonCanvas;

    public OptionsScreen(Game game) {
        super("OptionsScreen", game);

        /* Load font */
//        assetStore.loadAndAddTypeface("font", "fonts/Audiowide.ttf");
//        font = assetStore.getTypeface("font");

        assetStore.loadAndAddBitmap("menuBackground", "img/menuBackground.png");
        background = assetStore.getBitmap("menuBackground");

        //creating buttons to change music
        assetStore.loadAndAddBitmap("rightArrow", "img/buttons/rightArrow.png");
        assetStore.loadAndAddBitmap("rightArrowPressed", "img/buttons/rightArrowPressed.png");
        assetStore.loadAndAddBitmap("leftArrow", "img/buttons/leftArrow.png");
        assetStore.loadAndAddBitmap("leftArrowPressed", "img/buttons/leftArrowPressed.png");

        assetStore.loadAndAddBitmap("homeButton", "img/buttons/homeButton.png");
        assetStore.loadAndAddBitmap("homeButtonPressed", "img/buttons/homeButtonPressed.png");

        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        rightArrow = new PushButton(
                spacingX * 1.5f, spacingY * 1.6f, spacingX*0.5f, spacingY*0.5f,
                "leftArrow", "leftArrowPressed",this);
        rightArrow.setPlaySounds(false, false);

        leftArrow = new PushButton(
                spacingX * 3.5f, spacingY * 1.6f, spacingX*0.5f, spacingY*0.5f,
                "rightArrow", "rightArrowPressed",this);
        leftArrow.setPlaySounds(false, false);

        homeButton = new PushButton(
                spacingX * 0.23f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "homeButton", "homeButtonPressed",this);
        homeButton.setPlaySounds(false, false);

        // Load music
        assetStore.loadAndAddMusic("ChelseaDagger", "sound/ChelseaDagger.mp3");
        assetStore.loadAndAddMusic("SevenNationArmy", "sound/SevenNationArmy.mp3");
        assetStore.loadAndAddMusic("WavinFlag", "sound/WavinFlag.mp3");
        assetStore.loadAndAddMusic("WhatYouKnow", "sound/WhatYouKnow.mp3");
        assetStore.loadAndAddMusic("FA", "sound/FluorescentAdolescent.mp3");
        /* Set title text size and font */
        titlePaint = new TextPaint();
        titlePaint.setTextSize(screenWidth * 0.08f);
        titlePaint.setTypeface(font);
        /* Set button text size and font */
        buttonPaint = new TextPaint();
        buttonPaint.setTextSize(screenWidth * 0.04f);
        buttonPaint.setTypeface(font);

        assetStore.loadAndAddBitmap("ChelseaDagger", "img/songs/song1.png");
        assetStore.loadAndAddBitmap("SevenNationArmy", "img/songs/song2.png");
        assetStore.loadAndAddBitmap("WavinFlag", "img/songs/song3.png");
        assetStore.loadAndAddBitmap("WhatYouKnow", "img/songs/song4.png");
        assetStore.loadAndAddBitmap("FA", "img/songs/song5.png");
        currentMusicImage = new Sprite(screenWidth /2, screenHeight / 2, 400, 400, null, this);

        makeHighButton();
        makeNormalButton();
        makeLowButton();
    }

    /* Button for raising music */
    public void makeHighButton() {
        buttonPaint.getTextBounds("HIGH", 0, "HIGH".length(), highRect = new Rect());
        highBitmap = Bitmap.createBitmap(highRect.width(), highRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(highBitmap);
        buttonCanvas.drawText("HIGH", 0, highRect.height(), buttonPaint);
        assetStore.add("high", highBitmap);
        highButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.34f,
                highRect.width(), highRect.height(), "high", this);
    }

    /* Button for resetting music */
    public void makeNormalButton() {
        buttonPaint.getTextBounds("NORMAL", 0, "NORMAL".length(), normalRect = new Rect());
        normalBitmap = Bitmap.createBitmap(normalRect.width(), normalRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(normalBitmap);
        buttonCanvas.drawText("NORMAL", 0, normalRect.height(), buttonPaint);
        assetStore.add("normal", normalBitmap);
        normalButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.42f,
                normalRect.width(), normalRect.height(), "normal", this);
    }

    /* Button for lowering music */
    public void makeLowButton() {
        buttonPaint.getTextBounds("LOW", 0, "LOW".length(), lowRect = new Rect());
        lowBitmap = Bitmap.createBitmap(lowRect.width(), lowRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(lowBitmap);
        buttonCanvas.drawText("LOW", 0, lowRect.height(), buttonPaint);
        assetStore.add("low", lowBitmap);
        lowButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.50f,
                lowRect.width(), lowRect.height(), "low", this);
    }

    public void onButtonPressed() {

        if(highButton.isPushTriggered()) {
            mGame.setMusicActive(true);
            mGame.setMusicVolume(mGame.getMusicVolume() + 0.1f);
       }
       if(normalButton.isPushTriggered()) {
            mGame.setMusicActive(true);
           mGame.setMusicVolume(0.5f);
        }
        if(lowButton.isPushTriggered()) {
            mGame.setMusicActive(true);
            mGame.setMusicVolume(mGame.getMusicVolume() - 0.1f);

        }
        assetStore.getMusic("ChelseaDagger").setVolume(mGame.getMusicVolume());

    }

    public Paint getTitlePaint() {
        return titlePaint;
    }
    public void setTitlePaint(TextPaint titlePaint) {
        this.titlePaint = titlePaint;
    }
    public Paint getButtonPaint() {
        return buttonPaint;
    }
    public void setButtonPaint(TextPaint buttonPaint) {
        this.buttonPaint = buttonPaint;
    }

    public PushButton getHighButton() { return highButton; }
    public void setHighButton(PushButton button) { this.highButton = button; }
    public PushButton getNormalButton() { return normalButton; }
    public void setNormalButton(PushButton button) { this.normalButton = button; }
    public PushButton getLowButton() { return lowButton; }
    public void setLowButton(PushButton button) { this.lowButton = button; }

    @Override
    public void update(ElapsedTime elapsedTime) {

        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {


            timeAccumulator += elapsedTime.stepTime;
            while (timeAccumulator > 0.1) {
                timeAccumulator -= 0.1;
            }

            highButton.update(elapsedTime);
            normalButton.update(elapsedTime);
            lowButton.update(elapsedTime);
            rightArrow.update(elapsedTime);
            leftArrow.update(elapsedTime);
            homeButton.update(elapsedTime);
            onButtonPressed();

            currentMusicImage.update(elapsedTime);
                if (rightArrow.isPushed()) {
                    currentSongNumber++;
                    if (currentSongNumber > 5) {
                        currentSongNumber = 1;
                    }
                }
                if (leftArrow.isPushed()) {
                    currentSongNumber--;
                    if (currentSongNumber < 1) {
                        currentSongNumber = 5;
                    }
                }
                 if (homeButton.isPushTriggered()){
                    mGame.getScreenManager().addScreen(new MainMenu(mGame));
                }
        }
        changeOrPlayMusic(leftArrow.isPushed(), rightArrow.isPushed());

    }

    @Override
        public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
            graphics2D.drawBitmap(
                    background,
                    null,
                    new Rect(0, 0, (int) screenWidth, (int) screenHeight),
                    new Paint()
            );

            highButton.draw(elapsedTime, graphics2D);
            normalButton.draw(elapsedTime, graphics2D);
            lowButton.draw(elapsedTime, graphics2D);
            rightArrow.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
            leftArrow.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
            homeButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
            if(currentMusicImage.getBitmap() != null)currentMusicImage.draw(elapsedTime, graphics2D);

        }

    private void changeOrPlayMusic(boolean leftArrowPressed, boolean rightArrowPressed)
    {
        if((leftArrowPressed || rightArrowPressed) || !mGame.getAudioManager().isMusicPlaying()){
        mGame.getAudioManager().stopMusic();
        switch(currentSongNumber) {
            case 1:
                currentMusicImage.setBitmap(assetStore.getBitmap("ChelseaDagger"));
                mGame.getAudioManager().playMusic(getGame().getAssetManager().getMusic("ChelseaDagger"));
                break;
            case 2:
                currentMusicImage.setBitmap(assetStore.getBitmap("SevenNationArmy"));
                mGame.getAudioManager().playMusic(getGame().getAssetManager().getMusic("SevenNationArmy"));
                break;
            case 3:
                currentMusicImage.setBitmap(assetStore.getBitmap("WavinFlag"));
                mGame.getAudioManager().playMusic(getGame().getAssetManager().getMusic("WavinFlag"));
                break;
            case 4:
                currentMusicImage.setBitmap(assetStore.getBitmap("WhatYouKnow"));
                mGame.getAudioManager().playMusic(getGame().getAssetManager().getMusic("WhatYouKnow"));
                break;
            case 5:
                currentMusicImage.setBitmap(assetStore.getBitmap("FA"));
                mGame.getAudioManager().playMusic(getGame().getAssetManager().getMusic("FA"));
                break;
        }
        }

    }

    public int getCurrentSong(){return currentSongNumber;};
    public boolean setCurrentSong(int songNumber){
        if(songNumber > 5 || songNumber < 1){
            return false;
        } else {
            currentSongNumber = songNumber;
            return true;
        }
    }


}
