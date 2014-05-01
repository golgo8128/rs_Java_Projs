package testFolder.interface2;

public class InvokeGreet1 {

	// interface GreetingMessages1 as parameter
	public InvokeGreet1(){
		
	}
	public void invoke(GreetingMessages1 greeter){
		greeter.afternoon();
	}

	public static void main(String[] args) {
		English1 person_eng  = new English1();
		Japanese1 person_jpn = new Japanese1();
		InvokeGreet1 invokegreet = new InvokeGreet1();
		invokegreet.invoke(person_eng);
		invokegreet.invoke(person_jpn);
	}

}
