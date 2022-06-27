


public class GenericAdderTest1<T extends Number>{

	public T zero() {
		
		return((T)Integer.valueOf(0));
		
	}

	public T add(T a, T b) {
		
		return((T) Long.valueOf((a.longValue() + b.longValue())));

	}
		
}
