package com.revature.backendTest;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;


public class test_DAO {

	@InjectMocks
	ReimbursementDAO rem;

	@Mock
	Session mockSession;

	@Mock
	CriteriaBuilder mockCb;

	@Mock
	CriteriaQuery mockCq;

	@Mock
	CriteriaUpdate mockUp;

	@Mock
	Root mockRoot;

	@Mock
	Transaction mockTx;
	
	@Mock
	HibernateUtil mockHu;
	
	@Mock
	EmployeeDAO mockEmp;
	
	Session session; 

	@Test
	public void test_getAllReimbursements() {
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		Mockito.when(mockHu.buildSessionFactory().openSession()).thenReturn(session);
		Mockito.when(mockSession.getCriteriaBuilder()).thenReturn(mockCb);
		Mockito.when(mockCb.createQuery(Reimbursement.class)).thenReturn(mockCq);
		Mockito.when(mockCq.from(Reimbursement.class)).thenReturn(mockRoot);
		mockCq.select(mockRoot);
		Mockito.when(mockSession.createQuery(mockCq).getResultList()).thenReturn(rems);

	}

	@Test
	public void test_updateReimbursement() {
		String status = "Approved";
		Mockito.when(HibernateUtil.buildSessionFactory().openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getCriteriaBuilder()).thenReturn(mockCb);
		Mockito.when(mockCb.createCriteriaUpdate(Reimbursement.class)).thenReturn(mockUp);
		Mockito.when(mockUp.from(Reimbursement.class)).thenReturn(mockRoot);
		mockUp.set("approvalStatus", status);
		mockUp.where(mockCb.equal(mockRoot.get("reimbursementId"), 1));
		Mockito.when(mockSession.beginTransaction()).thenReturn(mockTx);
		Mockito.when(mockSession.createQuery(mockUp).executeUpdate());
		mockTx.commit();
		mockSession.close();

	}

	@Test
	public void test_insertReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		Mockito.when(HibernateUtil.buildSessionFactory().openSession()).thenReturn(mockSession);
		mockSession.getTransaction().begin();
		mockSession.save(reimbursement);
		mockSession.getTransaction().commit();
		mockSession.close();
	}

	@Test
	public void test_getReimbursementByEmpId() {
		String empId = "10";
		ArrayList<Reimbursement> r = new ArrayList<Reimbursement>();
		Mockito.when(HibernateUtil.buildSessionFactory().openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getCriteriaBuilder()).thenReturn(mockCb);
		Mockito.when(mockCb.createQuery(Reimbursement.class)).thenReturn(mockCq);
		Mockito.when(mockCq.from(Reimbursement.class)).thenReturn(mockRoot);
		mockCq.select(mockRoot).where(mockCb.equal(mockRoot.get("Employeed_Id"), empId));
		Mockito.when(mockSession.createQuery(mockCq).getResultList()).thenReturn(r);
		mockSession.close();
	}

	@Test
	public void test_getAllEmployees() {
		ArrayList<Employee> e = new ArrayList<Employee>();
		mockSession = mockHu.buildSessionFactory().openSession();
		mockCb = mockSession.getCriteriaBuilder();
		mockCq = mockCb.createQuery(Employee.class);
		mockRoot = mockCq.from(Employee.class);
		mockCq.select(mockRoot);
		e = (ArrayList<Employee>) mockSession.createQuery(mockCq).getResultList();
		mockSession.close();

	}

	@Test
	public void test_getEmployee() {
		Employee e = new Employee();

		Mockito.when(mockHu.buildSessionFactory().openSession()).thenReturn(session);
		mockCb = mockSession.getCriteriaBuilder();
		mockCq = mockCb.createQuery(Employee.class);
		mockRoot = mockCq.from(Employee.class);
		mockCq.select(mockRoot).where(mockCb.and(mockCb.equal(mockRoot.get("userName"), "ayankosky")),
				mockCb.equal(mockRoot.get("password"), "root"));
		e = (Employee) mockSession.createQuery(mockCq).getSingleResult();
		mockSession.close();
	}

	@Test
	public void test_getEmployeeById() {
		Employee e = new Employee();

		mockSession = mockHu.buildSessionFactory().openSession();
		mockCb = mockSession.getCriteriaBuilder();
		mockCq = mockCb.createQuery(Employee.class);
		mockRoot = mockCq.from(Employee.class);
		mockCq.select(mockRoot).where(mockCb.equal(mockRoot.get("employeeId"), 1));
		e = (Employee) mockSession.createQuery(mockCq).getSingleResult();
		mockSession.close();

	}
	
	@Test
	public void test_loginValidation() {
		Employee e = new Employee();
		mockSession = mockHu.buildSessionFactory().openSession();
		mockCb = mockSession.getCriteriaBuilder();
		mockCq = mockCb.createQuery(Employee.class);
		mockRoot = mockCq.from(Employee.class);
		mockCq.select(mockRoot).where(mockCb.and(mockCb.equal(mockRoot.get("userName"), "ayankosky")),
				mockCb.equal(mockRoot.get("password"), "root"));
		e = (Employee) mockSession.createQuery(mockCq).getSingleResult();
		mockSession.close();
		
		
		
	}
	
	@Test
	public void test_loginValidator() {
		Employee e = new Employee();
		Mockito.when(mockEmp.loginValidation("ayankosky", "root")).thenReturn(true);
		e = mockEmp.getEmployee("ayankosky", "root");
		
	}

}
