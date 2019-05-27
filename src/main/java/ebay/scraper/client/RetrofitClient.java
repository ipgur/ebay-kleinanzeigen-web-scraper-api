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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import ebay.scraper.model.ResultPage;

import java.io.IOException;
import java.util.Map;

@Configuration
public class RetrofitClient {

    @Autowired
    private OkHttpClient httpClient;

    @Autowired
    private Retrofit retrofit;

    @Autowired
    private RequestService requestService;

    @Bean
    OkHttpClient providesHTTPClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Bean
    Retrofit providesRetrofit(@Value("${ebay.scrape.url:http://ebay-kleinanzeigen.de/}") String ebayScrapeUrl) {
        return new Retrofit.Builder()
                .baseUrl(ebayScrapeUrl)
                .client(httpClient)
                .addConverterFactory(JspoonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Bean
    RequestService providesRequestService() {
        return retrofit.create(RequestService.class);
    }

    public ResultPage query(Map<String, String> parameters) throws IOException {
        Call<ResultPage> resultPageCall = requestService.getResultPage(parameters);
        Response<ResultPage> res = resultPageCall.execute();
        return res.body();
    }
}
