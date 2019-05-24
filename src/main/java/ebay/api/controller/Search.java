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

import ebay.scraper.model.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ebay.scraper.client.RetrofitClient;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

@RestController
public class Search {

    @Autowired
    RetrofitClient retrofitClient;

    @GetMapping(value = "/search",
                params = { "keywords", "locationStr", "radius", "minPrice", "maxPrice" },
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultPage getProducts(@RequestParam("keywords") String keywords,
                           @RequestParam("locationStr") String locationStr,
                           @RequestParam("radius") String radius,
                           @RequestParam("minPrice") String minPrice,
                           @RequestParam("maxPrice") String maxPrice) throws IOException {

        Map<String, String> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("keywords", keywords),
                new AbstractMap.SimpleEntry<>("locationStr", locationStr),
                new AbstractMap.SimpleEntry<>("radius", radius),
                new AbstractMap.SimpleEntry<>("minPrice", minPrice),
                new AbstractMap.SimpleEntry<>("maxPrice", maxPrice)
        );

        return retrofitClient.query(map);
    }
}
