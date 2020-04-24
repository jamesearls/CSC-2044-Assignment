//package uk.ac.qub.eeecs.gage.screenTests;
//
//import android.text.method.Touch;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import uk.ac.qub.eeecs.gage.Game;
//import uk.ac.qub.eeecs.gage.engine.AssetManager;
//import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
//import uk.ac.qub.eeecs.gage.engine.ScreenManager;
//import uk.ac.qub.eeecs.gage.world.GameScreen;
//import uk.ac.qub.eeecs.game.matchAttax.screens.MainMenu;
//import uk.ac.qub.eeecs.game.matchAttax.screens.OptionsScreen;
//import uk.ac.qub.eeecs.gage.engine.input.Input;
//import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
////James Earls
//@RunWith(MockitoJUnitRunner.class)
//public class MainMenuTest {
//    String menuScreenName = "Menu Screen";
//    String matchGameScreenName = "Match Game Screen";
//    String cardsScreenName = "Cards Screen";
//    String optionsScreenName = "Options Screen";
//    @Mock
//    Game aGame = Mockito.mock(Game.class);
//    @Mock
//    AssetManager assetManager = Mockito.mock(AssetManager.class);
//    @Mock
//    private Input input;
//    @Mock
//    GameScreen menuScreen = Mockito.mock(GameScreen.class);
//    @Mock
//    GameScreen matchGameScreen = Mockito.mock(GameScreen.class);
//    @Mock
//    GameScreen cardsScreen = Mockito.mock(GameScreen.class);
//    @Mock
//    GameScreen optionsScreen = Mockito.mock(GameScreen.class);
//
//    @Before
//    public void setUp() {
//        when(menuScreen.getName()).thenReturn(menuScreenName);
//        when(matchGameScreen.getName()).thenReturn(matchGameScreenName);
//        when(cardsScreen.getName()).thenReturn(cardsScreenName);
//        when(optionsScreen.getName()).thenReturn(optionsScreenName);
//        when(aGame.getInput()).thenReturn(input);
//    }
//    // Test to see if the instance creates
//    @Test
//    public void mainMenu_CreateInstance(){
//        when(aGame.getAssetManager()).thenReturn(assetManager);
//        MainMenu mainMenu = new MainMenu((aGame));
//        assertEquals(aGame, mainMenu.getGame());
//    }
//
//    // Tests to check if buttons will go to correct screens
//    @Test
//    public void play_button_TestSuccess() throws Exception {
//        MainMenu mainMenu = new MainMenu(aGame);
//        float buttonPositionX = mainMenu.getmPlayButton().position.x;
//        float buttonPositionY = mainMenu.getmPlayButton().position.y;
//        when(aGame.getInput()).thenReturn(input);
//        List testTouches = testTouch(buttonPositionX, buttonPositionY);
//
//        when(input.getTouchEvents()).thenReturn(testTouches);
//        mainMenu.update(new ElapsedTime());
//        assertEquals(matchGameScreen,aGame.getScreenManager().getCurrentScreen() );
//    }
//    @Test
//    public void cards_button_TestSuccess() throws Exception {
//        MainMenu mainMenu = new MainMenu(aGame);
//        float buttonPositionX = mainMenu.getmCardsButton().position.x;
//        float buttonPositionY = mainMenu.getmCardsButton().position.y;
//        when(aGame.getInput()).thenReturn(input);
//        List testTouches = testTouch(buttonPositionX, buttonPositionY);
//
//        when(input.getTouchEvents()).thenReturn(testTouches);
//        mainMenu.update(new ElapsedTime());
//        assertEquals(cardsScreen,aGame.getScreenManager().getCurrentScreen() );
//    }
//    @Test
//    public void settings_button_TestSuccess() throws Exception {
//        MainMenu mainMenu = new MainMenu(aGame);
//        float buttonPositionX = mainMenu.getmSettingsButton().position.x;
//        float buttonPositionY = mainMenu.getmSettingsButton().position.y;
//        when(aGame.getInput()).thenReturn(input);
//        List testTouches = testTouch(buttonPositionX, buttonPositionY);
//
//        when(input.getTouchEvents()).thenReturn(testTouches);
//        mainMenu.update(new ElapsedTime());
//        assertEquals(optionsScreen,aGame.getScreenManager().getCurrentScreen() );
//    }
//
//    // Method to set up touch events and return a list with a touch at the correct locations
//    private List testTouch(float touchX, float touchY){
//        TouchEvent touchEvent = new TouchEvent();
//        touchEvent.type = TouchEvent.TOUCH_UP;
//        touchEvent.x = touchX;
//        touchEvent.y = touchY;
//        List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
//        touchEvents.add(touchEvent);
//        return touchEvents;
//    }
//}
//
