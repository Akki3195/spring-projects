package com.transportsys.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.transportsys.model.AdminDTL;
import com.transportsys.model.BranchDTL;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;
import com.transportsys.util.PersistenceManager;

@Repository
public class AdminDaoImpl implements AdminDao {


	@Override
	public boolean checkUser(String uId, String uPass) {
		AdminDTL ad;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		Query query = em.createQuery("SELECT a from AdminDTL a where a.AD_USER_ID=:uId and a.AD_PASSWORD=:uPass");
		query.setParameter("uId", uId);
		query.setParameter("uPass", uPass);
		try {
			ad = (AdminDTL) query.getSingleResult();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			em.close();
			return false;
		}
		if (!ad.equals(null)) {
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean saveManagerDtl(ManagerDTL managerDtl) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(managerDtl);
			
		} catch (Exception e) {
			if(em != null) {
			e.printStackTrace();
			em.close();
			return false;
			}
		}

		em.getTransaction().commit();
		em.close();
		return true;

	}

	
	@Override
	public List<BranchDTL> getAllBranchDtl() {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<BranchDTL> query =  em.createQuery("SELECT b FROM BranchDTL b",BranchDTL.class);
		List<BranchDTL> branchDtl = query.getResultList();
		em.close();
		return branchDtl;
	}	

	@Override
	public boolean saveTruckDtl(TruckDTL truckDtl) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		try {		
		em.getTransaction().begin();
		em.persist(truckDtl);
		em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			em.close();
			return false;
		}
		em.close();
		return true;
		
	}

	@Override
	public List<ManagerDTL> getManagerDtlByName(String letter,String urlParam) {
		List<ManagerDTL> mgrDtlList = null;
		TypedQuery<ManagerDTL> query = null;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		try {
			
				if (!letter.isEmpty() && urlParam.isEmpty()) {
					int i = Integer.parseInt(letter);
					query = em.createQuery("SELECT m FROM ManagerDTL m WHERE m.MGR_ID = :name", ManagerDTL.class);
					query.setParameter("name", i);
					mgrDtlList = query.getResultList();
					em.close();
					return mgrDtlList;
				}
				else if(letter.isEmpty() && urlParam.equals("A")){
					query = em.createQuery("SELECT m FROM ManagerDTL m", ManagerDTL.class);
					mgrDtlList = query.getResultList();
					em.close();
					return mgrDtlList;
				}
				else {
					query = em.createQuery("SELECT m FROM ManagerDTL m WHERE m.MGR_NAME LIKE :name",ManagerDTL.class);
					query.setParameter("name", letter+"%");
					mgrDtlList = query.getResultList();
					em.close();
					return mgrDtlList;
				}
				
			}
			
		
		catch (Exception e) {
			/*PersistenceManager.INSTANCE.close();*/
			e.printStackTrace();
			em.close();
			return null;
		}
		
	}

	@Override
	public boolean deleteManager(int mgrId) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		try {
			ManagerDTL mgr = em.find(ManagerDTL.class, mgrId);
			em.remove(mgr);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		em.close();
		return false;
	}

	@Override
	public List<TruckDTL> getTruckDtl(String letter,String check) {
		TypedQuery<TruckDTL> query = null;
		List<TruckDTL> truckList = null;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		try {
			if(check.equals("L")) {
				query = em.createQuery("SELECT t FROM TruckDTL t WHERE t.TRC_NO LIKE :name",TruckDTL.class);
				query.setParameter("name", letter+"%");
				truckList = query.getResultList();
				em.close();
				return truckList;
			}
			else if (check.equals("S")) {
				query = em.createQuery("SELECT t FROM TruckDTL t WHERE t.TRC_NO = :name ",TruckDTL.class);
				query.setParameter("name", letter);
				truckList = query.getResultList();
				em.close();
				return truckList;
				
			}
			else if(check.equals("allResult")) {
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}
	
	/*public static void main(String[] args) {
		AdminDaoImpl adi = new AdminDaoImpl();
		
		System.out.println(adi.getManagerDtlByName("A"));
	}*/
}
