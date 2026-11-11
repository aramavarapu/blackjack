package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;
public class Program {

    private static final int NUMBER_OF_PLAYERS = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            players.add(new Player(askForName(scanner, i), new Hand()));
        }

        Deck deck = new Deck();
        deck.shuffle();

        for (int round = 0; round < 2; round ++) {
            for (Player player : players) {
                Card card = deck.deal();
                player.getHand().deal(card);
            }
        }

        System.out.println();
        for (Player player: players) {
            int value = player.getHand().getValue();
            String line = player.getName() + ": " + player.getHand() + " (" + value + ") ";
            System.out.println(line);
        }

        ArrayList<Player> winners = new ArrayList<>();
        int bestScore = 0;
        for (Player player: players) {
            int value = player.getHand().getValue();
            if (value > 21) {
                continue;
            }
            if (value > bestScore) {
                bestScore = value;
                winners.clear();
                winners.add(player);
            } else if (value == bestScore) {
                winners.add(player);
            }
        }

        System.out.println();
        if (winners.isEmpty()) {
            System.out.println("Everyone went over 21. No winners!");
        } else if (winners.size() == 1) {
            System.out.println(winners.get(0).getName() + " wins with " + bestScore + "!");
        } else {
            String names = "";
            for (int i = 0; i < winners.size(); i++) {
                if (i > 0) {
                    names += " and ";
                }
                names += winners.get(i).getName();
            }
            System.out.println("It's a tie between " + names + " with " + bestScore + "!");
        }

    }


    private static String askForName(Scanner scanner, int playerNumber) {
        while (true) {
            System.out.print("Enter the name of player " + playerNumber + ": ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("This name can't be blank");
        }
    }
}
