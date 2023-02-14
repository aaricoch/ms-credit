package pe.com.nttdbank.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pe.com.nttdbank.model.Credit;

public interface CreditRepository extends PanacheRepository<Credit> {
    List<Credit> getAll();

    Credit getById(Long id);

    Boolean Create(Credit customer);

    Boolean Edit(Credit customer);

    Boolean Delete(Long id);

    Long CountByNumberCredit(String numberCredit);
}
