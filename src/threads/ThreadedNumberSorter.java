package threads;

import java.util.Random;

public class ThreadedNumberSorter {
	/* Double thread is only faster at > 1,000,000 numbers */
	static final int TOTAL_NUMS = 10000000;

	// Complete the method below so that it uses threads to sort the integer
	// array.
	// Try to get the completion time as short as possible.
	// Use the printArray method to check sorting accuracy

	static void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	static void finalMerge(int arr1[], int arr2[], int n1, int n2, int arr[]) {

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = 0;
		while (i < n1 && j < n2) {
			if (arr1[i] <= arr2[j]) {
				arr[k] = arr1[i];
				i++;
			} else {
				arr[k] = arr2[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of arr1[] if any */
		while (i < n1) {
			arr[k] = arr1[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = arr2[j];
			j++;
			k++;
		}
	}

	public static void sort(int[] nums, int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(nums, l, m);
			sort(nums, m + 1, r);

			// Merge the sorted halves
			merge(nums, l, m, r);
		}
	}

	public static void mergeSort(int[] nums, int l, int r) {
		// Complete this method starting at this point

		// Main function that sorts arr[l..r] using
		// merge()
		sort(nums, l, r);

	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		int[] nums = new int[TOTAL_NUMS];

		Random randGen = new Random();
		for (int i = 0; i < TOTAL_NUMS; i++) {
			nums[i] = randGen.nextInt(TOTAL_NUMS);
		}

		int[] subArr1 = new int[TOTAL_NUMS / 2];
		int[] subArr2 = new int[TOTAL_NUMS / 2];

		for (int k = 0; k < (TOTAL_NUMS / 2); k++)
			subArr1[k] = nums[k];
		for (int j = 0; j < (TOTAL_NUMS / 2); j++)
			subArr2[j] = nums[j + (TOTAL_NUMS / 2)];

		int l = 0, r = nums.length - 1;
		int l1 = 0, r1 = subArr1.length - 1;
		int l2 = 0, r2 = subArr2.length - 1;

		// printArray(nums);
		Thread t1 = new Thread(() -> mergeSort(subArr1, l1, r1));
		Thread t2 = new Thread(() -> mergeSort(subArr2, l2, r2));
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// mergeSort(nums, l, r);

		finalMerge(subArr1, subArr2, (TOTAL_NUMS / 2), (TOTAL_NUMS / 2), nums);
		// printArray(nums);

		long totalTime = System.nanoTime() - startTime;
		double timeInSeconds = (double) totalTime / 1_000_000_000;
		System.out.println("The total computing time in seconds was: " + timeInSeconds);

	}

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}
}
