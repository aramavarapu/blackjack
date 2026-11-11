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

        for (Player player : players) {
            Card card = deck.deal();
            card.flip();
            player.getHand().deal(card);
        }

        System.out.println();
        for (Player player: players) {
            int value = player.getHand().getValue();
            String line = player.getName() + ": " + player.getHand() + " (" + value + " )";
            System.out.println(line);
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
