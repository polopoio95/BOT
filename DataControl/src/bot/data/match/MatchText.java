package bot.data.match;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bot.data.db.DBselect;
import bot.data.db.DBupdate;

public class MatchText {
	
	public MatchText() {
		
	}

	//text�� ���ͳ���  firstSQL�� �ܾ� �������� SQL secondSQL�� �� �ܾ��� ��Ȯ���� �������� SQL
	public void matchWord(String text, String firstSQL, String secondSQL)
			throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		List<String> onePointUp = new ArrayList<String>();
		List<String> onePointDown = new ArrayList<String>();

		DBselect ts = new DBselect(firstSQL);
		String[] wordList = ts.selectWord();
		int up = 0;
		int down = 0;
		
		/*
		 * DBselect tc = new DBselect(secondSQL); String[] accList =
		 * tc.selectAccuracy();
		 */
		
		
		for (int i = 0; i < wordList.length; i++) {
			
			if (text.contains(wordList[i])) {
				System.out.printf("%s�� ������ֽ��ϴ�\n", wordList[i]);
				onePointUp.add(wordList[i]);
				up++;
				
			} else {
				System.out.printf("%s�� ��������� �ʽ��ϴ�\n", wordList[i]);
				onePointDown.add(wordList[i]);
				down++;
				
			}
		}
		
		String[] upList = onePointUp.toArray(new String[onePointUp.size()]);
		String[] downList = onePointDown.toArray(new String[onePointUp.size()]);
		
		System.out.println("            ************************************************************");
				
		DBupdate UpdateForUp = new DBupdate();
		UpdateForUp.UpPointupdate(upList);
		System.out.println(up);
	//	DBupdate UpdateForDown = new DBupdate();
		//UpdateForDown.DownPointupdate(downList);
	//	System.out.println(down);

	}

}
