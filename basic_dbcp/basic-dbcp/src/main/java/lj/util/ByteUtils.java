package lj.util;

public class ByteUtils {

	/**
	 * 字节转无符号整数
	 * 
	 * @param b
	 * @return
	 */
	public static int byteToUnsignedInt(byte b) {
		int ret = (int) (b & 0xff);
		return ret;
	}

	

	/**
	 * 字节->16进制字符串
	 * 
	 * @param b
	 */
	public static String byteToHexString(byte b) {
		String temp = Integer.toHexString(b & 0xFF);
		if (temp.length() == 1) {
			temp = "0" + temp;
		}
		return temp;
	}

	/**
	 * 自己数组转成16进制字符串
	 * 
	 * @param buffer
	 * @return
	 */
	public static String bytesToHexString(byte[] buffer) {
		String str = "";
		for (int i = 0; i < buffer.length; i++) {
			str = str + " " + byteToHexString(buffer[i]);
		}
		return str;

	}
	
	/**
	 * 整形转字节数组
	 * @param res
	 * @return
	 */
	public static byte[] intTobyte(int res) {
		byte[] targets = new byte[4];
		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	/**
	 * 两个数组连接
	 * 
	 * @param ary1
	 * @param ary2
	 * @return
	 */
	public static byte[] concat(byte[] ary1, byte[] ary2) {
		byte[] ret = new byte[ary1.length + ary2.length];
		System.arraycopy(ary1, 0, ret, 0, ary1.length);
		System.arraycopy(ary2, 0, ret, ary1.length, ary2.length);
		return ret;
	}

	/*
	 * 求子数组
	 */
	public static byte[] subBytes(byte[] buffer, int srcIndex, int length) {
		byte[] ret = new byte[length];
		System.arraycopy(buffer, srcIndex, ret, 0, length);
		return ret;
	}

	/**
	 * 数组是否相等
	 * 
	 * @param ary1
	 * @param ary2
	 * @return
	 */
	public static boolean equals(byte[] ary1, byte[] ary2) {
		if (ary1 == null || ary2 == null)
			return false;
		if (ary1.length != ary2.length)
			return false;
		for (int i = 0; i < ary1.length; ++i)
			if (ary1[i] != ary2[i])
				return false;
		return true;
	}

}
