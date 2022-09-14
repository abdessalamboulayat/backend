package com.example.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.bean.Authority;
import com.example.form.repository.AuthorityRepo;

@Service
public class AuthorityServiceImp implements AuthorityService{

	@Autowired
	private AuthorityRepo authorityRepo;

	@Override
	public Authority addNewRole(Authority authority) {
		return authorityRepo.save(authority);
	}
	
	
}
