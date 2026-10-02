/**
 * STUDENT FILE: Duck behavior.
 *
 * Sprite provides the graphics code. Your job is to make the Duck move,
 * bounce, fall, and reset.
 */
public class Duck extends Sprite {
    // ===============================
    // STUDENT SETTINGS
    // ===============================
    private int dx = 4;
    private int dy = 2;

    // ===============================
    // GAME STATE - mostly provided
    // ===============================
    private int homeX;
    private int homeY;
    private int fallSpeed = 2;
    private boolean active = false;
    private boolean falling = false;
    private boolean landed = false;
    int dFlyX = 0;
    int FlyY = 0;

    public Duck() {
        this(32, 32);
    }

    public Duck(int startX, int startY) {
        // Change duck.gif to your own Halloween or fall image later.
        super("Ghost_fly.gif", startX, startY, 90, 90);

        homeX = startX;
        homeY = startY;
                
    }

    /**
     * STEP 1: make the Duck move.
     * STEP 2: add the bouncing rules.
     * STEP 3: add the falling rules.
     */
    
    
    
    public void update() {
        if (!active) {
            return;
        }

        if (falling) {
            // STEP 3: Uncomment and complete the falling code.
        	//Eventually change the sprite to the falling sprite
             y = y + fallSpeed;
             fallSpeed = fallSpeed + 1;
             if (y + height >= GameWorld.GROUND_TOP + height+50) {
                 landed = true;
                 y = GameWorld.WORLD_HEIGHT + 50;
             }

            return;
        }

        // STEP 1: Uncomment these lines to move the Duck.
         x = x + dx;
         y = y + dy;

        // STEP 2: Add if statements that bounce the Duck off the edges.
        // Hint: reverse a direction by changing dx to -dx or dy to -dy.
        // Hint: GameWorld.WORLD_WIDTH is the width of the game.
        // Hint: GameWorld.GROUND_TOP is the top of the ground.
         
         if(x + width + 50 >= GameWorld.WORLD_WIDTH || x <= 50) {
        	 dx *= -1;
         }
         if(y + height >= GameWorld.GROUND_TOP || y <= 50) {
        	 dy *= -1;
         }
         
    }

    /**
     * STEP 3: Uncomment the two lines below so a successful click starts the
     * falling behavior.
     */
    public void startFalling() {
        if (active && !falling) {
             falling = true;
             fallSpeed = 2;
        }
    }

    public boolean hasLanded() {
        return active && landed;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean wasClicked(int mouseX, int mouseY) {
        return active && !falling && super.wasClicked(mouseX, mouseY);
    }

    // These methods are provided so the GameWorld can manage progression.
    public void activate() {
        active = true;
        reset();
    }

    public void deactivate() {
        active = false;
    }

    /** STEP 4: verify that reset returns the Duck to its starting position. */
    public void reset() {
        x = homeX;
        y = homeY;
        fallSpeed = 2;
        falling = false;
        landed = false;
    }
}
