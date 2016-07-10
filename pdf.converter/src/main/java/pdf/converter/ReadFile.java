package pdf.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) {

		BufferedReader br = null;

		
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("C:\\finacle\\Y1.txt"));

			int cityCode = 0;
			String cityName = "";

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				sCurrentLine = sCurrentLine.replace(":", "|");
				sCurrentLine = sCurrentLine.replace("  ", "|");
				sCurrentLine = sCurrentLine.replace(" (", "|");
				sCurrentLine = sCurrentLine.replace("|(", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				sCurrentLine = sCurrentLine.replace("| ", "|");
				sCurrentLine = sCurrentLine.replace(" |", "|");
				sCurrentLine = sCurrentLine.replace("||", "|");
				// System.out.println(sCurrentLine);

				String[] line = sCurrentLine.split("\\|");
				
				// Capture City until it changes again
				if (line[0].equals("City")) {
					// City code
					cityCode = Integer.parseInt(line[1].substring(0, line[1].length() - 1));
					cityName = line[2];

				}
				// Delhi-NCR branch items
				else if (line[0].indexOf("110018") == 0) {

					String branchCode = line[0];
					String branchName = line[1];
					String inItems = line[4];
					String inAmount = line[5];
					System.out.println(branchCode + "\t" + branchName + "\t" + inItems + "\t" + inAmount);

				}

				// Delhi-NCR Total
				if (line[0].indexOf("Total") == 0 && cityCode == 110) {
					String total = line[0];
					String inItems = line[3];
					String inAmount = line[4];
					System.out.println(total + "\t" + inItems + "\t" + inAmount);

				}
				// Northern Grid Total
				else if (line[0].indexOf("Total") == 0 && cityCode != 110) {
//					String branchCode = line[0];
					String inItems = line[3];
					String inAmount = line[4];
					System.out.println(cityCode + "\t" + cityName + "\t" + inItems + "\t" + inAmount);
				}
				// Bank Summary
				else if (line[0].indexOf("Bank Summary") == 0) {

					String bankSummary = line[0];
					String inItems = line[3];
					String inAmount = line[4];
					System.out.println(bankSummary + "\t" + inItems + "\t" + inAmount);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}