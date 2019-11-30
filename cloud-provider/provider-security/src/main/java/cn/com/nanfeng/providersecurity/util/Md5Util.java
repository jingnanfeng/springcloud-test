package cn.com.nanfeng.providersecurity.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * MD5摘要计算类
 * @author erakis
 *
 */
public final class Md5Util {
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String encode(File file) {
		FileInputStream in = null;
		MessageDigest md5 = null;
		try {
			in = new FileInputStream(file);

			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
					0, file.length());
			md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toHex(md5.digest());
	}

	public static String getMD5(File file) {

		FileInputStream fis = null;

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			fis = new FileInputStream(file);

			byte[] buffer = new byte[2048];

			int length = -1;

			while ((length = fis.read(buffer)) != -1) {

				md.update(buffer, 0, length);

			}

			byte[] b = md.digest();

			return byteToHexString(b);

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		} finally {

			try {

				fis.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}



	/**

	 * @function 把byte[]数组转换成十六进制字符串表示形式

	 * @param tmp  要转换的byte[]

	 * @return 十六进制字符串表示形式

	 */

	private static String byteToHexString(byte[] tmp) {

		String s;

		// 用字节表示就是 16 个字节

		// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符

		// 比如一个字节为01011011，用十六进制字符来表示就是“5b”

		char str[] = new char[16 * 2];

		int k = 0; // 表示转换结果中对应的字符位置

		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换

			byte byte0 = tmp[i]; // 取第 i 个字节

			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移

			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

		}



		s = new String(str); // 换后的结果转换为字符串

		return s;

	}



	public static void main(String arg[]) {
		/*String a = getMD5(new File("e:/a.txt"));
		String b = getMD5(new File("e:/b.txt"));
		String c = getMD5(new File("e:/c.txt"));

		System.out.println("a.txt的摘要值为：" + a);
		System.out.println("b.txt的摘要值为：" + b);
		System.out.println("c.txt的摘要值为：" + c);

		if(a.equals(b)) {
			System.out.println("a.txt中的内容与b.txt中的内容一致");
		} else {
			System.out.println("a.txt中的内容与b.txt中的内容不一致");
		}

		if(a.equals(c)) {
			System.out.println("a.txt中的内容与c.txt中的内容一致");
		} else {
			System.out.println("a.txt中的内容与c.txt中的内容不一致");
		}*/
		String admin = encode("admin");
		System.out.println(admin);

	}

	public static String encode(String arg) {
		if (arg == null) {
			arg = "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(arg.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(md5.digest());
	}
	private static String toHex(byte[] bytes) {
		StringBuffer str = new StringBuffer(32);
		for (byte b : bytes) {
			str.append(hexDigits[(b & 0xf0) >> 4]);
			str.append(hexDigits[(b & 0x0f)]);
		}
		return str.toString();
	}

}
