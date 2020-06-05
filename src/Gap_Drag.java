import java.util.Random;


public class Gap_Drag {

  public static void main(String[] args) {

    int simulations;
    if (args.length > 0) {
      simulations = Integer.parseInt(args[0]);
    }
    else {
      simulations = 1000000;
    }

    Gap_Drag gap_drag = new Gap_Drag();
    gap_drag.gapDrag(simulations);

  }


  public void gapDrag(int simulations){
    int wins = 0;
    int losses = 0;

    int simul = simulations;

    while(simul>0){
      int lp = 990;
      int opplp = 990;
      int total = wins + losses;
      while(total == wins + losses){
        lp = lp - ranHit(99);
        if(lp<=0){
          losses++;
        }
        else{
          opplp = opplp - ranHit(99);
          if(opplp<=0){
            wins++;
          }
        }
      }
      simul--;
    }

    System.out.println("\nIn " + simulations + " gapped stakes you had: " + wins + " wins and " + losses + " losses.");
    System.out.printf("%.2f", (double)losses/(double)wins);
    System.out.println("x");

  }


  public int ranHit(double defLevel){
    Random Random = new Random();
    double max = ((462 / (1-0.099)) * (1 - (defLevel/1000)));
    return Random.nextInt(((int)max - 1) + 1) + 1;
  }


}
