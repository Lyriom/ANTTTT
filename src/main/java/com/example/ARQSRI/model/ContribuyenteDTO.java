package com.example.ARQSRI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContribuyenteDTO {
    @JsonProperty("numeroRuc")
    private String numeroRuc;

    @JsonProperty("razonSocial")
    private String razonSocial;

    @JsonProperty("estadoContribuyenteRuc")
    private String estadoContribuyenteRuc;

    @JsonProperty("actividadEconomicaPrincipal")
    private String actividadEconomicaPrincipal;

    @JsonProperty("tipoContribuyente")
    private String tipoContribuyente;

    @JsonProperty("regimen")
    private String regimen;

    @JsonProperty("obligadoLlevarContabilidad")
    private String obligadoLlevarContabilidad;

    @JsonProperty("agenteRetencion")
    private String agenteRetencion;

    @JsonProperty("contribuyenteEspecial")
    private String contribuyenteEspecial;

    @JsonProperty("contribuyenteFantasma")
    private String contribuyenteFantasma;

    @JsonProperty("transaccionesInexistente")
    private String transaccionesInexistente;

    @JsonProperty("informacionFechasContribuyente")
    private InformacionFechas informacionFechas;

    @Data
    public static class InformacionFechas {
        @JsonProperty("fechaInicioActividades")
        private String fechaInicioActividades;

        @JsonProperty("fechaCese")
        private String fechaCese;

        @JsonProperty("fechaReinicioActividades")
        private String fechaReinicioActividades;

        @JsonProperty("fechaActualizacion")
        private String fechaActualizacion;
    }
}
