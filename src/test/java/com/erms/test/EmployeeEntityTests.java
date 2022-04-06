package com.erms.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.constants.ERMConstants;
import com.erms.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class EmployeeEntityTests {

	private Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = createMockEmployeeObject();
	}

	private Employee createMockEmployeeObject() {
		return ERMConstants.mockEmployee1;
	}

	@Test
	public void shouldCheckGetNameReturnNotNull() {
		Assert.assertNotNull(employee.getName());
	}

	@Test
	public void shouldCheckGetDOBReturnNotNull() {
		Assert.assertNotNull(employee.getDob());
	}

	@Test
	public void shouldCheckGetIdReturnNotNull() {
		Assert.assertNotNull(employee.getId());
	}

	@Test
	public void shouldCheckGetRatingReturnNotNull() {
		Assert.assertNotNull(employee.getRating());
	}

	@Test
	public void shouldNameEqualsInEntity() {
		String expected = ERMConstants.MOCK_NAME;
		employee.setName(expected);
		Assert.assertEquals(employee.getName(), expected);
	}

	@Test
	public void shouldIdEqualsInEntity() {
		int expected = ERMConstants.MOCK_ID;
		employee.setId(expected);
		Assert.assertEquals(employee.getId(), expected);
	}

	@Test
	public void shouldDobEqualsInEntity() {
		String expected = ERMConstants.MOCK_DOB;
		employee.setDob(expected);
		Assert.assertEquals(employee.getDob(), expected);
	}

	@Test
	public void shouldRatingEqualsInEntity() {
		employee.setRating(ERMConstants.MOCK_RATING);
		Assert.assertEquals(employee.getRating(), ERMConstants.MOCK_RATING);
	}

	@Test
	public void shouldReturnTrueByPassingSameObject() {
		Employee employee2 = ERMConstants.mockEmployee1;
		boolean isValid = employee.equals(employee2);
		Assert.assertTrue(isValid);
	}

	@Test
	public void shouldReturnFalseByPassingDifferentObject() {
		Employee employee2 = ERMConstants.mockEmployee2;
		boolean isValid = employee.equals(employee2);
		Assert.assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseByPassingNullObject() {
		boolean isValid = employee.equals(null);
		Assert.assertFalse(isValid);
	}

	@Test
	public void shouldReturnTrueByPassingCurrentClassObject() {
		boolean isValid = employee.equals(this.employee);
		Assert.assertTrue(isValid);
	}

	@Test
	public void shouldReturnFalseByPassingIlleagalObject() {
		boolean isValid = employee.equals(this);
		Assert.assertFalse(isValid);
	}

	@Test
	public void shouldEqualsReturnedHashCode() {
		Employee employee2 = ERMConstants.mockEmployee1;
		int empHashCode1 = employee.hashCode();
		int empHashCode2 = employee2.hashCode();
		Assert.assertEquals(empHashCode1, empHashCode2);
	}

	@Test
	public void shouldNotEqualsReturnedHashCode() {
		Employee employee2 = ERMConstants.mockEmployee2;
		int empHashCode1 = employee.hashCode();
		int empHashCode2 = employee2.hashCode();
		Assert.assertNotEquals(empHashCode1, empHashCode2);
	}

}
