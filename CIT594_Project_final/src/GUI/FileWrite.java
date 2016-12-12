package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileWrite {
	
	public static void writeConfidential(ArrayList<String> info) {
		try {
			File file = new File("userinfo/confidential.txt");
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true); // true
																					// --->
																					// append
																					// data
			BufferedWriter bw = new BufferedWriter(fileWriter);

			for (String string : info) {
				bw.write(string);
				bw.write(' ');
			}
			bw.newLine();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
