
public class TestCase1 {
	//Test for Scanning Keywords and Operators
    abstract assert boolean break byte case catch char class const
    continue default do double else enum extends final finally float
    for if goto implements import instanceof int interface long native
    new package private protected public return short static strictfp
    switch super synchronized this throw throws transient try void 
    volatile while
    	
    = > < ! ~ ? : == <= >= != && || ++ -- + - * / & | ^ % << >> >>>
    += -= *= /= &= |= ^= %= <<= >>= >>>=
    
    //Test for multi-line comments
    
    /*This is a vaild comments, will be ignored*/
    
    /*
     * This is a vaild comments,
     * 
     * will be igorned.
     * 
     */
    
    /* This is a vaild comments, will be ignored.
     * ***********
     **/
    
    /* this comments /* // /** ends here: */
}


	/* This is not a vaild comments,
	 * because there is no close operator for comments,
	 * will return error.
	 *
