package com.projectpitang.contenthub.infrastructure.dao;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractLibraryDao<T extends IObjectPersistent<C>,C> extends AbstractDao<T,C> {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
