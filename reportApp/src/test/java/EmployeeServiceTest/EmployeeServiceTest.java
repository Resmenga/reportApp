package EmployeeServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.org.andreorg.reportApp.data.Employee;
import com.org.andreorg.reportApp.service.EmployeeService;

public class EmployeeServiceTest {
	EmployeeService employeeService = new EmployeeService();

	@Ignore
	@Test
	public void testDBConnection() {
		List<Employee> employees = new ArrayList<Employee>();
		String fName = getRandomString();
		String lName = getRandomString();
		
		Employee employee1 = new Employee(fName, lName, 1000, 2, 243234, getRandomString());
		employees.add(employee1);
		employeeService.addEmployees(employees);

		Employee employeeFromDb = employeeService.getEmployeeByID(employee1.getEmpID());
		assertEquals(fName, employeeFromDb.getFirstName());
		assertEquals(lName, employeeFromDb.getLastName());
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
