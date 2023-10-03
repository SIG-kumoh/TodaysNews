package com.sig.todaysnews;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CEntityManagerFactory {
    private static EntityManagerFactory entityManagerFactory;

    public static void initialization(){
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    }

    public static EntityManager createEntityManger(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(){
        entityManagerFactory.close();
    }
}
