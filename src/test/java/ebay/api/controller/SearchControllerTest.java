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
package ebay.api.controller;

import ebay.api.controller.SearchController;
import ebay.scraper.client.RequestService;
import ebay.scraper.client.RetrofitClient;
import ebay.scraper.model.Product;
import ebay.scraper.model.ResultPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import retrofit2.Response;
import retrofit2.mock.Calls;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
@ContextConfiguration(classes = {SearchController.class, RetrofitClient.class})
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RequestService requestService;

    @Test
    public void test() throws Exception {
        Map<String, String> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("keywords", "iphone"),
                new AbstractMap.SimpleEntry<>("locationStr", "darmstadt"),
                new AbstractMap.SimpleEntry<>("radius", "10"),
                new AbstractMap.SimpleEntry<>("minPrice", "50"),
                new AbstractMap.SimpleEntry<>("maxPrice", "90")
        );

        List<Product> products = Stream.of(
                Product.builder().title("mocked iphone 8").build(),
                Product.builder().title("mocked iphone se").build(),
                Product.builder().title("mocked iphone 7").build())
                .collect(Collectors.toList());
        ResultPage resultPage = ResultPage.builder().products(products).build();

        given(requestService.getResultPage(map)).willReturn(Calls.response(Response.success(resultPage)));


        mvc.perform(get("/search?keywords=iphone&locationStr=darmstadt&radius=10&maxPrice=90&minPrice=50")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[*]", hasSize(3)))
                .andExpect(jsonPath("$.products[0].title", is("mocked iphone 8")))
                .andExpect(jsonPath("$.products[1].title", is("mocked iphone se")))
                .andExpect(jsonPath("$.products[2].title", is("mocked iphone 7")));

    }
}
