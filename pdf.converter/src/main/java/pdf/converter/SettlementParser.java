package pdf.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import dena.branch.service.bean.BranchBean;
import dena.branch.service.bean.SettlementItem;
import dena.branch.service.utils.StringUtils;

public class SettlementParser {

	//variable to hold Delhi NCR Branches
	Set<SettlementItem> delhiNCR = new LinkedHashSet<SettlementItem>();
	SettlementItem delhiNCRtotal = new SettlementItem();
	
	//variable to hold branches where volume is low and which handled by Delhi Service branch
	Set<SettlementItem> delhiServiced = new LinkedHashSet<SettlementItem>();

	//variable to hold branches other than the above
	Set<SettlementItem> delhiNonServiced = new LinkedHashSet<SettlementItem>();
	
	//variable to hold northern GRID braches
	Set<SettlementItem> northernGrid = new LinkedHashSet<SettlementItem>();
	
	//variable to hold bank grand total
	Set<SettlementItem> bankSummary = new LinkedHashSet<SettlementItem>();

	public SettlementParser() {

		parseSettlement();
		

	}

	private void segregateDelhiServicedBraches(){
		
		Set<BranchBean> delServicesBranchs = new HashSet<BranchBean>();
		for (SettlementItem nGridBean : northernGrid) {
		}
		 
		
		
	}
	
	private void parseSettlement() {

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
					BigDecimal inAmount = StringUtils.getBigDecimalValue(line[5].replace(",", ""));

					// add to delhiNCR set
					SettlementItem settlement = new SettlementItem();
					settlement.setBranchCode(branchCode);
					settlement.setBranchName(branchName);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					delhiNCR.add(settlement);

					// System.out.println(branchCode + "\t" + branchName + "\t"
					// + inItems + "\t" + inAmount);

				}

				// Delhi-NCR Total
				if (line[0].indexOf("Total") == 0 && cityCode == 110) {
					String total = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					BigDecimal inAmount = StringUtils.getBigDecimalValue(line[4].replace(",", ""));

					// add to delhiNCR Total
					delhiNCRtotal.setBranchCode("Delhi-NCR");
					delhiNCRtotal.setBranchName(total);
					delhiNCRtotal.setInItems(inItems);
					delhiNCRtotal.setInAmount(inAmount);

					// System.out.println(total + "\t" + inItems + "\t" +
					// inAmount);

				}
				// Northern Grid Total
				else if (line[0].indexOf("Total") == 0 && cityCode != 110) {
					// String branchCode = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					BigDecimal inAmount = StringUtils.getBigDecimalValue(line[4].replace(",", ""));

					SettlementItem settlement = new SettlementItem();
					settlement.setCityCode(cityCode);
					settlement.setCityName(cityName);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					northernGrid.add(settlement);

					// System.out.println(cityCode + "\t" + cityName + "\t" +
					// inItems + "\t" + inAmount);
				}
				// Bank Summary
				else if (line[0].indexOf("Bank Summary") == 0) {

					String summary = line[0];
					int inItems = StringUtils.getIntValue(line[3].replace(",", ""));
					BigDecimal inAmount = StringUtils.getBigDecimalValue(line[4].replace(",", ""));

					SettlementItem settlement = new SettlementItem();
					settlement.setCityName(summary);
					settlement.setInItems(inItems);
					settlement.setInAmount(inAmount);
					bankSummary.add(settlement);

					// System.out.println(summary + "\t" + inItems + "\t" +
					// inAmount);
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

		SettlementParser settler = new SettlementParser();

		Set<SettlementItem> del = settler.delhiNCR;
		for (SettlementItem delhiItem : del) {
			System.out.println(delhiItem.getBranchCode() + "\t" + delhiItem.getBranchName() + "\t"
					+ delhiItem.getInItems() + "\t" + delhiItem.getInAmount());

		}
		

		System.out.println(settler.delhiNCRtotal.getBranchCode() + "\t" + settler.delhiNCRtotal.getBranchName() + "\t" + settler.delhiNCRtotal.getInItems() + "\t"
				+ settler.delhiNCRtotal.getInAmount());

	}
}