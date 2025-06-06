package com.example.ARQSRI.Controller;

import com.example.ARQSRI.model.ContribuyenteDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@RestController
@RequestMapping("/api/sri")
@CrossOrigin(origins = "*")
public class SriController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    public String testConnection() {
        return "Backend operativo - " + System.currentTimeMillis();
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarRuc(@RequestParam String ruc) {
        try {
            String urlExiste = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc=" + ruc;
            Boolean existe = restTemplate.getForObject(urlExiste, Boolean.class);

            if (existe == null || !existe) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El RUC no existe en el SRI");
            }

            String urlDatos = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?ruc=" + ruc;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            ResponseEntity<List<ContribuyenteDTO>> response = restTemplate.exchange(
                    urlDatos,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<ContribuyenteDTO>>() {}
            );

            List<ContribuyenteDTO> lista = response.getBody();

            if (response.getStatusCode() == HttpStatus.OK && lista != null && !lista.isEmpty()) {
                return ResponseEntity.ok(lista.get(0));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos no disponibles");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servicio: " + e.getMessage());
        }
    }

    @GetMapping("/matricula")
    public ResponseEntity<?> obtenerMatricula(@RequestParam String placa) {
        try {
            String url = "https://srienlinea.sri.gob.ec/sri-matriculacion-vehicular-recaudacion-servicio-internet/rest/BaseVehiculo/obtenerPorNumeroPlacaOPorNumeroCampvOPorNumeroCpn?numeroPlacaCampvCpn=" + placa;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener matrícula: " + e.getMessage());
        }
    }

    @Cacheable(value = "puntosLicencia", key = "#cedula + '_' + #placa")
    @GetMapping("/puntos")
    public ResponseEntity<?> obtenerPuntosLicencia(@RequestParam String cedula, @RequestParam String placa) {
        try {
            String url = "https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp?ps_tipo_identificacion=CED&ps_identificacion=" + cedula + "&ps_placa=" + placa;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al consultar puntos: " + e.getMessage());
        }
    }

}
