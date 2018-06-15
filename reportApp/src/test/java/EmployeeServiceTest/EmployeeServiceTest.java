package EmployeeServiceTest;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.org.andreorg.reportApp.data.Employee;
import com.org.andreorg.reportApp.service.EmployeeService;

public class EmployeeServiceTest {
	EmployeeService employeeService = new EmployeeService();

	//TODO remove/Ignore all this tests? without mock data is nonsense
	//This class will be used to test data Validation methods of adding/updating employee for example
	
	@Ignore
	@Test
	public void testDBConnection() {
		List<Employee> employees = new ArrayList<Employee>();
		String fName = getRandomString();
		String lName = getRandomString();

		Employee employee1 = addEmployee(employees, fName, lName);

		Employee employeeFromDb = employeeService.getEmployeeByID(employee1.getEmpID());
		assertEquals(fName, employeeFromDb.getFirstName());
		assertEquals(lName, employeeFromDb.getLastName());
	}

	@Test
	public void testAddAndGetEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		String fName = getRandomString();
		String lName = getRandomString();

		Employee employee1 = addEmployee(employees, fName, lName);

		Employee employeeFromDb = employeeService.getEmployeeByID(employee1.getEmpID());
		assertEquals(fName, employeeFromDb.getFirstName());
		assertEquals(lName, employeeFromDb.getLastName());
	}
	
	@Test
	public void testAddThenDeleteEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		String fName = getRandomString();
		String lName = getRandomString();

		Employee employee1 = addEmployee(employees, fName, lName);

		employeeService.deleteEmployees(employees);

		Employee employeeFromDb = employeeService.getEmployeeByID(employee1.getEmpID());
		assertNull(employeeFromDb);
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		
		for(Employee employee: employees) {
			System.out.println(employee.toString()); 
			assertNotNull(employee.getEmpID());
		}
	}
	
	@Test
	public void testUpdateEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employeeFromDb = employeeService.getEmployeeByID(1);
		employees.add(employeeFromDb);
		employeeFromDb.setSalary(20000);
		employeeService.updateEmployees(employees);	
	}

	private Employee addEmployee(List<Employee> employees, String fName, String lName) {
		Employee employee1 = new Employee(fName, lName, 1000, 2, 243234, getRandomString());
		employees.add(employee1);
		employeeService.addEmployees(employees);
		return employee1;
	}

	private String getRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
