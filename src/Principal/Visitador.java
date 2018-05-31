package Principal;

import ParMaterial.decafBaseVisitor;
import ParMaterial.decafParser;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.*;

import java.lang.reflect.Parameter;
import java.security.interfaces.ECKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Visitador extends decafBaseVisitor<String> {
    private Stack<SyTable> verificadorAmbitos = new Stack();
    private ArrayList<String> argSignature = new ArrayList<>();
    private ArrayList<String> argType = new ArrayList<>();
    private String type;
    private String error = "";
    private ArrayList<String> listaDeErrores = new ArrayList<String>();
    private Elemento objeto;
    private String locationID;
    private Elemento objetoAnterior = null;
    private Method recentlyCreated = new Method(null, null,  null, null,
            null, null, false, true, false );
    private boolean declaration = false;
    private ArrayList<Tuplas> paramTuplas = new ArrayList<>();
    private ArrayList<Tuplas> recentlyCreatedTuplas = new ArrayList<>();

    //Variables para creacion de codigo intermedio
    private String codigoEmitido = "";
    private String data = "\t.data\n";
    private String subrutinas = "";
    private int conteoLabel = 0;
    private int conteoDestinos= 0;
    private int conteoParametro = 0;
    private int numeroData = 0;

    /*Para argumentos*/
    private boolean simpleArgument = true;
    private String nucleoArgumento = "";

    /*Para declaracions*/
    private String nucleoDecl;

    /*Para block*/
    private Stack<String> codigoGuardado = new Stack<>();

    /*Para condicionales*/
    private int conteoSkip = 0;
    private String nucleoCondicional = "";

    /*Para condicionales RelExp*/
    private boolean isLocation = false;
    private String idLocation = "";
    private int numExp = 0;



    public String getError() {
        return error;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitInitProgram(decafParser.InitProgramContext ctx) {
        listaDeErrores = new ArrayList<>();
        String id = ctx.ID().getText();
        List<decafParser.DeclarationContext> dc = ctx.declaration();

        /*CODIGO INTERMEDIO*/
            data =  id + ":\n" + data;
        /*CODIGO INTERMEDIO*/
        for(decafParser.DeclarationContext g: dc){
            visit(g);
        }
        String resultado = data + "\n\t.text\nj main" + codigoEmitido + "\n" + subrutinas;
        System.out.println(resultado);


        // Aqui debe de aplicarse la creacion del documento
        return "ProgramaTerminado";

    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitDeclaraionStruct(decafParser.DeclaraionStructContext ctx) {
        return visit(ctx.structDeclaration());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitDeclarationVar(decafParser.DeclarationVarContext ctx) {
        return visit(ctx.varDeclaration());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitDeclarationMethod(decafParser.DeclarationMethodContext ctx) {
        return visit(ctx.methodDeclaration());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitNotValuedVar(decafParser.NotValuedVarContext ctx) {
        //varType ID ';'
        nucleoDecl = "";
        visit(ctx.varType());
        String id = ctx.ID().getText();

        boolean revisado = revisarExistencia(id);
        if (!revisado){
            /*CODIGO INTERMEDIO*/
            data = data + id + ":\t" + nucleoDecl;
            /*CODIGO INTERMEDIO*/

            //name , type, signature, return value, isStruct, symbolTable
            Symbol simbolo = new Symbol(id, type, null, type, false, null, true, false, false);
            ArrayList<Tuplas> tuplas = new ArrayList<>();
            SyTable tabla = new SyTable(tuplas);
            Tuplas tupla = new Tuplas(simbolo.getName(), simbolo);
            if(!(verificadorAmbitos.size() == 0)){
                tabla  = verificadorAmbitos.peek();
                tabla.getTablaDeSimbolos().add(tupla);
            }
            else{
                tabla.getTablaDeSimbolos().add(tupla);
                verificadorAmbitos.push(tabla);
            }
        }
        else{
            //Error, ya existe la variable a crear
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.ID().getText()+ " ya fue declarada anteriormente.\n";
            insertarError(erroneo);
            type = "null";
        }

        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitNotValuedList(decafParser.NotValuedListContext ctx) {
        //varType ID '[' NUM ']' ';'

        visit(ctx.varType());
        String id = ctx.ID().getText();
        boolean revisado =revisarExistencia(id);
        Integer num = Integer.parseInt(ctx.NUM().getText());

        if(!revisado){
            if(num > 0){
                //name, type, signature, cantElement, isStruct, tipoStruct
                /*CODIGO INTERMEDIO*/
                data = data + id + ":\t.space " + (num*4)+"\n";
                /*CODIGO INTERMEDIO*/
                boolean isStruct = false;
                String tipoStruct = null;

                if(!(type.equals("int") || type.equals("boolean") || type.equals("char")|| type.equals("void"))){
                    isStruct = true;
                    tipoStruct = id;
                }
                Conjunto lista = new Conjunto(id, type, null, num , isStruct, tipoStruct, false,
                        false, true);
                Tuplas tupla = new Tuplas(lista.getName(), lista);
                ArrayList<Tuplas> tuplas = new ArrayList<>();
                SyTable tabla = new SyTable(tuplas);
                if(!(verificadorAmbitos.size() == 0))  {
                    tabla = verificadorAmbitos.peek();
                    tabla.getTablaDeSimbolos().add(tupla);
                }
                else{
                    tabla.getTablaDeSimbolos().add(tupla);
                    verificadorAmbitos.push(tabla);
                }

            }
            else{
                //Error, la cantidad de la lista es  un entero 0 o negativo
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + ctx.ID().getText()+ " ha sido creada, no existe instancia de esta variable.\n";
                insertarError(erroneo);
                type = "null";


            }

        }
        else{
            //Error, ya existia la variable
            //Error, ya existe la variable a crear
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.ID().getText()+ " ya fue declarada anteriormente.\n";
            insertarError(erroneo);
            type = "null";

        }


        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitStructDecl(decafParser.StructDeclContext ctx) {
        //'struct' ID '{' varDeclaration* '}'
        type = ctx.ID().getText();
        String id = ctx.ID().getText();

        //name, type, signature, return value, isStruct, symbolTable

        Symbol struct = new Symbol(id, type, null, id, true, null, true, false, false);
        Tuplas nuevaTupla = new Tuplas(struct.getName(), struct);

        ArrayList<Tuplas> tuplas = new ArrayList<>();
        SyTable temporal = new SyTable(tuplas);

        if(verificadorAmbitos.size() > 0){
            temporal = verificadorAmbitos.peek();
            temporal.getTablaDeSimbolos().add(nuevaTupla);
        }
        else{
            temporal.getTablaDeSimbolos().add(nuevaTupla);
            verificadorAmbitos.push(temporal);
        }

        ArrayList<Tuplas> tuplasTrampa = new ArrayList<>();
        SyTable tablaTrampa = new SyTable(tuplasTrampa);
        verificadorAmbitos.push(tablaTrampa);

        //TablasTrampa para meter al struct recien creado
        List<decafParser.VarDeclarationContext> vc = ctx.varDeclaration();
        int numeroVariables = 0;
        for(decafParser.VarDeclarationContext g: vc){
            String varDec = visit(g);
            numeroVariables++;
        }

        /*CODIGO INTERMEDIO*/
        data = data + id + ":\t" + ".space " + (numeroVariables*4)+ "\n";
        nucleoDecl = ".space " + (numeroVariables*4)+ "\n";

        /*CODIGO INTERMEDIO*/

        SyTable tablon = verificadorAmbitos.pop();
        Stack<SyTable> stack = new Stack<>();
        stack.push(tablon);
        struct.setSymbolTable(stack);


        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitIntVar(decafParser.IntVarContext ctx) {
        /*CODIGO INTERMEDIO*/
        nucleoDecl = ".word 0\n";
        /*CODIGO INTERMEDIO*/
        type = "int";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitCharVar(decafParser.CharVarContext ctx) {
        /*CODIGO INTERMEDIO*/
        nucleoDecl = ".asciiz ''\n";
        /*CODIGO INTERMEDIO*/
        type = "char";
        return "";
    }
    /**
 queer      *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBoolVar(decafParser.BoolVarContext ctx) {
        /*CODIGO INTERMEDIO*/
        nucleoDecl = ".word 0\n";
        /*CODIGO INTERMEDIO*/
        type = "boolean";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitStructVar(decafParser.StructVarContext ctx) {
        String pruebaTipo = ctx.ID().getText();
        if(revisarExistencia(pruebaTipo)){
            type =  pruebaTipo;

            /*CODIGO INTERMEDIO*/
            Symbol contenedor = (Symbol) objeto;
            int counter =  contenedor.getSymbolTable().get(0).getTablaDeSimbolos().size();
            nucleoDecl = ".space " + (counter *4) + "\n";

            /*CODIGO INTERMEDIO*/
        }
        else{
            //Error, el tipo de expression no es booleano
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + pruebaTipo+ " no ha sido definido aun'.\n";
            insertarError(erroneo);
            type = "null";
        }

        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitStructDeclVar(decafParser.StructDeclVarContext ctx) {
        String retorno = visit(ctx.structDeclaration());
        return retorno;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitVoidVar(decafParser.VoidVarContext ctx) {
        type = "void";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitMethodDecl(decafParser.MethodDeclContext ctx) {
        String id = ctx.ID().getText();
        visit(ctx.methodType());
        String tipo =type;

        /*CODIGO INTERMEDIO*/
        String  contenidoSubrutina = id + ":\n";
        /*CODIGO INTERMEDIO*/

        //Falta crear el Metodo :D
        //name, type, signature, return value, type value, symbolTable
        recentlyCreated = new Method(id, tipo, argType, argSignature, argType, null, false,
                true, false);
        SyTable tablaParaMetodo = verificadorAmbitos.peek();
        Tuplas nuevaTupla = new Tuplas(recentlyCreated.getName(), recentlyCreated);
        tablaParaMetodo.getTablaDeSimbolos().add(nuevaTupla);
        //Ademas de eso hay que agregar los parametros a la nueva tabla de simbolos

        recentlyCreated =  new Method(null, null,  null, null, null,
                null , false, true, false );

        // hay que ver si existe y se crea una nueva signature o bien que
        List<decafParser.ParameterContext> parametros = ctx.parameter();

        for(decafParser.ParameterContext p: parametros){
            //regresa id. de una pa setear el Temporal
            visit(p);
        }

        String block = visit(ctx.block());
        contenidoSubrutina = contenidoSubrutina + block;
        subrutinas = subrutinas + contenidoSubrutina + "jr\t$ra\n\n";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitIntMeth(decafParser.IntMethContext ctx) {
        type = "int";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitCharMeth(decafParser.CharMethContext ctx) {
        type = "char";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBoolMeth(decafParser.BoolMethContext ctx) {
        type = "boolean";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitVoidMeth(decafParser.VoidMethContext ctx) {
        type = "void";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitParamType(decafParser.ParamTypeContext ctx) {
        visit(ctx.parameterType());
        String id = ctx.ID().getText();

        //name, tyoe, signature, returnvalue, isStruct, symboltable, symbol, method, conjunto
        Symbol simbolo = new Symbol(id, type, null, type, false, null,
                true, false, false);
        simbolo.setTemporal("t" + conteoLabel);
        conteoLabel++;
        Tuplas tupla = new Tuplas(id, simbolo);
        paramTuplas.add(tupla);
        return  id;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitParamListType(decafParser.ParamListTypeContext ctx) {
        visit(ctx.parameterType());
        String id = ctx.ID().getText();

        //name, type, signature, cantElementos, isStruct, tipoStruct, symbol, method, conjunto
        Conjunto conjunto = new Conjunto(id, type, null, 0, false, null,
                false, false, true);
        conjunto.setTemporal("t" + conteoLabel);
        conteoLabel++;
        Tuplas tupla = new Tuplas(conjunto.getName(), conjunto);
        paramTuplas.add(tupla);
        return id;

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitIntParam(decafParser.IntParamContext ctx) {
        type = "int";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitCharParam(decafParser.CharParamContext ctx) {
        type = "char";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBoolParam(decafParser.BoolParamContext ctx) {
        type = "boolean";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitVarDeclHelp(decafParser.VarDeclHelpContext ctx) {
        String retorno = visit(ctx.varDeclaration());
        return retorno;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String  visitStmHelp(decafParser.StmHelpContext ctx) {
        String retorno = visit(ctx.statement());
        return retorno;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBlockDecl(decafParser.BlockDeclContext ctx) {
        //aqui se declara y destruye un nuevo ambito despues de hacer las visitas que tocan :D

        //Buscar las tuplas creadas en la declaracion, si se realizo alguno y lueog vera las variables
        //declaradas en el bloque para meterlas a la tabla de simbolos

        /*CODIGO INTERMEDIO*/
        String ingreso = new String(codigoEmitido);
        codigoGuardado.push(ingreso);
        codigoEmitido = "";
        /*CODIGO INTERMEDIO*/


        ArrayList<Tuplas> imitation = new ArrayList<>();
        for (Tuplas t: paramTuplas){
            imitation.add(t);
        }
        SyTable ambitoActual = new SyTable(imitation);
        verificadorAmbitos.push(ambitoActual);
        paramTuplas.clear();

        int numeroBlock = ctx.blockHelp().size();


        for(int i = 0; i < numeroBlock; i++){
            String uso = visit(ctx.blockHelp(i));
        }
        //Pop Sytable
        verificadorAmbitos.pop();

        /*CODIGO INTERMEDIO*/
        String string = new String(codigoEmitido);
        codigoEmitido = codigoGuardado.pop();
        /*CODIGO INTERMEDIO*/
        return string;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitIfDeclStm(decafParser.IfDeclStmContext ctx) {
        String stm = visit(ctx.expression());
        if(type.equals("boolean")){
            String stmResult = "";
            if(!Boolean.parseBoolean(stm)){
                stmResult = "0";
            }
            else{
                stmResult = "1";
            }

            /*CODIGO INTERMEDIO*/
            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + stmResult + "\n";

            //https://courses.cs.washington.edu/courses/cse378/03wi/lectures/mips-asm-examples.html
            codigoEmitido = codigoEmitido + "beq\t$t" + conteoLabel + ", $zero, L" + conteoDestinos + "\n";
            /*CODIGO INTERMEDIO*/

            String resultado = visit(ctx.block());
            codigoEmitido = codigoEmitido + resultado;
            codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";

            conteoLabel++;
            conteoDestinos++;
            return resultado;
        }
        else{
            //Error, el tipo de expression no es booleano
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression().getText()+ " no es una expression de tipo 'boolean'.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitIfElseDeclStm(decafParser.IfElseDeclStmContext ctx) {
        String stm = visit(ctx.expression());
        if(type.equals("boolean")){
            String stmResult = "";
            if(!(Boolean.parseBoolean(stm))){
                stmResult = "0";
            }
            else{
                stmResult = "1";
            }

            /*CODIGO INTERMEDIO*/
            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + stmResult + "\n";
            codigoEmitido = codigoEmitido + "beq\t$t" + conteoLabel + ", 1, L" + conteoDestinos + "\n";
            conteoDestinos++;
            codigoEmitido = codigoEmitido + "b\tL" + conteoDestinos + "\n";
            /*CODIGO INTERMEDIO*/

            String resultadoB1  = visit(ctx.block(0));
            String resultadoB2  = visit(ctx.block(1));
            codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n"+resultadoB1+"\nb\tskip\n";
            codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n"+resultadoB2+"\nb\tskip"+conteoSkip+"\n";
            codigoEmitido = codigoEmitido + "skip"+ conteoSkip+ ":\n";
            conteoSkip++;
            conteoLabel++;
            conteoDestinos++;
            return "";
        }
        else{
            //Error, el tipo de expression no es booleano
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression().getText()+ " no es una expression de tipo 'boolean'.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitWhileDeclStm(decafParser.WhileDeclStmContext ctx) {
        String stm = visit(ctx.expression());
        if(type.equals("boolean")){
            String resultado = "";
            String stmResult = "";
            if(!(Boolean.parseBoolean(stm))){
                stmResult = "0";
            }
            else{
                stmResult = "1";
            }

            resultado  = visit(ctx.block());
            /*CODIGO INTERMEDIO*/
            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + stmResult + "\n";
            codigoEmitido = codigoEmitido + "L" + conteoDestinos+ ":\n";
            codigoEmitido = codigoEmitido + "beq\t$t" + conteoLabel + ", $zero, skip" + conteoSkip + "\n";
            codigoEmitido = codigoEmitido + resultado;
            codigoEmitido = codigoEmitido + "beq\t$t" + conteoLabel + ", 1, L" + conteoDestinos + "\n";
            codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
            conteoLabel++;
            conteoSkip++;
            conteoDestinos++;
            /*CODIGO INTERMEDIO*/
            return resultado;
        }
        else{
            //Error, el tipo de expression no es booleano
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression().getText()+ " no es una expression de tipo 'boolean'.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitReturnStm(decafParser.ReturnStmContext ctx) {
        type = "void";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitReturnTypeStm(decafParser.ReturnTypeStmContext ctx) {
        String stm = visit(ctx.expression());
        return stm;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitCallMethodStm(decafParser.CallMethodStmContext ctx) {
        String resultado = visit(ctx.methodCall());
        return resultado;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBlockStm(decafParser.BlockStmContext ctx) {
        String resultado = visit(ctx.block());
        return resultado;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitAssignStm(decafParser.AssignStmContext ctx) {
        //Identificar quienes son los dos involucrados, en terminos de ID
        //Luego ver si, tienen el mismo tipo de dato
        //Realizar la asignacion

        String idLoc = visit(ctx.location());
        String locationType = type;
        Object temporal = objeto;

        String idExp = visit(ctx.expression());
        String expressionType = type;

        if(locationType.equals(expressionType)){
            /*CODIGO INTERMEDIO*/
            if(idExp.equals("false")){
                idExp = "0";
            }
            else if(idExp.equals("true")){
                idExp = "1";
            }

            if(!simpleArgument){
                codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + idExp +"\n";
            }
            else{
                codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + idExp +"\n";
            }
            conteoLabel++;
            codigoEmitido = codigoEmitido + "sw\t$t" + conteoLabel + ", " + idLoc + "\n";
            conteoLabel++;
            /*CODIGO INTERMEDIO*/

            temporal = objeto;
            type = "void";
            return "";
        }
        else{
            //Error, los tipos de la asignacion no son iguales y no son null
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.location().getText() + " y " + ctx.expression().getText() + " no son del mismo tipo.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitEndStm(decafParser.EndStmContext ctx) {
        type = "void";
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitExpressionStm(decafParser.ExpressionStmContext ctx) {
        String resultado = visit(ctx.expression());
        return resultado;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */

    @Override public String visitSimpleLoc(decafParser.SimpleLocContext ctx) {
        //Revisar si el ID existe y pertenece a un simbolo.
        //Al visitar esto, el objeto se queda en la variable objeto
        String id = ctx.ID().getText();
        isLocation = true;
        idLocation = id;
        boolean revision = false;
        boolean objAntStruct = false;
        if(objetoAnterior == null){
            revision = revisarExistencia(id);
        }
        else{
            objeto = objetoAnterior;
            revision = true;
            objAntStruct = true;
        }

        if(revision){
            if(objAntStruct){
                if(objeto.isSymbol()){
                    Symbol simbolo = (Symbol)objeto;
                    if(simbolo.isStruct()){
                        Stack<SyTable> temporal = simbolo.getSymbolTable();
                        codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + id + "\n";
                        conteoLabel++;
                        //aqui me quede
                        searchInStack(temporal, id);
                        if(!type.equals("null")){
                            objetoAnterior = null;
                            return id;

                        }
                        else{
                            //Error: No se encontro el atributo buscado
                            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                    ". Atributo " + id + " no es un atributo de " +objeto.getName()+ ".\n";
                            insertarError(erroneo);
                            type = "null";
                        }


                    }
                    else{
                        type = simbolo.getType();
                        objetoAnterior = null;
                        return id;
                        //Error: no es un struct, pero si un simbolo
                        //String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        //        ". " + objeto.getName() + ", si es un simbolo, no es un struct .\n";
                        //insertarError(erroneo);
                        //type = "null";
                    }
                }
                else{
                    //Error: Struct no entra como symbolo
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + objeto.getName() + " No es un Simbolo legible correctamente.\n";
                    insertarError(erroneo);
                    type = "null";
                }
            }
            else{

                if(objeto.isSymbol() || objeto.isMethod()){
                    type = objeto.getType();
                    objetoAnterior = null;
                    return id;
                }

                else{
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + id + " no es un symbolo o una llamada a metodo.\n";
                    insertarError(erroneo);
                    type = "null";

                }

            }
        }
        else{
            //Error, ID no existente
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + id + " no ha sido declarado.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitSimpleLocExpr(decafParser.SimpleLocExprContext ctx) {
        //Revisar si el ID existe y pertenece a un simbolo.
        String id = ctx.ID().getText();
        boolean revision = false;
        if(objetoAnterior == null){
            revision = revisarExistencia(id);
        }
        else{
            objeto = objetoAnterior;
            revision = true;

        }

        if(revision){
            if(objeto.isSymbol()){
                type = objeto.getType();
                String tipoSymbol = type;
                Symbol temporal = (Symbol) objeto;
                boolean structurado = false;
                if(temporal.isStruct()){
                    objetoAnterior = objeto;
                    String deepening = visit(ctx.location());
                    structurado = true;
                    if(structurado && !(type.equals("null"))){
                        return id;
                    }
                }

                else{
                    //No es Struct, no puede obtener un ID.location
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + id + " no es un Struct, no puede obtenerse la " +
                            "condicion de atributo por medio de '.' .\n";
                    insertarError(erroneo);
                    type = "null";
                }
            }
            else{
                //No es symbol, fijo es method o List
                String erroneo ="Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + id + " no es un Symbol, es una Lista o un Method :D.\n";
                insertarError(erroneo);
                type = "null";

            }

        }
        else{
            //Error, ID no existente
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + id + " no ha sido declarado.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitListLocExpr(decafParser.ListLocExprContext ctx) {
        //Revisar si el ID existe y pertenece a un simbolo.
        String id = ctx.ID().getText();
        isLocation = true;
        idLocation = id;
        String expresion = visit(ctx.expression());
        if(type.equals("int")){
            String possibleGuion = expresion.substring(0,1);

            if(!possibleGuion.equals("-")){

                boolean revision = false;
                if(objetoAnterior == null){
                    revision = revisarExistencia(id);
                }else{
                    objeto = objetoAnterior;
                    revision = true;
                }
                if(revision){

                    if(objeto.isConjunto()){
                        Conjunto lista = (Conjunto) objeto;

                        //Si el numero puesto en la lista pertenece a la cantiada de elementos en la lista
                        if(lista.getCantElementos()> Integer.parseInt(expresion)){
                            if(lista.isStruct()){
                                objetoAnterior = objeto;
                                String atributo = visit(ctx.location());

                                if(!type.equals("null")){
                                    /*CODIGO INTERMEDIO*/
                                    int indice = Integer.parseInt(expresion);
                                    codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + (4*indice) +
                                                                        "(" +id + ")\n";
                                    conteoLabel++;
                                    /*CODIGO INTERMEDIO*/

                                    //obtener el objeto dentro de la lista
                                    Conjunto resultado = (Conjunto) objeto;
                                    type = resultado.getTipoStruct();
                                    return id;

                                }
                                else{
                                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                            ". " + ctx.location().getText() + " no existe o no pudo ser encontrado.\n";
                                    insertarError(erroneo);
                                    type = "null";

                                }

                            }
                            else{
                                //Contenido de la lista no es struct, marcar error.
                                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                        ". " + id + " no es una lista con Structs.\n";
                                insertarError(erroneo);
                                type = "null";

                            }
                        }
                        else{
                            //Error: index our of bounds
                            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                    ". " + ctx.ID().getText()+ " indexOutOfBounds.\n";
                            insertarError(erroneo);
                            type = "null";
                        }

                    }
                    else{
                        //Error, no es una lista, la estructura esta mal
                        String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                ". " + ctx.ID().getText()+ " no es una lista, no puede obtenerse el [ "
                                + ctx.expression().getText()+"] dato.\n";
                        insertarError(erroneo);
                        type = "null";

                    }

                }
                else{
                    //Error, ID no existente
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + id + " no existe, no ha sido creado.\n";
                    insertarError(erroneo);
                    type = "null";

                }

            }
            else{
                //Es un numero negativo
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + expresion + " no es un numero positivo..\n";
                insertarError(erroneo);
                type = "null";
            }


        }
        else{
            //Expression no es de tipo int
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression().getText() + " no es de tipo int.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitListLoc(decafParser.ListLocContext ctx) {
        //Revisar si el ID existe y pertenece a un simbolo.
        String id = ctx.ID().getText();
        boolean revision = false;
        if(objetoAnterior == null){
            revision = revisarExistencia(id);
        }else{
            objeto = objetoAnterior;
            revision = true;
        }
        type = objeto.getType();


        if(revision){
            if(objeto.isConjunto()){
                String tipoLista = type;
                String expresion = visit(ctx.expression());
                if(type.equals("int")){
                    Integer num = Integer.parseInt(expresion);
                    if(num >=0){
                        numExp = num;
                        //chequear si el numero esta dentro del rango de elementos de la lista
                        Conjunto temporal = (Conjunto) objeto;
                        if(temporal.getCantElementos() > num){
                            //Obtener la instancia de Line

                            try{
                                int indice = Integer.parseInt(visit(ctx.expression()));
                                type = tipoLista;
                                objetoAnterior = null;
                                return (4*num) + "("+ id+ ")";

                            }catch (Exception IndexOutOfBounds ){
                                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                        ". " + ctx.ID().getText() + " IndexOutOfBounds, posee:  "+ temporal.getCantElementos()+
                                        " elementos, se pidio el elemento " + ctx.expression().getText()+ ".\n";
                                insertarError(erroneo);
                                type = "null";

                            }
                        }
                        else{
                            //Error indexOutOfBounds
                            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                    ". " + expresion + " indexOutOfBounds.\n";
                            insertarError(erroneo);
                            type = "null";

                        }

                    }
                    else{
                        //NUmero negativo
                        //Expr no es de tipo int
                        String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                ". " + expresion + " es un numero negativo, no puede ser indice.\n";
                        insertarError(erroneo);
                        type = "null";
                        return error+="Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                                ". " + expresion + " es un numero negativo, no puede ser indice.\n";

                    }
                }
                else{
                    //Expr no es de tipo int
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + expresion + " no es de tipo 'int'.\n";
                    insertarError(erroneo);
                    type = "null";
                }

            }
            else{
                //error, no es instancia de una lista
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + id + " No es una instancia de Lista.\n";
                insertarError(erroneo);
                type = "null";
            }


        }
        else{
            //Error, ID no existente
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + id + " no ha sido declarado.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
  /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitRelOpExp(decafParser.RelOpExpContext ctx) {
        String operation = ctx.op.getText();
        String exp1 =  visit(ctx.expression(0));
        if(type.equals("int")){
            /*CODIGO INTERMEDIO*/
            if(isLocation){
                codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                        +numExp + "(" +idLocation + ")\n";
                conteoLabel++;
            }
            else{
                codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 + "\n";
                conteoLabel++;
            }
            /*CODIGO INTERMEDIO*/
            String exp2 = visit(ctx.expression(1));
            if(type.equals("int")){
                if(isLocation){
                    codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                            +numExp + "(" +idLocation + ")\n";
                    conteoLabel++;
                }
                else{
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 + "\n";
                    conteoLabel++;
                }
                /*CODIGO INTERMEDIO*/
                type = "boolean";
                if(operation.equals("<")){
                    /*CODIGO INTERMEDIO*/
                    codigoEmitido = codigoEmitido + "blt\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                                    ", L" +conteoDestinos+ "\n";
                    conteoDestinos ++ ;
                    codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
                    codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\nb\tskip"+conteoSkip+"\n";
                    codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
                    codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
                    conteoSkip ++ ;
                    conteoDestinos++;
                    conteoLabel++;
                    /*CODIGO INTERMEDIO*/
                    if(Integer.parseInt(exp1) < Integer.parseInt(exp2)){

                        return "true";
                    }

                    else if(Integer.parseInt(exp1) >= Integer.parseInt(exp2)){
                        return "false";
                    }


                }
                else if(operation.equals(">")){
                    /*CODIGO INTERMEDIO*/
                    codigoEmitido = codigoEmitido + "bgt\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                            ", L" +conteoDestinos+ "\n";
                    conteoDestinos ++ ;
                    codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
                    codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\nb\tskip"+conteoSkip+"\n";
                    codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
                    codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
                    conteoSkip ++ ;
                    conteoDestinos++;
                    conteoLabel++;
                    /*CODIGO INTERMEDIO*/
                    if(Integer.parseInt(exp1) > Integer.parseInt(exp2)){
                        return "true";
                    }
                    else if (Integer.parseInt(exp1) <= Integer.parseInt(exp2)){
                        return "false";
                    }

                }
                else if(operation.equals("<=")){
                    /*CODIGO INTERMEDIO*/
                    codigoEmitido = codigoEmitido + "ble\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                            ", L" +conteoDestinos+ "\n";
                    conteoDestinos ++ ;
                    codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
                    codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\nb\tskip"+conteoSkip+"\n";
                    codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
                    codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
                    conteoSkip ++ ;
                    conteoDestinos++;
                    conteoLabel++;
                    /*CODIGO INTERMEDIO*/
                    if(Integer.parseInt(exp1) <= Integer.parseInt(exp2)){
                        return "true";
                    }
                    else if(Integer.parseInt(exp1) > Integer.parseInt(exp2)){
                        return "false";
                    }

                }
                else if(operation.equals(">=")){
                    /*CODIGO INTERMEDIO*/
                    codigoEmitido = codigoEmitido + "bge\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                            ", L" +conteoDestinos+ "\n";
                    conteoDestinos ++ ;
                    codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
                    codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\nb\tskip"+conteoSkip+"\n";
                    codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
                    codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
                    conteoSkip ++ ;
                    conteoDestinos++;
                    conteoLabel++;
                    /*CODIGO INTERMEDIO*/
                    if(Integer.parseInt(exp1) >= Integer.parseInt(exp2)){
                        return "true";
                    }
                    else if(Integer.parseInt(exp1) < Integer.parseInt(exp2)){
                        return "false";
                    }
                }
                else{
                    //No es ningun signo esperado
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + ctx.expression(1).getText()+ " no es una expression de tipo 'int'.\n";
                    insertarError(erroneo);
                    type = "null";

                }
            }
            else{
                //La segunda expresion no es tipo int
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + ctx.expression(1).getText()+ " no es una expression de tipo 'int'.\n";
                insertarError(erroneo);
                type = "null";
            }
        }
        else{
            //Error porque no es tipo int
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression(0).getText()+ " no es una expression de tipo 'int'.\n";
            insertarError(erroneo);
            type = "null";
        }
        return "";
    }
    /**
     * {@inheritDoc
     * }
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitDashExp(decafParser.DashExpContext ctx) {
        String value = ctx.expression().getText();
        if(type.equals("int")){
            String posibleDash = value.substring(0,1);
            if(posibleDash.equals("-")){
                type = "int";
                String rialValue = value.substring(1,value.length());
                return rialValue;

            }
            else{
                type = "int";
                return "-" + value;

            }

        }
        else{
            //valor no es de tipo int, no se puede hacer dash;
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". " + ctx.expression().getText() + " no es una expression de tipo 'int'.\n";
            insertarError(erroneo);
            type = "null";


        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitLiteralExp(decafParser.LiteralExpContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitCondOpExp(decafParser.CondOpExpContext ctx) {
        String operaton = ctx.op.getText();
        if(operaton.equals("&&") || operaton.equals("||")){
            String exp1 = visit(ctx.expression(0));
            if(type.equals("boolean")){
                /*CODIGO INTERMEDIO*/
                if(isLocation){
                    codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                            +numExp + "(" +idLocation + ")\n";
                    conteoLabel++;
                }
                else{
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 + "\n";
                    conteoLabel++;
                }
                /*CODIGO INTERMEDIO*/
                String exp2 = visit(ctx.expression(1));
                if(type.equals("boolean")){
                    /*CODIGO INTERMEDIO*/
                    if(isLocation){
                        codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                                +numExp + "(" +idLocation + ")\n";
                        conteoLabel++;
                    }
                    else{
                        codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 + "\n";
                        conteoLabel++;
                    }
                    /*CODIGO INTERMEDIO*/
                    //2 expresiones booleanas
                    type = "boolean";
                    if(operaton.equals("&&")){
                        /*CODIGO INTERMEDIO*/
                        codigoEmitido = codigoEmitido + "beq\t$t" + (conteoLabel-2) + ", 0, L" + conteoDestinos + "\n";
                        codigoEmitido = codigoEmitido + "beq\t$t" + (conteoLabel-1) + ", 0, L" + conteoDestinos + "\n";
                        codigoEmitido = codigoEmitido + "li\t$t"+ conteoLabel + ", 1\n";
                        codigoEmitido  = codigoEmitido + "b\tskip" + conteoSkip;
                        codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                        codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
                        codigoEmitido = codigoEmitido + "skip" + conteoSkip+":\n";
                        conteoLabel++;
                        conteoDestinos++;
                        conteoSkip++;
                        /*CODIGO INTERMEDIO*/

                        if(exp1.equals(exp2) && exp1.equals("false")){
                            return "false";
                        }
                        else if(exp1.equals(exp2) && exp1.equals("true")){
                            return "true";
                        }
                        else{
                            //valores diferentes
                            return "false";
                        }

                    }
                    else{
                        /*CODIGO INTERMEDIO*/
                        codigoEmitido = codigoEmitido + "beq\t$t" + (conteoLabel-2) + ", 1, L" + conteoDestinos + "\n";
                        codigoEmitido = codigoEmitido + "beq\t$t" + (conteoLabel-1) + ", 1, L" + conteoDestinos + "\n";
                        codigoEmitido = codigoEmitido + "li\t$t"+ conteoLabel + ", 0\n";
                        codigoEmitido  = codigoEmitido + "b\tskip" + conteoSkip;
                        codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
                        codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\n";
                        codigoEmitido = codigoEmitido + "skip" + conteoSkip+":\n";
                        conteoLabel++;
                        conteoDestinos++;
                        conteoSkip++;
                        /*CODIGO INTERMEDIO*/
                        if(exp1.equals(exp2) && exp1.equals("false")){
                            return "false";
                        }
                        else if(exp1.equals(exp2) && exp1.equals("true")){
                            return "true";
                        }
                        else{
                            //valores diferentes
                            return "true";
                        }

                    }
                }
                else{
                    //Expression 2 no es booleana
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                            ". " + ctx.expression(1).getText()+ " no es una expression booleana.\n";
                    insertarError(erroneo);
                    type = "null";

                }
            }
            else{
                //Expression 1 no es booleana
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". " + ctx.expression(0).getText()+ " no es una expression booleana.\n";
                insertarError(erroneo);
                type = "null";

            }


        }
        else{
            //Esto mostrara error porque la operacion que esta enmedio no es
            //una condicional
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". Se esperaba signo '&&' o '||'.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitLocationExp(decafParser.LocationExpContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitMethodExp(decafParser.MethodExpContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitParentExp(decafParser.ParentExpContext ctx) {
        visit(ctx.expression());
        return "";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitEqOpExp(decafParser.EqOpExpContext ctx) {
        String operation = ctx.op.getText();
        String exp1 = visit(ctx.expression(0));
        /*CODIGO INTERMEDIO*/
        if(isLocation){
            codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                    +numExp + "(" +idLocation + ")\n";
            conteoLabel++;
        }
        else{
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 + "\n";
            conteoLabel++;
        }
        /*CODIGO INTERMEDIO*/


        String exp2 = visit(ctx.expression(1));
        /*CODIGO INTERMEDIO*/
        if(isLocation){
            codigoEmitido = codigoEmitido + "lw\t#t" + conteoLabel + ", "
                    +numExp + "(" +idLocation + ")\n";
            conteoLabel++;
        }
        else{
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 + "\n";
            conteoLabel++;
        }
        /*CODIGO INTERMEDIO*/
        if(operation.equals("==")){
            /*CODIGO INTERMEDIO*/
            codigoEmitido = codigoEmitido + "beq\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                    ", L" +conteoDestinos+ "\n";
            conteoDestinos ++ ;
            codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
            codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\nb\tskip"+conteoSkip+"\n";
            codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\n";
            codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
            conteoSkip ++ ;
            conteoDestinos++;
            conteoLabel++;
            /*CODIGO INTERMEDIO*/

            type = "boolean";
            if(exp1.equals(exp2)){
                return "true";
            }
            else{
                return "false";
            }


        }
        else if (operation.equals("!=")){
            /*CODIGO INTERMEDIO*/
            codigoEmitido = codigoEmitido + "blt\t$t" + (conteoLabel-2) + ", $t" + (conteoLabel-1)+
                    ", L" +conteoDestinos+ "\n";
            conteoDestinos ++ ;
            codigoEmitido = codigoEmitido + " b\tL" + conteoDestinos + "\n";
            codigoEmitido = codigoEmitido + "L" + (conteoDestinos-1)+ ":\n";
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 0\nb\tskip"+conteoSkip+"\n";
            codigoEmitido = codigoEmitido + "L" + conteoDestinos + ":\n";
            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", 1\n";
            codigoEmitido = codigoEmitido + "skip" + conteoSkip + ":\n";
            conteoSkip ++ ;
            conteoDestinos++;
            conteoLabel++;
            /*CODIGO INTERMEDIO*/

            type = "boolean";
            if(exp1.equals(exp2)){
                return "false";
            }
            else{
                return "true";
            }
        }
        else{
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". Se esperaba signo '==' o '!='.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitNotExp(decafParser.NotExpContext ctx) {
        String value = visit(ctx.expression());
        if(type.equals("boolean")){
            if(value.equals("false")){
                type = "boolean";
                return "true";

            }
            else if(value.equals("true")){
                type = "boolean";
                return "false";

            }
            else{
                //Error porque no tiene ninguno de los valores booleanos esperados
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                        ". Valor booleano esperado.\n";
                insertarError(erroneo);
                type = "null";

            }
        }
        else{
            //Mostrar error porque lo que se quiere negar no es un booleano
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+
                    ". Valor booleano esperado.\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitFirstArithOpExp(decafParser.FirstArithOpExpContext ctx) {
        String opearation = ctx.op.getText();
        if(opearation.equals("*") ||opearation.equals("/") ||opearation.equals("%") ){
            String exp1 = visit(ctx.expression(0));
            if(type.equals("int")){
                /*CODIGO INTERMEDIO*/
                if(simpleArgument){
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 +"\n";
                }
                else{
                    revisarExistencia(exp1);
                    codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                }
                conteoLabel++;
                /*CODIGO INTERMEDIO*/
                String exp2 = visit(ctx.expression(1));
                if(type.equals("int")){
                    type = "int";
                    if(opearation.equals("*")){
                        /*CODIGO INTERMEDIO*/
                        if(simpleArgument){
                            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 +"\n";
                        }
                        else{
                            revisarExistencia(exp1);
                            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                        }
                        codigoEmitido = codigoEmitido + "mult\t$t" + (conteoLabel-1)+ ",$t" + conteoLabel +"\n";
                        conteoLabel++;
                        codigoEmitido = codigoEmitido + "mfhi\t$t" + conteoLabel + "\n";
                        conteoLabel++;

                        /*CODIGO INTERMEDIO*/
                        int value = Integer.parseInt(exp1) * Integer.parseInt(exp2);
                        return "" + value;

                    }
                    else if(opearation.equals("/")){
                        /*CODIGO INTERMEDIO*/
                        if(simpleArgument){
                            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 +"\n";
                        }
                        else{
                            revisarExistencia(exp1);
                            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                        }
                        codigoEmitido = codigoEmitido + "div\t$t" + (conteoLabel-1)+ ",$t" + conteoLabel +"\n";
                        conteoLabel++;
                        codigoEmitido = codigoEmitido + "mflo\t$t" + conteoLabel + "\n";
                        conteoLabel++;
                        /*CODIGO INTERMEDIO*/
                        int value = Integer.parseInt(exp1) / Integer.parseInt(exp2);
                        return "" + value;

                    }
                    else{
                        /*CODIGO INTERMEDIO*/
                        if(simpleArgument){
                            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 +"\n";
                        }
                        else{
                            revisarExistencia(exp1);
                            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                        }
                        codigoEmitido = codigoEmitido + "div\t$t" + (conteoLabel-1)+ ",$t" + conteoLabel +"\n";
                        conteoLabel++;
                        codigoEmitido = codigoEmitido + "mfhi\t$t" + conteoLabel + "\n";
                        conteoLabel++;
                        /*CODIGO INTERMEDIO*/
                        int value = Integer.parseInt(exp1) % Integer.parseInt(exp2);
                        return ""  + value;

                    }
                }
                else{
                    //Exp2 no es de tipo int
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                            + ". Second expression type = "+ type +", expected 'int'\n";
                    insertarError(erroneo);
                    type = "null";

                }
            }
            else{
                //exp1 no es de tipo int
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                        + ". First expression type = "+ type +", expected 'int'\n";
                insertarError(erroneo);
                type = "null";

            }

        }
        else{
            //No es ninguno de los operadores esperados
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                    + ". Expected '*', '/', '%' operators, got "+ opearation +" opearator\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitSecondArithOpExp(decafParser.SecondArithOpExpContext ctx) {
        String operation = ctx.op.getText();
        if(operation.equals("+") || operation.equals("-")){
            String exp1 = visit(ctx.expression(0));
            if(type.equals("int")){
                /*CODIGO INTERMEDIO*/
                if(simpleArgument){
                    codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp1 +"\n";
                }
                else{
                    revisarExistencia(exp1);
                    codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                }
                conteoLabel++;
                /*CODIGO INTERMEDIO*/
                String exp2 = visit(ctx.expression(1));
                if(type.equals("int")){
                    if(operation.equals("+")){
                        /*CODIGO INTERMEDIO*/
                        if(simpleArgument){
                            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 +"\n";
                        }
                        else{
                            revisarExistencia(exp1);
                            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                        }
                        conteoLabel++;
                        codigoEmitido = codigoEmitido + "add\t$t" + (conteoLabel)+ ",$t" + (conteoLabel-2) +", $t"
                                                        + (conteoLabel-1)+ "\n";
                        conteoLabel++;

                        /*CODIGO INTERMEDIO*/
                        type = "int";
                        int resultado = Integer.parseInt(exp1) + Integer.parseInt(exp2);
                        return "" + resultado;

                    }
                    else{
                        if(simpleArgument){
                            codigoEmitido = codigoEmitido + "li\t$t" + conteoLabel + ", " + exp2 +"\n";
                        }
                        else{
                            revisarExistencia(exp1);
                            codigoEmitido = codigoEmitido + "lw\t$t" + conteoLabel + ", " + objeto.getName() +"\n";
                        }
                        conteoLabel++;
                        codigoEmitido = codigoEmitido + "sub\t$t" + (conteoLabel)+ ",$t" + (conteoLabel-2) +", $t"
                                + (conteoLabel-1)+ "\n";
                        conteoLabel++;
                        type = "int";
                        int resultado = Integer.parseInt(exp1) - Integer.parseInt(exp2);
                        return "" + resultado;

                    }
                }
                else{
                    //exp2 no es de tipo int
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                            + ". Expected 'int', second expression type =. "+ type +"\n";
                    insertarError(erroneo);
                    type = "null";
                }
            }
            else if (type.equals("char")){
                String exp2 = visit(ctx.expression(1));
                if(type.equals("char")){
                    if(operation.equals("+")){
                        type = "char";
                        return exp1 + exp2;
                    }
                    else{
                        //No se puede hacer resta de char :D solo concatenacion
                        String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                                + ". Non exisitent substraction of 'char'\n";
                        insertarError(erroneo);
                        type = "null";

                    }

                }
                else{
                    //Exp 2 no es de tipo char cuando exp 1 si es char
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                            + ". Expected 'char', first expression type = 'char', second expression type =. "+ type +"\n";
                    insertarError(erroneo);
                    type = "null";

                }

            }
            else{
                //No se puede hacer la operacion, exp1 no es int ni char
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                        + ". Expected 'int' or 'char', first expression type = "+ type +"\n";
                insertarError(erroneo);
                type = "null";

            }
        }
        else{
            //Error, el signo ingresado no es ninguno de los esperados
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                    + ". Expected '+' or '-', recieved "+ operation+ "\n";
            insertarError(erroneo);
            type = "null";

        }
        return "";

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitMethodCallDecl(decafParser.MethodCallDeclContext ctx) {
        //Chequear primero que existe el metodo
        nucleoArgumento = "";
        conteoParametro = 0;
        declaration = false;
        String identificador = ctx.ID().getText();

        boolean existente = revisarExistencia(identificador);
        if(objeto.isMethod()){
            Method temporal = (Method) objeto;
            if(existente){
                //Si existe el metodo, crear una lista de strings con le tipo de datos que es cada uno
                //Verificar si ese tipo de datos es parte del signature del metodo
                List<decafParser.ArgContext> argumentos = ctx.arg();
                if(argumentos.size() > 4){
                    String interrupcion = "\n------------------------------------------------\nEl lenguaje Mips solo " +
                                            "perimte el uso de 4 parametros. El metodo " + identificador + " utiliza mas" +
                                            " de los que se pueden soportar con 4 registros." +
                                            "\n------------------------------------------------\n";
                    codigoEmitido = codigoEmitido + interrupcion;

                }
                for(decafParser.ArgContext a: argumentos){
                    String value = visit(a);
                    if(!(type.equals("null"))){
                        //Define diferencia entre meterse con un literal o un ID en los argumentos
                        if(simpleArgument){
                            if(nucleoArgumento.equals("")){
                                //valores integers
                                codigoEmitido = codigoEmitido + "li\t$a" + conteoParametro + ", "+ value +"\n";
                                conteoParametro++;

                            }
                            else if(nucleoArgumento.equals("true")){
                                //valores verdaderos en booleanos
                                value = "1";
                                codigoEmitido = codigoEmitido + "li\t$a" + conteoParametro + ", "+ value +"\n";
                                conteoParametro++;

                            }
                            else if (nucleoArgumento.equals("false")){
                                //valores falsos en booleanos
                                value = "0";
                                codigoEmitido = codigoEmitido + "li\t$a" + conteoParametro + ", "+ value +"\n";
                                conteoParametro++;
                            }
                            else{
                                //valores char
                                data = data + "D" + numeroData + ":\t.asciiz " + value +"\n";
                                numeroData++;

                                codigoEmitido = codigoEmitido + "lw\t$a" + conteoParametro + ", D"+ (numeroData -1)+"\n";
                                conteoParametro++;

                            }
                        }
                        else{
                            //Valores que fueron ingresados con ID y que si existen.
                            String tipoDeData = "";
                            if(type.equals("int")){
                                tipoDeData = ".word";

                            }
                            else if (type.equals("char")){
                                tipoDeData = ".asciiz ";

                            }
                            else{
                                tipoDeData = ".word ";

                            }
                            //VALUE TIENE QUE ENTREGAR UN VALOR. HACERLO EN LA ASIGNACION. DE ESTA MANERA EN LA ASIGNACION
                            //SI TENES A = B
                            //ENTONCES SE DEBE DE PONER EN EL OBJETO DE TIPO A LAS FUNCION DE SET Y GET VALUE;
                            data = data + value + ":\t" + tipoDeData + objeto.getValue() + "\n";

                        }

                        argType.add(type);
                    }


                }
                /*CODIGO INTERMEDIO*/
                codigoEmitido = codigoEmitido + "jal\t" + identificador + "\n";
                numeroData = 0;
                /*CODIGO INTERMEDIO*/
                boolean firmaExistente = false;
                if(temporal.getTypeValue().contains(argType)){
                    firmaExistente = true;
                    type = temporal.getType();
                }

                if(!firmaExistente){
                    //Mostrar error porque del metodo, no existe con esa combinacion de parametros
                    String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+
                            ctx.getStart().getCharPositionInLine()+ ". "+ ctx.ID().getText() +": Firma no existente.\n";
                    insertarError(erroneo);
                    type = "null";

                }
            }
            else{
                //Mostrar error porque el metodo no existe
                String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()
                        + ". \""+ctx.ID().getText()+"\" , Method unexistent.\n";
                insertarError(erroneo);
                type = "null";

            }
        }
        else{
            //Error, se esperaba un metodo
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+ ". "
                    + ctx.ID().getText() +": Method expected.\n";
            insertarError(erroneo);
            type = "null";

        }


        /**
         * Tipo en Type = tipo del metodo.**/

        return "";
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitExpressionArg(decafParser.ExpressionArgContext ctx) {
        //Expresion de argumento, determinara el valor del argumento y el tipo del mismo
        String id = ctx.ID().getText();
        simpleArgument = false;
        boolean revision = revisarExistencia(id);
        if(revision){
            return id;
        }
        else{
            //No existe el argumento declarado en el MethodCall
            type = "null";
            String erroneo = "Error in line:" + ctx.getStart().getLine()+", "+ ctx.getStart().getCharPositionInLine()+ ". "
                    + ctx.ID().getText() +" no ha sido declarado.\n";
            insertarError(erroneo);
            return "";

        }

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitArgLiteral(decafParser.ArgLiteralContext ctx) {
        return visit(ctx.literal());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitArgumentTypeInt(decafParser.ArgumentTypeIntContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitArgumentTypeChar(decafParser.ArgumentTypeCharContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String  visitArgumentTypeBool(decafParser.ArgumentTypeBoolContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitLiteralInt(decafParser.LiteralIntContext ctx) {
        //Valor Numerico: NUM
        simpleArgument = true;
        type = "int";
        nucleoCondicional = ctx.NUM().getText();
        return ctx.NUM().getText();
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitLiteralChar(decafParser.LiteralCharContext ctx) {
        //Valor String: CHAR
        simpleArgument = true;
        type  = "char";
        String retorno = ctx.CHAR().getText();
        nucleoArgumento = retorno;
        nucleoCondicional = retorno;
        return retorno;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String  visitLiteralTrue(decafParser.LiteralTrueContext ctx) {
        //Valor booleano: Verdadero
        simpleArgument = true;
        type = "boolean";
        nucleoArgumento = "true";
        nucleoCondicional = "1";
        return "true";
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String  visitLiteralFalse(decafParser.LiteralFalseContext ctx) {
        //Valor booleano: Falso
        simpleArgument = true;
        type = "boolean";
        nucleoArgumento = "false";
        nucleoCondicional = "0";
        return "false";

    }

    public boolean revisarExistencia (String id){
        //verificar que el metodo, lista, struct o symbolo exista en la tabla de simbolos
        for(SyTable st: verificadorAmbitos){
            for(Tuplas t: st.getTablaDeSimbolos()){
                if(t.getNombre().equals(id)){
                    objeto = t.getElemento();
                    type.equals(objeto.getType());

                    return true;
                }

            }
        }
        return  false;
    }

    public void insertarError(String error){
        if(!listaDeErrores.contains(error)){
            listaDeErrores.add(error);
        }

    }

    public void searchInStack (Stack<SyTable> stack, String buscado){
        type = "null";
        boolean terminacion = true;
        for(SyTable tabla: stack){
            if(terminacion){
                for (Tuplas tupla: tabla.getTablaDeSimbolos()){
                    if(tupla.getNombre().equals(buscado)){
                        type = tupla.getElemento().getType();
                        break;
                    }
                }
            }

        }
    }



    public ArrayList<String> getListaDeErrores(){
        return listaDeErrores;
    }



}
