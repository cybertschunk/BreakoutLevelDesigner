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

import net.leeuwestein.data.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The main frame of the program
 */
public class Mainview extends JFrame implements ActionListener {

    /**
     * The main panel
     */
    private JPanel mainPanel;

    /**
     * the editor panel containing the stones
     */
    private EditorPanel editorPanel;

    /**
     * the menu bar with its items and submenus
     */
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem save;
    private JMenuItem clear;

    public Mainview() {
        this.createUIComponents();
        this.setTitle("Breakout Level Designer");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.save) {
            saveData();
            return;
        }
        if (e.getSource() == this.clear) {
            this.editorPanel.resetStonePanels();
            return;
        }
    }

    /**
     * initializes the ui components
     */
    private void createUIComponents() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());

        // editor panel
        this.editorPanel = new EditorPanel(this);
        this.mainPanel.add(editorPanel);

        // menu bar
        this.menuBar = new JMenuBar();
        this.file = new JMenu("Datei");
        this.save = new JMenuItem("Speichern");
        this.clear = new JMenuItem("löschen");
        this.file.add(save);
        this.file.add(clear);
        this.menuBar.add(file);
        this.add(menuBar);
        this.setJMenuBar(menuBar);


        // add listeners
        this.save.addActionListener(this);
        this.clear.addActionListener(this);
    }

    private void saveData() {

        // lives
        MaxLivesDialog dialog = new MaxLivesDialog();
        dialog.pack();
        dialog.setVisible(true);
        if(dialog.getMaxLives() < 0)
            return;

        // path
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify the path to save");
        int userFeedback = fileChooser.showSaveDialog(this);
        if (userFeedback != JFileChooser.APPROVE_OPTION)
            return;
        try {
            Stone.saveToJson(fileChooser.getSelectedFile().getPath(), dialog.getMaxLives(), editorPanel.getStones());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Saved file successfully");
    }
}
