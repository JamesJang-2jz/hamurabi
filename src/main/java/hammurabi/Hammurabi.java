package hammurabi;
import java.util.Random;
import java.util.Scanner;
public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);
    int year, people = 95;
    int peopleStarved, peopleNew;
    int bushelWheat, acresLand, landPrice;
    int bushelHarvest, bushelDestroyed, cityLand, bushelPerAcre;
    public static void main(String[] args) {
        new Hammurabi().playGame();
    }
    void playGame() {
        peopleNew = 5;
        people += peopleNew;
        year = 0;
        peopleStarved = 0;
        bushelHarvest = 3000;
        bushelDestroyed = 200;
        bushelWheat = bushelHarvest - bushelDestroyed;
        acresLand = 1000;
        landPrice = 19;
        cityLand = 1000;
        bushelPerAcre = 3;
        while (year < 10) {
            year++;
            Intro();
            int acresToBuy = askHowManyAcresToBuy(landPrice, bushelWheat);
            if (acresToBuy == 0) {
                askHowManyAcresToSell(acresLand);
            }
            
        }
    }
    public void Intro() {
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



}
