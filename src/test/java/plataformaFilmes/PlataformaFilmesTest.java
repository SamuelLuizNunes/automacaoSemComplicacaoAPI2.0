package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

    static String token;

    @Test
    public void validarLogin(){
        RestUtils.setBaseURI("http://localhost:8080/");

        String json = "{" +
                "    \"email\": \"aluno@email.com\"," +
                "    \"senha\": \"123456\"" +
                "}";

        RestUtils.post(json, ContentType.JSON, "auth");

        token = RestUtils.getResponse().jsonPath().get("token");
        System.out.println(token);

        assertEquals(200, RestUtils.getResponse().statusCode());
    }

    @BeforeAll
    public static void validarLoginMap(){
        RestUtils.setBaseURI("http://localhost:8080/");

        Map<String, String> map = new HashMap<>();
        map.put("email", "aluno@email.com");
        map.put("senha", "123456");

        RestUtils.post(map, ContentType.JSON, "auth");

        token = RestUtils.getResponse().jsonPath().get("token");
        System.out.println(token);

        assertEquals(200, RestUtils.getResponse().statusCode());
    }


    @Test
    public void validarConsultaCategorias(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        RestUtils.get(header, "categorias");

        assertEquals(200, RestUtils.getResponse().statusCode());

        System.out.println(RestUtils.getResponse().jsonPath().get().toString());

        String categoria = RestUtils.getResponse().jsonPath().get("tipo[2]");
        assertEquals("Terror", categoria);
        List<String> listTipo = RestUtils.getResponse().jsonPath().getList("tipo");
        assertTrue(listTipo.contains("Crime"));

    }

}
