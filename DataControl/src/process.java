import bot.data.match.MatchText;
import bot.data.textfile.TextFile;

public class process {
	
	public void process() {
		
		/* ��縦 �������� ���縦 �����ͼ� ��ģ ���� noun���̺� ��ģ �ܾ���� �����ϴ� ����
		 * String josaSQL = "SELECT WORD FROM JOSA"; String nounSQL =
		 * "SELECT WORD FROM DICTIONARY WHERE PART = '���'";
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
		 * nounsList.add(nounList[i]+josaList[j]); } } System.out.println("�Ϸ�");
		 * 
		 * String[] nounsLists = nounsList.toArray(new String[nounsList.size()]);
		 * 
		 * String nounSQL1 = "INSERT INTO NOUN VALUES(?,?)"; DBinsert nounInsert = new
		 * DBinsert(nounSQL1); nounInsert.insertTwo(nounsLists);
		 */
		
		
		
		
		/* ������ ��縦 �����ͼ� �ؽ�Ʈ���Ϳ� ���ϴ°�
		 * String url =
		 * "C:\\Users\\kiwon\\Desktop\\������\\Project\\DataFilter\\����������ڷ�\\����.txt";
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
