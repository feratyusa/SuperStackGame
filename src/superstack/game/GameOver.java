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
import static superstack.game.Game.bestScore;
import superstack.input.Keyboard;
import superstack.renderer.Renderer;

/**
 *
 * @author Asus
 */
public class GameOver extends Game{
    
    public final String RETRY = "Try Again?";
    public final String BACK_TO_MENU = "Back to Menu";
    public String[] options;
    public int selected;
    
    public GameOver(){
        this.options = new String[]{RETRY, BACK_TO_MENU};
        this.selected = 0;
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
                Stack.gameStatus = Stack.GAME_MENU;
            }
        }
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
        
        // Game Over
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(theme.getTitleColor());
        s = "GAME OVER";
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 - 150);
        
        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(theme.getTextColor());
        s = "Score : "+Playing.score;
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 - 85);
        
        // Best Score
        s = "Best Score : "+bestScore;
        w = g.getFontMetrics().stringWidth(s) / 2;
        g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 - 55);
        
        // Options Menu
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        for(int i=0;i<options.length;i++){
            if(this.selected == i) g.setColor(Color.GREEN);
            else g.setColor(theme.getTextColor());
            s = options[i];
            w = g.getFontMetrics().stringWidth(s) / 2;
            g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, Stack.HEIGHT * Stack.scale / 2 + (i+1)*40);
        }
    }
    
    
}
