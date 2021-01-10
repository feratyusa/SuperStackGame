/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstack.gamecomment;

import java.util.Random;
import superstack.game.Game;

/**
 *
 * @author Asus
 */
public class GameComment extends Game{
    
    public static String[] BAD;
    public static String[] GOOD;
    public static String[] EXCELLENT;
    public static String[] GOLDEVENT;
    
    private Random rand = new Random();
    
    public GameComment(){
        BAD = new String[]{"OOF", "THAT'S BAD", "BAD BABABOEY", "OOF BRUH", "YOU SHOULD QUIT",
                    "PRESS Q TO QUIT THE GAME", "GOONA CRY?", "MEH"};
        GOOD = new String[]{"NICE!", "KEEP UP!", "P BUTTON IS A LIE", 
            "GREAT", "NICE JOB BRO!", "NOT BAD", "GOOD JOOB", "BABBOEY"};
        EXCELLENT = new String[]{"PRESS P TO PAUSE THE GAME", "EXCELLENT",
                    "MAGNIFICENT", "SUPERRRRRRRR", "PERFECT BRO!", "YOOOOO EXCELLENT BRO!"};
        GOLDEVENT = new String[]{"GOLD BAR TIME!", "THAT'S GOLD", "LUCKY",
                    "YOU ONE LUCKY-", "1 OUT OF 5 CHANCES", "DON'T WASTE IT",
                    "IT'S PRETTY HARD YOU KNOW"};
    }
    
    public String getComment(String comment){
        switch (comment) {
            case "BAD":
                return BAD[rand.nextInt(BAD.length-1)];
            case "GOOD":
                return GOOD[rand.nextInt(GOOD.length-1)];
            case "EXCELLENT":
                return EXCELLENT[rand.nextInt(EXCELLENT.length-1)];
            case "GOLDEVENT":
                return GOLDEVENT[rand.nextInt(GOLDEVENT.length-1)];
            default:
                break;
        }
        return null;
    }
}
