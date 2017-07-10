package jminusminus;

import java.util.ArrayList;

public class JForInitDeclaration extends JStatement {
	
	private ArrayList<JVariableDeclarator> decls;
	
	public JForInitDeclaration(int line, ArrayList<JVariableDeclarator> decls) {
		super(line);
		this.decls = decls;
	}
	
	public JForInitDeclaration analyze(Context context) {
		return this;
	}
	
	public void codegen(CLEmitter output) {
		
	}
	
	public void writeToStdOut(PrettyPrinter p) {
        p.println("<JForLoopVariableDeclaration>");
        p.indentRight();
        if (decls != null) {
            p.println("<VariableDeclarators>");
            for (JVariableDeclarator decl : decls) {
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
