package com.dartscounter;

import java.sql.DriverManager;
import java.sql.Conncetion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Date;



public class App extends JFrame{
    public static void main(String[] args) {
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setTitle("Dartscounter");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dartscounter.png"));
        setIconImage(icon);
    }
}}
