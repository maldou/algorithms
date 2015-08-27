package sort;

public class MaxValueAndTimes {
	
	public static void f(int[] a) {
		int[] newArray = f2(a);
		int maxValue = newArray[newArray.length - 1];
		int times = 1;
		int tempTimes = 0;
		boolean b = false;
		for(int i = newArray.length - 2; i >= 0; i--) {
			int temp = newArray[i];
			
			if(temp == newArray[i + 1]) {
				if(!b) 
					times++;
				else {
					if(++tempTimes > times) {
						times = tempTimes;
						b = false;
						maxValue = temp;
						tempTimes = 0;
					}
				}
			}
			else {
				b = true;
				tempTimes = 1;
			}
		}
		
		System.out.println("maxValue: " + maxValue + "  times: " + times) ;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {-1,-1,0,5,3,1,-1,2,8,98,3,6,54,8,3,23,8,9,67,56,8,45,3,2,25,8,10,3,3};
		f(a);
	}
	
	private static int[] f2(int[] array) {
		quickSort(array, 0, array.length-1);
		return array;
	}
	
	private static int partition(int array[], int p, int r)
	{	
		int j = p;
		for(int i = p; i < r; i++)
		{
			if(array[i] <= array[r])
			{
//				int temp = array[i];
//				array[i] = array[j];
//				array[j++]  = temp;
				
				if(i != j)
				{
//					array[i] = array[i] + array[j];
//					array[j] = array[i] - array[j];
//					array[i] = array[i] - array[j];
					
					array[i] = array[i] ^ array[j];
					array[j] = array[i] ^ array[j];
					array[i] = array[i] ^ array[j];
				}				
				j++;
			}
		}
		
		int temp = array[r];
		array[r] = array[j];
		array[j]  = temp;

		
		return j;
	}
	
	private static void quickSort(int array[], int p, int r)
	{
		if(p < r)
		{
			int q = partition(array, p, r);
			quickSort(array, p, q-1);
			quickSort(array, q+1, r);
		}
	}
	

}
