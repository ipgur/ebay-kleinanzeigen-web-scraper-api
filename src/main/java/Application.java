/*
 * Copyright 2019 igur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import scraper.client.RequestServiceClient;
import scraper.model.Product;
import scraper.model.ResultPage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String [] args) {
        try (RequestServiceClient requestServiceClient = new RequestServiceClient()) {
            requestServiceClient.execute(Stream.of(new String[][] {
                    {"radius", "30"},
                    {"locationStr", "Darmstadt"},
                    {"keywords", "iphone SE"},
                    {"minPrice", "10"},
                    {"maxPrice", "200"}
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]))).subscribe(Application::printResultPage);
        }
    }

    private static void printResultPage(ResultPage resultPage) throws JsonProcessingException {
        List<Product> productList = resultPage.getProducts();

        for (Product p : productList) {
            System.out.println(p);
            String output = objectMapper.writeValueAsString(p);
            System.out.println(output);
        }
    }


}
