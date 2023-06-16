
public class TestGeneric1<T extends Number> {

	T val;
	
	TestGeneric1(T ival){
		this.val = ival;
	}
	
	long duplicate() {
		
		return this.val.longValue() * 2;
		
	}
	
	/*
	T duplicate() {
		
		return this.val * 2;
		
	}
	*/
}
