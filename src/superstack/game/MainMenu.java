/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import superstack.Stack;
import superstack.input.Keyboard;

import superstack.renderer.Renderer;

/**
 *
 * @author Asus
 */
public class MainMenu extends Game{
    
    private final String START_GAME = "Start Stacking";
    private final String QUIT_GAME = "Quit Stacking";
    private final String[] mainMenu;
    private int selected;
    
    public MainMenu(){
        this.mainMenu = new String[]{START_GAME, QUIT_GAME};
        this.selected = 0;
    }
    
    public void render(){
        Renderer.renderBackground();
        
        for(int i = 0; i < Stack.pixels.length; i++) {
            Stack.pixels[i] = Renderer.pixels[i];
        }
    }
    
    public void renderText(Graphics2D g){
        String s;
        int w;
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Judul
        g.setFont(new Font("Arial", Font.BOLD, 40));
        s = "SUPER STACK GAME";
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 - 100);
        
        // Main Menu Body
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        int lastPos = 0;
        for(int i=0;i<mainMenu.length;i++){
            if(this.selected == i) g.setColor(Color.GREEN);
            else g.setColor(Color.BLACK);
            s = mainMenu[i];
            w = g.getFontMetrics().stringWidth(s) / 2;
            g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 + i * 40);
            lastPos = i;
        }
        
        // Best Score
        lastPos++;
        g.setColor(Color.BLACK);
        s = "Best Score : "+bestScore;
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 + lastPos * 40);
        
    }
    
    public void update(){
        if(Keyboard.key(KeyEvent.VK_UP)){
            if(this.selected > 0) this.selected--;
        }
        else if(Keyboard.key(KeyEvent.VK_DOWN)){
            if(this.selected <= 0) this.selected++;
        }
        else if(Keyboard.keyUp(KeyEvent.VK_SPACE)){
            if(this.selected == 0){
                Stack.gameStatus = Stack.GAME_PLAYING;
            }
            else if(this.selected == 1){
                System.exit(0);
            }
        }
    }
    
}
