package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	
	
	public  int getLength(){
		return 1;
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		String opCode = this.getOpCode();
		
		if(CommandParser.aload_0.equalsIgnoreCase(opCode)){
		
			JavaObject jo = frame.getLocalVariableValue(0);
			
			frame.getOprandStack().push(jo);
			
		} else if(CommandParser.aload_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOprandStack().push(jo);
			
		} else if(CommandParser.aload_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOprandStack().push(jo);
			
		}else if(CommandParser.iload_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOprandStack().push(jo);
			
		} else if (CommandParser.iload_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOprandStack().push(jo);
			
		}  else if (CommandParser.iload_3.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOprandStack().push(jo);
			
		}else if (CommandParser.fload_3.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOprandStack().push(jo);
			
		}
		else if (CommandParser.voidreturn.equalsIgnoreCase(opCode)){
			
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
			
		} else if(CommandParser.ireturn.equalsIgnoreCase(opCode)){
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(jo);
			
		} else if(CommandParser.freturn.equalsIgnoreCase(opCode)){
			
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(jo);
		}
		
		else if(CommandParser.astore_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
		    frame.setLocalVariableValue(1, jo);
		    
		} else if(CommandParser.dup.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().peek();
			frame.getOprandStack().push(jo);
			
		} else if(CommandParser.iconst_0.equalsIgnoreCase(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(0);
			
			frame.getOprandStack().push(jo);
			
		} else if(CommandParser.iconst_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(1);
			
			frame.getOprandStack().push(jo);
			
		} else if(CommandParser.istore_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
			frame.setLocalVariableValue(1, jo);
			
		}  else if(CommandParser.istore_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
			frame.setLocalVariableValue(2, jo);
			
		} else if(CommandParser.iadd.equalsIgnoreCase(opCode)){
			
			JavaObject jo1 = frame.getOprandStack().pop();
			JavaObject jo2 = frame.getOprandStack().pop();
			
			JavaObject sum = Heap.getInstance().newInt(jo1.getIntValue()+jo2.getIntValue());
			
			frame.getOprandStack().push(sum);
			
		} else if (CommandParser.aconst_null.equalsIgnoreCase(opCode)){
			
			frame.getOprandStack().push(null);
			
		} 
		else{
			throw new RuntimeException("you must forget to implement the operation :" + opCode);
		}
		
	}

}
