package VaultCore_Financial.service;
import java.util.List;

import org.springframework.stereotype.Service;

import VaultCore_Financial.entity.AuditLog;
import VaultCore_Financial.implement.AuditLogService;
import VaultCore_Financial.repo.AuditLogRepository;


@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public AuditLog saveAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }
}
