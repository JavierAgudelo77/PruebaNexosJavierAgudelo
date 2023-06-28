package com.bankinc.credibanco.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bankinc.credibanco.dto.AdminCardDTO;
import com.bankinc.credibanco.exception.BankIncException;
import com.bankinc.credibanco.service.PruebaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class PruebaControllerTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PruebaServiceImpl pruebaServiceImpl;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    public void generarNumeroTarjetaTest() throws Exception {
        when(pruebaServiceImpl.generarNumeroTarjeta(any(Integer.class))).thenReturn("1234567890");
        mvc.perform(get("/api/v1/prueba/numeroTarjeta/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void issueCardTest() throws Exception {
        AdminCardDTO dto = new AdminCardDTO();
        dto.setIdproductoProducto(1);
        when(pruebaServiceImpl.generarNumeroTarjeta(any(Integer.class))).thenReturn("1234567890");
        
        String jsonString = objectMapper.writeValueAsString(dto);
        mvc.perform(post("/api/v1/prueba/issueCard")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonString))
            .andExpect(status().isOk());
        verify(pruebaServiceImpl, times(1)).generarNumeroTarjeta(any(Integer.class));
    }
    
    @Test
    public void crearAdministrador() throws Exception {
        mvc.perform(post("/api/v1/administrador").contentType("application/json").content("{\r\n"
        		+ "    \"idAdministrador\": 1,\r\n"
        		+ "    \"nombreadministrador\" : \"Javier Agudelo\"\r\n"
        		+ "}"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void crearProducto() throws Exception {
        mvc.perform(post("/api/v1/producto").contentType("application/json").content("{\r\n"
        		+ "    \"idproducto\" : 102030,\r\n"
        		+ "    \"descripcionproducto\" : \"Master Cartd Black\"\r\n"
        		+ "}"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void crearCliente() throws Exception {
        mvc.perform(post("/api/v1/producto").contentType("application/json").content("{\r\n"
        		+ "    \"idcliente\": 80577487,\r\n"
        		+ "    \"primerapellido\": \"Agudelo\",\r\n"
        		+ "    \"segundoapellido\": \"Alvarado\",\r\n"
        		+ "    \"primernombre\": \"Javier\",\r\n"
        		+ "    \"segundonombre\": \"Ricardo\"\r\n"
        		+ "}"))
                .andExpect(status().isOk());
    }    
    
    
    
    @Test
    public void activarTarjetaTest_validCard() throws Exception {
        when(pruebaServiceImpl.activateCard(any(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mvc.perform(post("/api/v1/prueba/card/enroll").contentType("application/json").content("\"1234\""))
                .andExpect(status().isOk());
    }


}
