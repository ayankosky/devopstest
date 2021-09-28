package com.revature.daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO {

	private ArrayList<Reimbursement> reimbursements;

	public ArrayList<Reimbursement> getAllReimbursements() {
		reimbursements = new ArrayList<Reimbursement>();
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> query = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = query.from(Reimbursement.class);
			query.select(root);
			reimbursements = (ArrayList<Reimbursement>) session.createQuery(query).getResultList();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return reimbursements;

	}

	public void updateReimbursement(Reimbursement reimbursement) {
		String status = reimbursement.getApprovalStatus();

		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {

			CriteriaBuilder cb = session.getCriteriaBuilder();

			CriteriaUpdate<Reimbursement> update = cb.createCriteriaUpdate(Reimbursement.class);
			Root<Reimbursement> root = update.from(Reimbursement.class);
			update.set("approvalStatus", status);
			update.where(cb.equal(root.get("reimbursementId"), reimbursement.getReimbursementId()));
			Transaction tx = session.beginTransaction();
			session.createQuery(update).executeUpdate();
			tx.commit();
			session.close();
		}
	}

	public void insertReimbursement(Reimbursement reimbursement) {
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
			session.getTransaction().begin();
			session.save(reimbursement);
			session.getTransaction().commit();
			session.close();
		}
	}

	public ArrayList<Reimbursement> getReimbursementByEmpId(int id) {
		String empId = String.valueOf(id);
		ArrayList<Reimbursement> r = new ArrayList<Reimbursement>();
		
		try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> query = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = query.from(Reimbursement.class);
			query.select(root).where(cb.equal(root.get("Employee_Id"),id));
			r = (ArrayList<Reimbursement>) session.createQuery(query).getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;

	}
}
