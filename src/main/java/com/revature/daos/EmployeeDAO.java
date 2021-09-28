package com.revature.daos;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Employee;

import com.revature.utils.HibernateUtil;

public class EmployeeDAO {

	private ArrayList<Employee> list;

	public ArrayList<Employee> getAllEmployees() {
		list = new ArrayList<Employee>();
		try(Session session = HibernateUtil.buildSessionFactory().openSession()){
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		query.select(root);
		list = (ArrayList<Employee>) session.createQuery(query).getResultList();
		session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		
		return list;

	}

	public void addEmployee(Employee employee) {
		try(Session session = HibernateUtil.buildSessionFactory().openSession()) {
		session.getTransaction().begin();
		session.save(employee);
		session.getTransaction().commit();
		session.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Employee getEmployee(String username, String password) {
		Employee emp = new Employee();
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root).where(builder.and(builder.equal(root.get("userName"), username)),
					builder.equal(root.get("password"), password));
			emp = session.createQuery(query).getSingleResult();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public Employee getEmployeeById(int id) {
		Employee emp = new Employee();
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root).where(builder.equal(root.get("employeeId"), id));
			emp = session.createQuery(query).getSingleResult();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public boolean loginValidation(String username, String password) {
		Employee emp = new Employee();
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root).where(builder.and(builder.equal(root.get("userName"), username)),
					builder.equal(root.get("password"), password));
			emp = session.createQuery(query).getSingleResult();
			session.close();
		}

		if (emp.getUserName().equals(username) && emp.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	


}
