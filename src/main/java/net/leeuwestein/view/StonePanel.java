package net.leeuwestein.view;

import net.leeuwestein.data.Stone;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StonePanel extends JPanel implements MouseListener {
    private final EditorPanel editorPanel;
    private Stone stone;
    public StonePanel(Stone stone, EditorPanel editorPanel) {
        this.stone = stone;
        this.editorPanel = editorPanel;
        this.addMouseListener(this);
        this.updateToStone();
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }

    public void updateToStone() {
        this.setBackground(stone.getColor());
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            int newStoneType = (this.stone.getType() +1) %4;
            this.stone.setType(newStoneType);
            this.updateToStone();
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            int newStoneType = 0;
            this.stone.setType(newStoneType);
            this.updateToStone();
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * resets the stone to type zero
     */
    public void reset() {
        this.stone.setType(0);
        this.updateToStone();
    }
}
