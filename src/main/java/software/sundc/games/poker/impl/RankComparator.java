package software.sundc.games.poker.impl;

import java.util.Comparator;

import software.sundc.games.poker.model.Rank;

public class RankComparator implements Comparator<Rank> {

	@Override
	public int compare(Rank rank1, Rank rank2) {
		
		if(rank1 == null && rank2 == null){
			return 0;
		}else if ( rank1 == null && rank2 != null){
			return -1;
		}else if ( rank1 != null && rank2 == null) {
			return 1;
		}else {
			return rank1.toNumericValue().compareTo(rank2.toNumericValue());
		}
	}

}
