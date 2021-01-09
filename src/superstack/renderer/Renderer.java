/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.renderer;

import java.awt.Color;
import java.awt.Graphics;
import superstack.Stack;

/**
 *
 * @author Asus
 */
public class Renderer {
    
    private static int w = Stack.WIDTH, h = Stack.HEIGHT;
	
    public static int[] pixels = new int[w * h];
    
    public static void renderBackground(int color){
        for(int i=0;i < pixels.length;i++){
            pixels[i] = color;
        }
    }
    
    public static void renderSprite(Sprite s, int xp, int yp){
        if(xp < -s.width || yp  < - s.height || xp >= w || yp >= h) return;
		
        for(int y = 0; y < s.height; y++) {
			
            int yy = y + yp;
            if(yy >= h || yy < 0) continue;
			
            for(int x = 0; x < s.width; x++) {
		int xx = x + xp;
		if(xx >= w || xx < 0) continue;
				
		int col = s.pixels[x + y * s.width];
		pixels[xx + yy * w] = col;
				
            }
        }
    }
    
}
