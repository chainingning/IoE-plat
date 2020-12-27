package com.chaining.iot.common.utils;

public class ByteTools {
	/**
	 * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
	 */
	public static byte[] getBitArray(byte b) {
		byte[] array = new byte[8];
		for (int i = 7; i >= 0; i--) {
			array[i] = (byte) (b & 1);
			b = (byte) (b >> 1);
		}
		return array;
	}

	/**
	 * 获取字节的某些BIT位
	 * 
	 * @param b
	 * @param s
	 *            从右开始的位
	 * @param e
	 *            从右结束的位
	 * @return
	 */
	public static int bitsToInt(byte b, int s, int e) {
		b = (byte) ((b & 0xff) >>> s);
		b = (byte) (b << (8 - (e - s)));
		b = (byte) ((b & 0xff) >>> (8 - (e - s)));
		return b;
	}

	/**
	 * 把byte转为字符串的bit
	 */
	public static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
				+ (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
				+ (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
				+ (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
	}

	/**
	 * 二进制字符串转byte
	 */
	public static byte decodeBinaryString(String byteStr) {
		int re, len;
		if (null == byteStr) {
			return 0;
		}
		len = byteStr.length();
		if (len != 4 && len != 8) {
			return 0;
		}
		if (len == 8) {// 8 bit处理
			if (byteStr.charAt(0) == '0') {// 正数
				re = Integer.parseInt(byteStr, 2);
			} else {// 负数
				re = Integer.parseInt(byteStr, 2) - 256;
			}
		} else {// 4 bit处理
			re = Integer.parseInt(byteStr, 2);
		}
		return (byte) re;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
	 * 
	 * @param ary
	 *            byte数组 从数组的第offset位开始
	 * @param
	 *
	 * @return int数值
	 */
	public static int bytesToInt(byte[] ary) {
		int value;
		value = (int) ((ary[0] & 0xFF) | ((ary[1] << 8) & 0xFF00));
		return value;
	}

	public static long bytesToInt4(byte[] ary) {
		long value;
		value = (long) ((ary[0] & 0xFF) | ((ary[1] << 8) & 0xFF00)
				| ((ary[2] << 16) & 0xFF0000) | ((ary[3] << 24) & 0xFF000000));
		return value;
	}

	/**
	 * 无符号
	 * 
	 * @param ary
	 * @return
	 */
	public static int byteToInt(byte ary) {
		int value;
		value = (int) ((ary & 0xFF));
		return value;
	}

	/**
	 * 有符号 最高位表示+-，1表示负，0表示正 正数直接取，负数反码+1
	 * 
	 * @param ary
	 * @return
	 */
	public static int byteToInt_fh(byte ary) {
		int value;

		if (((ary >> 7) & 0x1) == 0x1) {
			value = (int) ((~(ary) & 0x7F)) + 1;
			value = value * -1;
		} else {
			value = (int) (((ary) & 0x7F));
		}
		return value;
	}

	/**
	 * 将int数值转换为占2个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
	 * 
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] byte_src = new byte[3];
		byte_src[2] = (byte) ((value & 0x00FF0000) >> 16);
		byte_src[1] = (byte) ((value & 0x0000FF00) >> 8);
		byte_src[0] = (byte) ((value & 0x000000FF));
		return byte_src;
	}

	public static String toHexString(byte b) {
		return toHexString(new byte[] { b });
	}

	public static String toHexString(byte[] b) {
		StringBuffer hexB = new StringBuffer();
		for (int i = b.length - 1; i >= 0; i--) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexB.append(hex.toUpperCase() + ",");
		}
		hexB.deleteCharAt(hexB.length() - 1);
		return (hexB.toString());
	}

	public static String toHexString2(byte[] b) {
		StringBuffer hexB = new StringBuffer();
		hexB.append("[");
		for (int i = 0; i <= b.length - 1; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexB.append(hex.toUpperCase() + ",");
		}
		hexB.deleteCharAt(hexB.length() - 1).append("]");
		return (hexB.toString());
	}

	public static String byteToHex(byte[] b) {
		StringBuffer hexB = new StringBuffer();
		for (int i = b.length - 1; i >= 0; i--) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexB.append(hex.toUpperCase());
		}
		return (hexB.toString());
	}

	public static String byteToHex2(byte[] b) {
		StringBuffer hexB = new StringBuffer();
		for (int i = 0; i <= b.length - 1; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexB.append(hex.toUpperCase());
		}
		return (hexB.toString());
	}

	/**
	 * CRC16校验算法
	 * 
	 * @param userData
	 * @return
	 */
	public static byte[] CRC16(byte[] userData) {
		int us_crc = 0xffff;

		for (int i = 0; i < userData.length; i++) {
			for (int j = 0; j < 8; j++) {
				byte uc_b = (byte) (((userData[i] << j) & 0x80) ^ ((us_crc & 0x8000) >> 8));
				us_crc <<= 1;
				if (uc_b != 0)
					us_crc ^= 0x1021;
			}
		}
		byte[] a = new byte[2];
		a[0] = (byte) (0xff & us_crc);
		a[1] = (byte) ((0xff00 & us_crc) >> 8);
		return a;

	}

	public static byte CRC8(byte[] userData) {
		byte[] crc8_tab = { (byte) 0, (byte) 94, (byte) 188, (byte) 226,
				(byte) 97, (byte) 63, (byte) 221, (byte) 131, (byte) 194,
				(byte) 156, (byte) 126, (byte) 32, (byte) 163, (byte) 253,
				(byte) 31, (byte) 65, (byte) 157, (byte) 195, (byte) 33,
				(byte) 127, (byte) 252, (byte) 162, (byte) 64, (byte) 30,
				(byte) 95, (byte) 1, (byte) 227, (byte) 189, (byte) 62,
				(byte) 96, (byte) 130, (byte) 220, (byte) 35, (byte) 125,
				(byte) 159, (byte) 193, (byte) 66, (byte) 28, (byte) 254,
				(byte) 160, (byte) 225, (byte) 191, (byte) 93, (byte) 3,
				(byte) 128, (byte) 222, (byte) 60, (byte) 98, (byte) 190,
				(byte) 224, (byte) 2, (byte) 92, (byte) 223, (byte) 129,
				(byte) 99, (byte) 61, (byte) 124, (byte) 34, (byte) 192,
				(byte) 158, (byte) 29, (byte) 67, (byte) 161, (byte) 255,
				(byte) 70, (byte) 24, (byte) 250, (byte) 164, (byte) 39,
				(byte) 121, (byte) 155, (byte) 197, (byte) 132, (byte) 218,
				(byte) 56, (byte) 102, (byte) 229, (byte) 187, (byte) 89,
				(byte) 7, (byte) 219, (byte) 133, (byte) 103, (byte) 57,
				(byte) 186, (byte) 228, (byte) 6, (byte) 88, (byte) 25,
				(byte) 71, (byte) 165, (byte) 251, (byte) 120, (byte) 38,
				(byte) 196, (byte) 154, (byte) 101, (byte) 59, (byte) 217,
				(byte) 135, (byte) 4, (byte) 90, (byte) 184, (byte) 230,
				(byte) 167, (byte) 249, (byte) 27, (byte) 69, (byte) 198,
				(byte) 152, (byte) 122, (byte) 36, (byte) 248, (byte) 166,
				(byte) 68, (byte) 26, (byte) 153, (byte) 199, (byte) 37,
				(byte) 123, (byte) 58, (byte) 100, (byte) 134, (byte) 216,
				(byte) 91, (byte) 5, (byte) 231, (byte) 185, (byte) 140,
				(byte) 210, (byte) 48, (byte) 110, (byte) 237, (byte) 179,
				(byte) 81, (byte) 15, (byte) 78, (byte) 16, (byte) 242,
				(byte) 172, (byte) 47, (byte) 113, (byte) 147, (byte) 205,
				(byte) 17, (byte) 79, (byte) 173, (byte) 243, (byte) 112,
				(byte) 46, (byte) 204, (byte) 146, (byte) 211, (byte) 141,
				(byte) 111, (byte) 49, (byte) 178, (byte) 236, (byte) 14,
				(byte) 80, (byte) 175, (byte) 241, (byte) 19, (byte) 77,
				(byte) 206, (byte) 144, (byte) 114, (byte) 44, (byte) 109,
				(byte) 51, (byte) 209, (byte) 143, (byte) 12, (byte) 82,
				(byte) 176, (byte) 238, (byte) 50, (byte) 108, (byte) 142,
				(byte) 208, (byte) 83, (byte) 13, (byte) 239, (byte) 177,
				(byte) 240, (byte) 174, (byte) 76, (byte) 18, (byte) 145,
				(byte) 207, (byte) 45, (byte) 115, (byte) 202, (byte) 148,
				(byte) 118, (byte) 40, (byte) 171, (byte) 245, (byte) 23,
				(byte) 73, (byte) 8, (byte) 86, (byte) 180, (byte) 234,
				(byte) 105, (byte) 55, (byte) 213, (byte) 139, (byte) 87,
				(byte) 9, (byte) 235, (byte) 181, (byte) 54, (byte) 104,
				(byte) 138, (byte) 212, (byte) 149, (byte) 203, (byte) 41,
				(byte) 119, (byte) 244, (byte) 170, (byte) 72, (byte) 22,
				(byte) 233, (byte) 183, (byte) 85, (byte) 11, (byte) 136,
				(byte) 214, (byte) 52, (byte) 106, (byte) 43, (byte) 117,
				(byte) 151, (byte) 201, (byte) 74, (byte) 20, (byte) 246,
				(byte) 168, (byte) 116, (byte) 42, (byte) 200, (byte) 150,
				(byte) 21, (byte) 75, (byte) 169, (byte) 247, (byte) 182,
				(byte) 232, (byte) 10, (byte) 84, (byte) 215, (byte) 137,
				(byte) 107, 53 };

		byte ret = (byte) 0;
		for (int i = 0; i < userData.length; ++i) {
			ret = crc8_tab[(0x00ff & (ret ^ userData[i]))];
		}
		return ret;
	}

	private static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	/**
	 * ASCII码转BCD
	 * 
	 * @param ascii
	 * @param
	 * @return
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii) {
		int asc_len = ascii.length;
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static void main(String[] args) {
		// byte b = 0x35; // 0011 0101
		// // 输出 [0, 0, 1, 1, 0, 1, 0, 1]
		// System.out.println(new String(new byte[]{0x30,0x30,0x31}));
		// byte bbb = 0x15;
		// System.out.println(byteToHex(ASCII_To_BCD(new byte[]{0x30,0x31})));
		// System.out.println(byteToHex(new byte[] { bbb }));
		// // 输出 00110101
		// System.out.println(byteToBit(b));
		// // JDK自带的方法，会忽略前面的 0
		// System.out.println(Integer.toBinaryString(0xffff));
		// int l = 98;
		// byte[] c = intToBytes(l);
		// c[0] = (byte) (c[0] >> 2);
		// c[1] = (byte) (c[1] >> 2);
		// int ll = bytesToInt(c);
		// System.out.println(ll);
		// System.out.println(toHexString(c));
		// byte[] d = intToBytes(14);
		// System.out.println(toHexString(d));
		//
		// byte us_crc = (byte) 0xffff;
		// System.out.println(byteToBit(us_crc));
		// byte[] userData = new byte[16];
		// for (int i = 0; i < userData.length; i++) {
		// userData[i] = 0x0;
		// }
		// userData[0] = 0x2;
		// System.out.println(toHexString(CRC16(userData)));
		// System.out.println(byteToInt_fh((byte) 0x10));
		// System.out.println(toHexString(CRC8(userData)));
		//
		// byte t = 10;
		// byte[] bits = getBitArray(t);
		// for (byte e : bits) {
		// System.out.print(e);
		// }
		// System.out.println(bytesToInt4(new
		// byte[]{(byte)0xAD,(byte)0xEB,0x0C,0x0}));
		// byte[] bb =getBitArray((byte)0x1);
		long bb = ByteTools.bytesToInt4(new byte[] { 0x2E, 0x0, 0x0, 0x0 });
		System.out.println(ByteTools.byteToBit((byte) 23));
	}
}
