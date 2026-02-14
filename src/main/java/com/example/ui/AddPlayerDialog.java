package com.example.ui;

import com.example.db.PlayerRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddPlayerDialog extends JDialog {
    private final JTextField nameField = new JTextField(20);
    private boolean playerCreated = false;

    public AddPlayerDialog(Window owner, PlayerRepository repo) {
        super(owner, "Add Player", ModalityType.APPLICATION_MODAL);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx = 0;
        gc.gridy = 0;
        form.add(new JLabel("Name:"), gc);

        gc.gridx = 1;
        form.add(nameField, gc);

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            try {
                String name = nameField.getText();

                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bitte einen Namen eingeben.", "Invalid",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (repo.existsByName(name)) {
                    JOptionPane.showMessageDialog(this, "Dieser Name existiert bereits.", "Duplicate",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                repo.create(name);
                playerCreated = true;
                dispose();

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB Fehler: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(save);

        cancel.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(save);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isPlayerCreated() {
        return playerCreated;
    }
}
