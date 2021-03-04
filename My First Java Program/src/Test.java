
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Question #12
		/*int number = 2147483647;

		System.out.println(number);
		number+= 10;
		System.out.println(number);
		 */
		
		//Question #13
		final int N = 1024;
		double fraction = 1.0 / N;
		double total = 0;
		
		for (int count = 1; count <= N; count++)
			total += fraction;
		System.out.println(total);
	}

}
