package jminusminus;

class JLabeledStatement extends JStatement {
	
	private String identifier;
	private JStatement statement;
	
	public JLabeledStatement (int line, String identifier, 
								JStatement statment) {
		super(line);
		this.identifier = identifier;
		this.statement = statment;
	}
	
	public JLabeledStatement analyze (Context context) {
		return this;
	}
	
	public void codegen (CLEmitter output) {
		
	}
	
	public void writeToStdOut (PrettyPrinter p) {
        p.printf("<JLabeledStatement line=\"%d\">\n", line());
        p.indentRight();
    	p.printf("<Identifier = \"%s\">\n", identifier.toString());
    	p.printf("<TheStatement>\n");
    	p.indentRight();
    	statement.writeToStdOut(p);
    	p.indentLeft();
    	p.printf("</TheStatement>\n");
        p.indentLeft();
        p.printf("</JLabeledStatement>\n");
	}
}
