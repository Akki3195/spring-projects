package com.transportsys.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;
import com.transportsys.util.PersistenceManager;

@Repository
public class ManagerDaoImpl implements ManagerDao{
	EntityManager em = null;
	@Override
	public ManagerDTL managerLogin(String username, String password) {
		em = PersistenceManager.INSTANCE.getEntityManager();
		ManagerDTL mgr;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT m FROM ManagerDTL m WHERE m.MGR_USER_ID = :mId AND m.MGR_PASSWORD = :mPassword");
			query.setParameter("mId", username);
			query.setParameter("mPassword", password);
			mgr = (ManagerDTL)query.getSingleResult();
			em.close();
			return mgr;
		}
		catch(Exception e) {
			e.printStackTrace();
			em.close();
			return null;
		}
	}

	@Override
	public List<Object[]> routeDetails(int brnId) {
		em = PersistenceManager.INSTANCE.getEntityManager();
		String q = "SELECT t , r FROM TruckDTL t , RunningStatus r "
				+ " WHERE t.branchdtl.BRN_ID = :id AND t.TRC_ID = r.truckdtl.TRC_ID ORDER BY t.TRC_MODEL";
		try {
			Query query = em.createQuery(q);
			query.setParameter("id", brnId);
			@SuppressWarnings("unchecked")
			List<Object[]> truckDtlList = query.getResultList();
		
			em.close();
			return truckDtlList;
			
		}
		catch(Exception e){
			e.printStackTrace();
			em.close();
			return null;
		}
	}
	
	@Override
	public boolean updateStatus(int truckId , String runningSts) {
		em = PersistenceManager.INSTANCE.getEntityManager();
		String q = "UPDATE RunningStatus r SET r.RUN_STATUS = '"+runningSts+"' WHERE r.truckdtl.TRC_ID = :trckId";
		try {
			em.getTransaction().begin();
			/*it will  work only for primary key*/
			/*RunningStatus rs = em.find(RunningStatus.class, truckId);
			rs.setRUN_STATUS(runningSts);*/
			Query query = em.createQuery(q);
			query.setParameter("trckId", truckId);
			int i = query.executeUpdate();
			if(i>0) {
			em.getTransaction().commit();
			em.close();
			return true;
			}
			else 
			{	
				em.close();
				return false;
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
			em.close();
			return false;
		}
	}

	@Override
	public List<TruckDTL> viewIstalTruck(Date frmDt, Date toDt) {
		em = PersistenceManager.INSTANCE.getEntityManager();
		String q = "SELECT t FROM TruckDTL t WHERE t.TRC_INSTL_DT BETWEEN :frmDt AND :toDt";
		
		try {
		TypedQuery<TruckDTL> query = em.createQuery(q,TruckDTL.class);
		query.setParameter("frmDt", frmDt);
		query.setParameter("toDt", toDt);
		List<TruckDTL> truckDtlList = query.getResultList();
		em.close();
		return truckDtlList;
		}
		catch(Exception e) {
		e.printStackTrace();
			em.close();
			return null;
		}
	}
	
	public List<TruckDTL> getTruckList(String from , String to){
		em = PersistenceManager.INSTANCE.getEntityManager();
		List<TruckDTL> truckList = null;
		String q = "SELECT t FROM TruckDTL t WHERE t.TRC_ROUTE_FROM = :from AND t.TRC_ROUTE_TO = :to";
		try {
			TypedQuery<TruckDTL> query = em.createQuery(q , TruckDTL.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			truckList = query.getResultList();
			em.close();
			return truckList;
		}
		catch(Exception e) {
			e.printStackTrace();
			em.close();
			return truckList;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TruckDTL> getTruckListByPage(int pageId,int totalRecords,TruckDTL truckDtl) {
		List<TruckDTL> truckList = null;
		em = PersistenceManager.INSTANCE.getEntityManager();
		try {
			String query = "SELECT t2.*,ROWNUM FROM (SELECT t1.*,ROWNUM AS rnum FROM (SELECT t.*,ROWNUM AS rn FROM truck_dtl t "
					+ " WHERE TRC_ROUTE_FROM = ? AND TRC_ROUTE_TO = ? ORDER BY trc_id) t1 "+
								"WHERE rn >= ?) t2 WHERE  rnum <= ?";
			Query q = em.createNativeQuery(query, TruckDTL.class);
			q.setParameter(1, truckDtl.getTRC_ROUTE_FROM());
			q.setParameter(2, truckDtl.getTRC_ROUTE_TO());
			q.setParameter(3, pageId);
			q.setParameter(4, totalRecords);
			truckList = q.getResultList();
			em.close();
			return truckList;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			em.close();
		}
		return truckList;
	}
	
	public int getTotalNoOfRecords(TruckDTL truckDtl) {
		em = PersistenceManager.INSTANCE.getEntityManager();
		Long totalNoOfRecords = 0l;
		try {
			String query = "SELECT COUNT(t) from TruckDTL t WHERE t.TRC_ROUTE_FROM = :routeFrom AND t.TRC_ROUTE_TO = :routeTo";
			Query q = em.createQuery(query);
			q.setParameter("routeFrom", truckDtl.getTRC_ROUTE_FROM());
			q.setParameter("routeTo", truckDtl.getTRC_ROUTE_TO());
			totalNoOfRecords = (Long)q.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			
		}
		return totalNoOfRecords.intValue();

	}
	
	public static void main(String[] args) {
	 ManagerDaoImpl mgr = new ManagerDaoImpl();
	 TruckDTL truckDtl = new TruckDTL();
	 truckDtl.setTRC_ROUTE_FROM("Thane");
	 truckDtl.setTRC_ROUTE_TO("Vashi");
	System.out.println(mgr.getTotalNoOfRecords(truckDtl));
	}
	
}
