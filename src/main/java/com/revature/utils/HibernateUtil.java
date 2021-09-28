package com.revature.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			sessionFactory = metadata.getSessionFactoryBuilder().build();

		}

		return sessionFactory;
	}

}
