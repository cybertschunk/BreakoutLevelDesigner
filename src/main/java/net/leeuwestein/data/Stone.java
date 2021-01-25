package net.leeuwestein.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents the stones of the game
 *
 * @author Philipp Froehlich Group 168: Philipp Fröhlich, David Leeuwestein
 */

public class Stone {

    /*
     * Type of Stone
     */
    private int type;
    /*
     * Value to determine score
     */
    private int value;
    /*
     * Color of Stone
     */
    private Color color;
    /*
     * Position of stone
     */
    private Point position;

    /**
     * width of the stone
     */
    private int width;

    /**
     * height of the stone
     */
    private int height;

    /**
     * Constructor of stone Initalizes stones
     *
     * @param type     The type of the stone
     * @param position position of stone
     */
    public Stone(int type, Point position) {

        this.type = type;
        this.position = position;
        this.color = Stone.getColorForType(type);
        this.value = Stone.getValueForType(type);
    }

    /**
     * Gives back the color matching to the specified type
     *
     * @param type the specified type
     * @return the matching color
     */
    private static Color getColorForType(int type) {

        switch (type) {
            case 0:
                return Config.STONE_TYPE_0_BACKGROUND;
            case 1:
                return new Color(33, 49, 222); //Dark Blue
            case 2:
                return Color.GREEN;
            case 3:
                return Color.RED;
            default:
                throw new IllegalArgumentException("Unsupported type!");
        }
    }

    /**
     * Gives back the matching value to the specified type
     *
     * @param type the given type
     * @return the matching value
     */
    private static int getValueForType(int type) {

        switch (type) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 5;
            default:
                throw new IllegalArgumentException("Unsupported type");
        }
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set Also updates color and value for new type
     */
    public void setType(int type) {
        this.type = type;
        this.setColor(Stone.getColorForType(type));
        this.setValue(Stone.getValueForType(type));
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return Rectangle of Stone
     */
    public Rectangle getRectangle() {
        return new Rectangle((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.width, this.height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stone stone = (Stone) o;
        return type == stone.type && Objects.equals(position, stone.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, position);
    }

    public static void saveToJson(String path, int maxLives, ArrayList<Stone> data) throws IOException {
        JSONObject json = new JSONObject();
        json.put("maxLives", maxLives);
        JSONArray stones_json = new JSONArray();
        for (int x = 0; x < Config.SQUARES_X; ++x) {
            JSONArray stone_row = new JSONArray();
            for (int y = 0; y < Config.SQUARES_Y; ++y) {
                final int finalX = x;
                final int finalY = y;
                for (Stone stone : data) {
                    if (stone.getPosition().x == x && stone.getPosition().y == y) {
                        stone_row.add(stone.getType());
                        break;
                    }
                }
                // end y
            }
            stones_json.add(stone_row);
            // end x
        }
        json.put("field", stones_json);
        Files.write(Paths.get(path), json.toJSONString().getBytes(StandardCharsets.UTF_8));

    }
}
