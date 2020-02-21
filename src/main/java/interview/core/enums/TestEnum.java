package interview.core.enums;

public enum TestEnum {
	PENNY,PENNY2;
	
	public static void main(String[] args) {
		System.out.println(TestEnum.PENNY);
		System.out.println(TestEnum.PENNY.equals("PENNY"));
		System.out.println(TestEnum.PENNY.hashCode());
		System.out.println(TestEnum.PENNY2.ordinal());
	}
}
