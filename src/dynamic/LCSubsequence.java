package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 *  最长公共子序列
 * @author waldoudou
 *
 */
public class LCSubsequence<E> {
	
	public List<E> lcs(List<E> list1, List<E> list2) {
		int[][] b = lcsLength(list1, list2);
		List<E> result = new ArrayList<>();
		getLcs(b, list1, list1.size(), list2.size(), result);
		return result;
	}
	
	private int[][] lcsLength(List<E> list1, List<E> list2) {
		int[][] b = new int[list1.size() + 1][list2.size() + 1];
		int[][] c = new int[list1.size() + 1][list2.size() + 1];
		for(int i = 1; i < list1.size() + 1; i++) {
			for(int j = 1; j < list2.size() + 1; j++) {
				//c[i,j] = c[i - 1, j - 1] + 1
				if(list1.get(i - 1).equals(list2.get(j - 1))) {
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
	
	private void getLcs(int[][] b, List<E> list, int i, int j, List<E> result) {
		if(i == 0 || j == 0) {
			return ;
		}
		if(b[i][j] == 0) {
			getLcs(b, list, i - 1, j - 1, result);
			result.add(list.get(i - 1));
		}
		else if(b[i][j] == -1) {
			getLcs(b, list, i - 1, j, result);
		}
		else {
			getLcs(b, list, i, j - 1, result);
		}
	}
	
	public static void main(String[] args) {
		String[] array1 = new String[]{"a", "b", "c", "d", "e", "f", "g"};
		String[] array2 = new String[]{"a", "c", "a", "b", "f", "c", "e", "h"};
		LCSubsequence<String> lcs = new LCSubsequence<>();
		List<String> result = lcs.lcs(Arrays.asList(array1), Arrays.asList(array2));
		for(String str : result) {
			System.out.print(str);
		}
		System.out.println();
		array1 = new String[]{"中国", "美国", "法国", "德国", "英国", "意大利", "冰岛"};
		array2 = new String[]{"中国", "日本", "美国", "德国", "法国", "捷克", "英国", "冰岛"};
		lcs = new LCSubsequence<>();
		result = lcs.lcs(Arrays.asList(array1), Arrays.asList(array2));
		for(String str : result) {
			System.out.print(str + ";");
		}
	}
}
