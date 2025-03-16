package model;

import java.util.Scanner;

public class Player {
    private int chips;
    public Card hands;
    public int point;
    private enum type {
    	Player,
    	Dealer
    }
    private Scanner sc = new Scanner(System.in);

    
    public Player(int chips) {
        this.chips = chips;
    }

    // 플레이어가 가진 칩의 조정
    public void chipUp(int amount) { 
        chips += amount;
    }

    public void chipDown(int amount) {
        chips -= amount;
    }

    public int getChips() {
        return chips;
    }

    // 플레이어의 카드 숫자의 합
    public void addPoint(int additionalPoint) {
        
    	if (additionalPoint == 1) { // 에이스일 경우
    		if (point >= 11)  // 플레이어의 현재 점수가 11 이상이면 A는 1로 판정
    			point += 1;
    		else 
    			point += 11;   		
    	} else {
    	point += additionalPoint;
    	}
    }

    // 플레이어의 현재 카드의 합을 보여줌(버스트, 승패 판단을 위함)
    public int getPoint() {
        return point;
    }

    // 현재 포인트 클리어
    public void clearPoint() {
        point = 0;
    }
}

