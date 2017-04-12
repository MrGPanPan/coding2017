package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {

	private byte[]codes;
	private int pos=0;
	public ByteCodeIterator(byte[]codes){
		this.codes=codes;
	}
	
	public String nextU4TOHexString() {
		
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	public int nextU2ToInt() {
		
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++]});
	}
	
	public int nextU1ToInt() {
		
		return Util.byteToInt(new byte[]{codes[pos++]});
	}
	
	public String getBytesValue(int len) {
		if(pos+len>=codes.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		String byteValue=null;
		try {
			byteValue=new String(Arrays.copyOfRange(codes, pos, pos += len),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return byteValue;
	}
	
}
