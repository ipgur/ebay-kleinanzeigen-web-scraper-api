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
package ebay.scraper;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import ebay.scraper.client.RetrofitClient;
import ebay.scraper.model.ResultPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes = {RetrofitClient.class})
public class RequestServiceTest {

    @Autowired
    RetrofitClient retrofitClient;

    private final MockWebServer mockWebServer = new MockWebServer();

    @Before
    public void setUp() throws IOException {
        mockWebServer.enqueue(new MockResponse().setBody("<ul id=\"srchrslt-adtable\" class=\"itemlist-separatedbefore ad-list lazyload\"> <li class=\"ad-listitem lazyload-item \"> <article class=\"aditem\" data-adid=\"1108599188\"> <div class=\"aditem-image\"> <div class=\"imagebox srpimagebox\" data-href=\"/s-anzeige/apple-iphone-se-rose-16-gb/1108599188-173-4895\" data-imgsrc=\"https://i.ebayimg.com/00/s/NDY3WDgzMA==/z/3ykAAOSwkmJcxC2X/$_9.JPG\" data-imgtitle=\"Apple iPhone SE Rose 16 GB Hessen - Darmstadt Vorschau\" style=\"cursor: pointer;\"> <img src=\"https://i.ebayimg.com/00/s/NDY3WDgzMA==/z/3ykAAOSwkmJcxC2X/$_9.JPG\" alt=\"Apple iPhone SE Rose 16 GB Hessen - Darmstadt Vorschau\"></div> </div> <div class=\"aditem-main\"> <h2 class=\"text-module-begin\"> <a class=\"ellipsis\" href=\"/s-anzeige/apple-iphone-se-rose-16-gb/1108599188-173-4895\">Apple iPhone SE Rose 16 GB</a> </h2> <p>Gut erhaltenes Iphone SE mit Orginial Zubehör und Verpackung. Zusätzlich gibt es 3 originale Apple...</p> <p class=\"text-module-end\"> </p> </div> <div class=\"aditem-details\"> <strong>120 €</strong> <br> 64285<br> Darmstadt<br> 2 km</div> <div class=\"aditem-addon\"> 27.04.2019</div> </article> </li> <li class=\"ad-listitem lazyload-item \"> <article class=\"aditem\" data-adid=\"1054635595\"> <div class=\"aditem-image\"> <div class=\"imagebox srpimagebox\" data-href=\"/s-anzeige/apple-iphone-se-rose-gold-mit-ovp/1054635595-173-4895\" data-imgsrc=\"https://i.ebayimg.com/00/s/NDY3WDgzMA==/z/Q5oAAOSwddpcZFRe/$_9.JPG\" data-imgtitle=\"Apple iPhone SE rose gold mit OVP Hessen - Darmstadt Vorschau\" style=\"cursor: pointer;\"> <img src=\"https://i.ebayimg.com/00/s/NDY3WDgzMA==/z/Q5oAAOSwddpcZFRe/$_9.JPG\" alt=\"Apple iPhone SE rose gold mit OVP Hessen - Darmstadt Vorschau\"></div> </div> <div class=\"aditem-main\"> <h2 class=\"text-module-begin\"> <a class=\"ellipsis\" href=\"/s-anzeige/apple-iphone-se-rose-gold-mit-ovp/1054635595-173-4895\">Apple iPhone SE rose gold mit OVP</a> </h2> <p>Gut erhaltenes Iphone SE mit Orginial Zubehör und Verpackung. Zusätzlich gibt es 3 originale Apple...</p> <p class=\"text-module-end\"> </p> </div> <div class=\"aditem-details\"> <strong>120 €</strong> <br> 64285<br> Darmstadt<br> 2 km</div> <div class=\"aditem-addon\"> 13.02.2019</div> </article> </li> <li class=\"ad-listitem\"> <div id=\"pla-container\" class=\"box-centered\" style=\"\"></div> <script type=\"application/javascript\"> ProductLister.Loader.run({\"provider\":[\"AFSH\"],\"afshConfig\":{\"channel\":\"Total c_undefined r_HE d_SRPS_Total d_SRPS_undefined d_yield_test_search srp_rebrush-b-DEFAULT yo_B\",\"query\":\"iphone se\",\"pubId\":\"partner-vert-pla-ebay-kleinanzeigen-de-srp\"}}); </script> </li> </ul>"));
        mockWebServer.start(8080);
    }

    @Test
    public void testSuccessResponse() throws Exception {
        ResultPage resultPage = retrofitClient.query(new HashMap<>());
        System.out.println(resultPage);
        assertEquals(2, resultPage.getProducts().size());
        assertEquals("Apple iPhone SE Rose 16 GB", resultPage.getProducts().get(0).getTitle());
        assertEquals("Apple iPhone SE rose gold mit OVP", resultPage.getProducts().get(1).getTitle());

        assertEquals("120 €", resultPage.getProducts().get(0).getPrice());
        assertEquals("120 €", resultPage.getProducts().get(1).getPrice());

        assertEquals("www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-rose-16-gb/1108599188-173-4895", resultPage.getProducts().get(0).getLink());
        assertEquals("www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-rose-gold-mit-ovp/1054635595-173-4895", resultPage.getProducts().get(1).getLink());

        assertEquals("27.04.2019", resultPage.getProducts().get(0).getAddedOn());
        assertEquals("13.02.2019", resultPage.getProducts().get(1).getAddedOn());
    }
}
