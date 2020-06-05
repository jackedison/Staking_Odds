# Monte Carlo Method implementations of RS3 Polypore Odds Staking

This directory contains 2017 Java code of Monte Carlo methods for polypore staking on [Runescape](www.runescape.com).

Using these methods odds for stat variants can be calculated ensuring the xer maintains adequate odds to profit in the long run.

I personally applied these implementations to assist in the earnings of over 500bn RS3 gp in late 2017. I stopped to focus my efforts on my work and other more meaninful projects.

I am pushing the code here to share with old friends who continue to use this as a source of income.

## To run:
* Compile with `javac {class_name}.java`
* Run with `java {class_name}.java {*aargs}`

## Classes

| Class           | Arguments (optional)                                                          | Description                                                                                                                                 |
|-----------------|-------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Chance          | myMage, myDef, myHP, oppMage, oppDef, oppHP, (simulations=10_000_000)         | Computes odds based on your stats vs opponent's stats                                                                                       |
| Clean_Chance    | starting cash, stake amount, x, (simulations=10_000_000)                      | Computes odds of getting cleaned (losing entire bankroll) in long run. Assumes never if reaching 10x starting bankroll.                     |
| E_Profit        | "mystakes.txt", (simulations=10_000)                                          | Takes log of xs in as a .txt and computes E(Profit) with Monte Carlo. Fairly futile as calculation mathematically is trivial.               |
| Gap_Drag        | (simulations=1_000_000)                                                       | Computes loss drag from gapping in a duel. TL;DR with even odds ~1.60x                                                                      |
| Luck_Percentile | "mystakes.txt", actualProfit, stake, stakes, endCash, (simulations=1_000_000) | Takes log of xs in as a .txt and computes your luck percentile on the distribution vs Monte Carlo. Note: not Gaussian so this is necessary. |
| Scam_Chance     | wins, losses, (simulations=1_000_000)                                         | Trivial implementation of scam likelihood; odds of similar data to your logged wins & losses occurring.                                     |

## For example:
To compute odds of stat variation of 99 mage, 60 def, 99 hp, vs 99/99/99 run:
1. Compile with `javac Chance.java`
2. Run with `java Chance 99 60 99 99 99 99`

To adjust to run 100m simulations instead of the default 10m run as:
* `java Chance 99 60 99 99 99 99 100000000`

Output (executed in 3.173s):

`Running 100000000 simulations of 99/60/99 vs 99/99/99 (mage/def/hp)`

`Wins: 46074175`

`Losses: 53925825`

`1.170x`




## Requirements: 
* JDK 14+
* To run with java/javac keywords you may need to configure environmental variables. You mean also need to run as src.{class_name} depending on your setup.