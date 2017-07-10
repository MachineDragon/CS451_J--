package junit;

import junit.framework.TestCase;
import pass.Remainder;

public class RemainderTest extends TestCase {
	private Remainder remainder;
	
	protected void setUp() throws Exception {
		super.setUp();
		remainder = new Remainder();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testRemainder(){
		this.assertEquals(remainder.rem(1, 3), 1);
		this.assertEquals(remainder.rem(3, 1), 0);
		this.assertEquals(remainder.rem(6, 4), 2);
	}

}
