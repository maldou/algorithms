package dynamic;

import java.util.ArrayList;
import java.util.List;

/** 
 *  最长公共子序列
 * @author waldoudou
 *
 */
public class LCSubsequence {
	
	public String[] lcs(String[] strArray1, String[] strArray2) {
		String[] newStrArray1 = transStr(strArray1);
		String[] newStrArray2 = transStr(strArray2);
		int[][] b = lcsLength(newStrArray1, newStrArray2);
		List<String> result = new ArrayList<String>();
		getLcs(b, newStrArray1, newStrArray1.length - 1, newStrArray2.length - 1, result);
		
		return result.toArray(new String[result.size()]);
	}
	
	private String[] transStr(String[] array) {
		int length1 = array.length;
		String[] newArray = new String[length1 + 1];
		newArray[0] = "";
		for(int i = 0; i < length1; i++) {
			newArray[i + 1] = array[i];
		}
		return newArray;
	}
	
	private int[][] lcsLength(String[] strArray1, String[] strArray2) {
		int[][] b = new int[strArray1.length][strArray2.length];
		int[][] c = new int[strArray1.length][strArray2.length];
		for(int i = 1; i < strArray1.length; i++) {
			for(int j = 1; j < strArray2.length; j++) {
				//c[i,j] = c[i - 1, j - 1] + 1
				if(strArray1[i].equals(strArray2[j])) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 0; // 左上
				}
				else{ //c[i, j] = max(c[i, j-1], c[i - 1, j])
					if(c[i - 1][j] >= c[i][j - 1]) {
						c[i][j] = c[i - 1][j];
						b[i][j] = -1; //左
					}
					else{
						c[i][j] = c[i][j - 1];
						b[i][j] = 1; //右
					}
				}
			}
		}
		
		return b;
	}
	
	private void getLcs(int[][] b, String[] strArray, int i, int j, List<String> result) {
		if(i == 0 || j == 0) {
			return ;
		}
		if(b[i][j] == 0) {
			getLcs(b, strArray, i - 1, j - 1, result);
			result.add(strArray[i]);
		}
		else if(b[i][j] == -1) {
			getLcs(b, strArray, i - 1, j, result);
		}
		else {
			getLcs(b, strArray, i, j - 1, result);
		}
	}
	
	public static void main(String[] args) {
		String[] array1 = new String[]{"a", "b", "c", "d", "e", "f", "g"};
		String[] array2 = new String[]{"a", "c", "a", "b", "f", "c", "e", "h"};
		LCSubsequence lcs = new LCSubsequence();
		String[] result = lcs.lcs(array1, array2);
		for(String str : result) {
			System.out.print(str);
		}
	}
}
