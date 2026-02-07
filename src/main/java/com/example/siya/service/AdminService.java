package com.example.siya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.siya.entity.Admin;
import com.example.siya.repo.AdminRepository;
@Service
public class AdminService
{
	@Autowired
	private AdminRepository repo;
	public boolean Admin(Admin obj)
	{
		try
		{
			repo.save(obj);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("something is wrong is happing");
			return false;	}
	
	}
	public Admin Admin1(Admin admin) {
        return repo.save(admin);
    }

    
    
}