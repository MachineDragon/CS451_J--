package jminusminus;

class JBreakStatement extends JStatement {
	
	private String identifier;
	
	public JBreakStatement (int line, String identifier) {
		super(line);
		this.identifier = identifier;
	}
	
	public JBreakStatement analyze (Context context) {
		return this;
	}
	
	public void codegen (CLEmitter output) {
		
	}
	
	public void writeToStdOut (PrettyPrinter p) {
        p.printf("<JBreakStatement line=\"%d\">\n", line());
        p.indentRight();
        if (identifier != null) {
        	p.indentRight();
        	p.printf("<Identifier = \"%s\">\n", identifier.toString());
        	p.indentLeft();
        }
        p.indentLeft();
        p.printf("</JBreakStatement>\n");
	}
}
