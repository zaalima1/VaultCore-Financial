package VaultCore_Financial.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who performed the action
    @Column(nullable = false)
    private String username;

    // What action was performed (LOGIN, TRANSFER, UPDATE_PROFILE)
    @Column(nullable = false)
    private String action;

    // Which method was called
    @Column(name = "method_name")
    private String methodName;

    // Request parameters (JSON / String)
    @Lob
    @Column(columnDefinition = "TEXT")
    private String parameters;

    // Method return value / response
    @Lob
    @Column(columnDefinition = "TEXT")
    private String response;

    // User IP Address
    @Column(name = "ip_address")
    private String ipAddress;

    // When the action happened
    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Automatically set timestamp before insert
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructors
    public AuditLog() {}

    public AuditLog(String username, String action, String methodName,
                    String parameters, String response, String ipAddress) {
        this.username = username;
        this.action = action;
        this.methodName = methodName;
        this.parameters = parameters;
        this.response = response;
        this.ipAddress = ipAddress;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
