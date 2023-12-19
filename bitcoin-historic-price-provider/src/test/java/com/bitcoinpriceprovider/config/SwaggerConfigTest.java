package com.bitcoinpriceprovider.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SwaggerConfig.class)
public class SwaggerConfigTest {

    @Autowired
    private SwaggerConfig swaggerConfig;

    @Test
    public void testOpenAPIBean() {
        assertNotNull(swaggerConfig);
        OpenAPI openAPI = swaggerConfig.usersMicroserviceOpenAPI();
        assertNotNull(openAPI);
    }

    @Test
    public void testOpenAPITitle() {
        OpenAPI openAPI = swaggerConfig.usersMicroserviceOpenAPI();
        assertNotNull(openAPI.getInfo());
        assertEquals("BitCoin Historic Price Index Provider", openAPI.getInfo().getTitle());
    }

    @Test
    public void testOpenAPIDescription() {
        OpenAPI openAPI = swaggerConfig.usersMicroserviceOpenAPI();
        assertNotNull(openAPI.getInfo());
        assertEquals("Service provide the historical Bitcoin Price Indices based on the date range provided",
                openAPI.getInfo().getDescription());
    }

    @Test
    public void testOpenAPIVersion() {
        OpenAPI openAPI = swaggerConfig.usersMicroserviceOpenAPI();
        assertNotNull(openAPI.getInfo());
        assertEquals("1.0", openAPI.getInfo().getVersion());
    }
}
