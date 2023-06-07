/*
 * Copyright 2016 noti0na1 (i@notnl.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package LambdaCalculus.lambda;

import java.util.HashSet;
import java.util.Set;

/**
 * An application
 * apply a function with certain argument
 */
public class Application extends Expression {

    private Expression function;

    private Expression argument;

    /**
     * @param function
     * @param argument
     */
    public Application(Expression function, Expression argument) {
        this.function = function;
        this.argument = argument;
    }

    /**
     * @return
     */
    @Override
    public boolean reducible() {
        return this.function.reducible() || this.argument.reducible() || this.function instanceof Function;
    }

    /**
     * @return
     */
    @Override
    public Expression reduce() {
        if (this.function.reducible()) {
            return new Application(this.function.reduce(), this.argument);
        } else if (this.argument.reducible()) {
            return new Application(this.function, this.argument.reduce());
        } else if (this.function instanceof Function) {
            Function fun = (Function) this.getFunction();
            return subst(fun.getBody(), fun.getName(), this.getargument());
        }
        return this;
    }

    /**
     * @return
     */
    @Override
    public Expression deepReduce() {
        Expression app = this.reduce();
        if (app.reducible()) {
            return app.deepReduce();
        }
        return app;
    }

    /**
     * @return
     */
    public Expression getFunction() {
        return function;
    }

    /**
     * @param function
     */
    public void setFunction(Expression function) {
        this.function = function;
    }

    /**
     * @return
     */
    public Expression getargument() {
        return argument;
    }

    /**
     * @param argument
     */
    public void setargument(Expression argument) {
        this.argument = argument;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder apply = new StringBuilder();
        if (this.function instanceof Function) {
            apply.append('(').append(this.function.toString()).append(')');
        } else {
            apply.append(this.function.toString());
        }
        apply.append(' ');
        if (this.argument instanceof Variable) {
            apply.append(this.argument);
        } else {
            apply.append('(').append(this.argument).append(')');
        }
        return apply.toString();
    }

    /**
     * substitute variable in expression with replacement
     *
     * @param expr
     * @param name
     * @param repl
     * @return
     */
    private static Expression subst(Expression expr, Variable name, Expression repl) {
        if (expr instanceof Variable) {
            if (name.equals(expr)) {
                return repl;
            } else {
                return expr;
            }
        } else if (expr instanceof Application) {
            Application app = (Application) expr;
            return new Application(
                    subst(app.getFunction(), name, repl),
                    subst(app.getargument(), name, repl));
        } else if (expr instanceof Function) {
            Function fun = (Function) expr;
            Set<Variable> fvs = freeVariables(repl);
            fvs.add(name);
            Variable newName2 = uniqueName(fun.getName(), fvs);
            Expression newBody = subst(fun.getBody(), fun.getName(), newName2);
            return new Function(newName2, subst(newBody, name, repl));
        }
        // unexpected operation
        return null;
    }

    /**
     * get unique name
     *
     * @param name
     * @param usedNames
     * @return
     */
    private static Variable uniqueName(Variable name, Set<Variable> usedNames) {
        if (usedNames.contains(name)) {
            return uniqueName(new Variable(name.getName() + "'"), usedNames);
        } else {
            return name;
        }
    }

    /**
     * get all free variables
     *
     * @param expr
     * @return
     */
    private static Set<Variable> freeVariables(Expression expr) {
        Set<Variable> vars = null;
        if (expr instanceof Variable) {
            vars = new HashSet<>();
            vars.add((Variable) expr);
        } else if (expr instanceof Application) {
            Application app = (Application) expr;
            vars = freeVariables(app.getFunction());
            vars.addAll(freeVariables(app.getargument()));
        } else if (expr instanceof Function) {
            Function fun = (Function) expr;
            vars = freeVariables(fun.getBody());
            vars.remove(fun.getName());
        }
        return vars;
    }
}
