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
package scraper.client;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import scraper.model.ResultPage;

import java.util.Map;

public class RequestServiceClient implements AutoCloseable{

    private final Retrofit retrofit;
    private final RequestService requestService;
    private final OkHttpClient okHttpClient;

    public RequestServiceClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = createRetrofit(okHttpClient);
        requestService = retrofit.create(RequestService.class);
    }


    private Retrofit createRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://www.ebay-kleinanzeigen.de/")
                .client(httpClient)
                .addConverterFactory(JspoonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public Single<ResultPage> execute(Map<String, String> parameters) {
        return requestService.getResultPage(parameters);
    }

    @Override
    public void close() {
        System.out.println("Shutting down the executor service of the http client");
        okHttpClient.dispatcher().executorService().shutdown();
        okHttpClient.connectionPool().evictAll();
    }
}
