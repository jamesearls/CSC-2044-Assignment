package uk.ac.qub.eeecs.game.matchAttax.font;

import android.graphics.Bitmap;

import java.util.List;

public class FontStore {
    public String font;
    public String colour;
    public List<Bitmap> upperCase;
    public List<Bitmap> lowerCase;
    public List<Bitmap> numbers;
    public List<Bitmap> characters;

    public FontStore(String name, String colour){
        this.font = name;
        this.colour = colour;
    }
}
