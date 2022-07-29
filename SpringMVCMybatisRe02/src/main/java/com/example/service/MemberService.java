package com.example.service;

import org.springframework.stereotype.Service;

import com.example.mapper.MemberMapper;

@Service
public class MemberService {
	MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}
	
	
}
