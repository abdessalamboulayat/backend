package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long>{
	
	Authority findByAuthority(String authority);
}
