package model;

public class Card {
    String suit;
    int rank;
    String imagePath;

    public Card(String suit, int rank, String imagePath) {
        this.suit = suit;
        this.rank = rank;
        this.imagePath = imagePath;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
    	int score;
    	if (rank == 11) 
    		score = 10;
    	else if (rank == 12)
    		score = 10;
    	else if (rank == 13)
    		score = 10;
    	else
    		score = rank;
    	
        return score;
    }

    public String getRankS() {
        if (rank == 1) {
            return "A";
        } else if (rank == 11) {
            return "J";
        } else if (rank == 12) {
            return "Q";
        } else if (rank == 13) {
            return "K";
        } else {
            return Integer.toString(rank);
        }
    }
    
    public String getImagePath() {
    	return imagePath;
    }

    @Override
    public String toString() {
        return suit + getRankS();
    }
}