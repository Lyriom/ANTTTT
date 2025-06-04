package com.example.ARQSRI.model;

import lombok.Data;

@Data
public class VehiculoDTO {
    private String numeroPlaca;
    private String marca;
    private String modelo;
    private String anioFabricacion;
    private String tipoVehiculo;
    private String numeroMotor;
    private String numeroChasis;
    private String propietario;
}
