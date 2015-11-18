package interview.core;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class TestSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static class InstanceHolder {  
        private static final TestSerializable instatnce = new TestSerializable();  
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//反射调用. 可以阻止序列化破坏单例
	//当从I/O流中读取对象时，readResolve()方法都会被调用到,用来替换反序列化的对象.
	private Object readResolve() throws ObjectStreamException {
		return InstanceHolder.instatnce;
	}
	
	//系统总是先调用writeReplace()方法
	//用于安全检查
    private Object writeReplace()throws ObjectStreamException {
    	return InstanceHolder.instatnce;
    }
    
    
    

}
