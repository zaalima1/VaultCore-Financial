package VaultCore_Financial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VaultCore_Financial.repo.AdminRepository;

import VaultCore_Financial.entity.Admin;
@Service
public class AdminService {

	private final AdminRepository repo ;
	
	 public AdminService(AdminRepository repo)
	 {
	        this.repo = repo;
	    }
	
	/*
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
	
	}*/
	public Admin Admin1(Admin admin)
	{
        return repo.save(admin);
    }

    
    
}