package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

class JEnhanceForStatement extends JStatement {
	
	private JFormalParameter formalParameter;
	private JExpression expression;
	private JStatement statement;
	private JStatement init;
	private JExpression array;
	private JExpression	counter;
	private JExpression target;
	private int offset;
	
	public JEnhanceForStatement (int line, JFormalParameter formalParameter,
								JExpression expression, JStatement statement) {
		super(line);
		this.formalParameter = formalParameter;
		this.expression = expression;
		this.statement = statement;
		array = new JVariable(line, "a'");
		target = new JVariable(line, formalParameter.name());
		counter = new JVariable(line, "i'");
	}
	
	public JEnhanceForStatement analyze(Context context) {
		expression = (JExpression) expression.analyze(context);
		ArrayList<JVariableDeclarator> list = new ArrayList<JVariableDeclarator>();
		JExpression exp = (JExpression) (formalParameter.type() == Type.INT? new JLiteralInt(line,"0") :
			formalParameter.type() == Type.CHAR? new JLiteralChar(line,"a") :
				formalParameter.type() == Type.BOOLEAN? new JLiteralTrue(line) :
					formalParameter.type() == Type.STRING? new JLiteralString(line, "a") :
						formalParameter.type() == Type.DOUBLE? new JLiteralDouble(line, "0.0") :
							formalParameter.type() == Type.NULLTYPE? new JLiteralNull(line) : Type.OBJECT);
		JVariableDeclarator tempT = new JVariableDeclarator(line, formalParameter.name(),
												formalParameter.type(), exp);
		JVariableDeclarator tempA = new JVariableDeclarator(line, "a'", expression.type(), expression);
		JVariableDeclarator tempI = new JVariableDeclarator(line, "i'", 
														Type.INT, new JLiteralInt(line, "0"));
		list.add(tempA);
		list.add(tempT);
		list.add(tempI);
		init = (JStatement) new JVariableDeclaration(line, null, list);
		init = (JStatement)init.analyze(context);
		target = target.analyze(context);
		statement = (JStatement) statement.analyze(context);
		array = array.analyze(context);
		counter = counter.analyze(context);
		offset = ((LocalContext)context).offset() - 1;
		return this;
	}
	
	public void codegen(CLEmitter output) {
		init.codegen(output);
		
        String test = output.createLabel();
        String out = output.createLabel();
        
        output.addLabel(test);
        ((JVariable)counter).codegen(output);
        ((JVariable)array).codegen(output);
		output.addNoArgInstruction(ARRAYLENGTH);
		output.addBranchInstruction(IF_ICMPGE, out);
		
		((JVariable)array).codegen(output);
		((JVariable)counter).codegen(output);
		
		if (formalParameter.type() == Type.DOUBLE)
			output.addNoArgInstruction(DALOAD);
		else
			output.addNoArgInstruction(IALOAD);
		
		((JVariable)target).codegenStore(output);
		
		statement.codegen(output);
		
		output.addIINCInstruction(offset, 1);
		output.addBranchInstruction(GOTO, test);
		output.addLabel(out);	
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
