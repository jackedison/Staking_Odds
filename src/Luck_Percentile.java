import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Luck_Percentile {

  public static void main(String[] args) {



    int actualProfit = Integer.parseInt(args[0]);
    int stake = Integer.parseInt(args[1]);
    int stakes = Integer.parseInt(args[2]);
    int endCash = Integer.parseInt(args[3]);


    int simulations;
    if (args.length > 4) {
      simulations = Integer.parseInt(args[6]);
    }
    else {
      simulations = 1000000;
    }

    Luck_Percentile luck_percentile = new Luck_Percentile();

    // chance.myStakesSimulation(10000); Unused

    float luckpercentile = (1-luck_percentile.myStakesActualProfit(actualProfit))*(1-luck_percentile.my5050Simulation(stake, stakes, endCash, simulations));

    System.out.printf("You had an approximate " + "%1.2f", luckpercentile*100);
    System.out.println("% chance of ending with more than " + "x" + " profit for the last 2 months.");

  }





  // Simulate all stakes from .txt
  public void myStakesSimulation (int simulations) {
    try {
      theStakes(simulations);
    } catch (IOException e) {}
  }

  public void theStakes (int simulations)throws IOException{
    int maxProfit = 0;
    int minProfit = 0;
    OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("results.txt"), StandardCharsets.UTF_8);


    for(int i = 0; i<simulations; i++) {
      int next = stakeProfit();
      if (next > maxProfit) maxProfit = next;
      if (next < minProfit) minProfit = next;

      int j = i + 1;
      //System.out.println("Simulation " + j + " = " + next + "m profit.");
      out.write(next + "\n");
    }
    out.close();



    System.out.println("Maxprofit was = " + maxProfit);
    System.out.println("Minprofit was = " + minProfit);

  }


  public float myStakesActualProfit(int actualProfit){
    int simulations = 10000;

    int better = 0;
    for(int i=0; i<simulations; i++){
      if(stakeProfit()>actualProfit) better++;
    }
    float percentBetter = (float)better/simulations*100;

    System.out.printf("You had an approximate " + "%1.2f", percentBetter);
    System.out.println("% chance of ending with more than " + actualProfit + " profit with these x stakes.");

    return (float)better/simulations;


  }

  public int stakeProfit(){
    Random random = new Random();
    int profit = 0;

    try{
      Scanner in = new Scanner(new File("mystakes.txt"));
      while (in.hasNext()) { // Iterates each line in the file
        // Reads line to a string
        String line = in.nextLine();
        // Converts string into separate strings serperated by tabs (note strings to must be parsed to int later)
        String numbers [] = line.split("\t");

        if(random.nextBoolean()){
          profit = profit + Integer.parseInt(numbers[0]);
        }
        else{
          profit = profit - Integer.parseInt(numbers[1]);
        }
      }
      in.close(); // Closing resource leaks
    } catch (FileNotFoundException e){
      System.err.print("FileNotFoundException: " + e);
    }

    return profit;
    }


  // 1000, 300, -21000
  public float my5050Simulation(int stake, int stakes, int endcash, int simulations){
    Random Random = new Random();
    int better = 0;
    int profit = 0;

    for(int i = 0; i<simulations; i++){
      for (int j = 0; j < stakes; j++) {
        if (Random.nextBoolean()){
          profit = profit + stake;
        }
        else {
          profit = profit - stake;
        }
      }
      if (profit > endcash) {
        better++;
      }
      profit = 0;

    }
    float percentBetter = (float)better/simulations*100;

    System.out.printf("You had an approximate " + "%1.2f", percentBetter);
    System.out.println("% chance of ending with more than " + endcash + " profit 5050ing.");
    return (float)better/simulations;

  }
}
