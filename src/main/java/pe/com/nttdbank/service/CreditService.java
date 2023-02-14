package pe.com.nttdbank.service;

import java.util.List;

import pe.com.nttdbank.dto.CreditDto;

public interface CreditService {
    List<CreditDto> getAll();

    CreditDto getById(Long id);

    Boolean Create(CreditDto customerDto);

    Boolean Edit(Long id, CreditDto customerDto);

    Boolean Delete(Long id);

}
