package bot.data.match;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bot.data.db.DBselect;
import bot.data.db.DBupdate;

public class MatchText {
	
	public MatchText() {
		
	}

	public void matchWord(String text, String firstSQL, String secondSQL)
			throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		List<String> lists = new ArrayList<String>();

		DBselect ts = new DBselect(firstSQL);
		String[] wordList = ts.selectWord();
		
		DBselect tc = new DBselect(secondSQL);
		String[] accList = tc.selectAccount();
		
		
		for (int i = 0; i < wordList.length; i++) {
			
			if (text.contains(wordList[i])) {
				System.out.printf("%s는 담겨져있습니다\n", wordList[i]);
				System.out.println(accList[i]);
				
				int accOne = Integer.parseInt(accList[i]);
				int accTwo = accOne + 1;
				System.out.println(accTwo);
				lists.add(Integer.toString(accTwo));
				
			} else {
				System.out.printf("%s는 담겨져있지 않습니다\n", wordList[i]);
				System.out.println(accList[i]);
				
				int accOne = Integer.parseInt(accList[i]);
				int accTwo = accOne - 1;
				System.out.println(accTwo);
				lists.add(Integer.toString(accTwo));
				
			}
		}
		
		String[] accLists = lists.toArray(new String[lists.size()]);
		
		System.out.println("            ************************************************************");
				
		DBupdate up = new DBupdate();
		
		up.update(wordList, accLists);

	}

}
