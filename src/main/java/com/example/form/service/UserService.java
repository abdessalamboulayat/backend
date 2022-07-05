package com.example.form.service;

import com.example.form.bean.User;
import com.example.form.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findByRef(String ref) {
        if (ref == null) {
            return null;
        } else {
            return userDao.findByRef(ref);
        }

    }


    @Transactional
    public int deleteByRef(String ref) {
        if (ref == null) {
            return 0;
        } else {


            return userDao.deleteByRef(ref);
        }
    }


    public User findByFormulaireNcin(String ncin) {

        return userDao.findByFormulaireNcin(ncin);
    }
}
