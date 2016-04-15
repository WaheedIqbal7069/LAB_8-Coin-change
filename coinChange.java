import java.util.Random;

public class coinChange {

	public static int query = 45;	//input
	
	public static int Dynamicprog(int argNumber){
		System.out.println("\nRunning dynamic coin change...");
		int[] COINS = {1,5,10,15};
		query = argNumber;
		int remainingValue = query;
		
	    int m = COINS.length + 1;
	    int n = query + 1;

	    int[][] table = new int[m][n];
	    //Initializing table to a large value
	    for(int i = 0; i< m; ++i) {
	        for (int j = 1; j < n; j++) {
	            table[i][j] = 99999;
	        }
	    }

	    for (int i = 1; i < m; i++) {
	        for (int currentAmount = 1; currentAmount < n; currentAmount++) {
	            if (COINS[i-1] <= currentAmount) {
	                remainingValue = table[i][currentAmount - COINS[i-1]];
	            }
	            else {
	                remainingValue = 99999;
	            }
	            table[i][currentAmount] = Math.min(table[i-1][currentAmount], 1 + remainingValue);
	        }
	    }
	    System.out.println("Coins needed: " +table[m-1][n-1] );
	    return table[m-1][n-1];
	}

	public static int Greedy_Algo(int argNumber){
		System.out.println("Running greedy coin change...");
		int[] COINS = {1,5,10,15};
		query = argNumber;
		int remainingValue = query;
		String result = "";
		int coinCount = 0;
		//Simply start from the opposite and check if the coin is less than remaining value
		while(remainingValue!=0){
			for(int i=COINS.length-1; i>=0; i-- ){
				if(COINS[i]<=remainingValue){
					result = result + Integer.toString(COINS[i]) + " ";
					remainingValue -= COINS[i];
					coinCount++;
					break;
				}
			}
		}
		
		//System.out.println(result);
		System.out.println("Coins needed: " +coinCount);
		return coinCount;
	}
	
	public static void main(String[] args) {
		//Greedy_Algo(45);
		//Dynamicprog(45);
		int dynamicBetter = 0;
		
		//It never finds even number where dynamic is better than greedy with these denominations
		while(dynamicBetter!=1){
			Random rand = new Random();
			int someNum = rand.nextInt(10000)+1;
			System.out.println("Random number: " +someNum);
			int greedyResult = Greedy_Algo(someNum);
			int dynamicResult = Dynamicprog(someNum);
			if(dynamicResult<greedyResult){
				dynamicBetter++;
			}
		}
		
	}

}
