/**
 * This class contains four methods, according to the questions in Assignment 14.
 * @author Evyatar Ben David
 * @version 15.12.2020
 */
public class Ex14 {
	public static void main(String[] args) {
		int[] a = {0,4,4,3,3,6,6,7,7,12,12};
		int[] a1 = {2,2,2,2,3,2,2};
		int[] b = {8,8,-7,-7,3,3,0,0,10,10,5,5,4};
		int [] arr1 = {1, 4, 45, 6, 10, 19};
		int[] arr2 = {1,10,5,2,7};
		int[] arr3 = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
		int[] arr4 = {0,45,6,0};
		boolean[][] mat = new boolean[5][5];
		boolean[][] mat1 = {
				{false,false,false,false,true},
				{false,true,true,true,false},
				{false,false,true,true,false},
				{true,false,false,false,false},
				{true,true,false,false,false},
		};
		boolean [][] mat2 =  {
				{ false, false, false, false, false },
				{ false, true, true, true, true },
				{ false, true, true, false, false },
				{ true, false, false, false, false },
				{ true, true, false, false, false },
		};
		System.out.println(cntTrueReg(mat2));
	}

	/**
	 * findSignale returns the only number in the array that appears only once
	 * Efficiency: time - O(log(n)), space = O(1);
	 * @param a An array complete with integers, where each number appears twice in sequence except for one number that appears only once.
	 * @return The number that appears only once.
	 */
	public static int findSingle(int[] a) {
		int start = 0;
		int end = a.length;
		int mid = 0; // I have stated only three variables, so the space efficiency of the place is 1
		if (a.length == 1 || a[0] != a[1]) //Check whether the first number is different from the second or it is the only number.
			return a[0];
		else if (a[a.length - 1] != a[a.length - 2]) //Check if the last number is different from the previous one.
			return a[a.length - 1];
		while (start <= end){ // The half list that contains the single number is not divisible by 2 with no remainder, and so the number can be found by dividing the list in half each time.
			mid = (start + end) /2;
			if (a[mid] != a[mid - 1] && a[mid] != a[mid + 1])
				return a[mid];
			if (a[mid] == a[mid - 1]) {
				if((end - mid + 1) % 2 == 0)
					end = mid - 2;
				else
					start = mid +1;
			}
			else
				if((end - mid + 2) % 2 == 0)
					end = mid -1;
				else
					start = mid + 2;
		}
		return -1;
	}

	/**
	 * The method returns the minimum size of the smallest sub-array whose sum is greater than x.
	 * * Efficiency: time - O(n), space = O(1); (n is the array length).
	 * @param arr the parent array in which we will look for the sub-array.
	 * @param x the number that is less than the sum of the subsets.
	 * @return the smallest sub-array size, the amount of which is greater than x.
	 */
	public static int smallestSubSum(int [] arr, int x)
	{
		int right = 0, left = 0; //Variables for realizing a sliding window
		int sum = 0, total_sum = 0;
		int count = arr.length, cur_count = 0;
		while (sum <= x && right < arr.length) { //Sliding window technique allows me to test the whole array in one loop, so the efficiency is O(n).
			sum += arr[right];
			total_sum += arr[right++];
			while (sum > x) {
				cur_count = right - left;
				sum -= arr[left++];
				count = Math.min(count, cur_count);
			}
		}
		if (total_sum <= x || arr.length == 0) //Check if the array is empty or if the amount is less than x.
			return -1;
		return count;
	}

	/**
	 * solutions is a recursive method that returns the number of solutions that the equation has, and prints each solution.
	 * @param num The sum of the equation.
	 * @return the number of solutions that the equation has.
	 */
	public static int solutions(int num){
		if(num < 3 || num > 30) // Check whether the amount is possible, depending on the limitation of the variables between 1 and 10.
			return 0;
		else
			return solutions(num, 1 , 1, 1);
	}

	private static int solutions(int num, int x1, int x2, int x3){
		int sum = 0;
		if(x1 +x2 + x3 == num) {
			System.out.println(x1 + " + " + x2 + " + " + x3 + " = " + num);
			++sum;
		}
		//I created a mechanism that simulates a nested loop. First ran the inner loop, then the middle and finally the main.
		if(x3 <= 10)
			return sum + solutions(num, x1, x2, x3 +1);// Recursive reading that simulates a loop.
		if(x2 <= 10)
			return sum + solutions(num, x1, x2+1, 1);//Changing the variable that has already been tested to 1 leads to no recurring solutions.
		if(x1 <= 10)
			return sum + solutions(num, x1 +1, 1, 1);//Changing the variable that has already been tested to 1 leads to no recurring solutions.
		return sum;
	}

	/**
	 * cntTrueReg is a recursive method counts 'true zones' in the matrix.
	 * @param mat The matrix is full of truth and falsehoods
	 * @return 	How many areas of truth are there in the matrix
	 */
	public static int cntTrueReg (boolean[][]mat){
		return cntTrueReg(mat, 0, 0);
	}
	//A private method that goes over each cube in the matrix
	private static int cntTrueReg(boolean[][] mat, int i, int j){
		if(i == mat.length)
			return cntTrueReg(mat, 0, j+1);
		if(j == mat[0].length)
			return 0;
		if (mat[i][j] == true){
			mat = clearMat(mat, i, j);
			return 1 + cntTrueReg(mat, i+1, j);
		}
		return cntTrueReg(mat, i+1, j);
	}
	//A private method that deletes every real area after it is counted
	private static boolean[][] clearMat(boolean[][] mat, int i, int j){
		if (j <= mat[i].length -1){
			if(j < mat[0].length - 1  && mat[i][j+1] == true){
				clearMat(mat, i, j+1);
				mat[i][j] = false;
			}
			if( i <= mat.length -2 && mat[i+1][j] == true){
				clearMat(mat, i+1,j);
				mat[i+1][j] = false;
			}
		}
		if(j == mat[i].length){
			if(i<= mat.length -2 && mat[i+1][j] == true){
				clearMat(mat, i+1,j);
				mat[i+1][j] = false;
			}
		}
		if(i == mat.length-1){
			if(j <= mat[i].length -2 && mat[i][j+1] == true){
				clearMat(mat, i, j+1);
				mat[i][j] = false;
			}
		}
		mat[i][j] = false;
		return mat;
	}
}