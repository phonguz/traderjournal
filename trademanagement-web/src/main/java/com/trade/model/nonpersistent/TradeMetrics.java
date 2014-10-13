package com.trade.model.nonpersistent;

import java.math.BigDecimal;

public class TradeMetrics {
	
	private BigDecimal currentRiskReward;
	private BigDecimal originalRiskRewad;
	private BigDecimal lastPrice;
	private BigDecimal currentPnL;
	private BigDecimal currentPnLPercentage;
	public BigDecimal getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}
	public BigDecimal getCurrentPnL() {
		return currentPnL;
	}
	public void setCurrentPnL(BigDecimal currentPnL) {
		this.currentPnL = currentPnL;
	}
	public BigDecimal getCurrentPnLPercentage() {
		return currentPnLPercentage;
	}
	public void setCurrentPnLPercentage(BigDecimal currentPnLPercentage) {
		this.currentPnLPercentage = currentPnLPercentage;
	}
	public BigDecimal getCurrentRiskReward() {
		return currentRiskReward;
	}
	public void setCurrentRiskReward(BigDecimal currentRiskReward) {
		this.currentRiskReward = currentRiskReward;
	}
	public BigDecimal getOriginalRiskRewad() {
		return originalRiskRewad;
	}
	public void setOriginalRiskRewad(BigDecimal originalRiskRewad) {
		this.originalRiskRewad = originalRiskRewad;
	}
	

}
