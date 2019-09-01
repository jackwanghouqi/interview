package interview.facebook;

/**
 * Find two prime numbers with given sum
 * 
 * Input : 4
 * Output: 2 + 2 = 4
 * 
 * If [a, b] is one solution with a <= b,
 * and [c,d] is another solution with c <= d, then
 * [a, b] < [c, d] 
 * If a < c OR a==c AND b < d.
 * 
 * */
public class PrimeNumbersSum {

	public static void main(String[] args) {
		int testNumber = 10;
		int[] primePair = primesum(testNumber);
		if(primePair == null) {
			System.out.println("null");
		} else {
			System.out.println(primePair[0] + " + " + primePair[1] + " = " + testNumber);
		}
	}
	
	
	public static int[] primesum(int n) {
		boolean[] isPrime = sieve(n);
		for(int i=0; i<n; i++) {
			if(isPrime[i] && isPrime[n-i]) {
				return new int[] {i, n-i};
			}
		}
		return null;
	}
	
	private static boolean[] sieve(int n) {
		boolean[] isPrime = new boolean[n+1];
		isPrime[0] = isPrime[1] = false;
		//init
		for(int i = 2; i<isPrime.length; i ++) {
			isPrime[i] = true;
		}
		
		for(int i = 2; i*i <n; i++) {
			if(isPrime[i]) {
				for(int j = i*2; j<=n; j+= i) {
					isPrime[j] = false;
				}
			}
		}
		return isPrime;
	}
	
	
	
	

}
