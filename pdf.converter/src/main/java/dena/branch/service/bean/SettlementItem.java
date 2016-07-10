package dena.branch.service.bean;

public class SettlementItem {
	private int cityCode;
	private String cityName;
	private String branchCode;
	private String branchName;
	private int inItems;
	private double inAmount;

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getInItems() {
		return inItems;
	}

	public void setInItems(int inItems) {
		this.inItems = inItems;
	}

	public double getInAmount() {
		return inAmount;
	}

	public void setInAmount(double inAmount) {
		this.inAmount = inAmount;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	

}
