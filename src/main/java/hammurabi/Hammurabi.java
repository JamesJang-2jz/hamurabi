package hammurabi;
import java.nio.file.LinkPermission;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int year = 0;
    int people = 95;
    int peopleStarved = 0;
    int peopleFull = 0;
    int peopleNew = 5;
    int numPlagueDeaths;
    int bushelWheat;
    int acresLand = 1000;
    int landPrice = 19;
    int bushelHarvest = 3000;
    static int bushelDestroyed = 200;
    int bushelPerAcre = 3;
    boolean plague = false;
    boolean ratInfestation = false;

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        people += peopleNew;
        bushelWheat = bushelHarvest - bushelDestroyed;
        acresLand = bushelHarvest / bushelPerAcre;
        while (year < 10) {
            year++;
            plague = false;
            ratInfestation = false;
            Intro();
            int acresToBuy = askHowManyAcresToBuy(landPrice, bushelWheat);
                acresLand += acresToBuy;
                bushelWheat -= acresToBuy * landPrice;
            if (acresToBuy == 0) {
                int acresToSell = askHowManyAcresToSell(acresLand);
                bushelWheat += acresToSell * landPrice;
                acresLand -= acresToSell;
            }
            int grainToFeed = askHowMuchGrainToFeedPeople(bushelWheat);
                bushelWheat -= grainToFeed;
                peopleFull = grainToFeed / 20;
            int acresToPlant = askHowManyAcresToPlant(acresLand, people, bushelWheat);
                bushelWheat -= acresToPlant / 2;
            numPlagueDeaths = plagueDeaths(people);
            if (plague) {
                System.out.println("Plague has struck the land. Number of deaths: " + numPlagueDeaths);
            } else {
                System.out.println("Your land has been spared from plague, enjoy");
            }
            people -= numPlagueDeaths;
            peopleStarved = starvationDeaths(people, grainToFeed);
            if (uprising(people, peopleStarved)) {
                System.out.println("There was an uprising!");
                System.out.println("Senpai Settler, we banish you. Be Gone! RIP Harambe");
                break;
            }
            people -= peopleStarved;
            if (peopleStarved == 0){
                peopleNew = immigrants(people, acresLand, bushelWheat);
            } else {
                peopleNew = 0;
            }
                people += peopleNew;
            bushelHarvest = harvest(acresToPlant);
            bushelDestroyed = grainEatenByRats(bushelWheat);
            landPrice = newCostOfLand();
            printSummary();
        }
        if (year == 10) {
            finalSumary();
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
                "The city owns " + acresLand + " acres of land.\n" + "Land is currently worth " +
                landPrice + " bushels per acre. \n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public int askHowManyAcresToBuy(int price, int bushels) {
        int input;
        System.out.println("Greetings Harambe, how many acres of land will you buy?");
        do {
            input = getNumber("Cost of land " + price + ". Current inventory " + bushels + "\n" +
                    "Enter: 0 to skip");
            if (input * landPrice > bushelWheat) {
                System.out.println("Being cheeky are we? You only have " + bushels + " bushels");
            } else if (input < 0) {
                System.out.println("Bloody hell, No negative numbers!");
            } else if (input == 0){
                return input;
            }
        } while (input * landPrice > bushelWheat);
        return input;
    }
    public int askHowManyAcresToSell(int acresOwned) {
        int input;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nHow many acres would you like to sell over yonder?");
        do {
            input = getNumber("Cost of land " + landPrice + ". Current inventory " + acresOwned);
            if (input > acresOwned) {
                System.out.println("Sire, you are broke. You only possess " + acresLand + " acres.");
            } else if (input < 0) {
                System.out.println("Bloody hell, No negative numbers!");
            } else if (input == 0){
                return input;
            }
        } while (input > acresOwned);
        return input;
    }
    public int askHowMuchGrainToFeedPeople(int bushels) {
        int input;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \nCiao! How much grain do you want to feed your people?");
        do {
            input = getNumber("Current inventory: " + bushels + ". Population: " + people);
            if (input > bushels) {
                System.out.println("Thou not have enough bushels!");
            } else if (input < 0)
                System.out.println("Please don't be so negative");
        } while (input > bushels);
        return input;
    }
    public int askHowManyAcresToPlant(int acresLand, int population, int bushels) {
        int input;
        System.out.println("People fed: " + peopleFull + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \nSenpai! how many acres do you wish to sow with seed?");
        do {
            input = getNumber("Current acres owned: " + acresLand + " Current bushels: " + bushelWheat);
            if (input > acresLand) {
                System.out.println("You only have " + acresLand + " acres!");
            } else if (input / 2 > bushels) {
                System.out.println("Being cheeky are we? You only have " + bushels + " bushels!");
            } else if (input > population * 10) {
                System.out.println("Senpai! You only have " + population + " people to tend the fields!");
            }
        } while (input > acresLand || input / 2 > bushels || input > population * 10);
        return input;
    }
    public int plagueDeaths(int population) {
        int deaths = 0;
        if (rand.nextInt(100) < 15) {
            deaths = population/2;
            plague = true;
            return deaths;
        } return 0;
    }
    public int starvationDeaths(int population, int bushelsFedToPeople) {
        if (bushelsFedToPeople / 20 >= population) {
            return 0;
        } else {
            return population - bushelsFedToPeople / 20;
        }
    }
    public boolean uprising(int population, int howManyPeopleStarved){
        return (double) howManyPeopleStarved/population > .45;
    }
    public int immigrants(int population, int acresOwned, int grainInStorage){
        return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
    }
    public int harvest(int acresPlanted){
        return (rand.nextInt(6) + 1) * acresPlanted;
    }
    public int grainEatenByRats(int bushels){
        int eaten = (int) (bushels * (double) (rand.nextInt(20) + 10)/100);
        if (rand.nextInt(100) < 40) {
            return eaten;
        }
        return 0;
    }
    public int newCostOfLand(){
        return rand.nextInt(7) + 17;
    }

    public void printSummary() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + "Senpai Settler Harambe, year " +
                year + " in review:\n" + "People starved: " + peopleStarved +
                "\nImmigrants to the city: " + peopleNew );
                if (plague) {
                    System.out.println("Plague has struck the land! Half the people died.\n" +
                            "Number of deaths: " + numPlagueDeaths);
                }
        System.out.println("Number of people starved to death: " + peopleStarved + "\n");
        System.out.println("Grain eaten by rats " + bushelDestroyed);
    }
    public void finalSumary(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + "Senpai Settler! You have won the game. In your 10 years of Rule, " +
                "you helped " + people + " live a prosperous life in the Harambe kingdom.\n" +
                "Please don't play this game again I beg of you. FPS games preferred");
    }

}
