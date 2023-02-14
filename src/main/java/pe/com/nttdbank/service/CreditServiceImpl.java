package pe.com.nttdbank.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pe.com.nttdbank.dto.CreditDto;
import pe.com.nttdbank.model.Credit;
import pe.com.nttdbank.repository.CreditRepository;

@ApplicationScoped
public class CreditServiceImpl implements CreditService {

    @Inject
    CreditRepository creditRepository;

    public List<CreditDto> getAll() {
        List<Credit> credits = creditRepository.getAll();
        List<CreditDto> creditDtos = new ArrayList<CreditDto>();

        credits.forEach(credit -> {
            creditDtos.add(toCreditDto(credit));
        });

        return creditDtos;
    }

    public CreditDto getById(Long id) {
        Credit credit = creditRepository.getById(id);
        if (credit == null) {
            return null;
        }
        return toCreditDto(credit);
    }

    public Boolean Create(CreditDto creditDto) {
        Credit credit = toCredit(creditDto);

        if (creditRepository.CountByNumberCredit(credit.getCreditNumber()) > 0) {
            // throw new NullPointerException("Already registered credit");
            return false;
        }

        return creditRepository.Create(credit);
    }

    public Boolean Edit(Long id, CreditDto creditDto) {
        Credit credit = creditRepository.getById(id);
        if (credit == null) {
            // throw new NullPointerException("Credit not fount");
            return false;
        }

        credit = toCredit(creditDto);
        credit.setIdCredit(id);

        return creditRepository.Edit(credit);
    }

    public Boolean Delete(Long id) {
        Credit credit = creditRepository.getById(id);
        if (credit == null) {
            // throw new NullPointerException("Credit not fount");
            return false;
        }

        return creditRepository.Delete(id);
    }

    private CreditDto toCreditDto(Credit credit) {
        CreditDto creditDto = new CreditDto();
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesembolso = (credit.getDisbursementDate() == null) ? ""
                : DateFormat.format(credit.getDisbursementDate());
        String fechaFin = (credit.getEndDate() == null) ? "" : DateFormat.format(credit.getEndDate());

        creditDto.setId(credit.getIdCredit());
        creditDto.setIdCliente(credit.getIdCustomer());
        creditDto.setNumeroCredito(credit.getCreditNumber());
        creditDto.setFechaDesembolso(fechaDesembolso);
        creditDto.setFechaFin(fechaFin);
        creditDto.setCuotas(credit.getNumberPayments());
        creditDto.setDiaPagoMensual(credit.getMonthlyPaymentDay());
        creditDto.setSaldoInicial(credit.getInitialBalance());
        creditDto.setSaldoActual(credit.getCurrentBalance());
        creditDto.setMontoPagoMensual(credit.getPaymentAmountMonthly());

        return creditDto;
    }

    private Credit toCredit(CreditDto creditDto) {
        Credit credit = new Credit();
        Date fechaDesembolso = new Date();
        Date fechaFin = new Date();
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaDesembolso = DateFormat.parse(creditDto.getFechaDesembolso());
            fechaFin = DateFormat.parse(creditDto.getFechaFin());
        } catch (Exception e) {
            fechaDesembolso = null;
            fechaFin = null;
        }

        credit.setIdCredit(creditDto.getId());
        credit.setIdCustomer(creditDto.getIdCliente());
        credit.setCreditNumber(creditDto.getNumeroCredito());
        credit.setDisbursementDate(fechaDesembolso);
        credit.setEndDate(fechaFin);
        credit.setNumberPayments(creditDto.getCuotas());
        credit.setMonthlyPaymentDay(creditDto.getDiaPagoMensual());
        credit.setInitialBalance(creditDto.getSaldoInicial());
        credit.setCurrentBalance(creditDto.getSaldoActual());
        credit.setPaymentAmountMonthly(creditDto.getMontoPagoMensual());

        return credit;
    }

}
