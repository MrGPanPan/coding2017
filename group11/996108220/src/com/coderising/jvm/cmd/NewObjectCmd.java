package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int index=this.getIndex();
		ClassInfo info=(ClassInfo)this.getConstantInfo(index);
		UTF8Info utf8Info=(UTF8Info)this.getConstantInfo(info.getUtf8Index());
		String classname=utf8Info.getValue();
		
		JavaObject object=Heap.getInstance().newObject(classname);
		frame.getOprandStack().push(object);
		
	}

	
}
