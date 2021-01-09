/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.theme;

import java.awt.Color;

/**
 * List of Theme Color
 * 
 * How to add new Theme: Use Color Type for The Text and Hexode (0#ff...) for
 * the Background and Sprite Color
 *
 * @author Asus
 */
public class Theme {
    
    private final String BLACK_WHITE = "Black and White";
    private final String WHITE_BLACK = "White and Black";
    private final String BLUE_BLACK = "Black and Blue";
    private String[] themeOption;
    private int selectedTheme;
    
    public Theme(){
        this.themeOption = new String[]{BLACK_WHITE, WHITE_BLACK, BLUE_BLACK};
        this.selectedTheme = 0;   
    }
    
    public int getSelectedTheme(){
        return this.selectedTheme;
    }
    
    public int getThemeSize(){
        return this.themeOption.length;
    }
    
    public void changeSelectedTheme(int value){
        this.selectedTheme += value;
    }
    
    public String getThemeOption(){
        return this.themeOption[this.selectedTheme];
    }
    
    public Color getTitleColor(){
        switch (this.selectedTheme) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.RED;
            default:
                return null;
        }
    }
    
    public Color getTextColor(){
        switch (this.selectedTheme) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.BLUE;
            default:
                return null;
        }
    }
    
    public int getBackgroundColor(){
        switch (this.selectedTheme) {
            case 0:
                return 0xfff4f4f4; // White-Grey
            case 1:
                return 0xff000000; // Black
            case 2:
                return 0xff000000;
            default:
                return -1;
        }
    }
    
    public int getSpriteColor(){
        switch (this.selectedTheme) {
            case 0:
                return 0xff000000; // Black
            case 1:
                return 0xfff4f4f4; // White-Gray
            case 2:
                return 0xff0011ff;
            default:
                return -1;
        }
    }
}
