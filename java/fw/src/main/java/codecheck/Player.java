/*
    Player class of Shiritori Framework
    Create by chi on 02/19/2017
    説明: shiritoriゲームのプレイヤクラス
*/

package codecheck;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuffer;

abstract class Player {
	public static String word = null; //受け取る、言い出す単語
    public static ArrayList<String> dict; //単語辞書
    public static String startWord = null;

    private static Socket socket;

    public static String getShiritoriWord(String inputWord){
        dict.remove(inputWord);

        String tail = inputWord.substring(inputWord.length()-1,inputWord.length());  

        for(String word : dict) {
            if(tail.equals(word.substring(0,1))){
                dict.remove(word);
                return word;
            }
        }

        return " ";
    }

    //単語辞書の初期化を行う
    abstract public void setDict(ArrayList<String> dict);

    //最初の単語を指定する
    abstract public void setStartWord(String word);

    //単語の制約を定義する
    abstract public void wordRestriction();

    //制御文字が含まれている場合、削除する。
    private static void deleteControlCharsOfDict() {
        for (int i=0; i<dict.size(); i++) {
            dict.set(i, deleteControlChars(dict.get(i)));
        }
    }

    //制御文字が含まれている場合、削除する。
    private static String deleteControlChars(String s){
        // Control Codes 0 <= 001f and 007f
        StringBuffer buf = new StringBuffer();
        for(char ch : s.toCharArray()){
            if(ch > 0x1f && !(ch >= 0x7f && ch <= 0x9f)){
                buf.append(ch);
            }
        }
        return buf.toString();
    }


    //審判となるサーバーのアドレスとポート番号を設定
    public static void configRefereeInfo(String refereeIP, int refereePort) {
        try {
            if(refereeIP==null) {
                socket = new Socket("127.0.0.1", 9995);
            } else {
                socket = new Socket(refereeIP,refereePort);
            }
        } catch (ConnectException e) {
            // e.printStackTrace();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public static void joinGame() {
        
        //制御文字が含まれている場合、削除する。
        deleteControlCharsOfDict();

        new WordSender(socket).start();
        new WordReceiver(socket).start();
    }
}