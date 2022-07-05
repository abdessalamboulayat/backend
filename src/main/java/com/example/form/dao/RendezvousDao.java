package com.example.form.dao;

import com.example.form.bean.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RendezvousDao extends JpaRepository<Rendezvous,Long> {
  List <Rendezvous> findByDaterendezvous(Date date);
    List<Rendezvous> findByEmail(String email);

}
