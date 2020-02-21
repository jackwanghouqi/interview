package interview.core.io;

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

	//�������. ������ֹ���л��ƻ�����
	//����I/O���ж�ȡ����ʱ��readResolve()�������ᱻ���õ�,�����滻�����л��Ķ���.
	private Object readResolve() throws ObjectStreamException {
		return InstanceHolder.instatnce;
	}
	
	//ϵͳ�����ȵ���writeReplace()����
	//���ڰ�ȫ���
    private Object writeReplace()throws ObjectStreamException {
    	return InstanceHolder.instatnce;
    }
    
    
    

}
