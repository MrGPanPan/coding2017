package com.coderising.jvm.method;

import java.util.ArrayList;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.cmd.InvokeSpecialCmd;
import com.coderising.jvm.cmd.LdcCmd;
import com.coderising.jvm.cmd.PutFieldCmd;
import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;



public class Method {
	
	private int accessFlag;//u2
	private int nameIndex;//u2
	private int descriptorIndex;//u2	
	private CodeAttr codeAttr;
	private ClassFile clzFile;
	
	
	public ClassFile getClzFile() {
		return clzFile;
	}

	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
	}

	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	
	
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		return null;
		
	}

	public ByteCodeCommand[] getCmds() {

		return CommandParser.parse(clzFile, codeAttr.getCode());
	}

	public ArrayList<String> getParameterList() {
		ConstantPool pool=clzFile.getConstantPool();
		String param=pool.getUTF8String(descriptorIndex);
		int first=param.indexOf('(');
		int last=param.indexOf(')');
		ArrayList<String> localParamList =new ArrayList<>();
		String paramsub=param.substring(first+1,last);
		String[] suString=paramsub.split(";");
		if((null == paramsub) || "".equals(paramsub)){
			return localParamList;
		}
		for (int i = 0; i < suString.length; i++) {
			if (suString[i].charAt(0)=='L') {
				localParamList.add(suString[i]);
			}
			else {
				for (int j = 0; j < suString[i].length(); j++) {
					if (suString[i].charAt(j)=='I') {
						localParamList.add("I");
					}
					else if (suString[i].charAt(j)=='F') {
						localParamList.add("F");
					}
				}
			}
		}
		return localParamList;
	}
}
