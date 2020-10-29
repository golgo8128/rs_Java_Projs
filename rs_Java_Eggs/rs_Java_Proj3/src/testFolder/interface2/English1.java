package testFolder.interface2;

public class English1 implements GreetingMessages1 {

	public English1() {

	}

	@Override
	public void morning() {
        System.out.println("Good morning.");
	}

	@Override
	public void afternoon() {
		System.out.println("Good afternoon.");
	}

	@Override
	public void night() {
		System.out.println("Good night.");
	}

}
