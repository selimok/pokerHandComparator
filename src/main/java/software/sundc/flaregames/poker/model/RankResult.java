package software.sundc.flaregames.poker.model;

public class RankResult {
	
	private String handId;
	private Integer overallRank;
	private Rank rank;
	
	public RankResult(String handId, Integer overallRank, Rank rank){
		this.handId = handId;
		this.overallRank = overallRank;
		this.rank = rank;
	}
	
	public String getHandId() {
		return handId;
	}
	public void setHandId(String handId) {
		this.handId = handId;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Integer getOverallRank() {
		return overallRank;
	}

	public void setOverallRank(Integer overallRank) {
		this.overallRank = overallRank;
	}
	

}
