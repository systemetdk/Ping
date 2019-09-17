import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
    }    
    
    private void move() {
        if (Greenfoot.isKeyDown("left") && !hitsLeftWall()) {
            dx = -2;
            setLocation(getX() + dx, getY());
        } else if (Greenfoot.isKeyDown("right")  && !hitsRightWall()) {
            dx = 2;
            setLocation(getX() + dx, getY());
        }
    }
    
    private boolean hitsRightWall() {
        return getX() + width/2 >= getWorld().getWidth();
    }
    
    private boolean hitsLeftWall() {
        return getX() - width/2 <= 0;
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("paddle.png");
        image.scale(200, 40);
        setImage(image);
    }

}
