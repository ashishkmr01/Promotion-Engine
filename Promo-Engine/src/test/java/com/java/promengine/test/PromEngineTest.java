package com.java.promengine.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.promengine.PromEngine;
/**
 * To Test Promo Engine
 * @author Ashish
 *
 */
public class PromEngineTest {

	/**
	 * Test getCartCheckoutAmount method testScenarioA.
	 */
	@Test
	public void testScenarioA() {
		PromEngine engine = new PromEngine();
		char[] skuIds = { 'A', 'B', 'C' };
		int result = engine.getCartCheckoutAmount(skuIds);
		System.out.println(result);
		assertEquals(result, 100);

	}
	
	/**
	 * Test getCartCheckoutAmount method testScenarioB.
	 */
	@Test
	public void testScenarioB() {
		PromEngine engine = new PromEngine();
		char[] skuIds = { 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'C' };
		int result = engine.getCartCheckoutAmount(skuIds);
		System.out.println(result);
		assertEquals(result, 370);

	}

	/**
	 * Test getCartCheckoutAmount method testScenarioC.
	 */
	@Test
	public void testScenarioC() {
		PromEngine engine = new PromEngine();
		char[] skuIds = { 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'C', 'D' };
		int result = engine.getCartCheckoutAmount(skuIds);
		System.out.println(result);
		assertEquals(result, 280);

	}

}
