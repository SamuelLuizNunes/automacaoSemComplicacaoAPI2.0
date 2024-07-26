package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import maps.LoginMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RestUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {


    @Test
    public void validarLogin(){
        RestUtils.setBaseURI("http://localhost:8080/");

        LoginMap.initLogin();

        RestUtils.post(LoginMap.getLogin(), ContentType.JSON, "auth");

        LoginMap.token = RestUtils.getResponse().jsonPath().get("token");

        assertEquals(200, RestUtils.getResponse().statusCode());
    }

    @BeforeAll
    public static void validarLoginMap(){
        RestUtils.setBaseURI("http://localhost:8080/");
        LoginMap.initLogin();

        RestUtils.post(LoginMap.getLogin(), ContentType.JSON, "auth");
        LoginMap.token = RestUtils.getResponse().jsonPath().get("token");

        assertEquals(200, RestUtils.getResponse().statusCode());
    }

    @Test
    public void validarConsultaCategorias(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + LoginMap.token);
        RestUtils.get(header, "categorias");

        assertEquals(200, RestUtils.getResponse().statusCode());

        System.out.println(RestUtils.getResponse().jsonPath().get().toString());

        String categoria = RestUtils.getResponse().jsonPath().get("tipo[2]");
        assertEquals("Terror", categoria);
        List<String> listTipo = RestUtils.getResponse().jsonPath().getList("tipo");
        assertTrue(listTipo.contains("Crime"));

    }

}
