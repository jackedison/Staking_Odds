import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class E_Profit {

  public static void main(String[] args) {

    int simulations;
    if (args.length > 0) {
      simulations = Integer.parseInt(args[0]);
    }
    else {
      simulations = 10000;
    }

    E_Profit e_profit = new E_Profit();
    e_profit.myStakesEProfit(simulations);

  }

  public void myStakesEProfit(int simulations){

    int total = 0;
    for(int i=0; i<simulations; i++){
      total = total + stakeProfit();
    }
    int averageProfit = total/simulations;
    System.out.println("Your simulated expected profit from these exact stakes is: " + averageProfit + "m");


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






}
