package interview.facebook;

/**
 * Given a positive integer which fits in a 32 bit signed integer, find if it can be expressed as A^P where P > 1 and A > 0. A and P both should be integers.
 * 
 * 
 * */
public class IsPowerOf {

	public static void main(String[] args) {
		int pow = (int)Math.pow(12, 4);
		System.out.println(isPower(pow));
		System.out.println(isPower(pow+1));
		System.out.println(isPower(1));
	}
	
	public static boolean isPower(int num) {
		for(int a = 1; a*a <= num; a++) {
			if(isPower(num,a)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isPower(int a, int x) {
        if(a <= 0)
            return false;
        if(a == 1)
            return true;
        double log = Math.log10(a)/Math.log10(x);
        return log - (int)log == 0;
    }

}
