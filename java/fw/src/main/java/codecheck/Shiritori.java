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

	public Shiritori() throws IOException {
		createReferee();
		waitPlayer();
	}

	public Shiritori(ArrayList<String> dict) throws IOException {
		Shiritori.dict = dict;
		createReferee();
		waitPlayer();
	}

	public void waitPlayer() throws IOException {
		referee.waitPlayer();
	}

	//審判の生成
	public void  createReferee() {
		referee = new Referee();
	}
}