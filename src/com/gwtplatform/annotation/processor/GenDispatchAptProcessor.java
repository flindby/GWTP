/**
 * Copyright 2010 Gwt-Platform
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
package com.gwtplatform.annotation.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gwtplatform.annotation.GenDispatch;
import com.gwtplatform.annotation.In;
import com.gwtplatform.annotation.Out;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.FieldDeclaration;

/**
 * If you type:
 * 
 * <pre>
 * <code> 
 * {@literal}@GenDispatch
 * public class RetrieveFoo {
 *   @In(1) Key<Foo> fooKey;
 *   @Out(1) Foo foo;
 * }
 * </code>
 * </pre>
 * 
 * gwt-platform will generate two classes, RetrieveFooAction and
 * RetrieveFooResult.
 * <p/>
 * RetrieveFooAction will have fields, getters, and a constructor for fooKey,
 * plus equals, hashCode, toString etc, for it to function correctly as an Action.
 * <p/>
 * RetrieveFooAction will have fields, getters, and a constructor for fooKey,
 * plus equals, hashCode, toString etc, for it to function correctly as an Action.
 * <p/>
 * Notes:
 * <p/>
 * There is no naming requirement for your class name. It will be appended with
 * Action and Result
 * <p/>
 * {@literal}@GenDispatch takes 3 optional parameters
 * <li>isSecured: defaults to true.
 * <li>extraActionInterfaces: extra interfaces that the Action class will implement
 * <li>extraResultInterfaces: extra interfaces that the Result class will implement
 * <p/>
 * Be aware that the interface defined by extraActionInterfaces and extraResultInterfaces, 
 * must be able to be autogenerated using the field definitions.   
 * 
 * @author Brendan Doherty (All original code, concept attributed to Stephen
 *         Haberman)
 */

public class GenDispatchAptProcessor implements AnnotationProcessor {

  private AnnotationProcessorEnvironment env;
  private final AnnotationTypeDeclaration genDispatchDecl;

  public GenDispatchAptProcessor(AnnotationProcessorEnvironment env) {
    this.env = env;
    this.genDispatchDecl = (AnnotationTypeDeclaration) env
        .getTypeDeclaration(GenDispatch.class.getName());
  }

  @Override
  public void process() {

    for (Declaration decl : env.getDeclarationsAnnotatedWith(genDispatchDecl)) {

      ClassDeclaration classDecl = (ClassDeclaration) decl;
      GenDispatch genDispatch = classDecl.getAnnotation(GenDispatch.class);
      
      generateAction(classDecl, genDispatch.isSecure(), genDispatch.extraActionInterfaces());
      generateResult(classDecl, genDispatch.extraResultInterfaces());

    }
  }

  void generateAction(ClassDeclaration classDecl, boolean isSecure, String extraActionInterfaces) {
    PrintWriter out = null;
    try {
      out = env.getFiler().createSourceFile(
          classDecl.getQualifiedName() + "Action");
      AnnotationHelper helper = new AnnotationHelper(out);
      String name = classDecl.getSimpleName();
      
      SortedMap<Integer, FieldDeclaration> fieldsMap = new TreeMap<Integer, FieldDeclaration>();
      for (FieldDeclaration fieldDecl : classDecl.getFields()) {
        In order = fieldDecl.getAnnotation(In.class);
        if (order != null) {
          fieldsMap.put(order.value(), fieldDecl);
        }
      }
      
      out.println("package " + classDecl.getPackage() + ";");
      out.println();
      out.println("import com.gwtplatform.dispatch.shared.Action;");
      out.println();
      out.println("import javax.annotation.Generated;");
      out.println();
      out
          .println("@Generated(value = \"com.gwtplatform.annotation.processor.GenDispatchAptProcessor\", date = \""
              + (new Date()).toString() + "\")");

      out.print("public class ");
      out.print(name);
      out.print("Action implements Action<");
      out.print(name);
      out.print("Result>");
      if(!extraActionInterfaces.isEmpty()) {
        out.print(", ");
        out.print(extraActionInterfaces);
      }

      out.println(" { ");

      helper.generateFields(fieldsMap.values());

      if(fieldsMap.size() > 0) {
        out.println("  protected " + name + "Action() { }");
        out.println();
      }

      out.print("  public " + name + "Action(");
      helper.generateFieldList(fieldsMap.values(), true, false);
      out.println(") { ");
      for (FieldDeclaration fieldDecl : fieldsMap.values()) {
        out.println("    this." + fieldDecl.getSimpleName() + " = "
            + fieldDecl.getSimpleName() + ";");
      }
      out.println("  }");
      out.println();

      helper.generateAccessors(fieldsMap.values());

      out.println("  @Override");
      out.println("  public String getServiceName() {");
      out.println("    return Action.DEFAULT_SERVICE_NAME + \"" + name + "\";");
      out.println("  }");
      out.println();

      out.println("  @Override");
      out.println("  public boolean isSecured() {");
      out.println("    return " + isSecure + ";");
      out.println("  }");
      out.println();

      helper.generateEquals(name + "Action", fieldsMap.values());

      helper.generateHashCode(fieldsMap.values());

      helper.generateToString(name + "Action", fieldsMap.values());
      
      out.println("}");
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (out != null) {
        out.close();
      }
    }

  }

  void generateResult(ClassDeclaration classDecl, String extraResultInterfaces) {

    PrintWriter out = null;
    try {
      out = env.getFiler().createSourceFile(
          classDecl.getQualifiedName() + "Result");
      AnnotationHelper helper = new AnnotationHelper(out);
      String name = classDecl.getSimpleName();

      SortedMap<Integer, FieldDeclaration> fieldsMap = new TreeMap<Integer, FieldDeclaration>();
      for (FieldDeclaration fieldDecl : classDecl.getFields()) {
        Out order = fieldDecl.getAnnotation(Out.class);
        if (order != null) {
          fieldsMap.put(order.value(), fieldDecl);
        }
      }

      out.println("package " + classDecl.getPackage() + ";");
      out.println();
      out.println("import com.gwtplatform.dispatch.shared.Result;");
      out.println();
      out.println("import javax.annotation.Generated;");
      out.println();
      out
          .println("@Generated(value = \"com.gwtplatform.annotation.processor.GenDispatchAptProcessor\", date = \""
              + (new Date()).toString() + "\")");

      out.print("public class ");
      out.print(name);
      out.print("Result implements Result");
      if(!extraResultInterfaces.isEmpty()) {
        out.print(",");
        out.print(extraResultInterfaces);
      }

      out.print(" { ");
      out.println();

      helper.generateFields(fieldsMap.values());

      if (fieldsMap.size() > 0) {
        out.println("  protected " + name + "Result() { }");
        out.println();
      }

      out.print("  public " + name + "Result(");
      helper.generateFieldList(fieldsMap.values(), true, false);
      out.println(") { ");
      for (FieldDeclaration fieldDecl : fieldsMap.values()) {
        out.println("    this." + fieldDecl.getSimpleName() + " = "
            + fieldDecl.getSimpleName() + ";");
      }
      out.println("  }");
      out.println();

      helper.generateAccessors(fieldsMap.values());

      helper.generateEquals(name + "Result", fieldsMap.values());

      helper.generateHashCode(fieldsMap.values());

      helper.generateToString(name + "Result", fieldsMap.values());

      out.println("}");

    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

}
