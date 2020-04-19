package uk.ac.qub.eeecs.game.matchAttax.font;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class Font extends GameObject {

    private Bitmap newBitmap;

    private List<FontStore> populated = new ArrayList<>();

    private GameScreen gameScreen;

    private float[] totalLength;

    public Font(GameScreen gameScreen) {
        super(gameScreen);
        this.gameScreen = gameScreen;
    }

    private void loadFontAsset(String font, String colour) {
        Bitmap bitmap;
        Boolean found = false;
        for (FontStore fontStore : populated) {
            if (fontStore.font.equals(font) && fontStore.colour.equals(colour)) {
                found = true;
            }
        }
        if (!found) {
            gameScreen.getGame().getAssetManager().loadAndAddBitmap(font + colour, "img/fonts/" + font + colour + ".png");
            bitmap = gameScreen.getGame().getAssetManager().getBitmap(font + colour);
            FontStore newFontStore = new FontStore(font, colour);
            populateLists(newFontStore, bitmap);
            populated.add(newFontStore);
        }
    }

    private void populateLists(FontStore newFontStore, Bitmap bitmap) {

        newFontStore.upperCase = new ArrayList<>();
        newFontStore.lowerCase = new ArrayList<>();
        newFontStore.numbers = new ArrayList<>();
        newFontStore.characters = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            int x = 50 * i + 4 * i;
            int y = 0;
            newFontStore.upperCase.add(i, newBitmap.createBitmap(bitmap, x, y, 50, 50));
        }
        for (int i = 0; i < 26; i++) {
            int x = 50 * i + 4 * i;
            int y = 52;
            newFontStore.lowerCase.add(i, newBitmap.createBitmap(bitmap, x, y, 50, 50));
        }
        for (int i = 0; i < 10; i++) {
            int x = 50 * i + 4 * i;
            int y = 104;
            newFontStore.numbers.add(i, newBitmap.createBitmap(bitmap, x, y, 50, 50));
        }
        for (int i = 0; i < 17; i++) {
            int x = 50 * i + 4 * i;
            int y = 156;
            newFontStore.characters.add(i, newBitmap.createBitmap(bitmap, x, y, 50, 50));
        }
    }

    public void drawText(String string, String font, int fontSize, String colour, float x, float y, ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        loadFontAsset(font.substring(0, 1).toLowerCase() + font.substring(1).replace(" ", ""), colour);
        for (FontStore fontStore : populated) {
            if ((font.substring(0, 1).toLowerCase() + font.substring(1).replace(" ", "")).equals(fontStore.font) && colour.equals(fontStore.colour)) {
                if (fontStore.font.equals("hemiHeadBoldItalic")) {
                    getHemiHeadBoldItalic(string, (float) fontSize, x, y, elapsedTime, graphics2D, fontStore, false);
                    getHemiHeadBoldItalic(string, (float) fontSize, x, y, elapsedTime, graphics2D, fontStore, true);
                }
            }
        }
    }

    private void getHemiHeadBoldItalic(String string, float fontSize, float x, float y, ElapsedTime elapsedTime, IGraphics2D graphics2D, FontStore fontStore, boolean centred) {
        float height = gameScreen.getGame().getScreenHeight() * 0.0005f;
        float width = gameScreen.getGame().getScreenWidth() * 0.000375f;
        getBound().halfHeight = height * fontSize;
        getBound().halfWidth = width * fontSize;

        // smaller gap between smaller characters
        boolean nextLetterSmall = false;
        // bigger gap between bigger characters
        boolean nextLetterBig = false;
        boolean nextLetterLarge = false;
        String[] lines;
        string = string.replace("\\n", "\n");
        lines=string.split("\n");
        if(!centred) {
            this.totalLength = new float[lines.length];
        }
        // populate list of every character in the string
        for (int j = 0; j < lines.length; j++) {
            // starting position
            setPosition(x, y);
            if(!centred) {
                this.totalLength[j] = 0;
            }else{
                setPosition(position.x-(totalLength[j]/2.0f) - (width*fontSize/2.0f), y);
            }
            this.position.y+=getBound().halfHeight*2*j;
            List<Character> text = new ArrayList<>();
            for (int i = 0; i < lines[j].length(); i++) {
                text.add(lines[j].charAt(i));
            }
            for (char eachText : text) {
                // default space between each letter
                float letterSpacing = width;

                // capital letters
                if (eachText >= 'A' && eachText <= 'Z') {
                    letterSpacing = width * 20.0f / 16.0f * fontSize;

                    if (nextLetterSmall) {
                        letterSpacing -= width * 5.0f / 16.0f * fontSize;
                        nextLetterSmall = false;
                    }
                    if (nextLetterBig) {
                        letterSpacing += width * 1.0f / 16.0f * fontSize;
                        nextLetterBig = false;
                    }
                    if (nextLetterLarge) {
                        letterSpacing += width * 5.0f / 16.0f * fontSize;
                        nextLetterLarge = false;
                    }

                    int i = eachText - 'A';

                    setBitmap(fontStore.upperCase.get(i));

                    if (eachText == 'I') {
                        letterSpacing -= width * 4.0f / 16.0f * fontSize;
                        nextLetterSmall = true;
                    }
                    if (eachText == 'M' || eachText == 'W' || eachText == 'H') {
                        letterSpacing += width * 5.0f / 16.0f * fontSize;
                        nextLetterBig = true;
                    }
                }
                if (eachText >= 'a' && eachText <= 'z') {
                    letterSpacing = width * 20.0f / 16.0f * fontSize;

                    if (nextLetterSmall) {
                        letterSpacing -= width * 5.0f / 16.0f * fontSize;
                        nextLetterSmall = false;
                    }
                    if (nextLetterBig) {
                        letterSpacing += width * 1.0f / 16.0f * fontSize;
                        nextLetterBig = false;
                    }
                    if (nextLetterLarge) {
                        letterSpacing += width * 5.0f / 16.0f * fontSize;
                        nextLetterLarge = false;
                    }

                    int i = eachText - 'a';

                    setBitmap(fontStore.lowerCase.get(i));

                    if (eachText == 'f' || eachText == 'l' || eachText == 'i' || eachText == 'j' || eachText == 't' || eachText == 'r') {
                        letterSpacing -= width * 5.0f / 16.0f * fontSize;
                        nextLetterSmall = true;
                    }
                    if (eachText == 'm' || eachText == 'w') {
                        letterSpacing += width * 8.0f / 16.0f * fontSize;
                        nextLetterBig = true;
                    }
                }
                if (eachText >= '0' && eachText <= '9') {
                    letterSpacing = width * 14.0f / 16.0f * fontSize;

                    if (nextLetterSmall) {
                        letterSpacing -= width * 3.0f / 16.0f * fontSize;
                        nextLetterSmall = false;
                    }
                    if (nextLetterBig) {
                        letterSpacing += width * 1.0f / 16.0f * fontSize;
                        nextLetterBig = false;
                    }
                    if (nextLetterLarge) {
                        letterSpacing += width * 5.0f / 16.0f * fontSize;
                        nextLetterLarge = false;
                    }

                    int i = eachText - '0';
                    setBitmap(fontStore.numbers.get(i));
                }
                if (eachText == ' ') {
                    letterSpacing = width * 6.0f / 16.0f * fontSize;
                    if (nextLetterSmall) {
                        letterSpacing -= width * 2.0f / 10.0f * fontSize;
                        nextLetterSmall = false;
                    }
                    if (nextLetterBig) {
                        letterSpacing += width * 1.0f / 16.0f * fontSize;
                        nextLetterBig = false;
                    }
                    if (nextLetterLarge) {
                        letterSpacing += width * 5.0f / 16.0f * fontSize;
                        nextLetterLarge = false;
                    }

                    setBitmap(fontStore.characters.get(0));
                }
                if (eachText == '!') {
                    letterSpacing += width * 12.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(1));
                }
                if (eachText == '?') {
                    letterSpacing += width * 15.0f / 16.0f * fontSize;
                    nextLetterBig = true;
                    setBitmap(fontStore.characters.get(2));
                }
                if (eachText == '.') {
                    letterSpacing += width * 13.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(3));
                }
                if (eachText == ',') {
                    letterSpacing += width * 14.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(4));
                }
                if (eachText == '(') {
                    letterSpacing += width * 15.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(5));
                }
                if (eachText == ')') {
                    letterSpacing += width * 16.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(6));
                }
                if (eachText == '&') {
                    letterSpacing += width * 20.0f / 16.0f * fontSize;
                    nextLetterLarge = true;
                    setBitmap(fontStore.characters.get(7));
                }

                if (eachText == '\'') {
                    letterSpacing += width * 12.0f / 16.0f * fontSize;
                    nextLetterSmall = true;
                    setBitmap(fontStore.characters.get(9));
                }
                if (eachText == ':') {
                    letterSpacing += width * 13.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(10));
                }
                if (eachText == '£') {
                    letterSpacing += width * 16.0f / 16.0f * fontSize;
                    nextLetterLarge = true;
                    setBitmap(fontStore.characters.get(11));
                }
                if (eachText == '%') {
                    letterSpacing += width * 10.0f / 16.0f * fontSize;
                    nextLetterLarge = true;
                    setBitmap(fontStore.characters.get(12));
                    setPosition(this.position.x + letterSpacing, this.position.y);
                }
                if (eachText == '-') {
                    letterSpacing += width * 8.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(13));
                    setPosition(this.position.x + letterSpacing, this.position.y);
                }
                if (eachText == '+') {
                    letterSpacing += width * 9.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(14));
                    setPosition(this.position.x + letterSpacing, this.position.y);
                }
                if (eachText == '/') {
                    letterSpacing += width * 16.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(15));
                }
                if (eachText == '=') {
                    letterSpacing += width * 17.0f / 16.0f * fontSize;
                    setBitmap(fontStore.characters.get(16));
                }

                if (centred) {
                    this.position.x += letterSpacing;
                    super.draw(elapsedTime, graphics2D);
                } else {
                    this.totalLength[j] += letterSpacing;
                }
            }
        }
    }
}
