import java.util.Random;

public class Chance {

  public static void main(String[] args) {

    int myMage = Integer.parseInt(args[0]);
    int myDef = Integer.parseInt(args[1]);
    int myHP = Integer.parseInt(args[2]);
    int oppMage = Integer.parseInt(args[3]);
    int oppDef = Integer.parseInt(args[4]);
    int oppHP = Integer.parseInt(args[5]);

    int simulations;
    if (args.length > 6) {
      simulations = Integer.parseInt(args[6]);
    }
    else {
      simulations = 10000000;
    }

    Chance chance = new Chance();
    chance.myStatsSimulation(myMage, myDef, myHP, oppMage, oppDef, oppHP, simulations);

  }

  // Simulates odds of winning a stake based on your and opponent's stats
  public void myStatsSimulation(int myMage, int myDef, int myHP, int oppMage, int oppDef, int oppHP, int simulations){

    System.out.println("Running " + simulations + " simulations of " + myMage + "/" + myDef + "/" + myHP + " vs " + oppMage + "/" + oppDef + "/" + oppHP + " (mage/def/hp)");

    Random random = new Random();
    int myMax = maxHit(myMage, oppDef);
    int oppMax = maxHit(oppMage, myDef);
    int wins = 0;
    int losses = 0;

    for(int i = 0 ; i<simulations; i++){
      int myLP = myHP * 10;
      int oppLP = oppHP * 10;

      while (myLP>0 && oppLP>0){
        oppLP = oppLP - random.nextInt(myMax + 1 - 1) + 1;
        myLP = myLP - random.nextInt(oppMax + 1 - 1) + 1;
      }

      if(myLP<=0 && oppLP<=0){
        if(random.nextBoolean())wins++;
        else losses++;
      }
      else if (oppLP<=0) wins++;
      else if (myLP<=0) losses++;
    }

    System.out.println("Wins: " + wins);
    System.out.println("Losses: " + losses);
    System.out.printf("%.3f" + "x", (double)losses/(double)wins);
  }

  // Returns max polypore hit based on your magic level and opponent's defence level
  public int maxHit(int mageLevel, int defLevel){
    // 1984 from polypore damage, 2.5 per magic level sub 99. x2.3 for legacy multiplier. 0.1% dmg reduct per def lvl.
    double damage = (1984 + mageLevel*2.5)/10;
    double max = damage*2.3;
    max = max * (1 - ((double)defLevel/1000));

    return (int)max;

  }
}

