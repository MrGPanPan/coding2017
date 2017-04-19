package com.coding.basic;

import java.util.ArrayList;

public class MyStack {

	private Object[] elementData;
	private int size;//
	private int top=-1;//栈顶指针，-1为空数组
	private static final int CAPICITY=10;
	public MyStack(){
		elementData=new Object[CAPICITY];
	}
	public void push(Object o){	
		//如果数组已满，扩容
		if(size==elementData.length){
			bufferSize(elementData.length*2);
		}
		elementData[top++]=o;
		size++;
	}
	
	private void bufferSize(int size) {
		
		Object[]newElementData=new Object[size];
		for(int i=0;i<size;i++){
			newElementData[i]=elementData[i];
		}
		elementData=newElementData;
	}

	public Object pop(){
		if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
		size--;
        return elementData[top--];
	}
	
	public Object peek(){
		if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
		return elementData[top];
	}
	public boolean isEmpty(){
		return top ==-1;
	}
	public int size(){
		return size;
	}
}
