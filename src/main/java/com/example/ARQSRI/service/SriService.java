package com.example.ARQSRI.service;

import com.example.ARQSRI.model.ContribuyenteDTO;
import com.example.ARQSRI.model.VehiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SriService {

    private final RestTemplate restTemplate;

    @Autowired
    public SriService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean rucExiste(String ruc) {
        String url = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc=" + ruc;
        try {
            Boolean response = restTemplate.getForObject(url, Boolean.class);
            return response != null && response;
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar existencia de RUC en SRI", e);
        }
    }
    public ContribuyenteDTO obtenerContribuyente(String ruc) {
        String url = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?ruc=" + ruc;
        try {
            return restTemplate.getForObject(url, ContribuyenteDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos del contribuyente", e);
        }
    }
    public VehiculoDTO obtenerDatosVehiculo(String placa) {
        String url = "https://srienlinea.sri.gob.ec/sri-matriculacion-vehicular-recaudacion-servicio-internet/rest/BaseVehiculo/obtenerPorNumeroPlacaOPorNumeroCampvOPorNumeroCpn?numeroPlacaCampvCpn=" + placa;
        try {
            return restTemplate.getForObject(url, VehiculoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos del vehículo con placa: " + placa, e);
        }
    }


}
