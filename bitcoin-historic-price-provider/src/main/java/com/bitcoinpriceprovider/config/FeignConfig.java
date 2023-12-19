package com.bitcoinpriceprovider.config;

    import com.bitcoinpriceprovider.model.response.CoinDeskHistoricAPIResponse;
    import com.bitcoinpriceprovider.model.response.CurrencyInfo;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.databind.type.CollectionType;
    import com.fasterxml.jackson.databind.type.MapType;
    import com.fasterxml.jackson.databind.type.TypeFactory;
    import feign.Response;
    import feign.codec.Decoder;
    import feign.codec.ErrorDecoder;
    import feign.jackson.JacksonDecoder;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.lang.reflect.Type;
    import java.nio.charset.StandardCharsets;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Configuration
    public class FeignConfig {

        @Bean
        public Decoder feignDecoder() {
            return new JavaScriptDecoder(new JacksonDecoder());
        }

        @Bean
        public ErrorDecoder errorDecoder() {
            return new CustomErrorDecoder();
        }

        private static class JavaScriptDecoder implements Decoder {
            private final Decoder delegate;

            public JavaScriptDecoder(Decoder delegate) {
                this.delegate = delegate;
            }

            @Override
            public Object decode(Response response, Type type) throws IOException {
                // Check if the content type is application/javascript or text/html
                String contentType = response.headers().get("Content-Type").iterator().next();
                if (contentType.contains("application/javascript") || contentType.contains("text/html")) {
                    // Manually parse the JavaScript content
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
                        // Assuming the JavaScript content is a JSON string
                        String jsonString = reader.lines().reduce(String::concat).orElse("");
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode root = objectMapper.readTree(jsonString);
                        if(root.isArray()) {
                            TypeFactory typeFactory = objectMapper.getTypeFactory();
                            CollectionType collectionType = typeFactory.constructCollectionType(List.class, CurrencyInfo.class);
                            return objectMapper.<List<CurrencyInfo>>readValue(jsonString, collectionType);
                        } else {
                            // Extract the 'bpi' node and convert it to a map
                            JsonNode bpiNode = root.get("bpi");
                            TypeFactory typeFactory = objectMapper.getTypeFactory();
                            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Double.class);
                            Map<String, Double> bpi = objectMapper.convertValue(bpiNode, mapType);

                            // Create and return the response
                            CoinDeskHistoricAPIResponse coinDeskResponse = new CoinDeskHistoricAPIResponse();
                            coinDeskResponse.setBpi(bpi);
                            return coinDeskResponse;
                        }
                    }
                }  else {
                    // If the content type is not application/javascript or text/html, delegate to the default decoder
                    return delegate.decode(response, type);
                }
            }
        }
        private static class CustomErrorDecoder implements ErrorDecoder {
            @Override
            public Exception decode(String methodKey, Response response) {
                return new ErrorDecoder.Default().decode(methodKey, response);
            }
        }
    }
