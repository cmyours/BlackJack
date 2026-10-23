# BlackJack

Java Swing으로 제작한 데스크톱 블랙잭 게임입니다. Java의 객체지향 설계와 이벤트 기반 GUI 구현을 연습하기 위해 카드·덱·플레이어 모델과 게임 진행 로직, 화면 구성을 나누어 구현했습니다.

## 주요 기능

- **베팅:** 보유 칩을 확인하고 베팅 금액을 입력합니다. 시작 칩은 300개입니다.
- **카드 분배:** 섞인 52장 카드 덱에서 플레이어와 딜러에게 카드를 나누어 줍니다.
- **Hit / Stand:** 버튼으로 카드를 추가로 받거나 차례를 마칩니다.
- **딜러 진행:** 딜러가 17점 이상이 될 때까지 카드를 뽑습니다.
- **결과 처리:** 승리·패배·무승부·버스트에 따라 결과를 표시하고 칩을 정산합니다.
- **GUI:** Swing의 `JFrame`, `JPanel`, `CardLayout`과 카드 이미지로 화면을 구성했습니다.

## 사용 기술

- Java
- Java Swing / AWT
- Eclipse IDE

## 프로젝트 구조

```text
BlackJack/
├── README.md
├── .gitignore
├── src/
│   ├── main/
│   │   └── BlackJackFrame.java    # 프로그램 시작점, GUI
│   ├── controller/
│   │   └── GameManager.java       # 카드 분배, 베팅, 진행 및 승패 처리
│   └── model/
│       ├── Card.java              # 카드 정보 및 점수
│       ├── Deck.java              # 덱 생성, 셔플, 카드 뽑기
│       └── Player.java            # 플레이어 칩과 점수
└── resources/
    └── cards/                    # 카드 이미지 (52장 + 뒷면)
```

> 위 구조는 GUI 실행에 필요한 파일을 기준으로 한 권장 정리본입니다. 기존 콘솔 실습용 `GameController.java`와 컴파일 산출물(`bin/`, `*.class`)은 제외합니다.

## 실행 방법

1. 저장소를 Clone하거나 ZIP으로 내려받습니다.
2. Eclipse에서 **Java Project**로 엽니다. 프로젝트 설정 파일이 없다면 프로젝트 폴더를 위치로 지정해 새 Java 프로젝트를 만듭니다.
3. `src`를 Source Folder로 지정하고 Java 개발 환경(JDK)을 확인합니다.
4. **`src/main/BlackJackFrame.java`**를 우클릭하고 **Run As → Java Application**을 선택합니다.
5. 카드 이미지가 표시되지 않는 경우 실행 구성의 **Working directory**를 프로젝트 최상위 폴더로 설정합니다. 이미지 경로는 `resources/cards/`를 기준으로 합니다.

이 프로젝트는 데스크톱 Swing 프로그램이므로 GitHub 웹페이지에서 바로 실행할 수는 없습니다.

## 코드 구성

| 구성 요소 | 역할 |
| --- | --- |
| `BlackJackFrame` | 화면 구성, 버튼 이벤트, 카드 이미지 및 메시지 표시 |
| `GameManager` | 라운드 시작, 카드 분배, Hit, 딜러 차례, 정산 |
| `Card` | 카드 무늬·숫자·점수·이미지 경로 |
| `Deck` | 카드 52장 생성, 섞기, 한 장씩 뽑기 |
| `Player` | 보유 칩과 점수 상태 관리 |

## 현재 구현의 한계와 개선 방향

- **게임 로직과 GUI의 결합:** `GameManager`가 `BlackJackFrame`을 직접 호출하므로 향후 게임 규칙과 화면 표시를 분리할 필요가 있습니다.
- **에이스(A) 점수 계산:** 에이스를 11점으로 계산한 후 상황에 따라 1점으로 재계산하는 로직이 충분하지 않습니다.
- **카드 덱 재사용:** 라운드마다 덱을 재생성하지 않아 카드가 모두 소진될 경우 예외 처리가 필요합니다.
- **GUI 반응성:** 일부 카드 처리에 `Thread.sleep()`을 사용하므로 Swing 타이머 등 비차단 방식으로 개선할 수 있습니다.

## 참고

이 저장소는 **Java Swing GUI 버전**을 담고 있습니다. 콘솔 버전이나 Spring Boot·React 버전의 전체 소스코드가 함께 포함된 저장소는 아닙니다.
