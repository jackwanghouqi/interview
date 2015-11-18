package interview.core;

public enum TestEnum {
	PENNY;
	
	public static void main(String[] args) {
		System.out.println(TestEnum.PENNY);
		System.out.println(TestEnum.PENNY.equals("PENNY"));
	}
}
