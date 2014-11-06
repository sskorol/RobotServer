package info.testing.automated.client;

import info.testing.automated.remoteserver.interfaces.Element;

/**
 * Author: Sergey Kuts
 */
public class RobotElement implements Element {

    private int height;
    private int width;

    private int x;
    private int y;

    public RobotElement(final int height, final int width, final int x, final int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public String getElement() {
        return "[size = " + getHeight() + ":" + getWidth() +
                "; location = " + getX() + ":" + getY() + "]";
    }
}
