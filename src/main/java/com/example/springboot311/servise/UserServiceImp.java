package com.example.springboot311.servise;

import com.example.springboot311.dao.UserDao;
import com.example.springboot311.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> getListUser() {
        return userDao.getListUser();
    }


    @Override
    @Transactional
    public void save(String name, String surname, int age) {
        userDao.save(name, surname, age);
    }


    @Override
    @Transactional
    public void update(User user, long id) {
        userDao.update(user, id);
    }


    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);

    }


}
