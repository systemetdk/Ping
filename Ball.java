import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private static int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private int userHits;
    private GreenfootImage image;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball() {
        createImage();
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage() {
        image = new GreenfootImage("ball.png");
        image.scale(BALL_SIZE, BALL_SIZE);
        setImage(image);
        
        // GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        // ballImage.setColor(Color.BLACK);
        // ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        // setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (delay > 0) {
            delay--;
            return;
        }
        
        move(speed);
        checkBounceOffWalls();
        checkBounceOffCeiling();
        checkRestart();
        hitsPaddle();
        hitsMovingPaddle();
    } 
    
    private void hitsPaddle() {
        Paddle paddle = (Paddle) getOneObjectAtOffset(0, 0, Paddle.class);
        
        if(paddle != null) {
            Greenfoot.playSound("au.wav");
            revertVertically();
            userHits++;
            if (userHits % 10 == 0) {
                speed++;
            }
        }
    }
    
    private void hitsMovingPaddle() {
        MovingPaddle movingPaddle = (MovingPaddle) getOneObjectAtOffset(0, 0, MovingPaddle.class);
        
        if(movingPaddle != null && movingUp()) {
            Greenfoot.playSound("au.wav");
            revertVertically();
        }
    }

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            Greenfoot.playSound("au.wav");
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            Greenfoot.playSound("au.wav");
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            Greenfoot.playSound("die.wav");
            init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 1;
        userHits = 0;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }

    public boolean movingUp() {
        return getRotation() >= 180;
    }
    
    public static int getSpeed() {
        return speed;
    }
}
