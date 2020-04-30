import bot.data.match.MatchText;
import bot.data.textfile.TextFile;

public class process {
	
	public void process() {
		
		/* 명사를 가져오고 조사를 가져와서 합친 다음 noun테이블에 합친 단어들을 저장하는 과정
		 * String josaSQL = "SELECT WORD FROM JOSA"; String nounSQL =
		 * "SELECT WORD FROM DICTIONARY WHERE PART = '명사'";
		 * 
		 * DBselect josaSelect = new DBselect(josaSQL); String[] josaList =
		 * josaSelect.selectWord();
		 * 
		 * DBselect nounSelect = new DBselect(nounSQL); String[] nounList =
		 * nounSelect.selectWord();
		 * 
		 * List<String> nounsList = new ArrayList<String>();
		 * 
		 * for(int i=0; i < nounList.length; i++) { for(int j = 0; j < josaList.length;
		 * j++) {
		 * 
		 * nounsList.add(nounList[i]+josaList[j]); } } System.out.println("완료");
		 * 
		 * String[] nounsLists = nounsList.toArray(new String[nounsList.size()]);
		 * 
		 * String nounSQL1 = "INSERT INTO NOUN VALUES(?,?)"; DBinsert nounInsert = new
		 * DBinsert(nounSQL1); nounInsert.insertTwo(nounsLists);
		 */
		
		
		
		
		/* 합쳐진 명사를 가져와서 텍스트필터와 비교하는것
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
	}

}
