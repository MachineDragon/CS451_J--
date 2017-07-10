package jminusminus;

class JConditionalExpression extends JExpression {
	
	private JExpression condition;
	private JExpression firstExpression;
	private JExpression secondExpression;
	
	public JConditionalExpression(int line, JExpression lhs, JExpression first, JExpression second){
		super(line);
		condition = lhs;
		firstExpression = first;
		secondExpression = second;
	}
	
	public JExpression analyze(Context context) {
		return this;
	}
	
	public void codegen(CLEmitter output) {
		
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JConditionalExpression line=\"%d\" type=\"%s\" "
                + "operator=\"? :\">\n", line(), ((type == null) ? "" : type
                .toString()));
		p.indentRight();
        p.printf("<Condition>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
        p.printf("<FirstExpression>\n");
        p.indentRight();
        firstExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</FirstExpression>\n");
        p.printf("<SecondExpression>\n");
        p.indentRight();
        secondExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</SecondExpression>\n");
        p.indentLeft();
        p.printf("</JConditionalExpression>\n");
		
	}

}
