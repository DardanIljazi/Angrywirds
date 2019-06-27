package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.awt.Button;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Stage.BasicButton;
import ch.cpnv.angrywirds.Models.Stage.ScoreBoard;
import ch.cpnv.angrywirds.Models.Stage.Title;

/**
 * Created by Xavier on 10.06.18.
 */

public class Menu extends GameActivity implements InputProcessor {

    private Texture background;
    private Title title;
    private BasicButton button;

    public Menu() {
        super();
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Title("Menu", 0, 60, true);
        button = new BasicButton("btn.png", "Play", (WORLD_WIDTH - 193.f) / 2, (WORLD_HEIGHT - 62.f) / 2 - 55, 193, 62);
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
        button.draw(spriteBatch);
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
        if (this.button.getSprite().getBoundingRectangle().contains(new Vector2(positionTouched.x, positionTouched.y))) {
            Gdx.app.log("ANGRY", "CLICKED");
            AngryWirds.gameActivityManager.push(new Play());
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
