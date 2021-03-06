package org.eclipse.emf.ecore.xcore.tests.interpreter;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.junit.evaluation.AbstractXbaseEvaluationTest;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreInterpreterXbaseIntegrationTest extends AbstractXbaseEvaluationTest
{
	
	@Inject
	private ParseHelper<XPackage> parser;
	
	@Inject
	private ValidationTestHelper validator;

	protected Object invokeXbaseExpression(String expression) throws Exception {
		XPackage pack = parser.parse("package foo class Bar { op Object foo() { "+expression+" } }");
		validator.assertNoErrors(pack);
		EPackage ePack = (EPackage) pack.eResource().getContents().get(2);
		EClass barClass = (EClass) ePack.getEClassifier("Bar");
		EObject eObject = ePack.getEFactoryInstance().create(barClass);
		return eObject.eInvoke(barClass.getEOperations().get(0), new BasicEList<Object>());
	}
	
	@Override
	public void testShortCircuitBooleanExpression_03() throws Exception
	{
	}
	@Override
	public void testShortCircuitBooleanExpression_04() throws Exception
	{
	}
	@Override
	public void testFunctionConversion_00() throws Exception
	{
	}
	@Override
	public void testMapConstruction_01() throws Exception
	{
	}
}
