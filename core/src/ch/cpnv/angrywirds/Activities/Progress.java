package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Data.Vocabulary;
import ch.cpnv.angrywirds.Models.Data.Word;
import ch.cpnv.angrywirds.Models.Stage.BasicButton;
import ch.cpnv.angrywirds.Models.Stage.Title;
import ch.cpnv.angrywirds.Providers.FontProvider;
import ch.cpnv.angrywirds.Providers.VocProvider;


/**
 * Created by Xavier on 10.06.18.
 */

public class Progress extends GameActivity implements InputProcessor {

    private Texture background;
    private Title title;
    private BasicButton changeModeButton;
    private BasicButton returnGameButton;
    private int mode;
    private HashMap<Word, Boolean> wordList; // If word touched, boolean = true
    private Vocabulary vocabulary;
    private ArrayList<Title> words;
    private BitmapFont font;


    public Progress(ArrayList<String> touchedWords) {
        super();
        vocabulary = VocProvider.vocabularies.get(0); // hardcoded for now
        wordList = new HashMap<Word, Boolean>();
        font = new BitmapFont();

        words = new ArrayList<Title>();
        int i = 0;
        for(Word word : vocabulary.getWords()) {
            for(String touched: touchedWords) {
                if(word.getValue2().contains(touched) || word.getValue1().contains(touched)) {
                    wordList.put(word, true);
                    break;
                }
            }

            wordList.put(word, false);

        }

        background = new Texture(Gdx.files.internal("background.png"));
//        title = new Title("Progress", 0, 250, true);
        mode = 0;
        changeModeButton = new BasicButton("btn.png", "Change mode", Play.WORLD_WIDTH - 250, Play.FLOOR_HEIGHT, 192, 62);
        returnGameButton = new BasicButton("btn.png", "Return to game", 0, Play.FLOOR_HEIGHT, 192, 62);
        Gdx.input.setInputProcessor(this);

    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {


        words.clear();
        int i = 0;
        for (Map.Entry<Word, Boolean> entry : wordList.entrySet()) {

            // Mode 1 = show only what's not already found
            if(mode == 1) {

                if(entry.getValue() == false) {
                    Gdx.app.log("ANGRY", "Mode = 1");
                    Title text_left = new Title(entry.getKey().getValue1(), (Play.WORLD_WIDTH - 200f) / 4, Play.WORLD_HEIGHT - (i * 50));
                    words.add(text_left);

                    Title text_right = new Title(entry.getKey().getValue2(), (Play.WORLD_WIDTH - 200f) / 2, Play.WORLD_HEIGHT - (i * 50));
                    words.add(text_right);
                    i++;
                }
            }else {  // Mode 0 show all with special effect on text
                Gdx.app.log("ANGRY", "Mode = 0");
                if(entry.getValue() == true) {
                    Title text_left = new Title(entry.getKey().getValue1(), (Play.WORLD_WIDTH - 200f)/4, Play.WORLD_HEIGHT - (i * 50));
                    text_left.setFont(FontProvider.TitleBlack);
                    words.add(text_left);

                    Title text_right = new Title(entry.getKey().getValue2(), (Play.WORLD_WIDTH - 200f)/2, Play.WORLD_HEIGHT - (i * 50));
                    text_right.setFont(FontProvider.TitleBlack);
                    words.add(text_right);
                    i++;
                }else {
                    Title text_left = new Title(entry.getKey().getValue1(), (Play.WORLD_WIDTH - 200f)/4, Play.WORLD_HEIGHT - (i * 50));
                    words.add(text_left);

                    Title text_right = new Title(entry.getKey().getValue2(), (Play.WORLD_WIDTH - 200f)/2, Play.WORLD_HEIGHT - (i * 50));
                    words.add(text_right);
                    i++;
                }
            }
        }

    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
//        title.draw(spriteBatch);
        changeModeButton.draw(spriteBatch);
        returnGameButton.draw(spriteBatch);

        for(Title title : words) {
            title.draw(spriteBatch);
        }

        spriteBatch.end();
    }

    public void setVocabularyFound() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 positionTouched = camera.unproject(new Vector3(screenX, screenY, 0));
        // Check if the button is touched
        if (this.changeModeButton.getSprite().getBoundingRectangle().contains(new Vector2(positionTouched.x, positionTouched.y))) {
            mode = (mode == 1) ? 0 : 1;
            Gdx.app.log("ANGRY", "changeModeButton CLICKED" + mode);

        }

        if (this.returnGameButton.getSprite().getBoundingRectangle().contains(new Vector2(positionTouched.x, positionTouched.y))) {
            Gdx.app.log("ANGRY", "returnGameButton Button CLICKED");
//            AngryWirds.gameActivityManager.pop();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
