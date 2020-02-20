package uk.ac.qub.eeecs.game.matchAttax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.game.matchAttax.screens.MainMenu;

public class MatchAttaxGame extends Game {

    private float musicVolume;
    private boolean isSfxOn;

    public MatchAttaxGame() {
        super();
    }

    public float getMusicVolume(){return musicVolume;}
    public boolean getSfxOn(){return isSfxOn;}

    public void setMusicVolume(float volume){musicVolume = volume;}
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
        MainMenu mainMenu = new MainMenu(this);
        mScreenManager.addScreen(mainMenu);

        return view;
    }
}
