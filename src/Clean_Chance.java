import java.util.Random;


public class Clean_Chance {

  public static void main(String[] args) {

    double startcash = Double.parseDouble(args[0]);
    double stake = Double.parseDouble(args[1]);
    double x = Double.parseDouble(args[2]);

    int simulations;
    if (args.length > 3) {
      simulations = Integer.parseInt(args[3]);
    }
    else {
      simulations = 10000000;
    }

    Clean_Chance clean_chance = new Clean_Chance();
    clean_chance.cleanChance(startcash, stake, x, simulations);

  }


  public void cleanChance(double startcash, double stake, double x, int simulations){
    Random Random = new Random();
    int wins = 0;
    int losses = 0;

    while(wins+losses<simulations) {
      double cash = startcash;
      while (cash > (stake/x) && cash < (startcash*10)) {
        if (Random.nextBoolean()) {
          cash = cash + stake;
        } else {
          cash = cash - (stake / x);
        }
      }

      if (cash >= startcash) {
        wins++;
      } else {
        losses++;
      }

    }

    System.out.println(simulations +" simulations of: Starting Cash = " + startcash + "  " +
            "Stake Value = " + stake + "  X = " + x);
    System.out.println("Never Cleaned = " + wins + ", Cleaned = " + losses);
    System.out.println("Therefore, " + (double) losses / simulations + "% chance of being cleaned in LR");
  }

}
