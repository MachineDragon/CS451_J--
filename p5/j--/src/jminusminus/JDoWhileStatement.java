package jminusminus;

class JDoWhileStatement extends JStatement {
	
	private JExpression test;
	private JStatement statements;
	
	public JDoWhileStatement(int line, JStatement statements, JExpression test) {
		super(line);
		this.test = test;
		this.statements = statements;
	}
	
	public JStatement analyze(Context context) {
		return this;
	}
	
	public void codegen(CLEmitter output) {
		
	}
	
	public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JDoWhileStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TheStatements>\n");
        p.indentRight();
        statements.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TheStatements>\n");
        p.printf("<TestExpression>\n");
        p.indentRight();
        test.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.indentLeft();
        p.printf("</JDoWhileStatement>\n");
	}
}
