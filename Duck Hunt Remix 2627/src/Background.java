import java.awt.Color;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the sky behind the game objects.
 */
public class Background extends Sprite {
    public Background() {
        super("Background.png", 0, GameWorld.GROUND_TOP,
                GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT - GameWorld.GROUND_TOP);
    }
}
