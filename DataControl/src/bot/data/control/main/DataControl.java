package bot.data.control.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import bot.data.db.DBupdate;
import bot.data.match.MatchText;
import bot.data.textfile.TextFile;

public class DataControl {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException, SQLException {
		// TODO Auto-generated method stub

		String url = "C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\DataFilter\\germany.txt";
		TextFile tf = new TextFile();
		String text = Arrays.toString(tf.fileRead(url));
		//텍스트 파일을 한뭉탱이로 가져오기

		System.out.println(text);

		String sql = "SELECT WORD FROM TEST";
		String aql = "SELECT COUNT FROM TEST";

		MatchText match = new MatchText();

		match.matchWord(text, sql, aql);

		/*
		 * String sql = "UPDATE TEST SET COUNT = '44' WHERE WORD = '기원'";
		 * 
		 * DBupdate up = new DBupdate(); up.update(sql);
		 */

		/*
		 * String[] count = { "23", "76", "34", "99" }; String[] word = { "기원", "가디언",
		 * "누비", "안녕" };
		 * 
		 * for (int i = 0; i < count.length; i++) {
		 * 
		 * StringBuilder sql = new StringBuilder();
		 * 
		 * sql.append("UPDATE TEST SET COUNT = '").append(count[i]).
		 * append("' WHERE WORD = '").append(word[i]) .append("'");
		 * 
		 * System.out.println(sql.toString());
		 * 
		 * }
		 */

	}

}
