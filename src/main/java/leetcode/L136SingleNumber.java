package leetcode;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * Solution:
 * Approach1: List operation 
 * 		1. save all elements to list. 
 * 		2 If new, add. 
 * 		3 if existed, remove it. 
 * 		4 only one will be left in the end.
 * Time O(n^2) Space O(n)
 * 
 * Approach2: Hash Set/table
 * 		1. Iterate through all elements in nums	
 * 		2. Try if hash_table has the key for pop
 * 		3. If not, set up key/value pair
 * 		4. In the end, there is only one element in hash_table, so use popitem to get it
 * Time O(n) Space O(n)
 *
 * Approach3: Math
 * 	Concept 2*(a+b+c) - (a+a+b+b+c) = c
 * Time O(n) Space O(n)
 * 
 * 	Approach4: Bit Manipulation (exclusive OR)
 * 	Concept a^a = 0;
 * 	Time O(n) Space O(1)
 *
 */


public class L136SingleNumber 
{
    public static void main( String[] args )
    {
    	int[] x = new int[] {1,2, 2, 3, 1};
        System.out.println(exclusiveOR(x));
        long test = -2l;
        System.out.println(test);
    }
    
    public static int exclusiveOR(int[] x) {
    	int result = 0;
    	for (int i = 0; i < x.length; i++) {
    		result ^= x[i];
		}
    	return result;
    }
}
