public class BcdToStr {
	public static void test () {
	}

	public static void main (String args[]) {
		char s = (char)9;
		String str = ""+s+(char)4+(char)3;
		byte[] newbcd =str.getBytes();
		byte[] bcdDigits= new byte[]{0, 4, 3};
		// for double digit
//		StringBuilder sb = new StringBuilder(bcdDigits.length * 2);
//		for (byte b : bcdDigits) {
//			sb.append(String.format("%02x", b));
//		}

		// for single digit
		StringBuilder sb = new StringBuilder(newbcd.length);
		//for (byte b : bcdDigits) {
		for (byte b : newbcd) {
			sb.append(String.format("%x", b));
		}

		System.out.println(sb.toString());

		test();
	}
}
