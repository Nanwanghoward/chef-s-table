package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileWrite {
	
	public FileWrite(ArrayList<String> info) {
		write(info);
	}
	
	private void write(ArrayList<String> info) {
		try {
			File file = new File("confidential.txt");
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);   // true ---> append data
			BufferedWriter bw = new BufferedWriter(fileWriter);
			
			bw.newLine();
			
			for (String string : info){
				bw.write(string);
				bw.write(' ');
			}
			
			bw.close();
			
		} catch (Exception e) {
		}
	}
	
}
