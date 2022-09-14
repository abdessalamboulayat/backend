package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	User findByRef(String ref);

    int deleteByRef(String ref);

    User findByFormulaireNcin(String ncin);
}
