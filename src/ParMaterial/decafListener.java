// Generated from decaf.g4 by ANTLR 4.7.1
package ParMaterial;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link decafParser}.
 */
public interface decafListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code initProgram}
	 * labeled alternative in {@link decafParser#program}.
	 * @param ctx the parse tree
	 */
	void enterInitProgram(decafParser.InitProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initProgram}
	 * labeled alternative in {@link decafParser#program}.
	 * @param ctx the parse tree
	 */
	void exitInitProgram(decafParser.InitProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaraionStruct}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaraionStruct(decafParser.DeclaraionStructContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaraionStruct}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaraionStruct(decafParser.DeclaraionStructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarationVar}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationVar(decafParser.DeclarationVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarationVar}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationVar(decafParser.DeclarationVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarationMethod}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationMethod(decafParser.DeclarationMethodContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarationMethod}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationMethod(decafParser.DeclarationMethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notValuedVar}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNotValuedVar(decafParser.NotValuedVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notValuedVar}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNotValuedVar(decafParser.NotValuedVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notValuedList}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNotValuedList(decafParser.NotValuedListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notValuedList}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNotValuedList(decafParser.NotValuedListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structDecl}
	 * labeled alternative in {@link decafParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDecl(decafParser.StructDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structDecl}
	 * labeled alternative in {@link decafParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDecl(decafParser.StructDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterIntVar(decafParser.IntVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitIntVar(decafParser.IntVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterCharVar(decafParser.CharVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitCharVar(decafParser.CharVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterBoolVar(decafParser.BoolVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitBoolVar(decafParser.BoolVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterStructVar(decafParser.StructVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitStructVar(decafParser.StructVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structDeclVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclVar(decafParser.StructDeclVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structDeclVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclVar(decafParser.StructDeclVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVoidVar(decafParser.VoidVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVoidVar(decafParser.VoidVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodDecl}
	 * labeled alternative in {@link decafParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(decafParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodDecl}
	 * labeled alternative in {@link decafParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(decafParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterIntMeth(decafParser.IntMethContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitIntMeth(decafParser.IntMethContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterCharMeth(decafParser.CharMethContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitCharMeth(decafParser.CharMethContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterBoolMeth(decafParser.BoolMethContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitBoolMeth(decafParser.BoolMethContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterVoidMeth(decafParser.VoidMethContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitVoidMeth(decafParser.VoidMethContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParamType(decafParser.ParamTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParamType(decafParser.ParamTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramListType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParamListType(decafParser.ParamListTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramListType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParamListType(decafParser.ParamListTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterIntParam(decafParser.IntParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitIntParam(decafParser.IntParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterCharParam(decafParser.CharParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitCharParam(decafParser.CharParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterBoolParam(decafParser.BoolParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitBoolParam(decafParser.BoolParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockDecl}
	 * labeled alternative in {@link decafParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlockDecl(decafParser.BlockDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockDecl}
	 * labeled alternative in {@link decafParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlockDecl(decafParser.BlockDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclHelp(decafParser.VarDeclHelpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclHelp(decafParser.VarDeclHelpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 */
	void enterStmHelp(decafParser.StmHelpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 */
	void exitStmHelp(decafParser.StmHelpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfDeclStm(decafParser.IfDeclStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfDeclStm(decafParser.IfDeclStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseDeclStm(decafParser.IfElseDeclStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseDeclStm(decafParser.IfElseDeclStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileDeclStm(decafParser.WhileDeclStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileDeclStm(decafParser.WhileDeclStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStm(decafParser.ReturnStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStm(decafParser.ReturnStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnTypeStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnTypeStm(decafParser.ReturnTypeStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnTypeStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnTypeStm(decafParser.ReturnTypeStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callMethodStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCallMethodStm(decafParser.CallMethodStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callMethodStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCallMethodStm(decafParser.CallMethodStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStm(decafParser.BlockStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStm(decafParser.BlockStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStm(decafParser.AssignStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStm(decafParser.AssignStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code endStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEndStm(decafParser.EndStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code endStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEndStm(decafParser.EndStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStm(decafParser.ExpressionStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStm(decafParser.ExpressionStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterSimpleLoc(decafParser.SimpleLocContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitSimpleLoc(decafParser.SimpleLocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterListLoc(decafParser.ListLocContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitListLoc(decafParser.ListLocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterSimpleLocExpr(decafParser.SimpleLocExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitSimpleLocExpr(decafParser.SimpleLocExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterListLocExpr(decafParser.ListLocExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitListLocExpr(decafParser.ListLocExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelOpExp(decafParser.RelOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelOpExp(decafParser.RelOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dashExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDashExp(decafParser.DashExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dashExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDashExp(decafParser.DashExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExp(decafParser.LiteralExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExp(decafParser.LiteralExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCondOpExp(decafParser.CondOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCondOpExp(decafParser.CondOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code locationExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLocationExp(decafParser.LocationExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code locationExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLocationExp(decafParser.LocationExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodExp(decafParser.MethodExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodExp(decafParser.MethodExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExp(decafParser.ParentExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExp(decafParser.ParentExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqOpExp(decafParser.EqOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqOpExp(decafParser.EqOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExp(decafParser.NotExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExp(decafParser.NotExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code firstArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFirstArithOpExp(decafParser.FirstArithOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code firstArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFirstArithOpExp(decafParser.FirstArithOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code secondArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSecondArithOpExp(decafParser.SecondArithOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code secondArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSecondArithOpExp(decafParser.SecondArithOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallDecl}
	 * labeled alternative in {@link decafParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallDecl(decafParser.MethodCallDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallDecl}
	 * labeled alternative in {@link decafParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallDecl(decafParser.MethodCallDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionArg}
	 * labeled alternative in {@link decafParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArg(decafParser.ExpressionArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionArg}
	 * labeled alternative in {@link decafParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArg(decafParser.ExpressionArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argumentTypeInt}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void enterArgumentTypeInt(decafParser.ArgumentTypeIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argumentTypeInt}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void exitArgumentTypeInt(decafParser.ArgumentTypeIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argumentTypeChar}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void enterArgumentTypeChar(decafParser.ArgumentTypeCharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argumentTypeChar}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void exitArgumentTypeChar(decafParser.ArgumentTypeCharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argumentTypeBool}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void enterArgumentTypeBool(decafParser.ArgumentTypeBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argumentTypeBool}
	 * labeled alternative in {@link decafParser#argumentType}.
	 * @param ctx the parse tree
	 */
	void exitArgumentTypeBool(decafParser.ArgumentTypeBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalInt}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralInt(decafParser.LiteralIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalInt}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralInt(decafParser.LiteralIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalChar}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralChar(decafParser.LiteralCharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalChar}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralChar(decafParser.LiteralCharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalTrue}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralTrue(decafParser.LiteralTrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalTrue}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralTrue(decafParser.LiteralTrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalFalse}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralFalse(decafParser.LiteralFalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalFalse}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralFalse(decafParser.LiteralFalseContext ctx);
}