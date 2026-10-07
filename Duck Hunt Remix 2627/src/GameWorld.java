import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED FRAMEWORK CODE.
 *
 * GameWorld stores three named Ducks, draws scenery and objects in layers,
 * and coordinates the Dog. Students should not edit this file for the core
 * assignment7
 */
public class GameWorld {
    public static final int WORLD_WIDTH =900;
    public static final int WORLD_HEIGHT = 600;
    public static final int GROUND_TOP = 320;

    private Ghost duck1;
    private Ghost duck2;
    private Ghost duck3;
    private Ghost currentDuck;
    private Dog dog;
    private Scarecrow scarecrow = new Scarecrow();
    private Pum bush1 = new Pum();
    private Pum bush2 = new Pum();
    private Background background = new Background();
    private Foreground foreground = new Foreground();

    private int stars = 5;
    private boolean finished = false;
    private boolean won = false;

    public GameWorld(Dog dog) {
        this.dog = dog;
    }

    public void addDuck(Ghost ghost) {
        if (duck1 == null) {
            duck1 = ghost;
        } else if (duck2 == null) {
            duck2 = ghost;
        } else if (duck3 == null) {
            duck3 = ghost;
        }
    }

    public void start() {
        currentDuck = duck1;
        if (currentDuck != null) {
            currentDuck.activate();
        }
    }

    /** Updates each named Duck, then the Dog and game progression. */
    public void update() {
        if (finished) {
            return;
        }

        if (duck1 != null) {
            duck1.update();
        }
        if (duck2 != null) {
            duck2.update();
        }
        if (duck3 != null) {
            duck3.update();
        }

        dog.update();

        if (currentDuck != null && currentDuck.hasLanded()
                && !dog.isRetrieving() && !dog.hasRetrievedDuck()) {
            dog.startRetrieving(currentDuck.getX());
        }

        if (currentDuck != null && dog.hasRetrievedDuck()) {
            currentDuck.deactivate();
            activateNextDuck();
        }
    }

    public void paint(Graphics g) {
        background.paint(g);
        scarecrow.paint(g);
        bush1.paint(g);
        bush2.paint(g);

        if (duck1 != null && duck1.isActive()) {
            duck1.paint(g);
        }
        if (duck2 != null && duck2.isActive()) {
            duck2.paint(g);
        }
        if (duck3 != null && duck3.isActive()) {
            duck3.paint(g);
        }

        dog.paint(g);
        foreground.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("Stars / lives: " + stars, 20, 30);

        if (finished) {
            g.setFont(new Font("SansSerif", Font.BOLD, 32));

            if (won) {
                g.drawString("You retrieved every duck!", 245, 80);
            } else {
                g.drawString("Out of stars!", 350, 80);
            }
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (finished) {
            return;
        }

        if (currentDuck == null || currentDuck.isFalling()
                || dog.isRetrieving()) {
            return;
        }

        if (currentDuck.wasClicked(mouseX, mouseY)) {
            currentDuck.startFalling();
        } else {
            stars = stars - 1;

            if (stars <= 0) {
                finished = true;
                won = false;
            }
        }
    }

    private void activateNextDuck() {
        dog.reset();
        if (currentDuck == duck1) {
            currentDuck = duck2;
        } else if (currentDuck == duck2) {
            currentDuck = duck3;
        } else {
            currentDuck = null;
        }

        if (currentDuck == null) {
            finished = true;
            won = true;
            return;
        }

        currentDuck.activate();
    }
}
