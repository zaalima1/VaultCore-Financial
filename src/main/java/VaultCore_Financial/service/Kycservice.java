package VaultCore_Financial.service;

import org.springframework.stereotype.Service;

import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Kyc;
import VaultCore_Financial.repo.AdminRepository;
import VaultCore_Financial.repo.KycRepository;

@Service
public class Kycservice {
	 private AdminRepository adminRepository;
	 private KycRepository kycrepo;
	 public void saveKyc(Long adminId, Kyc kycData) {

		    Admin admin = adminRepository.findById(adminId).get();

		    kycData.setAdmin(admin);
		    kycData.setKycStatus("PENDING");

		    kycrepo.save(kycData);
		}

	 public void approveKyc(Long kycId) {

		    Kyc kyc = kycrepo.findById(kycId).get();
		    kyc.setKycStatus("APPROVED");

		    kycrepo.save(kyc);
		}


}
