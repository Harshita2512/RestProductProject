package com.database;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDatabase {
	
	Products p = new Products();
	
	private static SessionFactory sf = getSessionFactory();

	private static SessionFactory  getSessionFactory() {

		if(sf==null) {

			Configuration con = new Configuration().configure();

			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

			sf = con.buildSessionFactory(reg);
		}

		return sf;
	}
	
	public int savePersons(Products p) {
		
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		int id = (Integer) session.save(p);
		
		t.commit();
		session.flush();
		session.close();
		
		return id;
	}
	
	public void deleteProduct (int id) {
		
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		p.setProductID(id);
		session.delete(p);
		
		t.commit();
		session.flush();
		session.close();
	}
	
	public void updateProduct (Products p) {
		
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		session.update(p);
		
		t.commit();
		session.flush();
		session.close();
	}
	
	public Products getProducts (int id) {

		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Products pro = (Products) session.get(Products.class, id);
		
		t.commit();
		session.flush();
		session.close();
		
		return pro;
	}
	
	public ArrayList<Products> getAllProducts (){
		
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		

		Criteria c = session.createCriteria(Products.class);
		ArrayList<Products> products= (ArrayList<Products>) c.list();

		session.flush();
		session.close();
		
		return products;
	}
	
	

}
