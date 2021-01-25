/*
 * Copyright (c) 2021, David Leeuwestein.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 *  distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package net.leeuwestein.view;

import net.leeuwestein.data.Config;
import net.leeuwestein.data.Stone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditorPanel extends JPanel {

    /**
     * the mainview containing the editorpanel
     */
    private final Mainview mainView;

    /**
     * The layout manager used in the panel
     */
    private LayoutManager layout;

    /**
     * the current list of stones
     */
    private ArrayList<StonePanel> stonePanels;


    public EditorPanel(Mainview mainView) {
        this.mainView = mainView;
        this.layout = new GridLayout(Config.SQUARES_Y, Config.SQUARES_X, 2, 2);
        this.setLayout(layout);
        this.stonePanels = new ArrayList<StonePanel>();
        this.initStonePanels();
    }

    /**
     * Initializes the list of stone panels with type 0 stones
     */
    private void initStonePanels() {
        for (int x = 0; x < Config.SQUARES_Y; ++x) {
            for (int y = 0; y < Config.SQUARES_X; ++y) {
                Point position = new Point(x, y);
                Stone stone = new Stone(0, position);
                StonePanel stonePanel = new StonePanel(stone, this);
                stonePanels.add(stonePanel);
                this.add(stonePanel);
            }
        }
    }

    /**
     * resets all stone panels to default
     */
    public void resetStonePanels() {
        for(StonePanel stonePanel: stonePanels)
            stonePanel.reset();
    }

    /**
     * retrieves all stones from the stone panels
     * @return a list of stones
     */
    public ArrayList<Stone> getStones() {
        ArrayList<Stone> stones = new ArrayList<Stone>();
        for (StonePanel stonePanel : this.stonePanels)
            stones.add(stonePanel.getStone());
        return stones;
    }

}
