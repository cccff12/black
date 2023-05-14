package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Service {

	// main에서 사용하는 객체 사용하기
	Dealer dealer;
	Player player;

	public Service(Dealer dealer, Player player) {
		this.dealer = dealer;
		this.player = player;
	}

	public List<Card> makeCard() {

		List<Card> cardList = new ArrayList<>();
		Card card;
		for (int i = 1; i < 11; i++) {
			card = new Card();
			card.setPattern("d"); // 다이아
			card.setNumber("" + i);
			cardList.add(card);
		}
		for (int i = 1; i < 11; i++) {
			card = new Card();
			card.setPattern("h"); // 하트
			card.setNumber("" + i);
			cardList.add(card);
		}
		for (int i = 1; i < 11; i++) { // 클로버
			card = new Card();
			card.setPattern("c");
			card.setNumber("" + i);
			cardList.add(card);
		}
		for (int i = 1; i < 11; i++) { // 스페이드
			card = new Card();
			card.setPattern("s");
			card.setNumber("" + i);
			cardList.add(card);
		}

		return cardList;

	} // makeCard() end

	// 딜러 카드 분배
	public List<Card> giveCard(List<Card> card) {

		int rnd1 = (int) (Math.random() * card.size());
		dealer.setCard(card.get(rnd1));
		dCardSave(card.get(rnd1));
		// 사용한 카드 삭제
		card.remove(rnd1);

		int rnd2 = (int) (Math.random() * card.size());
		dealer.setCard(card.get(rnd2));
		dCardSave(card.get(rnd2));
		// 사용한 카드 삭제
		card.remove(rnd2);

		int rnd3 = (int) (Math.random() * card.size());
		player.setCard(card.get(rnd3));
		pCardSave(card.get(rnd3));
		// 사용한 카드 삭제
		card.remove(rnd3);

		int rnd4 = (int) (Math.random() * card.size());
		player.setCard(card.get(rnd4));
		pCardSave(card.get(rnd4));

		// 사용한 카드 삭제
		card.remove(rnd4);
		getCardList();
		System.out.println();
		// 남은 카드 리스트 반환
		return card;
	}

	// 플레이어와 딜러가 가지고 있는 카드 리스트
	List<Card> playerCard = new ArrayList<>();
	List<Card> dealerCard = new ArrayList<>();

	// 플레이어 카드 보관
	public void pCardSave(Card card) {
		playerCard.add(card);
	}

	// 딜러 카드 보관
	public void dCardSave(Card card) {
		dealerCard.add(card);
	}

	/*
	 * 여기부터 추가
	 */
	int dsum = 0;// 밑의 두 메서드에 사용할 변수

// 딜러가 받은 카드를 합산할 메서드
	public void dealerCardcondition() {

		for (Card card : dealerCard) {
			dsum += Integer.valueOf(card.getNumber());
		}
	}

//	딜러가 받은 카드가 17이 넘으면 카드를 받고 
//	21이 넘으면 패배하는 매서드

	public void dealerCardcondition(List<Card> card) {
		if (dsum < 17) {
			System.out.println("딜러의 카드 합이 17미만이라 딜러의 드로우");
			int rnd5 = (int) (Math.random() * card.size());
			dealer.setCard(card.get(rnd5));
			dCardSave(card.get(rnd5)); // 딜러 카드 추가

			card.remove(rnd5);// 카드 삭제
			System.out.println("딜러 카드 리스트");

			for (Card car : dealerCard) { // 17 미만일시 가진 카드 출력
				System.out.print(car.getPattern() + "-");
				System.out.print(car.getNumber() + "\t");
			}
		}

	}

	public void dealerexit() {
		dsum = 0; // 새로 카드를 뽑았으니 초기화
		for (Card card : dealerCard) {
			dsum += Integer.valueOf(card.getNumber());// 초기화를 했으니 딜러 카드 합을 다시 덧셈
		}
		System.out.println(dsum);
		if (dsum > 21) {
			System.out.println("딜러의 카드 합이 " + dsum + "이 나와서 딜러가 패배");
			System.exit(0);
		}

	}

	/*
	 * 여기까지
	 *
	 */

	// 각각 카드의 점수 합을 구한 후 승패 결정
	public void whoWin() {
		int pSum = 0;
		int dSum = 0;
		for (Card card : playerCard) {
			pSum += Integer.valueOf(card.getNumber());
		}

		for (Card card : dealerCard) {
			dSum += Integer.valueOf(card.getNumber());
		}

		if (dSum > pSum) {
			System.out.println("딜러 점수 : " + dSum);
			System.out.println("플레이어 점수 : " + pSum);
			System.out.println("딜러 승");
		} else if (dSum < pSum) {
			System.out.println("딜러 점수 : " + dSum);
			System.out.println("플레이어 점수 : " + pSum);
			System.out.println("플레이어 승");

		}
	}

	public void hitCard(List<Card> card) {
		int rnd = (int) (Math.random() * card.size());
		player.setCard(card.get(rnd));
		pCardSave(card.get(rnd));
		getCardList();
		// 사용한 카드 삭제
		card.remove(rnd);

	}

	public void getCardList() {
		System.out.println("딜러 카드 리스트");
		for (Card card : dealerCard) {
			System.out.print(card.getPattern() + "-");
			System.out.print(card.getNumber() + "\t");
		}
		System.out.println();
		System.out.println("플레이어 카드 리스트");
		for (Card card : playerCard) {
			System.out.print(card.getPattern() + "-");
			System.out.print(card.getNumber() + "\t");
		}
		System.out.println();

	}

	public void resetList() {
		playerCard = new ArrayList<>();
		dealerCard = new ArrayList<>();
	}

}