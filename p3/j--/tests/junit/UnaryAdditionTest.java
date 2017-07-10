package junit;

import junit.framework.TestCase;
import pass.UnaryAddition;

public class UnaryAdditionTest extends TestCase {
	private UnaryAddition uAdd;
	
	protected void setUp() throws Exception {
		super.setUp();
		uAdd = new UnaryAddition();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testUAdd(){
		this.assertEquals(uAdd.unaryAddition(5), 5);
	}

}
