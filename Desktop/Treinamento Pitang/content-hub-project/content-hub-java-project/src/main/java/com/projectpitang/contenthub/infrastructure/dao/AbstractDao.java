package com.projectpitang.contenthub.infrastructure.dao;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao <T extends IObjectPersistent<C>, C> implements IDao<T, C>{

    private Class objectClass;
    public SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.objectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void insert(T model) {
        this.getSession().persist(model);
    }

    @Override
    public Boolean delete(T model) {
        return null;
    }

    @Override
    public T update(T model) {
        return null;
    }

    @Override
    public T findOne(Long id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        String ano = "1993";


        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(this.objectClass);
        Root root = query.from(this.objectClass);
        query.select(root).where(criteriaBuilder.isNotNull(root.get("sinopse")));

        Query<T> result = getSession().createQuery(query);




        return result.getResultList();
    }

    @Override
    public List<T> findAllPaginating(PagingAndSortingRepository pagingAndSortingRepository) {
        return null;
    }

    @Override
    public Class<T> getObjectClass() {
        return objectClass;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
