package hammurabi;
import java.nio.file.LinkPermission;
import java.util.InputMismatchException;
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
    static int bushelDestroyed = 200;
    int cityLand = 1000;
    int bushelPerAcre = 3;
    static boolean plague = false;
    static boolean ratInfestation = false;

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        people += peopleNew;
        bushelWheat = bushelHarvest - bushelDestroyed;
        landPrice = (int) (Math.random() * 10 + 17);
        acresLand = bushelHarvest / bushelPerAcre;
        while (year < 10) {
            year++;
            Intro();
            int acresToBuy = askHowManyAcresToBuy(landPrice, bushelWheat);
            if (acresToBuy == 0) {
                int acresToSell = askHowManyAcresToSell(acresLand);
            }
            int peopleFed = askHowMuchGrainToFeedPeople(bushelWheat);
            int acresToPlant = askHowManyAcresToPlant(acresLand, people, bushelWheat);
            int numberPlagueDeaths = plagueDeaths(people);
            int numberStarvationDeaths = starvationDeaths(people, peopleFed);
            if (uprising(people, numberStarvationDeaths)) {
                System.out.println("Senpai Settler, we banish you. Be Gone!");
                break;
            }

        }
    }
    int getNumber(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
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
        int input = getNumber("Greetings Harambe, how many acres of land are we buying? \n" +
                "Cost of land " + price + ". Current inventory " + bushels + "\n" +
                "Enter: 0 to skip");
        do {
            if (input * landPrice > bushelWheat) {
                System.out.println("Being cheeky are we? You only have " + bushels + " bushels");
            } else if (input < 0) {
                System.out.println("Bloody hell, No negative numbers!");
            }
        } while (input * landPrice > bushelWheat);
        acresLand += input;
        bushelWheat -= input * landPrice;
        return input;
    }
    public int askHowManyAcresToSell(int acresOwned) {
        int input = getNumber("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \nHow many acres would you like to sell over yonder? \n" +
                "Cost of land " + landPrice + ". Current inventory " + acresLand);
        do {
            if (input > acresOwned) {
                System.out.println("Sire, you are broke. You only possess " + acresLand + " acres.");
                askHowManyAcresToSell(acresOwned);
            } else if (input < 0) {
                System.out.println("Bloody hell, No negative numbers!");
            }
        } while (input > acresOwned);
        bushelWheat += input * landPrice;
        acresLand -= input;
        return input;
    }
    public int askHowMuchGrainToFeedPeople(int bushels) {
        int input = getNumber("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \nCiao! How much grain do you want to feed your people?\n" +
                "Current inventory: " + bushels + ". Population: " + people);
        do {
            if (input > bushels) {
                System.out.println("Thou not have enough bushels!");
                askHowMuchGrainToFeedPeople(bushelWheat);
            } else if (input < 0)
                System.out.println("Please don't be so negative");
        } while (input > bushels);
        bushelWheat -= input;
//        peopleFull = input / 20;
        return input;
    }
    public int askHowManyAcresToPlant(int acresLand, int population, int bushels) {
        int input = getNumber("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n Senpai! how many acres do you wish to sow with seed?" +
                "Current inventory: " + acresLand + "");
        do {
            if (input > acresLand) {
                System.out.println("You only have " + acresLand + " acres!");
            } else if (input / 2 > bushels) {
                System.out.println("Being cheeky are we? You only have " + bushels + " bushels!");
            } else if (input > population * 10) {
                System.out.println("Senpai! You only have " + population + " people to tend the fields!");
            }
        } while (input > acresLand || input / 2 > bushels || input > population * 10);
//        bushelWheat -= input / 2;
//        bushelPerAcre = (int) (Math.random() * 5 + 1);
//        bushelHarvest = input * bushelPerAcre;
//        input = (int) (Math.random() * 5 + 1);
        return input;
    }
    public int plagueDeaths(int population) {
        int deaths = 0;
        if (rand.nextInt(100) < 15) {
            deaths = population/2;
        } return deaths;
    }
    public int starvationDeaths(int population, int bushelsFedToPeople) {
        if (bushelsFedToPeople / 20 > population) {
            return 0;
        } else {
            return population - bushelsFedToPeople / 20;
        }
    }
    public boolean uprising(int population, int howManyPeopleStarved){
        if ()
    }
//    public int immigrants(int population, int acresOwned, int grainInStorage){
//
//    }
//    public int harvest(int acresPlanted){
//
//    }
//    public int grainEatenByRats(int bushels){
//
//    }
//    public int newCostOfLand(){
//
//    }

}
