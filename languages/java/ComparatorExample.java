package sort;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExample {
	
	//Integer.compare(o2[0], o1[0]) == o2[0] - o1[0]
	//using comparator
	private static void sort1(int arr2d[][]){
		Comparator<int[]> arrComparator = new Comparator<int[]>(){
			@Override
			public int compare(int o1[], int o2[]){
				return Integer.compare(o2[0], o1[0]);
			}
		};
		
		Arrays.sort(arr2d, arrComparator);
	}
	
	//using comparator without defining new object
	private static void sort2(int arr2d[][]){
		Arrays.sort(arr2d, new Comparator<int[]>(){
			@Override
			public int compare(int o1[], int o2[]){
				return Integer.compare(o2[0], o1[0]);
			}
		});
	}
	
	//using ramda
	private static void sort3(int arr2d[][]){
		Arrays.sort(arr2d, (o1, o2) -> {
			return Integer.compare(o2[0], o1[0]);
		});
	}
	
	public static void main(String[] args) {
		int arr2d[][] = {{2, -2}, {1, -1}, {3, -3}};
		
		sort1(arr2d);
//		sort2(arr2d);
//		sort3(arr2d);
		
		//print array
		for (int i = 0; i < arr2d.length; i++) {
			for (int j = 0; j < arr2d[0].length; j++) {
				System.out.print(arr2d[i][j] + " ");
			}
			System.out.println();
		}
	}
}
