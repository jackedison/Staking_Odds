import java.util.Random;

public class Scam_Chance {

  public static void main(String[] args) {

    int wins = Integer.parseInt(args[0]);
    int losses = Integer.parseInt(args[1]);

    int simulations;
    if (args.length > 2) {
      simulations = Integer.parseInt(args[2]);
    }
    else {
      simulations = 1000000;
    }

    Scam_Chance scam_chance = new Scam_Chance();
    scam_chance.scamChance(wins, losses, simulations);


  }

  // Just shows percentile on distribution curve really. Will just be normal distribution of w/l
  public void scamChance(int w, int l, int simulations){
    int wins;
    int losses;
    int stakes = w+l;

    int scam = 0;
    int notscam = 0;

    Random Random = new Random();

    for(int i = 0; i<simulations; i++){
      wins = 0;
      losses = 0;
      for(int j = 0; j<stakes; j++){
        if(Random.nextBoolean()){
          wins++;
        }
        else{
          losses++;
        }
      }
      if(Math.abs(wins - losses) < Math.abs(w - l)){
        scam++;
      }
      else{
        notscam++;
      }
    }
    scam = scam + (int)(0.5 * notscam);
    notscam = notscam - (int)(0.5 * notscam);

    System.out.println(scam + " and " + notscam);
  }
}

