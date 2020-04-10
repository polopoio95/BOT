import java.util.ArrayList;
import java.util.List;

public class CombineWord {

	public String[] combine(String[] word, String[] josa) {

		List<String> combineWord = new ArrayList<String>();

		for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < josa.length; j++) {

				combineWord.add(word[i] + josa[j]);

			}

		}
		
		String[] wordListS = combineWord.toArray(new String[combineWord.size()]);
		System.out.println("리스트 길이는 총:" + wordListS.length + "개 입니다");

		return wordListS;

	}

}
