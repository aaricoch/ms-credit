package pe.com.nttdbank.model;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {
    public int State;
    public int AuditCreateUser;
    public Date AuditCreateDate;
    public int AuditUpdateUser;
    public Date AuditUpdateDate;
    public int AuditDeleteUser;
    public Date AuditDeleteDate;
}
