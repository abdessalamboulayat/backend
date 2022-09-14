package com.example.form.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Authority;
import com.example.form.bean.Safir;

@Repository
public interface SafirRepo extends JpaRepository<Safir, Long>{

	Safir findByUsername(String username);
	
	List<Safir> findByIsSafir(boolean isSafir);
	//@Query("SELECT safir FROM Safir safir, Safir_authorities authority WHERE safir.id=authority.safir_id AND authority.authorities_id=2;")
	//List<Safir> findByAuthorities(String authority);
	
	//List<Safir> findByAuthorities(Collection<Authority> authorities);
}
