/* Evyatar Ben David

*/
public class Answer {

	//  2018 A6_87, Question 2, Complications O(1), O(n)
	public static boolean what(int[] a, int num) {
		int right = 0;
		int left = 0;
		int sum = 0;
		while (right < a.length) {
			if (sum == num)
				return true;
			sum += a[right++];
			if (sum > num) {
				sum -= a[left++];
			}
		}
		return false;
	}
}
//---------------------------------------------------------------------------
