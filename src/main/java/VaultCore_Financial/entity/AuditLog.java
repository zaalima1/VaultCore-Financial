package VaultCore_Financial.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String methodName;

    @Column(length = 2000)
    private String parameters;

    @Column(length = 2000)
    private String returnValue;

    private long timestamp;

    public AuditLog() {
    }

    public AuditLog(String methodName, String parameters, String returnValue) {
        this.methodName = methodName;
        this.parameters = parameters;
        this.returnValue = returnValue;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
