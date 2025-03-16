package main;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.event.ActionEvent;


import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;


import model.Player;
import model.Card;
import model.Deck;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.GameManager;



public class BlackJackFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	// ==== 오브젝트 ====
	private Player player;
	private Player dealer;
	private Card card;
	private Deck deck;
	
	// ==== UI ====
	private JLabel ChipsLabel;
	private GameManager GM;
	private JLabel DPointLabel;
	private JLabel PPointLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	
	
	// ==== 카드 카운트 ====
	private int PlayerCardCount = 0;
	private int DealerCardCount = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackFrame frame = new BlackJackFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public BlackJackFrame() {
		
		player = new Player(300);
		dealer = new Player(9999);
		
		GM = new GameManager(player, dealer, this);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 650, 450);
		contentPane.add(panel, "name_18695443673000");
		panel.setLayout(null);
		
		
		JSpinner BetSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 300, 1));
		BetSpinner.setBounds(218, 345, 72, 22);
		panel.add(BetSpinner);
		
		ChipsLabel =  new JLabel("Chips : " + player.getChips());
		ChipsLabel.setBounds(12, 10, 91, 15);
		panel.add(ChipsLabel);
		
		JButton btnNewButton = new JButton("Bet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int betAmount = (Integer) BetSpinner.getValue();
				
				if (betAmount <= player.getChips() && betAmount >= 1) {
					GM.start(betAmount);
				}
				
			}
		});
		btnNewButton.setBounds(309, 344, 97, 23);
		panel.add(btnNewButton);
		
		
		
		
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "name_18695449574100");
		panel_1.setLayout(null);
		
		
		// Hit 버튼
		JButton HitButton = new JButton("Hit");
		HitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bust = GM.Hit();
				if (bust) 
					GM.Result();
			}
		});
		HitButton.setBounds(169, 165, 97, 45);
		panel_1.add(HitButton);
		
		
		// Stand 버튼
		JButton StandButton = new JButton("Stand");
		StandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GM.dealerTurn();
				GM.Result();
			}
		});
		StandButton.setBounds(314, 165, 97, 45);
		panel_1.add(StandButton);
		
		DPointLabel = new JLabel("0");
		DPointLabel.setBounds(509, 38, 57, 15);
		panel_1.add(DPointLabel);
		
		PPointLabel = new JLabel("0");
		PPointLabel.setBounds(509, 347, 57, 15);
		panel_1.add(PPointLabel);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 650, 450);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
	}
	
	
	// 이미지의 해상도를 맞춰주는 메서드
	public ImageIcon getScaledIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
	    Image image = icon.getImage();
	    Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}
	
	// ==== UI 관련 메서드들 ====
	
	// 칩 업데이트
	public void updateChipsLabel(int chips) {
		ChipsLabel.setText("Chips : " + chips);
	}
	
	// 점수 업데이트
	public void updateDPointLabel(int point) {
		DPointLabel.setText(Integer.toString(point));
	}
	
	public void updatePPointLabel(int point) {
		PPointLabel.setText(Integer.toString(point));
	}
	
	// 화면 변경
	public void showGamePanel() {
		CardLayout cardLayout = (CardLayout) contentPane.getLayout();
		cardLayout.show(contentPane, "name_18695449574100");
	}
	
	public void showMainPanel() {
		CardLayout cardLayout = (CardLayout) contentPane.getLayout();
		cardLayout.show(contentPane, "name_18695443673000");
	}
	
	// 카드 라벨이 생성될 패널 생성
	public void CreatPanel() {
		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 650, 450);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
	}
	
	// 플레이어의 새로운 카드 생성
	public void CreatPlayerCard(Card card) {
		// 횟수 측정
		int count = PlayerCardCount;
		JLabel newCardLabel = new JLabel();
		newCardLabel.setBounds(270 + (7 * count), 320 - (7 * count), 49, 70); // 새로운 카드가 추가되면 원래 카드가 보이도록 위치를 조정
		panel_2.add(newCardLabel);
		panel_2.setComponentZOrder(newCardLabel, 0);
		newCardLabel.setIcon(getScaledIcon(card.getImagePath(), 49, 70));
		PlayerCardCount++;
	}
	
	// 딜러의 새로운 카드 생성
	public void CreatDealerCard(Card card) {
		int count = DealerCardCount;
		JLabel newCardLabel = new JLabel();
		newCardLabel.setBounds(270 - (7 * count), 10 + (7 * count), 49, 70);
		panel_2.add(newCardLabel);
		panel_2.setComponentZOrder(newCardLabel, 0);
		newCardLabel.setIcon(getScaledIcon(card.getImagePath(), 49, 70));
		DealerCardCount++;		
		
		panel_2.repaint();
		
		try {
		    Thread.sleep(200); 
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	// 메시지
	public void showBustMsg() {
		JOptionPane.showMessageDialog(this, "Bust!", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showWinMsg() {
		JOptionPane.showMessageDialog(this, "You Win!", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showLoseMsg() {
		JOptionPane.showMessageDialog(this, "You Lose...", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showDrawMsg() {
		JOptionPane.showMessageDialog(this, "Draw!", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showBlackJackMsg() {
		JOptionPane.showMessageDialog(this, "Black Jack!!", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// 수치 초기화
	public void paramReset() {
		PlayerCardCount = 0;
		DealerCardCount = 0;
		panel_2.removeAll();
	}
	
}
