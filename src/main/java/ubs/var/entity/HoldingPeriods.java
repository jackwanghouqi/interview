package ubs.var.entity;

public enum HoldingPeriods {
	P1D("1D"), 
	P10D("10D");

	private String period;

	private HoldingPeriods(String period) {
		this.period = period;
	}
	public String getPeriod() {
		return period;
	}
}