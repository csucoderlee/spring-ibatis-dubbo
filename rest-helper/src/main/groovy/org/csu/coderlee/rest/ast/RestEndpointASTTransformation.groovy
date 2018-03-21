package org.csu.coderlee.rest.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.AttributeExpression
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.ConstructorCallExpression
import org.codehaus.groovy.ast.expr.DeclarationExpression
import org.codehaus.groovy.ast.expr.FieldExpression
import org.codehaus.groovy.ast.expr.ListExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.TupleExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.CatchStatement
import org.codehaus.groovy.ast.stmt.EmptyStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.stmt.TryCatchStatement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.Token
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import java.lang.reflect.Modifier

/**
 * @author by bixi.lx
 * @created on 2018 03 21 14:08
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class RestEndpointASTTransformation implements ASTTransformation {

    static final ClassNode controllerClassNode = new ClassNode(Controller)
    static final ClassNode requestMappingClassNode = new ClassNode(RequestMapping)
    static final ClassNode requestMethodClassNode = new ClassNode(RequestMethod)
    static final ClassNode requestParamClassNode = new ClassNode(RequestParam)
    static final ClassNode requestBodyClassNode = new ClassNode(RequestBody)
    static final ClassNode responseBodyClassNode = new ClassNode(ResponseBody)
    static final ClassNode autoWiredClassNode = new ClassNode(Autowired)

    static final ClassNode stringClassNode = new ClassNode(String)
    static final ClassNode dateClassNode = new ClassNode(Date)
    static final ClassNode objectClassNode = new ClassNode(Object)
    static final ClassNode mapClassNode = new ClassNode(Map)

    static final ClassNode exceptionClassNode = new ClassNode(Exception)

    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        ModuleNode moduleNode = sourceUnit.getAST()

        sourceUnit.getAST()?.getClasses().findAll {
            it.getAnnotations(restEndPointClassNode)
        }.each {
            String controllerClassName = it.getNameWithoutPackage()
            String controllerPackageName = it.getPackageName()

            AnnotationNode controllerAnnotationNode = new AnnotationNode(controllerClassNode)
            it.addAnnotation(controllerAnnotationNode)

            AnnotationNode restEndPointAnnotationNode = it.getAnnotations(restEndPointClassNode).get(0)
            def valueExpr = restEndPointAnnotationNode.getMember("value")
            if (valueExpr) {
                AnnotationNode requestMappingAnnotationNode = new AnnotationNode(requestMappingClassNode)
                requestMappingAnnotationNode.setMember("value", valueExpr)
                it.addAnnotation(requestMappingAnnotationNode)
            }

            def delegateFieldNode = it.getField("delegate")
            delegateFieldNode.addAnnotation(new AnnotationNode(autoWiredClassNode))

            if (delegateFieldNode) {

                FieldNode loggerFieldNode = it.addField("logger",
                        Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL,
                        commonsLogClassNode,
                        new MethodCallExpression(new ClassExpression(commonsLogFactoryClassNode),
                                "getLog",
                                new ClassExpression(it)))


                ClassNode delegateClassNode = delegateFieldNode.getType()

                delegateClassNode.getMethods().each { m ->
                    String methodName = m.getName()
                    Parameter[] parameters = m.getParameters()


                    int modifiers = m.getModifiers()

                    if (!(modifiers & Modifier.PUBLIC) || (modifiers & Modifier.STATIC))
                        return

                    modifiers ^= Modifier.ABSTRACT

                    MethodNode controllerMethodNode = new MethodNode(methodName, modifiers, m.getReturnType(), parameters, m.getExceptions(), null)
                    AnnotationNode requestMappingAnnotationNode = new AnnotationNode(requestMappingClassNode)
                    requestMappingAnnotationNode.setMember("value", new ListExpression([new ConstantExpression("/" + methodName)]))
                    requestMappingAnnotationNode.setMember("produces", new ListExpression([new ConstantExpression("application/json")]))
                    requestMappingAnnotationNode.setMember("consumes", new ListExpression([new ConstantExpression("application/json"), new ConstantExpression("application/json; charset=UTF-8;")]))

                    BlockStatement mainBlock = new BlockStatement()
                    Statement startTime = new ExpressionStatement(new DeclarationExpression(
                            new VariableExpression("startTime"),
                            Token.newSymbol("=", -1, -1),
                            new MethodCallExpression(new ConstructorCallExpression(new ClassNode(Date), new ArgumentListExpression()), "getTime", new ArgumentListExpression())))
                    mainBlock.addStatement(startTime)
                    if (allBasicType(parameters)) {
                        requestMappingAnnotationNode.setMember("method", new ListExpression([new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("GET")),
                                                                                             new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("POST"))]))
                        controllerMethodNode.setParameters(annotateParams(parameters))

                        Statement stmt = new ExpressionStatement(new MethodCallExpression(new FieldExpression(delegateFieldNode),
                                methodName,
                                new ArgumentListExpression(parameters)))
                        mainBlock.addStatement(stmt)


                        controllerMethodNode.setCode(mainBlock)
                    } else if (singleMap(parameters)) {
                        requestMappingAnnotationNode.setMember("method", new ListExpression([new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("GET")),
                                                                                             new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("POST"))]))
                        Parameter param = parameters[0]
                        Parameter annotatedParameter = new Parameter(param.getType(), param.getName())
                        annotatedParameter.addAnnotation(new AnnotationNode(requestBodyClassNode))

                        Parameter[] params = new Parameter[1]
                        params[0] = annotatedParameter
                        controllerMethodNode.setParameters(params)
                        controllerMethodNode.setReturnType(mapClassNode)

                        TupleExpression logMethodCallParams = new TupleExpression(new FieldExpression(loggerFieldNode), new ConstantExpression(m.toString()))
                        parameters.each { p ->
                            logMethodCallParams.addExpression(new VariableExpression(p))
                        }

                        mainBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'logMethod',
                                logMethodCallParams)))
                        mainBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(DataSourceContextHolderClassNode),
                                'clear',
                                new ArgumentListExpression())))

                        BlockStatement tryBlock = new BlockStatement()
                        tryBlock.addStatement(new ExpressionStatement(new DeclarationExpression(new VariableExpression('data'),
                                Token.newSymbol('=', -1, -1),
                                new MethodCallExpression(new FieldExpression(delegateFieldNode),
                                        methodName,
                                        new ArgumentListExpression(parameters)))))
                        tryBlock.addStatement(new ReturnStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'handleResult',
                                new TupleExpression(new FieldExpression(loggerFieldNode), new VariableExpression('data')))))

                        TryCatchStatement tryCatch = new TryCatchStatement(tryBlock, new EmptyStatement())
                        Parameter exception = new Parameter(exceptionClassNode, "ex")

                        Statement catchCode = new ReturnStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'handleException',
                                new TupleExpression(new FieldExpression(loggerFieldNode), new VariableExpression('ex'))))

                        tryCatch.addCatch(new CatchStatement(exception, catchCode))

                        Statement endTime = new ExpressionStatement(new DeclarationExpression(
                                new VariableExpression("endTime"),
                                Token.newSymbol("=", -1, -1),
                                new MethodCallExpression(new ConstructorCallExpression(new ClassNode(Date), new ArgumentListExpression()), "getTime", new ArgumentListExpression())))

                        TupleExpression logMethodTimeCallParams = new TupleExpression(new FieldExpression(loggerFieldNode), new ConstantExpression(m.toString()),
                                new VariableExpression("startTime"), new VariableExpression("endTime"))

                        Statement finallyBlock = new BlockStatement()
                        finallyBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(DataSourceContextHolderClassNode),
                                'clear',
                                new ArgumentListExpression())))
                        finallyBlock.addStatement(endTime)
                        finallyBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'logMethodTime',
                                logMethodTimeCallParams)))

                        tryCatch.setFinallyStatement(finallyBlock)
                        mainBlock.addStatement(tryCatch)

                        controllerMethodNode.setCode(mainBlock)

                    } else {
                        requestMappingAnnotationNode.setMember("method", new ListExpression([new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("GET")),
                                                                                             new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("POST"))]))
                        controllerMethodNode.setParameters(wrapParametersInNewClass(controllerPackageName, controllerClassName, methodName, parameters, moduleNode))

                        def args = parameters.collect {
                            new AttributeExpression(new VariableExpression("all"), new ConstantExpression(it.getName()))
                        }

                        Statement stmt = new ExpressionStatement(new MethodCallExpression(new FieldExpression(delegateFieldNode),
                                methodName,
                                new ArgumentListExpression(args)))
                        mainBlock.addStatement(stmt)
                        controllerMethodNode.setCode(mainBlock)
                    }

                    controllerMethodNode.addAnnotation(requestMappingAnnotationNode)
                    controllerMethodNode.addAnnotation(new AnnotationNode(responseBodyClassNode))
                    it.addMethod(controllerMethodNode)
                }



                delegateClassNode.getMethods().each { m ->
                    String methodName = m.getName()
                    Parameter[] parameters = m.getParameters()


                    int modifiers = m.getModifiers()

                    if (!(modifiers & Modifier.PUBLIC) || (modifiers & Modifier.STATIC))
                        return

                    modifiers ^= Modifier.ABSTRACT

                    MethodNode controllerMethodNode = new MethodNode(methodName + "1", modifiers, m.getReturnType(), parameters, m.getExceptions(), null)
                    AnnotationNode requestMappingAnnotationNode = new AnnotationNode(requestMappingClassNode)
                    requestMappingAnnotationNode.setMember("value", new ListExpression([new ConstantExpression("/" + methodName)]))
                    requestMappingAnnotationNode.setMember("produces", new ListExpression([new ConstantExpression("application/json")]))
                    requestMappingAnnotationNode.setMember("consumes", new ListExpression([new ConstantExpression("!application/json"), new ConstantExpression("!application/json; charset=UTF-8;")]))

                    BlockStatement mainBlock = new BlockStatement()
                    Statement startTime = new ExpressionStatement(new DeclarationExpression(
                            new VariableExpression("startTime"),
                            Token.newSymbol("=", -1, -1),
                            new MethodCallExpression(new ConstructorCallExpression(new ClassNode(Date), new ArgumentListExpression()), "getTime", new ArgumentListExpression())))
                    mainBlock.addStatement(startTime)


                    if (singleMap(parameters)) {
                        requestMappingAnnotationNode.setMember("method", new ListExpression([new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("GET")),
                                                                                             new AttributeExpression(new ClassExpression(requestMethodClassNode), new ConstantExpression("POST"))]))
                        Parameter param = parameters[0]
                        Parameter annotatedParameter = new Parameter(param.getType(), param.getName())
                        annotatedParameter.addAnnotation(new AnnotationNode(requestParamClassNode))

                        Parameter[] params = new Parameter[1]
                        params[0] = annotatedParameter
                        controllerMethodNode.setParameters(params)
                        controllerMethodNode.setReturnType(mapClassNode)

                        TupleExpression logMethodCallParams = new TupleExpression(new FieldExpression(loggerFieldNode), new ConstantExpression(m.toString()))
                        parameters.each { p ->
                            logMethodCallParams.addExpression(new VariableExpression(p))
                        }

                        mainBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'logMethod',
                                logMethodCallParams)))
                        mainBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(DataSourceContextHolderClassNode),
                                'clear',
                                new ArgumentListExpression())))

                        BlockStatement tryBlock = new BlockStatement()
                        tryBlock.addStatement(new ExpressionStatement(new DeclarationExpression(new VariableExpression('data'),
                                Token.newSymbol('=', -1, -1),
                                new MethodCallExpression(new FieldExpression(delegateFieldNode),
                                        methodName,
                                        new ArgumentListExpression(parameters)))))
                        tryBlock.addStatement(new ReturnStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'handleResult',
                                new TupleExpression(new FieldExpression(loggerFieldNode), new VariableExpression('data')))))

                        TryCatchStatement tryCatch = new TryCatchStatement(tryBlock, new EmptyStatement())
                        Parameter exception = new Parameter(exceptionClassNode, "ex")

                        Statement catchCode = new ReturnStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'handleException',
                                new TupleExpression(new FieldExpression(loggerFieldNode), new VariableExpression('ex'))))

                        tryCatch.addCatch(new CatchStatement(exception, catchCode))
                        Statement endTime = new ExpressionStatement(new DeclarationExpression(
                                new VariableExpression("endTime"),
                                Token.newSymbol("=", -1, -1),
                                new MethodCallExpression(new ConstructorCallExpression(new ClassNode(Date), new ArgumentListExpression()), "getTime", new ArgumentListExpression())))

                        TupleExpression logMethodTimeCallParams = new TupleExpression(new FieldExpression(loggerFieldNode), new ConstantExpression(m.toString()),
                                new VariableExpression("startTime"), new VariableExpression("endTime"))

                        Statement finallyBlock = new BlockStatement()
                        finallyBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(DataSourceContextHolderClassNode),
                                'clear',
                                new ArgumentListExpression())))
                        finallyBlock.addStatement(endTime)
                        finallyBlock.addStatement(new ExpressionStatement(new MethodCallExpression(new ClassExpression(astHelperClassNode),
                                'logMethodTime',
                                logMethodTimeCallParams)))

                        tryCatch.setFinallyStatement(finallyBlock)

                        mainBlock.addStatement(tryCatch)

                        controllerMethodNode.setCode(mainBlock)
                        controllerMethodNode.addAnnotation(requestMappingAnnotationNode)
                        controllerMethodNode.addAnnotation(new AnnotationNode(responseBodyClassNode))
                        it.addMethod(controllerMethodNode)

                    }

                }
            }
        }

    }
}
