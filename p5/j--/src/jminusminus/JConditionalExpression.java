package jminusminus;

import static jminusminus.CLConstants.GOTO;

class JConditionalExpression extends JExpression {
	
	private JExpression condition;
	private JExpression firstExpression;
	private JExpression secondExpression;
	
	public JConditionalExpression(int line, JExpression lhs, 
			JExpression first, JExpression second){
		super(line);
		condition = lhs;
		firstExpression = first;
		secondExpression = second;
	}
	
	public JExpression analyze(Context context) {
        condition = (JExpression) condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        firstExpression = (JExpression) firstExpression.analyze(context);
        secondExpression = (JExpression) secondExpression.analyze(context);
        type = firstExpression.type();
        secondExpression.type().mustMatchExpected(line, type);
		return this;
	}
	
	public void codegen(CLEmitter output) {
        String secondLabel = output.createLabel();
        String endLabel = output.createLabel();
        condition.codegen(output, secondLabel, false);
        firstExpression.codegen(output);
        output.addBranchInstruction(GOTO, endLabel);
        output.addLabel(secondLabel);
        secondExpression.codegen(output);
        output.addLabel(endLabel);
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
