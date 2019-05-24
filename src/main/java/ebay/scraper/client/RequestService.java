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
package ebay.scraper.client;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ebay.scraper.model.ResultPage;

import java.util.Map;

public interface RequestService {
    @GET("s-suchanfrage.html?sortingField=SORTING_DATE&action=find")
    Single<ResultPage> getResultPage(@Query("keywords") String keywords, @Query("minPrice") int minPrice,
                                     @Query("maxPrice") int maxPrice,
                                     @Query("radius") int radius, @Query("locationStr") String location);

    @GET("s-suchanfrage.html?sortingField=SORTING_DATE&action=find")
    Single<ResultPage> getSingleResultPage(@QueryMap Map<String, String> options);

    @GET("s-suchanfrage.html?sortingField=SORTING_DATE&action=find")
    Call<ResultPage> getResultPage(@QueryMap Map<String, String> options);
}
