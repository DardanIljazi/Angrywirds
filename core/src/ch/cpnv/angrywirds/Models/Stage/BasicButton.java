package ch.cpnv.angrywirds.Models.Stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BasicButton {

    private String text;
    private Sprite sprite;
    private BitmapFont font;

    public BasicButton(String path, String text, float xPos, float yPos, float width, float height) {
        this.text = text;
        sprite = new Sprite(new Texture(path));
        sprite.setBounds(xPos, yPos, width, height);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
    }



    public Sprite getSprite() {
        return sprite;
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
        font.draw(batch, text, sprite.getX() + 50, sprite.getY() + 50);
    }
}
