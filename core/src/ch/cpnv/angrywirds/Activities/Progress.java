package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Stage.BasicButton;
import ch.cpnv.angrywirds.Models.Stage.Title;

/**
 * Created by Xavier on 10.06.18.
 */

public class Progress extends GameActivity implements InputProcessor {

    private Texture background;
    private Title title;
    private BasicButton changeModeButton;
    private BasicButton returnGameButton;

    public Progress() {
        super();
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Title("Progress", 0, 250, true);

        changeModeButton = new BasicButton("btn.png", "Change mode", Play.WORLD_WIDTH - 192, Play.FLOOR_HEIGHT, 192, 62);
        returnGameButton = new BasicButton("btn.png", "Return to game", 0, Play.FLOOR_HEIGHT, 192, 62);

        Gdx.input.setInputProcessor(this);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(spriteBatch);
        changeModeButton.draw(spriteBatch);
        returnGameButton.draw(spriteBatch);
        spriteBatch.end();
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
            Gdx.app.log("ANGRY", "changeModeButton CLICKED");
//            AngryWirds.gameActivityManager.push(new Play());
        } else if (this.returnGameButton.getSprite().getBoundingRectangle().contains(new Vector2(positionTouched.x, positionTouched.y))) {
            Gdx.app.log("ANGRY", "returnGameButton Button CLICKED");
//            AngryWirds.gameActivityManager.push(new Play());
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
