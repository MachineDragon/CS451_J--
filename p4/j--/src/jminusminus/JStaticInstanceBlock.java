package jminusminus;

class JStaticInstanceBlock extends JAST implements JMember {
	
	private boolean staticStatus;
	private JBlock block;
	
	public JStaticInstanceBlock(int line, JBlock block, boolean status){
		super(line);
		this.block = block;
		this.staticStatus = status;
	}
	
	public void preAnalyze(Context context, CLEmitter partial){

	}
	
	public JStaticInstanceBlock analyze(Context context){
		return this;
	}
	
	public void codegen(CLEmitter output){
		
	}
	
    public void writeToStdOut(PrettyPrinter p) {
    	if (staticStatus)
    		p.printf("<JStaticBlock line=\"%d\">\n", line());
    	else
    		p.printf("<JInstanceBlock line=\"%d\">\n", line());
    	p.indentRight();
    	block.writeToStdOut(p);
    	p.indentLeft();
        if (staticStatus)
        	p.printf("</JStaticBlock>\n");
        else
        	p.printf("</JInstanceBlock>\n");
    }

}
