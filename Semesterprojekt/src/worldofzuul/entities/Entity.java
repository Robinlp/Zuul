package worldofzuul.entities;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author Group 7
 */
public abstract class Entity {
    protected int x, y;
    protected int width, height;
    protected Room currentRoom;
    protected Image entityImage;
    
    public Entity(int x, int y, int width, int height, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentRoom = currentRoom;
    }
    
    // GETTERS & SETTERS
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Image getEntityImage() {
        return entityImage;
    }

    public void setEntityImage(Image entityImage) {
        this.entityImage = entityImage;
    }
}
//    public BufferedImage getGraphics() {
//        return graphics;
//    }
//
//    public void setGraphics(BufferedImage graphics) {
//        this.graphics = graphics;
//    }
//}