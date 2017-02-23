package codecheck;

/*
WordReceiver class of Shiritori Framework
Create by chi on 02/19/2017
*/

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class WordReceiver extends Thread {

private final Socket socket;

public WordReceiver(Socket socket) {
    this.socket = socket;
}

@Override
public void run() {
    try {
        byte[] b = new byte[1024];
        InputStream is;
        String word;

        while(true){
            synchronized(this) {
                if(Player.word==null) {
                    is = this.socket.getInputStream();
                    word = new String(b, 0, is.read(b));
                    
                    if(Player.playerID  ==null && 
                        (word.equals("FIRST") || word.equals("SECOND"))) {
                        Player.playerID = word;
                        word = word.equals("FIRST") ? "SECOND" : "FIRST";
                    } else {
                        word = Player.getShiritoriWord(word);
                    }
                    
                    Player.word = word;

                    this.notify();

                    // if(word.equals("")) {
                    //     Player.word = Player.playerID.equals("FIRST") ? "SECOND" : "FIRST";
                    //     System.out.println("WIN - " + Player.word);

                    //     System.exit(0);
                    // }
                }
            }

        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        System.exit(1);
    }
}

}