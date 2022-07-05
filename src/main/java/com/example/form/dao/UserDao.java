package com.example.form.dao;

import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    User findByRef(String ref);

    int deleteByRef(String ref);

    User findByFormulaireNcin(String ncin);
}

