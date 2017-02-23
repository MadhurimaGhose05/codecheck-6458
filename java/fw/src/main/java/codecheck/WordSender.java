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

            // Player.word = Player.getShiritoriWord(Player.startWord);
            // Player.word = Player.getShiritoriWord(Player.startWord);
            Player.word = "my name?";

            while(true){
                synchronized(this) {
                    OutputStream os = socket.getOutputStream();
                    if(Player.word != null) {
                        if(Player.word.equals("SECOND") && Player.playerID.equals("FIRST")) {
                            Player.word = Player.getShiritoriWord(Player.startWord);


                        }

                        if(Player.word.equals("my name?") || Player.word.equals("FIRST")
                             || Player.word.equals("SECOND")) {
                        } else {
                            if(Player.word.equals("")) {
                                System.out.println(Player.playerID + " (NG): " + Player.word);
                                Player.word = Player.playerID.equals("FIRST") ? "SECOND" : "FIRST";
                                System.out.println("WIN - " + Player.word);
                                System.exit(0);
                            } else {
                                System.out.println(Player.playerID + " (OK): " + Player.word);
                            }
                        }

                        os.write(Player.word.getBytes());
                        Player.word = null;
                    }
                }
            }
        } catch (NullPointerException e) {
            //connectionが存在しない場合、ローカルで実行する
            Player.word = Player.getShiritoriWord(Player.startWord);
            System.out.println(Player.word);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }

}