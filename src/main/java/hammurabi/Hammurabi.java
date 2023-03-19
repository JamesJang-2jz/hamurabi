package hammurabi;
import java.util.Random;
import java.util.Scanner;
public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);
    int year = 0;
    int people = 95;
    int peopleStarved = 0;
    int peopleFull = 0;
    int peopleNew = 5;
    int bushelWheat;
    int acresLand = 1000;
    int landPrice = 19;
    int bushelHarvest = 3000;
    int bushelDestroyed = 200;
    int cityLand = 1000;
    int bushelPerAcre = 3;
    public static void main(String[] args) {
        new Hammurabi().playGame();
    }
    void playGame() {
        people += peopleNew;
        bushelWheat = bushelHarvest - bushelDestroyed;
        while (year < 10) {
            year++;
            Intro();
            int acresToBuy = askHowManyAcresToBuy(landPrice, bushelWheat);
            if (acresToBuy == 0) {
                int acresToSell = askHowManyAcresToSell(acresLand);
            }
            int howMuchToFeed = askHowMuchGrainToFeedPeople(bushelWheat);

        }
    }
    public void Intro() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Senpai Settler! \nYou are in year " + year + " of your 10 year rule");
        System.out.println("In the past year " + peopleStarved + " people got hangry & left\n"
                + "In the past year " + peopleNew + " people came to build houses & roads to settle\n"
                + "The population is now " + people + ".\n" + "We harvested " + bushelHarvest +
                " bushels at " + bushelPerAcre + " bushels per acre.\n" + "Rats destroyed " +
                bushelDestroyed + " bushels, leaving " + bushelWheat + " bushels in storage.\n" +
                "The city owns " + cityLand + " acres of land.\n" + "Land is currently worth " +
                landPrice + " bushels per acre. \n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public int askHowManyAcresToBuy(int price, int bushels) {
        System.out.println("Greetings Hammertime, how many acres of land are we buying? \n" +
                "Cost of land " + price + ". Current inventory " + bushels + "\n" +
                "Enter: 0 to skip");
        int input = scanner.nextInt();
        if (input * landPrice > bushelWheat) {
            System.out.println("Being cheeky are we? You don't have enough bushels");
            askHowManyAcresToBuy(price, bushels);
        } else if (input > 0) {
            acresLand += input;
            bushelWheat -= input * landPrice;
        }
        return input;
    }
    public int askHowManyAcresToSell(int acresOwned) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n How many acres would you like to sell over yonder? \n " +
                "Cost of land " + landPrice + ". Current inventory " + acresLand );
        int input = scanner.nextInt();
        if (input > acresOwned) {
            System.out.println("Sire, you are broke. You only possess " + acresLand + " acres.");
            askHowManyAcresToSell(acresOwned);
        } else if (input < acresOwned) {
            bushelWheat += input * landPrice;
            acresLand -= input;
        }
        return input;
    }
    public int askHowMuchGrainToFeedPeople(int bushels) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n Ciao! How much grain do you want to feed your people?" +
                "Current inventory: " + bushels + ". Population: " + people);
        int input = scanner.nextInt();
        if (input > bushelWheat) {
            System.out.println("Thou not have enough bushels!");
            askHowMuchGrainToFeedPeople(bushelWheat);
        } else if (input < bushelWheat) {
            bushelWheat -= input;
            peopleFull = input/20;
        } return input;
    }
    public int askHowManyAcresToPlant(int acresLand, int population, int bushels) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n Senpai! how many acres do you wish to sow with seed?" +
                "Current inventory: " + acresLand + "");
        int input = scanner.nextInt();
        if (input)
        return input;
    }


}
