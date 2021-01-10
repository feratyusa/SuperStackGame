/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.resource;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class Resources {
    
    private static File BGM;
    private static File OVER;
    
    public Resources(){
        BGM = new File("res/bgm.wav");
        OVER = new File("res/gameover.wav");
    }
    
    public File getBGM(){
        return this.BGM;
    }
    
    public File getOver(){
        return this.OVER;
    }
}
