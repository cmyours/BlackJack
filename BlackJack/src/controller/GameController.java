package controller;

import java.util.Scanner;

import model.Deck;
import model.Player;

import java.util.Scanner;

public class GameController {

    private Deck deck;
    private Player player;
    private Player dealer;

    public GameController() {
        deck = new Deck();
        deck.shuffle();
        player = new Player(300);
        dealer = new Player(99999);
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);

        while (player.getChips() > 0) {
            int bet = 0;
            player.clearPoint();
            dealer.clearPoint();

            System.out.println("보유 칩 : " + player.getChips());
            while (true) {
                System.out.print("배팅 금액 : ");
                bet = sc.nextInt();
                if (player.getChips() >= bet) break;
                System.out.println("칩이 부족합니다.");
            }

            // 초기 카드 배분
            dealer.hands = deck.draw();
            player.hands = deck.draw();

            System.out.println("Dealer : " + dealer.hands);
            System.out.println("Player : " + player.hands);
            dealer.countPoint();
            player.countPoint();

            player.hands = deck.draw();
            player.countPoint();
            System.out.println("Player : " + player.hands);

            // 블랙잭 확인
            if (player.getPoint() == 21) {
                System.out.println("BlackJack!");
                player.chipUp(bet * 2);
                continue;
            }

            int dd = 0;
            while (true) {
                System.out.print((dd == 0) ? "h(Hit), s(Stand), d(Double Down)? " : "h(Hit), s(Stand)? ");
                String action = sc.next();
                if (action.equalsIgnoreCase("h")) {
                    player.hands = deck.draw();
                    player.countPoint();
                    System.out.println("Player : " + player.hands);
                } else if (action.equalsIgnoreCase("d") && dd == 0) {
                    System.out.print("추가 배팅 금액 입력 (현재 칩: " + (player.getChips() - bet) + ") : ");
                    int addBet = sc.nextInt();
                    bet += addBet;
                    player.hands = deck.draw();
                    player.countPoint();
                    System.out.println("Player : " + player.hands);
                    break;
                } else {
                    break;
                }

                dd++;
                if (player.getPoint() >= 21) break;
            }

            if (player.getPoint() > 21) {
                System.out.println("Bust!");
                player.chipDown(bet);
                continue;
            }

            // 딜러 차례
            while (dealer.getPoint() <= 16) {
                dealer.hands = deck.draw();
                dealer.countPoint();
                System.out.println("Dealer : " + dealer.hands);
            }

            if (dealer.getPoint() > 21) {
                System.out.println("You Win!");
                player.chipUp(bet);
                continue;
            }

            // 승패 판정
            if (dealer.getPoint() > player.getPoint()) {
                System.out.println("You lose!");
                player.chipDown(bet);
            } else if (dealer.getPoint() < player.getPoint()) {
                System.out.println("You Win!");
                player.chipUp(bet);
            } else {
                System.out.println("Draw!");
            }
        }

        System.out.println("게임 오버! 보유 칩이 없습니다.");
    }
}