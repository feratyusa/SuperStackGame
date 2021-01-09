/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import superstack.Stack;
import superstack.gameobject.GameObject;
import superstack.renderer.Renderer;
import superstack.renderer.Sprite;

/**
 *
 * @author Asus
 */
public class Playing extends Game{
    
    private Random rand = new Random();
    
    public static List<GameObject> objects;
    boolean spawnNew = false;
    public static boolean animate = false, animating = false;
    public static boolean gameOver = false;
    
    Sprite objectSprite;
    
    public static int score = 0;
    
    public Playing(){
        
    }
    
    public void init(){
        objectSprite = new Sprite(100, 29, theme.getSpriteColor());
        objects = new ArrayList<GameObject>();
        objects.add(new GameObject(Stack.WIDTH/2 - objectSprite.width/2, Stack.HEIGHT - 30, objectSprite, false));
        objects.add(new GameObject(Stack.WIDTH/2 - objectSprite.width/2, Stack.HEIGHT - 30 * 2, objectSprite, false));
        objects.add(new GameObject(Stack.WIDTH/2 - objectSprite.width/2, Stack.HEIGHT - 30 * 3, objectSprite, false));
        spawnNew = true;
        gameOver = false;
        animate = false;
        animating = false;
        
        if(score > bestScore){
            bestScore = score;
            Game.bestScore = score;
        }
        score = 0;
    }
    
    public void update(){
        if(gameOver){
            if(score > bestScore){
                config.saveConfig(score);
                bestScore = score;
            }
            Stack.gameStatus = Stack.GAME_OVER;
            return;
        }
        
        for(int i=0;i<objects.size();i++){
            objects.get(i).update();
        }
        animate = false;
        
        if(spawnNew){
            if(!animating){
                score++;
                objectSprite = new Sprite(objects.get(objects.size()-1).width, 29, theme.getSpriteColor());
                objects.add(new GameObject(rand.nextInt(Stack.WIDTH)-objectSprite.width, Stack.HEIGHT - 30 *4, objectSprite, true));
                spawnNew = false;
            }
            else{
                boolean endOfAnim = true;
                for(int i=0;i<objects.size();i++){
                    if(objects.get(i).animate){
                        endOfAnim = false;
                    }
                }
                if(endOfAnim) animating = false;
            }
        }
        else{
            if(!objects.get(objects.size() - 1).moving){
                spawnNew = true;
                animate = true;
                animating = true;
            }
        }
    }
    
    public void render(){
        Renderer.renderBackground(theme.getBackgroundColor());

	for(int i = 0; i < objects.size(); i++) {
            objects.get(i).render();
	}
		
	for(int i = 0; i < Stack.pixels.length; i++) {
		Stack.pixels[i] = Renderer.pixels[i];
	}
    }
    
    public void renderText(Graphics2D g){
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(theme.getTextColor());
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
	String s = "Score: " + Playing.score;
	int w = g.getFontMetrics().stringWidth(s) / 2;
	g.drawString(s, Stack.WIDTH * Stack.scale / 2 - w, 80);
                
	String best = "Best: " + Game.bestScore;
	int bw = g.getFontMetrics().stringWidth(best) / 2;
	g.drawString(best, Stack.WIDTH * Stack.scale / 2 - bw, 120);
    }
}
