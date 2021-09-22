import java.util.Scanner;

public class CoffeeMachine {
    static int hasWater = 400;
    static int hasMilk = 540;
    static int hasBeans = 120;

    static int hasMoney = 550;
    static int hasCups = 9;

    enum HotBeverage {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        int water;
        int milk;
        int beans;
        int price;

        HotBeverage(int water, int milk, int beans, int price) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }
    }

    public static void showState() {
        System.out.println("The coffee machine has:");
        System.out.println(hasWater + " ml of water");
        System.out.println(hasMilk + " ml of milk");
        System.out.println(hasBeans + " g of beans");
        System.out.println(hasCups + " disposable cups");
        System.out.println(hasMoney + "$ of money");
        System.out.println();
    }

    public static void processUserInput(String input) {
        while (true) {
            switch (input) {
                case "buy":
                    buy();
                    return;
                case "fill":
                    ServiceWorker.fill();
                    return;
                case "take":
                    ServiceWorker.takeMoney();
                    return;
                case "back":
            }
        }
    }

    public static void buy() {


        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino");
        final Scanner scanner = new Scanner(System.in);

        String userOrder = scanner.nextLine();

        switch (userOrder) {
            case "1":
                if (checkIngredients(new int[]{HotBeverage.ESPRESSO.water, HotBeverage.ESPRESSO.milk, HotBeverage.ESPRESSO.beans})) {
                    System.out.println("I have enough resources, making you a coffee!");
                    hasWater = hasWater - HotBeverage.ESPRESSO.water;
                    hasMilk = hasMilk - HotBeverage.ESPRESSO.milk;
                    hasBeans = hasBeans - HotBeverage.ESPRESSO.beans;
                    hasMoney = hasMoney + HotBeverage.ESPRESSO.price;
                    hasCups--;
                } else {
                    findNeededIngredients(new int[]{HotBeverage.ESPRESSO.water, HotBeverage.ESPRESSO.milk, HotBeverage.ESPRESSO.beans});
                }
                break;

            case "2":
                if (checkIngredients(new int[]{HotBeverage.LATTE.water, HotBeverage.LATTE.milk, HotBeverage.LATTE.beans})) {
                    System.out.println("I have enough resources, making you a coffee!");
                    hasWater = hasWater - HotBeverage.LATTE.water;
                    hasMilk = hasMilk - HotBeverage.LATTE.milk;
                    hasBeans = hasBeans - HotBeverage.LATTE.beans;
                    hasMoney = hasMoney + HotBeverage.LATTE.price;
                    hasCups--;
                } else {
                    findNeededIngredients(new int[]{HotBeverage.ESPRESSO.water, HotBeverage.ESPRESSO.milk, HotBeverage.ESPRESSO.beans});
                }
                break;

            case "3":
                if (checkIngredients(new int[]{HotBeverage.CAPPUCCINO.water, HotBeverage.CAPPUCCINO.milk, HotBeverage.CAPPUCCINO.beans})) {
                    System.out.println("I have enough resources, making you a coffee!");
                    hasWater = hasWater - HotBeverage.CAPPUCCINO.water;
                    hasMilk = hasMilk - HotBeverage.CAPPUCCINO.milk;
                    hasBeans = hasBeans - HotBeverage.CAPPUCCINO.beans;
                    hasMoney = hasMoney + HotBeverage.CAPPUCCINO.price;
                    hasCups--;
                } else {
                    findNeededIngredients(new int[]{HotBeverage.ESPRESSO.water, HotBeverage.ESPRESSO.milk, HotBeverage.ESPRESSO.beans});
                }

        }
    }

    private static void findNeededIngredients(int[] need) {
        int[] has = {hasWater, hasMilk, hasBeans, hasCups};
        String[] nameOfIngredients = {"water", "milk", "beans", "cups"};
        for (int i = 0; i < need.length; i++) {
            if (has[i] < need[i]) {
                System.out.println("Sorry, not enough " + nameOfIngredients[i] + " !");
                break;
            }
        }
    }

    public static boolean checkIngredients(int[] need) {
        int[] has = {hasWater, hasMilk, hasBeans};
        for (int i = 0; i < need.length; i++) {
            if (has[i] < need[i]) {
                return false;
            }
        }
        return true;
    }

    static class ServiceWorker {

        public static void takeMoney() {
            System.out.println("I gave you $" + hasMoney);
            hasMoney = 0;
        }

        public static void fill() {
            final Scanner scanner = new Scanner(System.in);
            System.out.println("Write how many ml of water you want to add: ");
            int fillWater = scanner.nextInt();
            hasWater = hasWater + fillWater;
            System.out.println("Write how many ml of milk you want to add: ");
            int fillMilk = scanner.nextInt();
            hasMilk = hasMilk + fillMilk;
            System.out.println("Write how many grams of coffee beans you want to add: ");
            int fillBeans = scanner.nextInt();
            hasBeans = hasBeans + fillBeans;
            System.out.println("Write how many disposable cups of coffee you want to add: ");
            int fillCups = scanner.nextInt();
            hasCups = hasCups + fillCups;
        }
    }

    public static void main(String[] args) {

        while (true) {
            final Scanner scanner = new Scanner(System.in);
            System.out.println("Write action (buy, fill, take): ");

            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }
            if (userInput.equalsIgnoreCase("remaining")) {
                showState();
                continue;
            }
            processUserInput(userInput);
            System.out.println();
        }
    }
}

