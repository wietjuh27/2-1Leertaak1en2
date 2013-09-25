package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.ConnectionController;
import control.InputHandler;

import javax.swing.JTabbedPane;

import java.awt.Color;

public class Gui extends JFrame {

    private JPanel contentPane;
    private static ConnectionController connectionController;
    private static JTextArea textArea;
    private static String error;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    connectionController = ConnectionController.getConnectionController();
                } catch (SQLException e) {
                    error = e.getMessage();
                }
                Gui frame = new Gui();
                frame.pack();
                frame.setVisible(true);
            }
        }); //end of invoke later thread
        InputHandler ih = new InputHandler();
        ih.run();

    } // end of method: main

    /**
     * Create the frame.
     */
    public Gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        tabbedPane.addTab("Info", null, panel, null);

        textArea = new JTextArea(error);
        error = "";
        panel.add(textArea);


        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Control", null, panel_1, null);


    }

    /*public static void addToInfo(String arg) {
        try {
            textArea.append(arg + "\n");
        } catch (NullPointerException e) {
            textArea.append("An unknown error appeared...\n");
        }

    }*/
}
