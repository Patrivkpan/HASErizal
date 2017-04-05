import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile{
	private String path;
	public ReadFile(String file_path){
		path = file_path;
	}

	public String[] OpenFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int lines= readLines();
		String[] textData = new String[lines];

		int i;

		for (i=0; i<lines; i++) {
			textData[i]=textReader.readLine();
		}

		textReader.close();
		return textData;
	}

	int readLines() throws IOException{
		FileReader file_to_read= new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;
		int lines = 0;

		while ((aLine = bf.readLine()) != null) {
			lines++;
		}

		bf.close();

		return lines;
	}
}