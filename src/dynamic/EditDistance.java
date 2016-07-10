package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 编辑距离，动态规划
 * 在实际应用场景中，为了计算两个list（串）之间的编辑距离，需要对输入进行归一化（降噪）
 * @author waldoudou
 *
 * @param <E>
 */
public class EditDistance<E extends Comparable<E>> {
	
	private int updateCost = 1;
	private int insertCost = 1;
	private int deleteCost = 1;
	
	public EditDistance() {
		this(1, 1, 1);
	}
	public EditDistance(int updateCost, int insertCost, int deleteCost) {
		this.updateCost = updateCost;
		this.insertCost = insertCost;
		this.deleteCost = deleteCost;
	}
	
	public int editDistance(List<E> list1, List<E> list2) {
		int[][] editArray = editArray(list1, list2);
		printEditArray(editArray, list1.size() + 1, list2.size() + 1);
        int distance = editArray[list1.size()][list2.size()];
        return distance;
	}
	
	private int[][] editArray(List<E> list1, List<E> list2) {
		int[][] editArray = new int[list1.size() + 1][list2.size() + 1];
		//E（0，0）= 0，E（i，0）= i，E（0，j）= j
		for(int i = 0; i <= list1.size(); i++) {
			editArray[i][0] = i;
		}
		for(int i = 0; i <= list2.size(); i++) {
			editArray[0][i] = i;
		}
		//E(i, j) = min( [E(i-1, j) + D], [E(i, j-1) + I],  [E(i-1, j-1) + R (如果 i,j 字符不一样)] )
		for(int i = 1; i <= list1.size(); i++) {
			E e1 = list1.get(i - 1);
			for(int j = 1; j <= list2.size(); j++) {
				E e2 = list2.get(j - 1);
				int min = Math.min(editArray[i - 1][j] + deleteCost , editArray[i][j - 1] + insertCost);
				int r = e1.compareTo(e2) == 0? 0 : updateCost;
				min = Math.min(min, editArray[i - 1][j- 1] + r);
				editArray[i][j] = min;
			}
		}
		
		return editArray;
	}
	
	private void printEditArray(int[][] editArray, int length1, int length2) {
		System.out.println("========edit array=====================");
        for(int i = 0; i < length1; i++) {
            for(int j = 0; j < length2; j++) {
                System.out.print(editArray[i][j] + "  ");
            }
            System.out.println("");
        }
	}
	
	public static void main(String[] args) {
		String[] arr1 = new String[]{"b", "r", "o", "t", "h", "e", "r"}; //brother;
		List<String> list1 = Arrays.asList(arr1);
		String[] arr2 = new String[]{"f", "a", "t", "h", "e", "r"}; //father
		List<String> list2 = Arrays.asList(arr2);
		
		EditDistance<String> ed = new EditDistance<>();
		int distance = ed.editDistance(list1, list2);
		System.out.println("edit distance:" + distance);
		
		EditDistance<Entity> ed2 = new EditDistance<>();
		Entity e1 = new Entity(1);
		Entity e2 = new Entity(3);
		Entity e3 = new Entity(10);
		Entity e4 = new Entity(9);
		Entity e5 = new Entity(8);
		Entity e6 = new Entity(5);
		Entity e7 = new Entity(6);
		Entity e8 = new Entity(15);
		Entity e9 = new Entity(23);
		Entity e10 = new Entity(24);
		Entity e11 = new Entity(91);
		Entity e12 = new Entity(19);
		List<Entity> liste1 = new ArrayList<>();
		liste1.add(e1);
		liste1.add(e12);
		liste1.add(e5);
		liste1.add(e8);
		liste1.add(e10);
		List<Entity> liste2 = new ArrayList<>();
		liste2.add(e12);
		liste2.add(e8);
		liste2.add(e10);
		liste2.add(e2);
		liste2.add(e3);
		distance = ed2.editDistance(liste1, liste2);
		System.out.println("edit distance:" + distance);
	}
}

class Entity implements Comparable<Entity>{
	private int index;
	
	public Entity(int index) {
		this.index = index;
	}

	@Override
	public int compareTo(Entity o) {
		if(index > o.index) {
			return 1;
		}
		else if(index < o.index) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
