/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/**
 *
 * @author Asus
 */
public class Main{
    
    public Stack stack;
    public JFrame frame;
  
    public boolean running = false;
	
    public static BufferedImage image = new BufferedImage(Stack.WIDTH, Stack.HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    public Main(){
        stack = new Stack();
        
        frame = new JFrame();
        frame.setTitle("Super Stack Game");
        frame.setResizable(false);
        frame.add(stack);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        start();
    }
    
    public void start(){
        stack.init();
    }
    
    public void stop(){
        
    }

    public static void main(String[]args){
        new Main();
    }
    
}
