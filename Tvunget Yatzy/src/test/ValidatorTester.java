package test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import validering.Validator;

public class ValidatorTester {

	
	public Validator validator = new Validator();
	
	@Test
	public void brukernavnFalse() {
		assertFalse(validator.brukernavnSjekk("E"));
	}
	
	@Test
	public void brukernavnTrue() {
		assertTrue(validator.brukernavnSjekk("Øystein2 "));
	}
	
	@Test
	public void mobilFalse() {
		assertFalse(validator.mobilSjekk("45"));
	}
	
	@Test
	public void mobilTrue() {
		assertTrue(validator.mobilSjekk("45459856"));
	}
	
	@Test
	public void passordFalse() {
		assertFalse(validator.passordSjekk("ayy"));
	}
	
	@Test
	public void passordTrue() {
		assertTrue(validator.passordSjekk("ayylmao23-?=[]56"));
	}
	
	@Test
	public void epostFalse() {
		
		assertFalse(validator.epostSjekk("?@gmail.com"));
	}
	
	@Test
	public void epostTrue() {
		
		assertTrue(validator.epostSjekk("ayy@yahoo.com"));
	}
}
