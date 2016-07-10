package pdf.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import dena.branch.service.bean.SettlementItem;
import dena.branch.service.utils.StringUtils;

public class settlementParser {

	Set<SettlementItem> delhiNCR = new LinkedHashSet<SettlementItem>();
	Set<SettlementItem> nothernGrid = new LinkedHashSet<SettlementItem>();
	Set<SettlementItem> bankSummary = new LinkedHashSet<SettlementItem>();
	

	public void parseSettlement() {

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
				// sCurrentLine = sCurrentLine.replace(" (", "|");
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
					cityCode = StringUtils.getIntValue(line[1].substring(0, line[1].length() - 1));
					cityName = line[2];

				}
				// Delhi-NCR branch items
				else if (line[0].indexOf("110018") == 0) {

					String branchCode = line[0];
					String branchName = line[1];
					int inItems = StringUtils.getIntValue(line[4].replace(",", ""));
					double inAmount = StringUtils.getDoubleValue(line[5].replace(",", ""));

					// add to delhiNCR set
					SettlementItem settlement = new SettlementItem();
					settlement.setBranchCode(branchCode);
					settlement.setBranchName(branchName);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					delhiNCR.add(settlement);

					System.out.println(branchCode + "\t" + branchName + "\t" + inItems + "\t" + inAmount);

				}

				// Delhi-NCR Total
				if (line[0].indexOf("Total") == 0 && cityCode == 110) {
					String total = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					double inAmount = StringUtils.getDoubleValue(line[4].replace(",", ""));

					// add to delhiNCR set
					SettlementItem settlement = new SettlementItem();
					// settlement.setBranchCode(branchCode);
					settlement.setBranchName(total);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					delhiNCR.add(settlement);
					
					System.out.println(total + "\t" + inItems + "\t" + inAmount);

				}
				// Northern Grid Total
				else if (line[0].indexOf("Total") == 0 && cityCode != 110) {
					// String branchCode = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					double inAmount = StringUtils.getDoubleValue(line[4].replace(",", ""));
					
					SettlementItem settlement = new SettlementItem();
					settlement.setCityCode(cityCode);
					settlement.setCityName(cityName);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					nothernGrid.add(settlement);
					
					System.out.println(cityCode + "\t" + cityName + "\t" + inItems + "\t" + inAmount);
				}
				// Bank Summary
				else if (line[0].indexOf("Bank Summary") == 0) {

					String summary = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					double inAmount = StringUtils.getDoubleValue(line[4].replace(",", ""));
					
					SettlementItem settlement = new SettlementItem();
					settlement.setCityName(summary);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					bankSummary.add(settlement);
					
					System.out.println(summary + "\t" + inItems + "\t" + inAmount);
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

	public static void main(String[] args) {

		new settlementParser().parseSettlement();

	}
}