/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.game;

import java.awt.event.KeyEvent;
import superstack.Audio.Audio;
import superstack.gamecomment.GameComment;
import superstack.resource.Resources;
import superstack.theme.Theme;

/**
 *
 * @author Asus
 */
public class Game {
    
    Configuration config = new Configuration();
    
    public static Resources resources = new Resources();
    
    public static Theme theme = new Theme();
    
    public static GameComment gameComment = new GameComment();
    public static int commentTime = 0;
    public static String comment;
    
    public static int bestScore = 0;
    
    public static boolean goldColor = false;
    
    public Game(){
        
    }
    
    public void load(){
        config.loadConfig(Configuration.SAVE_PATH);
    }
    
    public void render(){
    }
    
}
