package pe.com.nttdbank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditDto {
    private Long id;
    private Long idCliente;
    private String numeroCredito;
    private String fechaDesembolso;
    private String fechaFin;
    private int cuotas;
    private int diaPagoMensual;
    private Double saldoInicial;
    private Double saldoActual;
    private Double montoPagoMensual;

    public CreditDto() {
    }
}
