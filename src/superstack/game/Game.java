/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.game;

import java.awt.event.KeyEvent;
import superstack.theme.Theme;

/**
 *
 * @author Asus
 */
public class Game {
    
    Configuration config = new Configuration();
    public static Theme theme = new Theme();
    
    public static int bestScore = 0;
    
    public Game(){
        
    }
    
    public void load(){
        config.loadConfig(Configuration.SAVE_PATH);
    }
    
    public void render(){
    }
    
}
