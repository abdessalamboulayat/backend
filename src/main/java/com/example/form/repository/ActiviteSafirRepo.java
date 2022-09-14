package com.example.form.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.ActiviteSafir;
import com.example.form.bean.Safir;

@Repository
public interface ActiviteSafirRepo extends JpaRepository<ActiviteSafir, Long>{
	List<ActiviteSafir> findBySafir(Safir safir);
}
