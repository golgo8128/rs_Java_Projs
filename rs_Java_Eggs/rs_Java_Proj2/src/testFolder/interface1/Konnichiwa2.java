package testFolder.interface1;

public class Konnichiwa2 implements GreetingMessages1 {

	@Override
	public void morning() {
		// TODO Auto-generated method stub
        System.out.println("Ohayo.");
	}

	@Override
	public void afternoon() {
		// TODO Auto-generated method stub
        System.out.println("Konnichiwa.");
	}

	@Override
	public void night() {
		// TODO Auto-generated method stub
        System.out.println("Kombanwa.");
	}

}
