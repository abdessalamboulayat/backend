package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.ActiviteSafir;

@Repository
public interface ActiviteSafirRepo extends JpaRepository<ActiviteSafir, Long>{

}
