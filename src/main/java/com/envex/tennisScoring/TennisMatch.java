package com.envex.tennisScoring;

public class TennisMatch {
  public static final String PLAYER_A = "a";
  public static final String PLAYER_B = "b";
  private Player playerA;
  private Player playerB;
  private boolean tieBreak = false;

  public void createPlayerA(String name) {
    playerA = new Player(name);
  }

  public void createPlayerB(String name) {
    playerB = new Player(name);
  }

  public boolean pointWonByA() {
    return score(playerA, playerB);
  }

  public boolean pointWonByB() {
    return score(playerB, playerA);
  }

  /**
   * return true if match is over.
   *
   * @param scoredPlayer
   * @param opponent
   * @return
   */
  public boolean score(Player scoredPlayer, Player opponent) {
    //game scoring
    scoredPlayer.gameScore++;
    if (!tieBreak) {
      //regular game
      if (scoredPlayer.gameScore >= 4 && (scoredPlayer.gameScore - opponent.gameScore) >= 2) {
        //A game is won by the first player to have won at least 4 points in total and at least 2 points more than the opponent
        scoredPlayer.setScore++;
        scoredPlayer.gameScore = 0;
        opponent.gameScore = 0;
      }
    } else {
      //tie-break
      if (scoredPlayer.gameScore >= 7 && (scoredPlayer.gameScore - opponent.gameScore) >= 2) {
        scoredPlayer.setScore++;
        return true;
      } else {
        return false;
      }
    }

    //set scoring
    if (scoredPlayer.setScore >= 6) {
      if (scoredPlayer.setScore == opponent.setScore) {
        //tie-break is on
        tieBreak = true;
        return false;
      } else if ((scoredPlayer.setScore - opponent.setScore) >= 2) {
        //A player wins a set by winning at least 6 games and at least 2 games more than the opponent.
        return true;
      }
    }
    //carry on
    return false;
  }

  /**
   * return score for display.
   *
   * @return
   */
  public String getScore() {
    String gameScore = "";
    if (!tieBreak) {
      if (playerA.gameScore >= 3 && playerB.gameScore >= 3) {
        if (playerA.gameScore == playerB.gameScore) {
          //Deuce
          gameScore = "Deuce";
        } else {
          if (playerA.gameScore > playerB.gameScore) {
            gameScore = "Advantage " + playerA.name;
          } else {
            gameScore = "Advantage " + playerB.name;
          }
        }
      } else {
        gameScore = covertGameScore(playerA.gameScore) + "-" + covertGameScore(playerB.gameScore);
      }
    } else {
      gameScore = playerA.gameScore + "-" + playerB.gameScore;
    }
    StringBuilder sb = new StringBuilder(playerA.setScore + "-" + playerB.setScore);
    sb.append(",").append(gameScore);
    return sb.toString();
  }

  public void scoreReport(){
    System.out.printf("%s  :  %s \n%s-%s,%s-%s\n\n",
      playerA.name,
      playerB.name,
      playerA.setScore,
      playerB.setScore,
      playerA.gameScore,
      playerB.gameScore);
  }

  /**
   * convert gameScore from 1,2,3 to 15,30,40 for regular games.
   *
   * @param score
   * @return
   */
  private int covertGameScore(int score) {
    switch (score) {
      case 0:
        return 0;
      case 1:
        return 15;
      case 2:
        return 30;
      case 3:
        return 40;
      default:
        return -1;
    }
  }
}
