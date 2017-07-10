package jminusminus;

import java.util.ArrayList;

class JInterfaceDeclaration extends JAST implements JTypeDecl {

    private ArrayList<String> mods;
    private String name;
    private ArrayList<JMember> interfaceBody;
    private ArrayList<Type> extendList;
    private Type thisType;
    private ClassContext context;

    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name,
            ArrayList<Type> extendList, ArrayList<JMember> interfaceBody) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.extendList = extendList;
        this.interfaceBody = interfaceBody;
    }

    public String name() {
        return name;
    }

    public Type superType() {
        return null;
    }
    
    public Type thisType() {
        return thisType;
    }

    
    public void declareThisType(Context context) {
    	
    }
    
    public void preAnalyze(Context context) {

    }
    
    public JAST analyze(Context context) {
    	return this;
    }
    
    public void codegen(CLEmitter output) {

    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\">\n", 
        		line(), name);
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (interfaceBody != null) {
            p.println("<InterfaceBlock>");
            for (JMember member : interfaceBody) {
                ((JAST) member).writeToStdOut(p);
            }
            p.println("</InterfaceBlock>");
        }
        p.indentLeft();
        p.println("</JInterfaceDeclaration>");

    }

}
