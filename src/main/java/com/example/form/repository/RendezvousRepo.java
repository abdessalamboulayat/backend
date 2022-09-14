package com.example.form.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Rendezvous;

@Repository
public interface RendezvousRepo extends JpaRepository<Rendezvous, Long>{
	List <Rendezvous> findByDaterendezvous(Date date);
    List<Rendezvous> findByEmail(String email);
}
