import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            setBackground();
            populate();
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    private void setBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
    }

    private void populate() {
        addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
        addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
        addObject(new MovingPaddle(100,20), 60, Greenfoot.getRandomNumber(WORLD_HEIGHT / 2));
        addObject(new Text(), WORLD_WIDTH - 60, 30);
        setPaintOrder(Ball.class, Paddle.class, MovingPaddle.class, Text.class);
    }
}
