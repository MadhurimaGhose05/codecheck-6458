/*
	Shiritori Framework
	Create by chi on 02/19/2017
	説明: shiritoriゲームクラス
*/

package codecheck;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuffer;

public class Shiritori {
	private Referee referee;
	public static ArrayList<String> dict = new ArrayList<String>();

	public Shiritori(ArrayList<String> dict, int portNum) throws IOException {
		//最初の単語を除く
        dict.remove(dict.get(0));
		Shiritori.dict = dict;
		
		//単語制限を検査する
		wordRestriction();

		//制御文字が含まれている場合、削除する。
		deleteControlCharsOfDict();

		//審判プログラムを起動
		createReferee(portNum);

		//プレイヤの参加のまつ
		waitPlayer();
	}

    private void wordRestriction() {
        // //入力単語の単語数の最大は 1000 単語とする。
        if(Shiritori.dict.size()>1001) {
            System.out.println("The max word dictionary size is 1000!");
            System.out.println("Your dictionary size: " + Shiritori.dict.size());
            System.exit(0);
        }
        for(String word : Shiritori.dict) {
            if(word.length()>10000) {
                System.out.println("The length of each word should be less than 10000!");
                System.out.println("Your word size: " + word.length() + ", word: " + word);
                System.exit(0);
            }
        }
    }

	private void waitPlayer() throws IOException {
		referee.waitPlayer();
	}

	//審判の生成
	private void  createReferee(int portNum) {
		referee = new Referee(portNum);
	}

	private static void deleteControlCharsOfDict() {
		for (int i=0; i<dict.size(); i++) {
			Shiritori.dict.set(i, deleteControlChars(Shiritori.dict.get(i)));
		}
	}

	//制御文字が含まれている場合、削除する。
	private static String deleteControlChars(String s) {
	// Control Codes not in (0<= s <= 001f) and (007f <= s <=009f)
		StringBuffer buf = new StringBuffer();
		for(char ch : s.toCharArray()){
			if(ch > 0x1f && !(ch >= 0x7f && ch <= 0x9f)){
				buf.append(ch);
			}
		}
		return buf.toString();
	}
}