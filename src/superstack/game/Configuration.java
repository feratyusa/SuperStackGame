/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class Configuration {
    
    public static final String SAVE_PATH = "config.xml";
    public static final String BEST_SCORE = "best";
    Properties properties = new Properties();
    
    public void saveConfig(int value){
        try{
            File file = new File(SAVE_PATH);
            boolean exist = file.exists();
            if(!exist) file.createNewFile();
            
            OutputStream write = new FileOutputStream(SAVE_PATH);
            properties.setProperty(BEST_SCORE, Integer.toString(value));
            properties.storeToXML(write, "Options");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadConfig(String path){
        try{
            InputStream read = new FileInputStream(path);
            properties.loadFromXML(read);
            String best = properties.getProperty(BEST_SCORE);
            setBest(Integer.parseInt(best));
            read.close();
        }catch(FileNotFoundException e){
            saveConfig(0);
            loadConfig(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setBest(int best){
        Game.bestScore = best;
    }
}
