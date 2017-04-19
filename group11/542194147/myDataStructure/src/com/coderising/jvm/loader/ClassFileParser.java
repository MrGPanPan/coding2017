package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ByteCodeIterator iterator=new ByteCodeIterator(codes);
		String magicNum=iterator.nextU4TOHexString();
		if(!magicNum.equals("cafebabe")){
			return null;
		}
		ClassFile clzFile = new ClassFile();
		clzFile.setMinorVersion(iterator.nextU2ToInt());
		clzFile.setMajorVersion(iterator.nextU2ToInt());
		clzFile.setConstPool(parseConstantPool(iterator));
		clzFile.setAccessFlag(parseAccessFlag(iterator));
		clzFile.setClassIndex(parseClassInfex(iterator));
		return clzFile;
	}
	
	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool=new ConstantPool();
		int constantPoolCount=iter.nextU2ToInt();
		System.out.println("Constant Pool Count :" + constantPoolCount);
		pool.addConstantInfo(new NullConstantInfo());
		for(int i=1;i<=constantPoolCount-1;i++){
			int flag=iter.nextU1ToInt();
			if(flag==1){//UTF8Info
				int len=iter.nextU2ToInt();
				String value=iter.getBytesValue(len);
				UTF8Info utf8Info= new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			}else if(flag==7){//classInfo
				ClassInfo classInfo=new ClassInfo(pool);
				int utf8Index=iter.nextU2ToInt();
				classInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(classInfo);
			}else if(flag==9){
				FieldRefInfo fieldRefInfo=new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldRefInfo);
			}else if(flag==10){
				MethodRefInfo methodRefInfo=new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodRefInfo);
			}else if(flag==12){
				NameAndTypeInfo nameAndTypeInfo =new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameAndTypeInfo);
			}else if(flag==8){
				StringInfo stringInfo =new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
			}
		}
		return pool;
	}
	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		
		AccessFlag afg=new AccessFlag(iter.nextU2ToInt());
		return afg;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex cix=new ClassIndex();
		cix.setThisClassIndex(iter.nextU2ToInt());
		cix.setSuperClassIndex(iter.nextU2ToInt());
		return cix;

	}

}
