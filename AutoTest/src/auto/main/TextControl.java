package auto.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextControl {
	
	public void textControl(String inUrl, String outUrl, String finalUrl) throws IOException {
		
		FileRead fr = new FileRead();
		String[] text = fr.fileRead(inUrl);
		

		String fileName = outUrl;
		
		try {
			File file = new File(fileName);
			
			FileWriter fw = new FileWriter(file, true);
			
			for(int i = 0; i < text.length; i++) {
				fw.write(text[i]);
				
			}
			
			fw.flush();
			
			fw.close();
		} catch(Exception e) {
			System.out.println(e);
			
		}
		
		fileRemove(finalUrl);
		fileMove(outUrl, finalUrl);
		
	}

	private void fileMove(String outUrl, String finalUrl) {
		
		File file = new File(outUrl);
		File filemove = new File(finalUrl);
		boolean moved = file.renameTo(filemove);

		System.out.println(moved);
	}
	
	
	
	private void fileRemove(String finalUrl) {
		boolean as = true;
		
		File file = new File(finalUrl);
		
		if(file.exists()) {
			as = file.delete();
			if(as) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		} else {
			System.out.println("미존재");
		}
	}

}
