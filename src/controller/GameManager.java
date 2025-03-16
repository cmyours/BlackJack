package controller;

import model.Player;
import model.Card;
import model.Deck;
import main.BlackJackFrame;

public class GameManager {
	
	private Player player;
    private Player dealer;
    private Card card;
    private Deck deck;
    private BlackJackFrame ui;
    
    // ==== 카드 개수 카운트 ====
    private int PlayerCardCount = 0;
    private int DealerCardCount = 0;
    private int BetChips = 0; // 베팅한 칩
    
    public GameManager(Player player, Player dealer, BlackJackFrame ui) {
        this.player = player;
        this.dealer = dealer;
        this.ui = ui;
        this.deck = new Deck();  
        deck.shuffle();          
    }
    
    
    public void start(int BetAmount) {
    	
    	// 1. 베팅
    	player.chipDown(BetAmount);
        ui.updateChipsLabel(player.getChips());
        BetChips = BetAmount;
        
        System.out.println(player.getChips());

        // 2. 초기화
        player.clearPoint();
        dealer.clearPoint();

        // 4. 패널 전환
        ui.showGamePanel();
        
        // 3. 카드 분배
        CardsDistribute();

    }
    
    
    private void CardsDistribute() {
        // 1. 플레이어 카드 2장
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Card playerCard1 = deck.draw();
        ui.CreatPlayerCard(playerCard1);
        Card playerCard2 = deck.draw();
        ui.CreatPlayerCard(playerCard2);
        
        player.addPoint(playerCard1.getRank());
        player.addPoint(playerCard2.getRank());

        // 2. 딜러 카드 1장
        Card dealerCard1 = deck.draw();
        ui.CreatDealerCard(dealerCard1);
        dealer.addPoint(dealerCard1.getRank());
        
        // 3. 점수 갱신
        ui.updatePPointLabel(player.getPoint());
        ui.updateDPointLabel(dealer.getPoint());
        
        // 4. 블랙잭 확인(플레이어의 점수가 21점이면 블랙잭으로 판정, 즉시 승리)
        if (player.getPoint() == 21) {
        	ui.showBlackJackMsg();
        	player.chipUp(BetChips * 2);
        	ui.updateChipsLabel(player.getChips());
    		ui.showMainPanel();
    		ui.paramReset();
        }
        
    }
    
    // Hit 했을 경우
    public boolean Hit() {
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// 1. 카드 한장 새로 받기
    	Card newCard = deck.draw();
    	
    	// 2. 새로운 카드를 UI에 생성
    	ui.CreatPlayerCard(newCard);
    	
    	// 2. 점수 계산 및 점수 갱신
    	player.addPoint(newCard.getRank());
    	ui.updatePPointLabel(player.getPoint());
    	
    	// 3. 버스트 판단
    	if (player.getPoint() > 21)
    		return true;
    	return false;
    	}

    // 딜러 차례
    public void dealerTurn() {
    	while (true) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	// 1. 카드 한장 새로 받기
    	Card newCard = deck.draw();
    	
    	// 2. 새로운 카드를 UI에 생성
    	ui.CreatDealerCard(newCard);
    	
    	// 3. 점수 계산 및 점수 갱신
    	dealer.addPoint(newCard.getRank());
    	ui.updateDPointLabel(dealer.getPoint());
    	
    	if (dealer.getPoint() >= 17) 
    		break;
    	}
    } 	
    
    // 결과 판정
    public void Result() {
    	int playerScore = player.getPoint();
    	int dealerScore = dealer.getPoint();
    	
    	if (playerScore > 21) {
    		ui.showBustMsg();
    	} else if (dealerScore > 21 || playerScore > dealerScore) {
    		ui.showWinMsg();
    		player.chipUp(BetChips * 2);
    	} else if (playerScore == dealerScore) {
    		ui.showDrawMsg();
    		player.chipUp(BetChips);
    	} else {
    		ui.showLoseMsg();
    	}
    	
    	System.out.println(BetChips);
    	BetChips = 0;

		ui.updateChipsLabel(player.getChips()); // 칩 갱신
		ui.showMainPanel();
		ui.paramReset();

    }
    	
    	
    	
    	
    	
    
    
    
}
