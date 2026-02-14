package com.example.ui;

import com.example.db.PlayerRepository;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final PlayerRepository players;

    public MainPanel(PlayerRepository players) {
        this.players = players;

        setLayout(new GridLayout(4, 1, 0, 10));

        JButton newGameBtn = new JButton("New Game");
        JButton continueGameBtn = new JButton("Continue Game");
        JButton editPlayerBtn = new JButton("Edit Player");
        JButton statisticsBtn = new JButton("View Statistics");

        newGameBtn.addActionListener(e -> onNewGame());
        continueGameBtn.addActionListener(e -> onContinueGame());
        editPlayerBtn.addActionListener(e -> onEditPlayer());
        statisticsBtn.addActionListener(e -> onViewStatistics());

        add(newGameBtn);
        add(continueGameBtn);
        add(editPlayerBtn);
        add(statisticsBtn);
    }

    private void onNewGame() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "New Game",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void onContinueGame() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Continue Game",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void onEditPlayer() {
        Window owner = SwingUtilities.getWindowAncestor(this);
        AddPlayerDialog dialog = new AddPlayerDialog(owner, players);
        dialog.setVisible(true);

        if (dialog.isPlayerCreated()) {
            JOptionPane.showMessageDialog(this, "Player added!");
            // später: Spieler-Liste refreshen
        }
    }

    private void onViewStatistics() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Statisics",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 650);
    }
}
