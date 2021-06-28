package DI01;

public class Calculator {
	public void addition(int f, int s) {
		System.out.println("addition()");
		int result = f + s;
		System.out.println(f + " + " + s + " = " + result);
	}

	public void substraction(int f, int s) {
		System.out.println("substraction()");
		int result = f - s;
		System.out.println(f + " - " + s + " = " + result);
	}

	public void multipication(int f, int s) {
		System.out.println("multipication()");
		int result = f * s;
		System.out.println(f + " * " + s + " = " + result);
	}

	public void division(int f, int s) {
		System.out.println("division()");
		int result = f / s;
		System.out.println(f + " / " + s + " = " + result);
	}


}
