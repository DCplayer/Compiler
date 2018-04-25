// Generated from decaf.g4 by ANTLR 4.7.1
package ParMaterial;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link decafParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface decafVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code initProgram}
	 * labeled alternative in {@link decafParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitProgram(decafParser.InitProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaraionStruct}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaraionStruct(decafParser.DeclaraionStructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationVar}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationVar(decafParser.DeclarationVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationMethod}
	 * labeled alternative in {@link decafParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationMethod(decafParser.DeclarationMethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notValuedVar}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotValuedVar(decafParser.NotValuedVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notValuedList}
	 * labeled alternative in {@link decafParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotValuedList(decafParser.NotValuedListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structDecl}
	 * labeled alternative in {@link decafParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDecl(decafParser.StructDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVar(decafParser.IntVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharVar(decafParser.CharVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVar(decafParser.BoolVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructVar(decafParser.StructVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structDeclVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclVar(decafParser.StructDeclVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidVar}
	 * labeled alternative in {@link decafParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidVar(decafParser.VoidVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodDecl}
	 * labeled alternative in {@link decafParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(decafParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntMeth(decafParser.IntMethContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharMeth(decafParser.CharMethContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolMeth(decafParser.BoolMethContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidMeth}
	 * labeled alternative in {@link decafParser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidMeth(decafParser.VoidMethContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamType(decafParser.ParamTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramListType}
	 * labeled alternative in {@link decafParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamListType(decafParser.ParamListTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntParam(decafParser.IntParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharParam(decafParser.CharParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolParam}
	 * labeled alternative in {@link decafParser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolParam(decafParser.BoolParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockDecl}
	 * labeled alternative in {@link decafParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockDecl(decafParser.BlockDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclHelp(decafParser.VarDeclHelpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmHelp}
	 * labeled alternative in {@link decafParser#blockHelp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmHelp(decafParser.StmHelpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfDeclStm(decafParser.IfDeclStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseDeclStm(decafParser.IfElseDeclStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileDeclStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileDeclStm(decafParser.WhileDeclStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStm(decafParser.ReturnStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnTypeStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTypeStm(decafParser.ReturnTypeStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callMethodStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMethodStm(decafParser.CallMethodStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStm(decafParser.BlockStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStm(decafParser.AssignStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code endStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndStm(decafParser.EndStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionStm}
	 * labeled alternative in {@link decafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStm(decafParser.ExpressionStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleLoc(decafParser.SimpleLocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listLoc}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLoc(decafParser.ListLocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleLocExpr(decafParser.SimpleLocExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listLocExpr}
	 * labeled alternative in {@link decafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLocExpr(decafParser.ListLocExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOpExp(decafParser.RelOpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dashExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDashExp(decafParser.DashExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExp(decafParser.LiteralExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondOpExp(decafParser.CondOpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code locationExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationExp(decafParser.LocationExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodExp(decafParser.MethodExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExp(decafParser.ParentExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOpExp(decafParser.EqOpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(decafParser.NotExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code firstArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFirstArithOpExp(decafParser.FirstArithOpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code secondArithOpExp}
	 * labeled alternative in {@link decafParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecondArithOpExp(decafParser.SecondArithOpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodCallDecl}
	 * labeled alternative in {@link decafParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallDecl(decafParser.MethodCallDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionArg}
	 * labeled alternative in {@link decafParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArg(decafParser.ExpressionArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalInt}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralInt(decafParser.LiteralIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalChar}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralChar(decafParser.LiteralCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalTrue}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralTrue(decafParser.LiteralTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalFalse}
	 * labeled alternative in {@link decafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralFalse(decafParser.LiteralFalseContext ctx);
}