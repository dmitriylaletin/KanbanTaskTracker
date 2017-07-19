package com.eisgroup.tasktracker.dao.Impl;

import com.eisgroup.tasktracker.dao.UserDAO;
import com.eisgroup.tasktracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:03
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.
                getCurrentSession().
                save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.
                getCurrentSession().
                update(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User getUserByUuid(String uuid) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        return (User) criteria.uniqueResult();
    }
}
