package jminusminus;

class JEnhanceForStatement extends JStatement {
	
	private JFormalParameter formalParameter;
	private JExpression expression;
	private JStatement statement;
	
	public JEnhanceForStatement (int line, JFormalParameter formalParameter,
								JExpression expression, JStatement statement) {
		super(line);
		this.formalParameter = formalParameter;
		this.expression = expression;
		this.statement = statement;
	}
	
	public JEnhanceForStatement analyze(Context context) {
		return this;
	}
	
	public void codegen(CLEmitter output) {
		
	}
	
	public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JEnhanceForLoopStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TheFormalParameter>\n");
        p.indentRight();
        formalParameter.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TheFormalParameter>\n");
        p.printf("<TheExpression>\n");
        p.indentRight();
        expression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TheExpression>\n");
    	p.printf("<LoopStatement>\n");
    	p.indentRight();
    	statement.writeToStdOut(p);
    	p.indentLeft();
    	p.printf("</LoopStatement>\n");
        p.indentLeft();
        p.printf("</JEnhanceForLoopStatement>\n");
		
	}
}
