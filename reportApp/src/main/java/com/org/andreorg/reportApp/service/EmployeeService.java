package com.org.andreorg.reportApp.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.org.andreorg.reportApp.data.Employee;

public class EmployeeService {
	
	static Employee employee;
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory employeeBuildSessionFactory() {
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		configObj.addAnnotatedClass(Employee.class); // TODO: add this in hibernate.cfg.xml as mapping?

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}
	
	public Employee getEmployeeByID(int employeeID) {
		Employee employee = null;
		try {
			sessionObj = employeeBuildSessionFactory().openSession();
			sessionObj.beginTransaction();
			employee = sessionObj.get(Employee.class, employeeID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	public void addEmployees(List<Employee> employees) {
		try {
			sessionObj = employeeBuildSessionFactory().openSession();
			sessionObj.beginTransaction();

			for (Employee employee : employees) {
				sessionObj.save(employee);
			}
			sessionObj.getTransaction().commit();
		} catch (HibernateException exception) {
			System.out.println("Problem creating session factory");
			exception.printStackTrace();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
