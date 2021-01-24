// Evyatar Ben David
public class Answer {

	//  2018 A6_87, question 2, Complications O(1), O(n)
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

	//---------------------------------------------------------------------------
	//2020 A_85 question 1
	public static int findMaximum(int[][] mat) {
		if (mat[0][0] == -1)
			return -1;
		return findMaximum(mat, 0, 0);
	}

	private static int findMaximum(int[][] mat, int i, int j) {
		if (i < 0 || i > mat.length || j < 0 || j > mat[0].length || mat[i][j] == -1)
			return 0;
		if (i % 2 == 0) {
			int temp = mat[i][j];
			mat[i][j] = -1;
			return temp + Math.max(findMaximum(mat, i, j + 1), findMaximum(mat, i + 1, j));
		}
		int temp = mat[i][j];
		mat[i][j] = -1;
		return temp + Math.max(findMaximum(mat, i, j - 1), findMaximum(mat, i + 1, j));
	}

	//--------------------------------------------------------------
	//2020 A_85 question 2
	public static int strictlyIncreasing(int[] a) {
		int right = 1, left = 0, sum = 0, count = 1, temp = 0;
		while (right < a.length) {
			if (a[left] < a[right]) {
				right++;
				left++;
				count++;
				temp += count - 1;
			} else {
				left++;
				right++;
				count = 1;
				sum += temp;
				temp = 0;
			}
		}
		return sum + temp;
	}

	//-----------------------------------------------------------
	public static int maxSumKnight(int[][] mat) {
		return maxSumKnight(mat, 0, 0);
	}

	private static int maxSumKnight(int[][] mat, int n, int m) {
		if (n < 0 || n >= mat.length || m < 0 || m >= mat.length || !canMove(mat, n, m))
			return -1;
		if (n == mat.length - 1 && m == mat[0].length - 1)
			return mat[n][m];
		int temp = mat[n][m];
		mat[n][m] = Integer.MAX_VALUE;
		return temp + Math.max(Math.max(Math.max(maxSumKnight(mat, n - 2, m + 1), maxSumKnight(mat, n - 2, m + 1)),
				Math.max(maxSumKnight(mat, n - 1, m + 2), maxSumKnight(mat, n - 1, m - 1)))
				, Math.max(Math.max(maxSumKnight(mat, n + 1, m + 2), maxSumKnight(mat, n + 1, m - 2)),
						Math.max(maxSumKnight(mat, n + 2, m + 1), maxSumKnight(mat, n + 2, m - 1))));
	}

	private static boolean canMove(int[][] mat, int n, int m) {
		return Math.abs(mat[n][m] - mat[n - 2][m + 1]) == 1 ||
				Math.abs(mat[n][m] - mat[n - 2][m - 1]) == 1 ||
				Math.abs(mat[n][m] - mat[n - 1][m + 2]) == 1 ||
				Math.abs(mat[n][m] - mat[n - 1][m - 2]) == 1 ||
				Math.abs(mat[n][m] - mat[n + 1][m + 2]) == 1 ||
				Math.abs(mat[n][m] - mat[n + 1][m - 2]) == 1 ||
				Math.abs(mat[n][m] - mat[n + 2][m + 1]) == 1 ||
				Math.abs(mat[n][m] - mat[n + 2][m - 1]) == 1;
	}

	//------------------------------------------------------------
	//2020 b_84 question 2
	/*
	public void findFactors(int n) {
		int low = 0;
		int high = n / 2;
		int mid = (low + high) / 2;
		while (n % mid != 0) {
			mid = (low + high) / 2;
			if (isHighFactorInRange(n, mid, high)) // There is no need to practice this method, I did it to avoid a run error
				low = mid;
			else
				mid = high;
		}
		int high_num = mid;
		int low_num = n / mid;
		System.out.println(high_num + "*" + low_num + "=" + n);
	}

	 */

	//------------------------------------------------------
	//2019_A 84 question 1
	public static boolean sumPower3(int num) {
		return sumPower3(num, 1, 0);
	}

	private static boolean sumPower3(int num, int x, int sum) {
		if (sum == num)
			return true;
		if (x > num)
			return false;
		if (sum > num)
			return false;
		return sumPower3(num, x * 3, sum + x) || sumPower3(num, x * 3, sum);
	}

	//------------------------------------------------------------------
	// 2019_B 85 question 2
	//effiency O(lonN), O(1)
	public static int meetingPoint(int[] a, int[] b) {
		int low = 0;
		int high = a.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (a[mid] == b[mid]) {
				while (a[mid] == b[mid]) {
					if (a[mid - 1] == b[mid - 1])
						mid = mid - 1;
					else
						return mid;
				}
				return mid;
			}
			if (a[mid] < b[mid])
				high = mid;
			else
				low = mid;
		}
		return -1;
	}

	//----------------------------------------------
	public static boolean isSum(int[] a, int num) {
		return isSum(a, num, 0, 0, 0);
	}

	private static boolean isSum(int[] a, int num, int count, int sum, int i) {
		if (count == 3)
			return false;
		if (num == sum)
			return true;
		if (sum > num)
			return false;
		if (i == a.length)
			return false;
		return isSum(a, num, count + 1, sum + a[i], i++) || isSum(a, num, 0, sum + a[i], i++);
	}

	//-----------------------------------------------
	//2018A_85 question 1
	public static int longestSlope(int[][] mat, int num) {
		return longestSlope(mat, num, 0, 0, 1, 0, mat[0][0]);
	}

	private static int longestSlope(int[][] mat, int num, int i, int j, int count, int sum, int last) {
		if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || mat[i][j] < 0)
			return 0;
		if (i == mat.length - 1 && j == mat[0].length - 1)
			return sum;
		if (last - mat[i][j] == num) {
			count++;
			sum = Math.max(sum, count);
			int temp = mat[i][j];
			mat[i][j] = -1;
			sum = Math.max(Math.max(longestSlope(mat, num, i + 1, j, count, sum, temp), longestSlope(mat, num, i - 1, j, count, sum, temp)),
					Math.max(longestSlope(mat, num, i, j + 1, count, sum, temp), longestSlope(mat, num, i, j - 1, count, sum, temp)));
			mat[i][j] = temp;
		}
		int temp = mat[i][j];
		mat[i][j] = -1;
		sum = Math.max(Math.max(longestSlope(mat, num, i + 1, j, 1, sum, temp), longestSlope(mat, num, i - 1, j, 1, sum, temp)),
				Math.max(longestSlope(mat, num, i, j + 1, 1, sum, temp), longestSlope(mat, num, i, j - 1, 1, sum, temp)));
		return sum;
	}
}