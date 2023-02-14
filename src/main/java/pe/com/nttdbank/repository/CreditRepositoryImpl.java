package pe.com.nttdbank.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.com.nttdbank.common.StatusType;
import pe.com.nttdbank.model.Credit;

@ApplicationScoped
public class CreditRepositoryImpl implements CreditRepository {

    public List<Credit> getAll() {
        return Credit.list("state", StatusType.Active.value);
    }

    public Credit getById(Long id) {
        Optional<Credit> credit = Credit.findByIdOptional(id);
        if (!credit.isPresent()) {
            return null;
        }
        return credit.get();
    }

    @Transactional
    public Boolean Create(Credit credit) {
        Boolean result = false;

        credit.State = StatusType.Active.value;
        credit.AuditCreateUser = 1;
        credit.AuditCreateDate = new Date();

        credit.persist();
        if (credit.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Edit(Credit credit) {
        Boolean result = false;

        Credit creditEdit = getById(credit.getIdCredit());

        creditEdit = merge(creditEdit, credit);
        creditEdit.AuditUpdateUser = 1;
        creditEdit.AuditUpdateDate = new Date();
        creditEdit.persist();

        if (creditEdit.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Delete(Long id) {
        Boolean result = false;
        Credit credit = getById(id);

        credit.State = StatusType.Inactive.value;
        credit.AuditDeleteUser = 1;
        credit.AuditDeleteDate = new Date();
        credit.persist();

        if (credit.isPersistent()) {
            result = true;
        }

        return result;
    }

    public Long CountByNumberCredit(String creditNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("creditnumber", creditNumber);
        params.put("state", StatusType.Active.value);

        String query = "creditnumber = :creditnumber and state = :state";

        return Credit.count(query, params);
    }

    private Credit merge(Credit credit, Credit creditEdit){
        credit.setEndDate(creditEdit.getEndDate());
        credit.setNumberPayments(creditEdit.getNumberPayments());
        credit.setMonthlyPaymentDay(creditEdit.getMonthlyPaymentDay());
        credit.setCurrentBalance(creditEdit.getCurrentBalance());
        credit.setPaymentAmountMonthly(creditEdit.getPaymentAmountMonthly());

        return credit;
    }

}
