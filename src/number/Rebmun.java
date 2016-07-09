package number;

/**
 * input: 123000; output:321
 * @author meng
 *
 */
public class Rebmun {
	
	public long value(long sourceValue) {
		long result = 0;
		
		String source = String.valueOf(sourceValue);
		String s0 = source.substring(0, 1);
		result = Long.valueOf(s0);
		
		for(int i = 1; i < source.length(); i++) {
			String sx = source.substring(i, i + 1);
			result += Long.valueOf(sx) * Math.pow(10, i);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Rebmun rebmun = new Rebmun();
		System.out.println(rebmun.value(12300276));
	}
}
