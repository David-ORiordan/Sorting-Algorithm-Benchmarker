package ie.gmit.dip;

import java.util.Arrays;

public class BenchmarkingProject {

	// arrays hold the times taken for the algorithms to sort, with each element dedicated to one of the array lengths
	public static double[] avgBubbleTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static double[] avgSelectionTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static double[] avgInsertionTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static double[] avgMergeTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static double[] avgCountingTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	// Bubble Sort Algorithm
	// Code taken from https://www.javatpoint.com/bubble-sort
	public static void bubbleSort(int a[]) // function to implement bubble sort
	{
		int n = a.length;
		int i, j, temp;
		for (i = 0; i < n; i++) { // iterate through each element in the array
			for (j = i + 1; j < n; j++) { // iterate through each subsequent element in the array (except those already sorted)
				if (a[j] < a[i]) { // If second element is greater than the first element then the two elements swap places
					temp = a[i];  
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	// Selection Sort Algorithm
	// Code taken from https://www.javatpoint.com/selection-sort
	static void selection(int a[]) // function to sort an array with selection sort
	{
		int i, j, small;
		int n = a.length;
		for (i = 0; i < n - 1; i++) { // iterate through the unsorted array
			small = i; // minimum element in unsorted array

			for (j = i + 1; j < n; j++) // iterate through every other element in the unsorted array to find the smallest element
				if (a[j] < a[small])
					small = j;
			// Swap the minimum element with the first element  
			int temp = a[small];
			a[small] = a[i];
			a[i] = temp;
		}

	}

	// Insertion Sort Algorithm
	// Code taken from https://www.javatpoint.com/insertion-sort
	static void insert(int a[]) // function to sort an array with insertion sort
	{
		int i, j, temp;
		int n = a.length;
		for (i = 1; i < n; i++) {
			temp = a[i]; // temp element chosen
			j = i - 1; // index number behind temp element
			while (j >= 0 && temp <= a[j]) // checks if temp element is greater than or equal to element behind it
			{							   // Move the elements greater than temp to one position ahead from their current position
				a[j + 1] = a[j]; 		   // A sorted sub-array is left behind as temp continues to move through the unsorted sub-array
				j = j - 1;       
			}
			a[j + 1] = temp; 
		}
	}

	// Merge Sort Algorithm
	// Code taken from https://www.javatpoint.com/merge-sort
	static void merge(int a[], int beg, int mid, int end) { // function to merge the sub-arrays 
		int i, j, k;
		int n1 = mid - beg + 1;
		int n2 = end - mid;

		// temporary Arrays
		int LeftArray[] = new int[n1];
		int RightArray[] = new int[n2];

		// copy data to temp arrays
		for (i = 0; i < n1; i++)
			LeftArray[i] = a[beg + i];
		for (j = 0; j < n2; j++)
			RightArray[j] = a[mid + 1 + j];

		i = 0; // initial index of first sub-array 
		j = 0; // initial index of second sub-array 
		k = beg; // initial index of merged sub-array 

		while (i < n1 && j < n2) {
			if (LeftArray[i] <= RightArray[j]) {
				a[k] = LeftArray[i];
				i++;
			} else {
				a[k] = RightArray[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			a[k] = LeftArray[i];
			i++;
			k++;
		}
		while (j < n2) {
			a[k] = RightArray[j];
			j++;
			k++;
		}
	}

	static void mergeSort(int a[], int beg, int end) {
		if (beg < end) {
			int mid = (beg + end) / 2; // middle of the unsorted array
			mergeSort(a, beg, mid); // divides the array in half until it cannot be divided further
			mergeSort(a, mid + 1, end); // divides the array in half until it cannot be divided further
			merge(a, beg, mid, end); // merged sub-arrays are merged into a sorted array
		}
	}

	// Counting Sort Algorithm
	// Code taken from https://www.javatpoint.com/counting-sort
	static int getMax(int[] a, int n) {  
		  int max = a[0];  
		  for(int i = 1; i<n; i++) {  
		      if(a[i] > max)  
		         max = a[i];  
		  }  
		  return max; //maximum element from the array  
		}  
		  
		static void countSort(int[] a, int n) // function to perform counting sort  
		{  
		   int[] output = new int [n+1];  
		   int max = getMax(a, n);   
		   int[] count = new int [max+1]; //create count array with size [max+1]  
		  
		  for (int i = 0; i <= max; ++i)   
		  {  
		    count[i] = 0; // Initialize count array with all zeros  
		  }  
		    
		  for (int i = 0; i < n; i++) // Store the count of each element  
		  {  
		    count[a[i]]++;  
		  }  
		  
		   for(int i = 1; i<=max; i++)   
		      count[i] += count[i-1]; //find cumulative frequency  
		  
		  /* This loop will find the index of each element of the original array in  
		  count array, and place the elements in output array*/  
		  for (int i = n - 1; i >= 0; i--) {  
		    output[count[a[i]] - 1] = a[i];  
		    count[a[i]]--; // decrease count for same numbers  
		}  
		  
		   for(int i = 0; i<n; i++) {  
		      a[i] = output[i]; //store the sorted elements into main array  
		   }  
		}  

	// Code for creating the unsorted arrays was taken from the project specification sheet
	static int[] randomArray(int n) { // function to create arrays of random integers of size n (100, 250, ... 10000)
		int[] array = new int[n]; // create array of size n
		for (int i = 0; i < n; i++) { // iterate over each element in the array
			array[i] = (int) (Math.random() * 100); // generates a random integer from 0 to 99 and assign it to the array element
		}
		return array;
	}

	
	
	static void runSortingAlgorithm(int i, int whichAlgo, int[] unsortedArray) { // i indicates array length
		int copy[] = Arrays.copyOf(unsortedArray, unsortedArray.length); // create a copy of the unsorted array
		if (whichAlgo == 0) { // Bubble Sort
			// Code for calculating the time taken by the algorithms was taken from the project specification sheet
			// calculates the time taken to sort a copy of the array, converts time from nanoseconds to milliseconds
			long start = System.nanoTime(); 
			bubbleSort(copy); 
			long end = System.nanoTime(); 
			long timeElapsed = end - start; 
			double elapsedMillis = (double) timeElapsed / 1000000; 
			avgBubbleTimes[i] += elapsedMillis; // add time to array element housing all the times for a given array length
		} else if (whichAlgo == 1) { // Selection Sort
			// calculates the time taken to sort a copy of the array, converts time from nanoseconds to milliseconds
			long start = System.nanoTime(); 
			selection(copy); 
			long end = System.nanoTime();
			long timeElapsed = end - start; 
			double elapsedMillis = (double) timeElapsed / 1000000;
			avgSelectionTimes[i] += elapsedMillis; // add time to array element housing all the times for a given array length
		} else if (whichAlgo == 2) { // Insertion Sort
			// calculates the time taken to sort a copy of the array, converts time from nanoseconds to milliseconds
			long start = System.nanoTime(); 
			insert(copy);
			long end = System.nanoTime();
			long timeElapsed = end - start;
			double elapsedMillis = (double) timeElapsed / 1000000;
			avgInsertionTimes[i] += elapsedMillis;// add time to array element housing all the times for a given array length
		} else if (whichAlgo == 3) { // Merge Sort
			// calculates the time taken to sort a copy of the array, converts time from nanoseconds to milliseconds
			int n = unsortedArray.length;
			long start = System.nanoTime(); 
			mergeSort(copy, 0, n - 1); 
			long end = System.nanoTime();
			long timeElapsed = end - start;
			double elapsedMillis = (double) timeElapsed / 1000000;
			avgMergeTimes[i] += elapsedMillis; // add time to array element housing all the times for a given array length
		} else if (whichAlgo == 4) { // Counting Sort
			// calculates the time taken to sort a copy of the array, converts time from nanoseconds to milliseconds
			int n = unsortedArray.length;
			long start = System.nanoTime(); 
			countSort(copy, n); 
			long end = System.nanoTime(); 
			long timeElapsed = end - start;
			double elapsedMillis = (double) timeElapsed / 1000000;
			avgCountingTimes[i] += elapsedMillis; // add time to array element housing all the times for a given array length
		}
	}

	public static void main(String[] args) {
		int[] arrayLengths = { 100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000 }; // the different array lengths being used whilst benchmarking the algorithms
		for (int i = 0; i < arrayLengths.length; i++) { // loop through each of the array lengths
			for (int j = 0; j < 10; j++) { // perform benchmarking on each of the algorithms ten times 
				int[] unsorted = randomArray(arrayLengths[i]); // generate the unsorted array  
				for (int k = 0; k < 5; k++) { // loop through the five algorithms being used
					runSortingAlgorithm(i, k, unsorted); // i indicates array length, k indicates which algorithm is being tested, unsorted indicates the unsorted array
				}
			}
		}
		// prints each of the various array lengths
		System.out.print("Size:  \t");
		for (int l = 0; l < arrayLengths.length; l++) {
			System.out.print("\t" + arrayLengths[l]);
		}
		// prints the sorting times for each of the various array lengths
		System.out.print("\nBubble Sort: ");
		for (int l = 0; l < arrayLengths.length; l++) {
			avgBubbleTimes[l] = avgBubbleTimes[l] / 10; // divide by ten to get the average speed of the ten performances
			System.out.print("\t");
			System.out.printf("%.3f", avgBubbleTimes[l]); // prints time to three decimal places
		}
		// prints the sorting times for each of the various array lengths
		System.out.print("\nSelection Sort: ");
		for (int l = 0; l < arrayLengths.length; l++) {
			avgSelectionTimes[l] = avgSelectionTimes[l] / 10; // divide by ten to get the average speed of the ten performances
			System.out.printf("%.3f", avgSelectionTimes[l]); // prints time to three decimal places
			System.out.print("\t");

		}
		// prints the sorting times for each of the various array lengths
		System.out.print("\nInsertion Sort: ");
		for (int l = 0; l < arrayLengths.length; l++) {
			avgInsertionTimes[l] = avgInsertionTimes[l] / 10; // divide by ten to get the average speed of the ten performances
			System.out.printf("%.3f", avgInsertionTimes[l]); // prints time to three decimal places
			System.out.print("\t");
		}
		// prints the sorting times for each of the various array lengths
		System.out.print("\nMerge Sort: ");
		for (int l = 0; l < arrayLengths.length; l++) {
			avgMergeTimes[l] = avgMergeTimes[l] / 10; // divide by ten to get the average speed of the ten performances
			System.out.print("\t");
			System.out.printf("%.3f", avgMergeTimes[l]); // prints time to three decimal places
		}
		// prints the sorting times for each of the various array lengths
		System.out.print("\nCounting Sort: ");
		for (int l = 0; l < arrayLengths.length; l++) {
			avgCountingTimes[l] = avgCountingTimes[l] / 10; // divide by ten to get the average speed of the ten performances
			System.out.print("\t");
			System.out.printf("%.3f", avgCountingTimes[l]); // prints time to three decimal places
		}
	}

}
