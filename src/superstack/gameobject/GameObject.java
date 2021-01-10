/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.gameobject;

import java.awt.event.KeyEvent;
import java.util.Random;
import superstack.Stack;
import superstack.game.Game;
import superstack.game.Playing;
import superstack.input.Keyboard;
import superstack.renderer.Renderer;
import superstack.renderer.Sprite;

/**
 *
 * @author Asus
 */
public class GameObject extends Game{
    
    public float x, y, newY;
    public int width, height;
    public Sprite sprite;
    public boolean moving, animate = false;
    public double speed = 1.0, maxSpeed = 3.0;

    public Random rand = new Random();
    
    private boolean goldColor = false;
    
    public GameObject(int x, int y, Sprite sprite, boolean moving){
        this.x = x;
	this.y = y;
	this.newY = y;
	this.width = sprite.width;
	this.height = sprite.height;
	this.sprite = sprite;
	this.moving = moving;
        this.speed = rand.nextDouble() * (maxSpeed - 1.0) + 1.0;
	if(rand.nextInt(2) == 0) this.speed *= -1;
    }
    
    public void setWidth(int newWidth){
        if(goldColor){
            this.sprite = new Sprite(newWidth, height, 0xffffd700);
            goldColor = false;
        }
        else{
            this.sprite = new Sprite(newWidth, height, theme.getSpriteColor());
        }
	this.width = newWidth;
    }
    
    public void update(){
        if(moving){
            x += speed;
            if(x + width >= Stack.WIDTH){
                x = Stack.WIDTH - width;
                speed *= -1;
            }
            if(x < 0){
                x = 0;
                speed *= -1;
            }
            if(Keyboard.keyUp(KeyEvent.VK_SPACE)){
                this.moving = false;
                int previousWidth = Playing.objects.get(Playing.objects.size() - 2).width;
                int newWidth;
                if(rand.nextInt(5) == 3){
                    newWidth = 100;
                    goldColor = true;
                }
                else{
                     newWidth = (int) ((previousWidth - Math.abs(Stack.WIDTH / 2 - (x + width / 2))));
                }
                if(newWidth < 0) {
                    Playing.gameOver = true;
                    return;
		}
                int widthPercentage = (int) (newWidth*100/previousWidth);
                if(goldColor){
                    comment = gameComment.getComment("GOLDEVENT");
                }
                else if(widthPercentage > 90){
                    comment = gameComment.getComment("EXCELLENT");
                }
                else if(widthPercentage > 50){
                    comment = gameComment.getComment("GOOD");
                }
                else if(widthPercentage > 0){
                    comment = gameComment.getComment("BAD");
                }
                commentTime = 100;
		setWidth(newWidth);
            }
        }
        if(Playing.animate){
            this.animate = true;
            newY += height + 1;
            x = Math.round(x);
        }
        if(animate){
            boolean hasAnimated = false;
            if(y < newY){
                y++;
                hasAnimated = true;
            }
            if(x + width/2 < Stack.WIDTH / 2){
                x++;
                hasAnimated = true;
            }
            else if(x + width/2 > Stack.WIDTH / 2){
                x--;
                hasAnimated = true;
            }
            if(!hasAnimated) animate = false;
        }
    }
    
    public void render(){
        Renderer.renderSprite(sprite, (int) x, (int) y);
    }
}
