package com.shrishti.siri.dataAccessDelegate.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shrishti.siri.dataAccessDelegate.dao.DataAccessDelegate;
import com.shrishti.siri.dataAccessDelegate.entity.UserComment;
import com.shrishti.siri.dataAccessDelegate.entity.UserDetails;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;

@Repository
public class DataAccessDelegateImpl implements DataAccessDelegate{
	
	public enum UserQueryStatus {CLOSED,OPEN,EXPIRED};
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = LoggerFactory.getLogger(DataAccessDelegateImpl.class);
//	private static Log log = LogFactory.getLog(DataAccessDelegateImpl.class);
	
	@SuppressWarnings("unchecked")
	public UserDetails fetchUserDetails(UserDetails user){
		List<UserDetails> userDetailsList = null;
		try{
			Map<String,String> criteriaMap = new HashMap<String,String>();
			criteriaMap.put("userName", user.getUserName());
			criteriaMap.put("userPassword", user.getUserPassword());
			Session session = this.sessionFactory.openSession();
			Criteria criretia = session.createCriteria(UserDetails.class);
			userDetailsList = criretia.add(Restrictions.allEq(criteriaMap)).list();
			session.close();
			System.out.println("List from DB: "+userDetailsList);
			if(null==userDetailsList || userDetailsList.isEmpty()){
				//invalid user details
			}else if(userDetailsList.size()>1){
				//should never happen 
			}else{
				return userDetailsList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDetails saveUser(UserDetails user){
		Session session = this.sessionFactory.openSession();
		try{
			System.out.println("Before saving in delegate");
			user.setCreationDate(new Date());
			session.save(user);
			System.out.println("After saving in delegate: "+user.getUserId());
			
			session.flush();
			session.refresh(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserQuery> fetchOpenQueries(){
		log.info("Inside Impl..: "+this.sessionFactory);
		Session session = this.sessionFactory.openSession();
		List<UserQuery> userQueries;
		try{
			System.out.println("Before fetching open queries");
			Criteria criretia = session.createCriteria(UserQuery.class);
			Collection<UserQuery>  queries = new LinkedHashSet<UserQuery>(criretia.add(Restrictions.eq("queryStatus", UserQueryStatus.OPEN.toString()).ignoreCase()).addOrder(Order.desc("creationDate")).list());
			userQueries = new ArrayList<UserQuery>(queries);
			userQueries = setHasMoreCommentsFlag(userQueries);
			System.out.println("After fetching open queries:Size>> "+userQueries.size());
			return userQueries;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserQuery> fetchExpiredQueries(){
		Session session = this.sessionFactory.openSession();
		List<UserQuery> userQueries = null;
		try{
			System.out.println("Before fetching expired queries");
			Criteria criretia = session.createCriteria(UserQuery.class);
			Collection<UserQuery>  queries = new LinkedHashSet<UserQuery>(criretia.add(Restrictions.eq("queryStatus", UserQueryStatus.EXPIRED.toString()).ignoreCase()).addOrder(Order.desc("creationDate")).list());
			userQueries = new ArrayList<UserQuery>(queries);
			userQueries = setHasMoreCommentsFlag(userQueries);
			System.out.println("After fetching expired queries:Size>> "+userQueries.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return userQueries;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserQuery> fetchClosedQueries(){
		Session session = this.sessionFactory.openSession();
		List<UserQuery> userQueries = null;
		try{
			System.out.println("Before fetching closed queries");
			Criteria criretia = session.createCriteria(UserQuery.class);
			Collection<UserQuery>  queries = new LinkedHashSet<UserQuery>(criretia.add(Restrictions.eq("queryStatus", UserQueryStatus.CLOSED.toString()).ignoreCase()).addOrder(Order.desc("creationDate")).list());
			userQueries = new ArrayList<UserQuery>(queries);
			userQueries = setHasMoreCommentsFlag(userQueries);
			System.out.println("After fetching closed queries:Size>> "+userQueries.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return userQueries;
	}
	
	private List<UserQuery> setHasMoreCommentsFlag(List<UserQuery> userQueries){
		for(UserQuery query : userQueries){
			if(query.getUserCommentsList()!=null && query.getUserCommentsList().size()>2){
				query.setHasMoreComments(true);
				query.getUserCommentsList().subList(2, query.getUserCommentsList().size()).clear();
			}
		}
		return userQueries;
	}
	
	public UserQuery fetchQueryDetails(int queryId){
		UserQuery userQuery = null;
		Session session = this.sessionFactory.openSession();
		
		try{
			System.out.println("Before fetching user query");
			Criteria criretia = session.createCriteria(UserQuery.class);
			userQuery = (UserQuery) criretia.add(Restrictions.eq("queryId", queryId)).uniqueResult();
			System.out.println("After fetching user query>> "+userQuery);
		}catch(Exception e){
			e.printStackTrace();
		}
		return userQuery;
	}
	
	public UserQuery addUserComment(UserComment userComment){
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		UserQuery userQuery = null;
		try{
			
			userComment.setCreationDate(new Date());
			session.save(userComment);
			
			System.out.println("Data After saving in impl: "+userComment.getUserQuery().toString());
			
			session.flush();
			session.refresh(userComment);
			transaction.commit();
		
			userQuery = (UserQuery) session.createCriteria(UserQuery.class).add(Restrictions.eq("queryId", userComment.getUserQuery().getQueryId())).uniqueResult();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return userQuery;
	}
	
	public void addUserQuery(UserQuery userQuery){
		Session session = this.sessionFactory.openSession();
			
		userQuery.setCreationDate(new Date());
		userQuery.setQueryStatus(UserQueryStatus.OPEN.toString());
		session.save(userQuery);
			
		System.out.println("Data After saving in impl: "+userQuery.toString());
			
	}
	
	public boolean isUserNameUnique(String userName){
		Session session = this.sessionFactory.openSession();
		
		List<UserDetails> userDetails = (List<UserDetails>) session.createCriteria(UserDetails.class).add(Restrictions.eq("userName", userName).ignoreCase()).list();
		System.out.println("Size of userName list: "+userDetails.size());
		if(userDetails.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	public List<UserQuery> fetchUserQueries(int userId){
		Session session = this.sessionFactory.openSession();
		List<UserQuery> userQueries = null;
		try{
			System.out.println("Before fetching user queries");
			Criteria criretia = session.createCriteria(UserQuery.class);
			Collection<UserQuery>  queries = new LinkedHashSet<UserQuery>(criretia.add(Restrictions.eq("user.userId", userId)).addOrder(Order.desc("creationDate")).list());
			userQueries = new ArrayList<UserQuery>(queries);
			System.out.println("After fetching expired queries:Size>> "+userQueries.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return userQueries;
	}
	
	public List<UserQuery> closeUserQueries(String[] queryIdsToClose){
		Session session = this.sessionFactory.openSession();
		Integer[] queryIdsToCloseIntArray = new Integer[queryIdsToClose.length];
		List<UserQuery>  queries = null;
		
		for(int index=0;index<queryIdsToClose.length;index++){
			queryIdsToCloseIntArray[index] = Integer.parseInt(queryIdsToClose[index].trim());
		}
		Collection<UserQuery> userQueries = null;
		try{
			Transaction transaction = session.beginTransaction();
			userQueries = new LinkedHashSet<UserQuery> (session.createCriteria(UserQuery.class).add(Restrictions.in("queryId", queryIdsToCloseIntArray)).list());
			
			System.out.println("No of rows to be updated: "+userQueries.size());
			int count=0;
			for(UserQuery query : userQueries){
				query.setQueryStatus(UserQueryStatus.CLOSED.toString());
				query.setCreationDate(new Date());
				session.saveOrUpdate(query);
                if ( ++count % 2 == 0 ) {
                	session.flush();
                	session.clear();
                }
			}
			
			int userId = userQueries.iterator().next().getUser().getUserId();
//			session.refresh(userQueries);
			transaction.commit();
			
			Criteria criretia = session.createCriteria(UserQuery.class);
			Collection<UserQuery>  refreshedQueryList = new LinkedHashSet<UserQuery> (criretia.add(Restrictions.eq("user.userId", userId)).addOrder(Order.desc("creationDate")).list());
			queries = new ArrayList<UserQuery>(refreshedQueryList);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return queries;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
