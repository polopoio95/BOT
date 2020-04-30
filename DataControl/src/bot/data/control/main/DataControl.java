package bot.data.control.main;

import java.io.IOException;
import java.sql.SQLException;

import bot.data.db.DBselect;
import bot.data.match.MatchText;
import bot.data.textfile.TextFile;

public class DataControl {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException, SQLException {
		// TODO Auto-generated method stub

		/*
		 * String url =
		 * "C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\DataFilter\\grimbrother.txt";
		 * TextFile tf = new TextFile(); String text =
		 * Arrays.toString(tf.returnArrange(url));
		 * 
		 * String firstSQL = "SELECT WORD FROM DICTIONARY"; String secondSQL =
		 * "SELECT ACCURACY FROM DICTIONARY";
		 * 
		 * MatchText match = new MatchText(); match.matchWord(text, firstSQL,
		 * secondSQL);
		 */

		/*
		 * String url =
		 * "C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\DataFilter\\국립국어원자료\\미팅.txt";
		 * TextFile txt = new TextFile(); String filterList = txt.excepteKorean(url);
		 * 
		 * String nounSQL = "SELECT WORD FROM NOUN";
		 * 
		 * String accSQL = "SELECT ACCURACY FROM NOUN";
		 * 
		 * MatchText match = new MatchText(); match.matchWord(filterList, nounSQL,
		 * accSQL);
		 */
		
		TextFile text = new TextFile();
		String url = "C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\DataFilter\\국립국어원자료\\미팅.txt";
		
		System.out.println(text.excepteKorean(url));
		

	}

}
