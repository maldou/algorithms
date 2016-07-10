package dynamic;

import java.util.ArrayList;
import java.util.List;

/** 
 *  最长公共子串
 * @author waldoudou
 *
 */
public class LCSubstring {
	public List<String[]> lcs(String[] strArray1, String[] strArray2) {
		List<int[]> maxIndex = maxIndex(strArray1, strArray2);
		List<String[]> lcsList = new ArrayList<>();
		for(int[] array : maxIndex) {
			int i = array[0];
			int maxLong = array[2];
			String[] result = getLcs(i, maxLong, strArray1);
			lcsList.add(result);
		}
		return lcsList;
	}
	
	private List<int[]> maxIndex(String[] strArray1, String[] strArray2) {
		int maxLong = 0;
		List<int[]> maxIndex = null;
		int[][] b = new int[strArray1.length][strArray2.length];
		for(int i = 0; i < strArray1.length; i++) {
			for(int j = 0; j < strArray2.length; j++) {
				if(strArray1[i].equals(strArray2[j])) {
					if(i == 0 || j == 0) {
						b[i][j] = 1;
					}
					else {
						b[i][j] = b[i - 1][j - 1] + 1;
					}
					if(b[i][j] > maxLong) {
						maxLong = b[i][j];
						maxIndex = new ArrayList<>();
						maxIndex.add(new int[]{i, j, maxLong});
					}
					else if(b[i][j] == maxLong && maxLong > 0) {
						maxIndex.add(new int[]{i, j, maxLong});
					}
				}
				else {
					b[i][j] = 0;
				}
			}
		}
		
		printProcess(b);
		
		return maxIndex;
	}
	
	private void printProcess(int[][] b) {
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j] +"  ");
			}
			System.out.println();
		}
	}
	
	private String[] getLcs(int endIndex, int maxLong, String[] strArray) {
		int startIndex = endIndex - maxLong + 1;
		String[] result = new String[maxLong];
		for(int i = 0; i < maxLong; i++) {
			result[i] = strArray[startIndex + i];
		}
		return result;
	}
	
	public static void main(String[] args) {
		String[] array1 = new String[]{"a", "b", "c", "d", "e", "f", "g"};
		String[] array2 = new String[]{"a", "c", "a", "b", "f", "c", "e", "h"};
		LCSubstring lcs = new LCSubstring();
		List<String[]> lcsList = lcs.lcs(array1, array2);
		for(String[] result : lcsList) {
			for(String str : result) {
				System.out.print(str);
			}
			System.out.println();
		}
		
		array1 = new String[]{"a", "b", "c", "d", "e", "f", "g"};
		array2 = new String[]{"a", "c", "a", "b", "f", "c", "d", "h"};
		lcsList = lcs.lcs(array1, array2);
		for(String[] result : lcsList) {
			for(String str : result) {
				System.out.print(str);
			}
			System.out.println();
		}
	}
}
