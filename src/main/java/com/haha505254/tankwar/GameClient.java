package com.haha505254.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {

    private Tank playertank;

    private GameClient(){
        this.playertank = new Tank(400,100,Direction.DOWN);

        this.setPreferredSize(new Dimension(800,600));

    }

    @Override
    protected void paintComponent(Graphics g) {

        playertank.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setTitle("坦克小遊戲");
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        final GameClient client=new GameClient();
        client.repaint();
        frame.add(client);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                client.playertank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                client.playertank.keyReleased(e);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        while (true){
            client.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
