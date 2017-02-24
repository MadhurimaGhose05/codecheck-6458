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

    public void setDictFromFile() {
        // try {
            // Stream<String> stream = Files.lines(Paths.get("dict.txt"));
            // Player.dict.addAll(stream.collect(Collectors.toList()));
            // for (String s : Player.dict ) {
                // System.out.println("input: " + s);
            // }
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

    @Override
    public void setStartWord(String word) {
        Player.startWord = word;
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
        // ai.setDict(new ArrayList<String>(Arrays.asList(args)));
        ai.options(args);
        configRefereeInfo("127.0.0.1", 9995);
        joinGame();
    }
}