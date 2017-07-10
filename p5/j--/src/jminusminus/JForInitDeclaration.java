package jminusminus;

import java.util.ArrayList;

public class JForInitDeclaration extends JStatement {
	
	private ArrayList<JVariableDeclaration> decls;
	
	public JForInitDeclaration(int line, ArrayList<JVariableDeclaration> decls) {
		super(line);
		this.decls = decls;
	}
	
	public JForInitDeclaration analyze(Context context) {
		for(JVariableDeclaration decl : decls)
			decl.analyze(context);
		return this;
	}
	
	public void codegen(CLEmitter output) {
		for(JVariableDeclaration decl : decls){
			decl.codegen(output);
		}
	}
	
	public void writeToStdOut(PrettyPrinter p) {
        p.println("<JForLoopVariableDeclaration>");
        p.indentRight();
        if (decls != null) {
            p.println("<VariableDeclarators>");
            for (JVariableDeclaration decl : decls) {
                p.indentRight();
                decl.writeToStdOut(p);
                p.indentLeft();
            }
            p.println("</VariableDeclarators>");
        }
        p.indentLeft();
        p.println("</JForLoopVariableDeclaration>");
    }
}
