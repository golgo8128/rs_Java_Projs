package testFolder.interface2;

public class Japanese1 implements GreetingMessages1 {

	public Japanese1() {

	}

	@Override
	public void morning() {
        System.out.println("Ohayo.");
	}

	@Override
	public void afternoon() {
        System.out.println("Konnichiwa.");
	}

	@Override
	public void night() {
        System.out.println("Kombanwa.");
	}

}
