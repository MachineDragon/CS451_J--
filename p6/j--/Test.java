public class Test {
	public static int computeIter(int n){
		int result = 1;
		while ( n > 0){
			result = result * n--;
		}
		return result;
	}
}
