package jminusminus;

import static jminusminus.CLConstants.GOTO;

import java.util.ArrayList;

public class JForStatement extends JStatement {
	
	ArrayList<JStatement> forInit;
	ArrayList<JStatement> forUpdate;
	JExpression expression;
	JStatement statement;
	
	public JForStatement(int line, ArrayList<JStatement> forInit,
						JExpression expression, ArrayList<JStatement> forUpdate,
						JStatement statement){
		super(line);
		this.forInit = forInit;
		this.forUpdate = forUpdate;
		this.expression = expression;
		this.statement = statement;
	}
	
	public JForStatement analyze(Context context) {
		if(forInit != null){
			for(JStatement stat : forInit)
				stat.analyze(context);
		}
		
		if(forUpdate != null){
			for(JStatement stat : forUpdate)
				stat.analyze(context);
		}
		
		expression = (JExpression) expression.analyze(context);
		statement = (JStatement) statement.analyze(context);
		return this;
	}
	
	public void codegen(CLEmitter output) {
		if(forInit != null){
			for(JStatement stat : forInit)
				stat.codegen(output);
		}
		
        String test = output.createLabel();
        String out = output.createLabel();
        
        output.addLabel(test);
        expression.codegen(output, out, false);
        
        statement.codegen(output);
        
        if(forUpdate != null){
        	for(JStatement stat : forUpdate)
        		stat.codegen(output);
        }
        
        output.addBranchInstruction(GOTO, test);
        
        output.addLabel(out);	
	}
	
	public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForLoopStatement line=\"%d\">\n", line());
        p.indentRight();
        if (!forInit.isEmpty()) {
        	p.printf("<ForInits>\n");
        	p.indentRight();
        	for (JStatement statement : forInit) {
                p.indentRight();
                statement.writeToStdOut(p);
                p.indentLeft();
        	}
        	p.indentLeft();
        	p.printf("</ForInits>\n");
        }
        if (expression != null) {
        	p.printf("<ForLoopExpression>\n");
        	p.indentRight();
        	expression.writeToStdOut(p);
        	p.indentLeft();
        	p.printf("</ForLoopExpression>\n");
        }
        if (!forUpdate.isEmpty()) {
        	p.printf("<ForUpdate>\n");
        	p.indentRight();
        	for (JStatement statement : forUpdate) {
                p.indentRight();
                statement.writeToStdOut(p);
                p.indentLeft();
        	}
        	p.indentLeft();
        	p.printf("</ForUpdate>\n");
        }
    	p.printf("<LoopStatement>\n");
    	p.indentRight();
    	statement.writeToStdOut(p);
    	p.indentLeft();
    	p.printf("</LoopStatement>\n");
        p.indentLeft();
        p.printf("</JForLoopStatement>\n");
	}

}
