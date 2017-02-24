/*
    AI class of Shiritori Framework
    Create by chi on 02/19/2017
    説明: shiritoriゲームのplayerクラスから
    継承したAIクラス
*/

package codecheck;

import java.util.Arrays;
import java.util.stream.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AI extends Player {

    @Override
    public void setDict(ArrayList<String> dict) {
        setStartWord(dict.get(0));
        dict.remove(dict.get(0));
        Player.dict = dict;
    }

    @Override
    public void setStartWord(String word) {
        Player.startWord = word;
    }

    @Override
    public void wordRestriction() {
        // //入力単語の単語数の最大は 1000 単語とする。
        if(Player.dict.size()>1001) {
            System.out.println("The max word dictionary size is 1000!");
            System.out.println("Your dictionary size: " + Player.dict.size());
            System.exit(0);
        }
        for(String word : Player.dict) {
            if(word.length()>10000) {
                System.out.println("The length of each word should be less than 10000!");
                System.out.println("Your word size: " + word.length() + ", word: " + word);
                System.exit(0);
            }
        }
    }

    public void setDictFromFile() {
        FileReader files = null;
        try {
            files = new FileReader("dict.txt");
            Scanner input = new Scanner(files);
            String line = input.nextLine();
            String[] words = line.split(" ");
            Player.dict = new ArrayList<String>(Arrays.asList(words));
            
            setStartWord(Player.dict.get(0));

            Player.dict.remove(Player.dict.get(0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void options(String[] args) {
           for (int i = 0; i < args.length; i++) {
                switch (args[i].charAt(0)) {
                case '-':
                    if(args[i].charAt(1)=='f') {
                        setDictFromFile();
                    }
                    break;
                default:
                    setDict(new ArrayList<String>(Arrays.asList(args)));
                    break;
                }
        }
    }

    public static void main(String[] args) {
        if(args.length<0) {
            System.out.println("Please insert word dictionary!");
            System.exit(1);
        }
        
        AI ai = new AI();
        
        //optionsによってword dictionaryの設定が変わる
        ai.options(args);
        
        //単語群のサイズ、一つの単語の長さなど制限を指定する
        ai.wordRestriction();

        //接続する審判プログラムを指定
        configRefereeInfo("127.0.0.1", 9995);

        //ゲームに参加
        joinGame();
    }
}