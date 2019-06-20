package com.haha505254.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {

    private static final GameClient INSTANCE = new GameClient();

    static GameClient getInstance(){
        return INSTANCE;
    }

    private Tank playertank;

    private List<Tank> enemyTanks ;

    private List<Wall> walls;

    List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    List<Wall> getWalls() {
        return walls;
    }

    private GameClient(){
        this.playertank = new Tank(400,100,Direction.DOWN);
        this.enemyTanks = new ArrayList<>(12);
        this.walls =  Arrays.asList(
                new Wall(200,140,true,15),
                new Wall(200,540,true,15),
                new Wall(100,80,false,15),
                new Wall(700,80,false,15)
        );
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
            this.enemyTanks.add(new Tank(200+j*120,400+40*i,true,Direction.UP));
            }
        }

        this.setPreferredSize(new Dimension(800,600));

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);

        playertank.draw(g);
        for(Tank tank:enemyTanks){
            tank.draw(g);
        }
        for(Wall wall:walls){
            wall.draw(g);
        }

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
        //noinspection InfiniteLoopStatement
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
