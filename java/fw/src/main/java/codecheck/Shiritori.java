/*
	Shiritori Framework
	Create by chi on 02/19/2017
	説明: shiritoriゲームクラス
*/

package codecheck;
import java.io.IOException;
import java.util.ArrayList;

public class Shiritori {
	private Referee referee;
	public static ArrayList<String> dict = new ArrayList<String>();

	public Shiritori(ArrayList<String> dict, int portNum) throws IOException {
		Shiritori.dict = dict;
		createReferee(portNum);
		waitPlayer();
	}

	public void waitPlayer() throws IOException {
		referee.waitPlayer();
	}

	//審判の生成
	public void  createReferee(int portNum) {
		referee = new Referee(portNum);
	}
}