package VaultCore_Financial.implement;



import java.util.List;
import VaultCore_Financial.entity.AuditLog;

public interface AuditLogService {

    List<AuditLog> getAllAuditLogs();

    AuditLog saveAuditLog(AuditLog auditLog);
}
