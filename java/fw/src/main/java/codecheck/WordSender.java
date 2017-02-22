package codecheck;
/*
WordSender class of Shiritori Framework
Create by chi on 02/19/2017
*/

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.lang.NullPointerException;

class WordSender extends Thread {

private final Socket socket;

public WordSender(Socket socket) {
    this.socket = socket;
}

@Override
public void run() {
    try {
        Player.word = Player.startWord;
        while(true){
            synchronized(this) {
            OutputStream os = socket.getOutputStream();
            if(Player.word != null) {
                os.write(Player.word.getBytes());
                Player.word = null;
            }
            }
        }
    } catch (NullPointerException e) {
        //connectionが存在しない場合、ローカルで実行する
        String word = Player.getShiritoriWord(Player.startWord);
        System.out.println(word);
        System.exit(0);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        System.exit(1);
    }
}

}