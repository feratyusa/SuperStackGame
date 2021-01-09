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
    private final String THEME_GAME = "Theme"; 
    private final String QUIT_GAME = "Quit Stacking";
    private final String[] mainMenu;
    private int selectedOption;
    
    public MainMenu(){
        this.mainMenu = new String[]{START_GAME, THEME_GAME, QUIT_GAME};
        this.mainMenu[1] = "<< "+THEME_GAME+": "+theme.getThemeOption()
                            +" >>";
        this.selectedOption = 0;
    }
    
    public void render(){
        Renderer.renderBackground(theme.getBackgroundColor());
        
        for(int i = 0; i < Stack.pixels.length; i++) {
            Stack.pixels[i] = Renderer.pixels[i];
        }
    }
    
    public void renderText(Graphics2D g){
        String s;
        int w;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Judul
        g.setColor(theme.getTitleColor());
        g.setFont(new Font("Arial", Font.BOLD, 40));
        s = "SUPER STACK GAME";
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 - 100);
        
        g.setColor(theme.getTextColor());
        // Main Menu Body
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        int lastPos = 0;
        for(int i=0;i<mainMenu.length;i++){
            if(this.selectedOption == i) g.setColor(Color.GREEN);
            else g.setColor(theme.getTextColor());
            s = mainMenu[i];
            w = g.getFontMetrics().stringWidth(s) / 2;
            g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 + i * 40);
            lastPos = i;
        }
        
        // Best Score
        lastPos++;
        g.setColor(theme.getTextColor());
        s = "Best Score : "+bestScore;
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 + lastPos * 40);
        
    }
    
    public void update(){
        if(Keyboard.keyUp(KeyEvent.VK_UP)){
            if(this.selectedOption <= 0) this.selectedOption = this.mainMenu.length - 1;
            else this.selectedOption--;
        }
        else if(Keyboard.keyUp(KeyEvent.VK_DOWN)){
            if(this.selectedOption >= this.mainMenu.length - 1) this.selectedOption = 0;
            else this.selectedOption++;
        }
        else if(Keyboard.keyUp(KeyEvent.VK_RIGHT)){
            if(this.selectedOption == 1){
                if(theme.getSelectedTheme() >= theme.getThemeSize() - 1) theme.changeSelectedTheme(-(theme.getThemeSize()-1));
                else theme.changeSelectedTheme(1);
                this.mainMenu[1] = "<< "+THEME_GAME+": "+theme.getThemeOption()+" >>";
            }
        }
        else if(Keyboard.keyUp(KeyEvent.VK_LEFT)){
            if(this.selectedOption == 1){
                if(theme.getSelectedTheme() <= 0) theme.changeSelectedTheme(theme.getThemeSize()-1);
                else theme.changeSelectedTheme(-1);
                this.mainMenu[1] = "<< "+THEME_GAME+": "+theme.getThemeOption()+" >>";
            }
        }
        else if(Keyboard.keyUp(KeyEvent.VK_SPACE)){
            if(this.selectedOption == 0){
                Stack.gameStatus = Stack.GAME_PLAYING;
            }
            else if(this.selectedOption == 2){
                System.exit(0);
            }
        }
    }
    
}
