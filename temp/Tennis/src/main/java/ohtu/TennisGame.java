package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        int difference = m_score1 - m_score2;
        if (m_score1 == 4 && m_score2 == 4) {
            return "Deuce";
        } else if (difference == 0) {
            return scoreTranslation(m_score1) + "-All";
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            return advantageOrWin(difference) + whoIsWinning(difference);
        } else {
            return scoreTranslation(m_score1) + "-" + scoreTranslation(m_score2);
        }
    }

    private String advantageOrWin(int difference) {
        if (Math.abs(difference) < 2) {
            return "Advantage player";
        } else {
            return "Win for player";
        }
    }

    private String whoIsWinning(int difference) {
        if (difference > 0) {
            return "1";
        } else {
            return "2";
        }
    }

    private String scoreTranslation(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
}
