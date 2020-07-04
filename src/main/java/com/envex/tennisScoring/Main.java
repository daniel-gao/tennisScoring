package com.envex.tennisScoring;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    TennisMatch tm = new TennisMatch();
    System.out.println("EVNEX tennis match started... \nPlease input the name of playerA :");
    Scanner scanner = new Scanner(System.in);
    String playerAName = scanner.nextLine();
    tm.createPlayerA(playerAName);


    System.out.println("Please input the name of playerB :");
    String playerBName = scanner.nextLine();
    tm.createPlayerB(playerBName);
    System.out.println("Please intput 'a' if playerA scored, 'b' if playerB scored :");
    while (true) {
      String scoringPlayer = scanner.nextLine();
      boolean isGameOver = false;
      if (TennisMatch.PLAYER_A.equalsIgnoreCase(scoringPlayer)) {
        isGameOver = tm.pointWonByA();
      } else if (TennisMatch.PLAYER_B.equalsIgnoreCase(scoringPlayer)) {
        isGameOver = tm.pointWonByB();
      } else {
        System.out.println("Invalid input.");
      }
      System.out.println(tm.getScore());
      if (isGameOver) {
        System.out.println("\n\nGame Over.");
        tm.scoreReport();
        break;
      }
    }
  }
}
