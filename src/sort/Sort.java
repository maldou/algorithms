package sort;

import java.util.*;

public class Sort {
	public static void main(String[] args) {
		// HeapSort heapsort=new HeapSort();
		// heapsort.sort();
		int[] B = { 20, 12, 62, 89, 5, 33, 51, 28, 91, 10 };
		HeapSort.sort();

		QuickSort quickSort = new QuickSort();
		quickSort.sort();

		InsertSort insertSort = new InsertSort(B);
		insertSort.sort();
		System.out.println(insertSort.toString());

		MergeSort mergeSort = new MergeSort();
		mergeSort.sort();

		PSort pSort = new PSort(B);
		pSort.sort();

		CountingSort cSort = new CountingSort();
		cSort.sort();
	}

}

class HeapSort {
	public static void sort() {

		int[] A = { 20, 12, 62, 89, 5, 33, 51, 28, 91, 10 };
		int length = A.length;
		BulidMaxHeap(A, length);
		for (int i = length - 1; i >= 1; i--) {
			int t;
			t = A[0];
			A[0] = A[i];
			A[i] = t;
			length--;
			MaxHeapify(A, length, 0);
		}
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println(new Exception().getStackTrace()[0]);
		System.out.println(Thread.currentThread().getStackTrace());
	}

	private static void MaxHeapify(int[] a, int le, int i) {
		int largest;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (l < le && a[l] > a[i]) {
			largest = l;
		} else {
			largest = i;
		}
		if (r < le && a[r] > a[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp;
			temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			MaxHeapify(a, le, largest);
		}
	}

	private static void BulidMaxHeap(int[] a, int le) {
		for (int i = a.length / 2 - 1; i >= 0; i--) {
			MaxHeapify(a, le, i);
		}
	}

}

class QuickSort {
	public void sort() {
		int[] A = { 20, 12, 62, 89, 5, 33, 51, 28, 91, 10 };
		int length = A.length;
		quicksort(A, 0, length - 1);
		System.out.println(Arrays.toString(A));
	}

	private void quicksort(int[] a, int p, int r) {
		if (p < r) {
			int q = partition(a, p, r);
			quicksort(a, p, q - 1);
			quicksort(a, q + 1, r);
		}
	}

	private int partition(int[] a, int p, int r) {
		int k = a[r];
		int m = p - 1;
		int temp;
		for (int i = p; i < r; i++) {
			if (a[i] < k) {
				m++;
				temp = a[i];
				a[i] = a[m];
				a[m] = temp;
			}
		}
		m++;
		temp = a[m];
		a[m] = a[r];
		a[r] = temp;
		return m;
	}
}

class InsertSort {
	private int[] A;

	InsertSort(int[] A) {
		this.A = A;
	}

	public void sort() {

		int length = A.length;
		for (int i = 1; i < length; i++) {
			int k = A[i];
			int j = i - 1;
			while (j >= 0 && k < A[j]) {
				A[j + 1] = A[j];
				j--;
			}
			A[j + 1] = k;
		}
		System.out.println(Arrays.toString(A));
	}

	public String toString() {
		return getClass().getName() + "    the array=" + A;
	}
}

class MergeSort {
	public void sort() {
		int[] A = { 20, 12, 62, 89, 5, 33, 51, 28, 91, 10 };
		int length = A.length;
		mergeSort(A, 0, length - 1);
		System.out.println(Arrays.toString(A));
	}

	private void mergeSort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

	private void merge(int[] a, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			L[i] = a[p + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = a[q + j + 1];
		}
		L[n1] = (int) (1.0 / 0); // 小数除以0可以得到一个无穷大数
		R[n2] = (int) (1.0 / 0);
		// System.out.println(L[n1]);
		int i = 0, j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				a[k] = L[i++];
			} else {
				a[k] = R[j++];
			}
		}
	}
}

class PSort {
	private int[] A;

	PSort(int[] A) {
		this.A = A;
	}

	public void sort() {
		int length = A.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (A[i] > A[j]) {
					int t = A[i];
					A[i] = A[j];
					A[j] = t;
				}
			}
		}
		System.out.println(Arrays.toString(A));
	}
}

class CountingSort // 线形时间排序：计数排序
{
	public void sort() {
		int[] a = { 12, 25, 67, 98, 22, 46, 55, 30, 12, 29 };
		int k = 100;
		int length = a.length;
		int[] b = new int[length];
		int[] c = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			c[i] = 0;
		}
		for (int i = 0; i < length; i++) {
			c[a[i]] = c[a[i]] + 1;
		}
		for (int i = 1; i <= k; i++) {

			c[i] = c[i] + c[i - 1];

		}
		for (int i = length - 1; i >= 0; i--) {
			b[c[a[i]] - 1] = a[i];
			c[a[i]]--;
		}
		System.out.println(Arrays.toString(b));
	}

}