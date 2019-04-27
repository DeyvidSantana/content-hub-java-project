package com.projectpitang.contenthub.infrastructure.dao;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IDao<T extends IObjectPersistent<C>,C> {

    public void insert(T model);

    public Boolean delete(T model);

    public T update(T model);

    public T findOne(Long id);

    public List<T> findAll();

    public List<T> findAllPaginating(PagingAndSortingRepository pagingAndSortingRepository);

    public Class<T> getObjectClass();
}
