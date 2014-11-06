package info.testing.automated.remoteserver.utils;

import info.testing.automated.remoteserver.interfaces.Element;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.logging.Logger;

/**
 * Author: Sergey Kuts
 */
public class RobotUtils {

    private static final Logger ROBOT_LOGGER = Logger.getLogger(RobotUtils.class.getName());

    public static boolean dragAndDrop(final Element draggable, final Element droppable, final int xOffset, final int yOffset) {

        Robot robot;
        boolean isDraggedAndDropped;

        try {
            robot = new Robot();
            robot.setAutoDelay(500);

            final int xCentreFrom = draggable.getWidth() / 2;
            final int yCentreFrom = draggable.getHeight() / 2;
            final int xCentreTo = droppable.getWidth() / 2;
            final int yCentreTo = droppable.getHeight() / 2;

            draggable.setX(draggable.getX() + xOffset + xCentreFrom);
            draggable.setY(draggable.getY() + yOffset + yCentreFrom);
            droppable.setX(droppable.getX() + xOffset + xCentreTo);
            droppable.setY(droppable.getY() + yOffset + yCentreTo);

            robot.mouseMove(draggable.getX(), draggable.getY());
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove(((droppable.getX() - draggable.getX()) / 2) + draggable.getX(),
                    ((droppable.getY() - draggable.getY()) / 2) + draggable.getY());
            robot.mouseMove(droppable.getX(), droppable.getY());
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            isDraggedAndDropped = true;
        } catch (AWTException ex) {
            ROBOT_LOGGER.severe(ex.getMessage());
            isDraggedAndDropped = false;
        }

        return isDraggedAndDropped;
    }
}
