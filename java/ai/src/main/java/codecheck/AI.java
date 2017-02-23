/*
    AI class of Shiritori Framework
    Create by chi on 02/19/2017
    説明: shiritoriゲームのplayerクラスから
    継承したAIクラス
*/

package codecheck;

import java.util.Arrays;
import java.util.ArrayList;

public class AI extends Player {

    @Override
    public void setDict(ArrayList<String> dict) {
        setStartWord(dict.get(0));
        dict.remove(dict.get(0));
        Player.dict = dict;
    }
    
    // public void setDictFromFile() {
        
    // }

    @Override
    public void setStartWord(String word) {
        Player.startWord = word;
    }



    public static void main(String[] args) {
        if(args.length<0) {
            System.out.println("Please insert word dictionary!");
            System.exit(1);
        }
        
        AI ai = new AI();
        ai.setDict(new ArrayList<String>(Arrays.asList(args)));
        configRefereeInfo("127.0.0.1", 9995);
        joinGame();
    }
}