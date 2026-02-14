package com.example;

import com.example.db.PlayerRepository;
import com.example.db.Schema;
import com.example.ui.MainPanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App(PlayerRepository players) {
        super("Dartscounter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(new MainPanel(players));
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 650));
    }

    public static void main(String[] args) {
        com.example.db.Schema.init();
        PlayerRepository players = new PlayerRepository();

        SwingUtilities.invokeLater(() -> new App(players).setVisible(true));
    }

}
