package uk.ac.qub.eeecs.game.matchAttax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.game.MenuScreen;
import uk.ac.qub.eeecs.game.matchAttax.screens.MainMenu;

public class MatchAttaxGame extends Game {

    private float musicVolume;
    private boolean isSfxOn;

    public MatchAttaxGame() {
        super();
    }

    public float getMusicVolume(){return musicVolume;}
    public boolean getSfxOn(){return isSfxOn;}

    //public void setMusicVolume(float volume){musicVolume = volume;}
    public void setSfxOn(boolean val) {isSfxOn = val;}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTargetFramesPerSecond(30);

        musicVolume = 0.5f;
        isSfxOn = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = super.onCreateView(inflater, container, savedInstanceState);
        MainMenu stubMainMenu = new MainMenu(this);
        mScreenManager.addScreen(stubMainMenu);

        return view;
    }

    @Override
    public boolean onBackPressed(){
        //Exit game if on Menu
        if (mScreenManager.getCurrentScreen().getName().equals("MainMenu")){
            return false;
        }
        //Return to Menu if in Game Screen
        if (mScreenManager.getCurrentScreen().getName().equals("MatchGameScreen"))
        {

            mScreenManager.removeScreen("MatchGameScreen");
            mScreenManager.addScreen(new MainMenu(this));
            return true;
        }
        if (mScreenManager.getCurrentScreen().getName().equals("OptionsScreen"))
        {

            mScreenManager.removeScreen("OptionsScreen");
            mScreenManager.addScreen(new MainMenu(this));
            return true;
        }
        return true;
    }
}
