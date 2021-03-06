package org.eclipse.emf.ecore.xcore.tests.interpreter;

import com.google.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreInterpreterTest {
  @Inject
  private ParseHelper<XPackage> parse;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Test
  public void testInterpretation() throws Exception, InvocationTargetException {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String doStuff(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"Foo says hi to \"+msg");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      Object _eInvoke = foo.eInvoke(_head, _basicEList);
      Assert.assertEquals("Foo says hi to Bar", _eInvoke);
  }
  
  @Test
  public void testInterpretation_2() throws Exception, InvocationTargetException {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String call1(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"call1\"+call2(\"call1\"+msg)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String call2(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"call2\"+msg");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      Object _eInvoke = foo.eInvoke(_head, _basicEList);
      Assert.assertEquals("call1call2call1Bar", _eInvoke);
  }
  
  @Test
  public void testFeatureAccessors() throws Exception, InvocationTargetException {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String value");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void storeValue(String newValue) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("value = newValue");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String fetchValue() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return value");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      foo.eInvoke(_head, _basicEList);
      EList<EOperation> _eOperations_1 = fooClass.getEOperations();
      EOperation _get_1 = _eOperations_1.get(1);
      Object _eInvoke = foo.eInvoke(_get_1, null);
      Assert.assertEquals("Bar", _eInvoke);
  }
  
  @Test
  public void testConversionDelegates() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("type URI wraps org.eclipse.emf.common.util.URI ");
      _builder.newLine();
      _builder.append("create { if (it == null) null else org::eclipse::emf::common::util::URI::createURI(it) } ");
      _builder.newLine();
      _builder.append("convert { it?.toString  }");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("URI");
      final EDataType uriDataType = ((EDataType) _eClassifier);
      final String literal = "http://www.eclipse.org";
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      Object _createFromString = _eFactoryInstance.createFromString(uriDataType, literal);
      final URI uri = ((URI) _createFromString);
      EFactory _eFactoryInstance_1 = ePackage.getEFactoryInstance();
      String _convertToString = _eFactoryInstance_1.convertToString(uriDataType, uri);
      Assert.assertEquals(literal, _convertToString);
  }
  
  @Test
  public void testSettingDelegates() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.append("class Foo");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String name");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String alias get { name}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EStructuralFeature _eStructuralFeature = fooClass.getEStructuralFeature("name");
      foo.eSet(_eStructuralFeature, "Sven");
      EStructuralFeature _eStructuralFeature_1 = fooClass.getEStructuralFeature("alias");
      Object _eGet = foo.eGet(_eStructuralFeature_1);
      Assert.assertEquals("Sven", _eGet);
  }
  
  @Test
  public void testEnum() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.append("enum NodeKind { Singleton Root Intermediate Leaf }");
      _builder.newLine();
      _builder.append("class Node");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers Node parent opposite children");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("contains Node[0..*] children opposite parent");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op boolean hasChildren() { !children.empty }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("transient volatile derived readonly NodeKind nodeKind");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("get");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (hasChildren()) {if (parent == null) {NodeKind::ROOT} else {NodeKind::INTERMEDIATE}}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("else {if (parent == null) {NodeKind::SINGLETON} else {NodeKind::LEAF}}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("NodeKind");
      final EEnum nodeKindEnum = ((EEnum) _eClassifier);
      EClassifier _eClassifier_1 = ePackage.getEClassifier("Node");
      final EClass nodeClass = ((EClass) _eClassifier_1);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(nodeClass);
      final EObject node = _create;
      EEnumLiteral _eEnumLiteral = nodeKindEnum.getEEnumLiteral("Singleton");
      EStructuralFeature _eStructuralFeature = nodeClass.getEStructuralFeature("nodeKind");
      Object _eGet = node.eGet(_eStructuralFeature);
      Assert.assertEquals(_eEnumLiteral, _eGet);
      EFactory _eFactoryInstance_1 = ePackage.getEFactoryInstance();
      EObject _create_1 = _eFactoryInstance_1.create(nodeClass);
      final EObject childNode = _create_1;
      EStructuralFeature _eStructuralFeature_1 = nodeClass.getEStructuralFeature("children");
      Object _eGet_1 = node.eGet(_eStructuralFeature_1);
      ((List) _eGet_1).add(childNode);
      EEnumLiteral _eEnumLiteral_1 = nodeKindEnum.getEEnumLiteral("Root");
      EStructuralFeature _eStructuralFeature_2 = nodeClass.getEStructuralFeature("nodeKind");
      Object _eGet_2 = node.eGet(_eStructuralFeature_2);
      Assert.assertEquals(_eEnumLiteral_1, _eGet_2);
  }
}
