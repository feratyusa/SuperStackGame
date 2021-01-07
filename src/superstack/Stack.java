/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.Timer;

import superstack.game.Game;
import superstack.game.GameOver;
import superstack.game.MainMenu;
import superstack.game.Playing;
import superstack.input.Keyboard;
import superstack.renderer.Renderer;


/**
 *
 * @author Asus
 */
public class Stack extends Canvas implements ActionListener{
    
    public static final int WIDTH = 250, HEIGHT = 300;
    public static float scale = 2.0f;
    
    public static int GAME_MENU = 0, GAME_PLAYING = 1, GAME_OVER = 2;
    
    public Timer timer;
    
    public Game game;
    public MainMenu menu;
    public Playing playing;
    public GameOver over;
    
    public Keyboard keys;
    
    public static int gameStatus = GAME_MENU; // 0 = Main Menu, 1 = Playing
    public boolean running = false;
    
    public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
   
    public Stack(){
        setPreferredSize(new Dimension((int)(WIDTH*scale), (int)(HEIGHT*scale)));
        
        game = new Game();
        menu = new MainMenu();
        playing = new Playing();
        over = new GameOver();
        
        timer = new Timer(10, this);
        
        keys = new Keyboard();
        addKeyListener(keys);
    }
    
    public void init(){
        game.load();
        timer.start();
        requestFocus();
    }
    
    public void run(){
        if(gameStatus == GAME_PLAYING){
            if(!running){
                playing.init();
                running = true;
            }
        }
        else if(gameStatus == GAME_OVER){
            running = false;
        }
        render();
        update();
    }
    
    public void update(){
        // Main Menu
        if(gameStatus == GAME_MENU){
            menu.update();
        }
        // Playing
        else if(gameStatus == GAME_PLAYING){
            playing.update();
        }
        // Game Over
        else if(gameStatus == GAME_OVER){
            over.update();
        }
        keys.update();
    }
    
    public void render(){
        // Main Menu
        if(gameStatus == GAME_MENU){
            BufferStrategy bs = getBufferStrategy();
            if(bs == null) {
		createBufferStrategy(3);
		return;
            }
            menu.render();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.drawImage(image, 0, 0, (int) (WIDTH * scale), (int) (HEIGHT * scale), null);
            menu.renderText(g);
		
            g.dispose();
            bs.show();
        }
        // Playing
        else if(gameStatus == GAME_PLAYING){
            BufferStrategy bs = getBufferStrategy();
            if(bs == null) {
		createBufferStrategy(3);
		return;
            }
            playing.render();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.drawImage(image, 0, 0, (int) (WIDTH * scale), (int) (HEIGHT * scale), null);
            playing.renderText(g);
		
            g.dispose();
            bs.show();
        }
        // Game Over
        else if(gameStatus == GAME_OVER){
            BufferStrategy bs = getBufferStrategy();
            if(bs == null) {
		createBufferStrategy(3);
		return;
            }
            over.render();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.drawImage(image, 0, 0, (int) (WIDTH * scale), (int) (HEIGHT * scale), null);
            over.renderText(g);
		
            g.dispose();
            bs.show();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        run();
    }
    
}
