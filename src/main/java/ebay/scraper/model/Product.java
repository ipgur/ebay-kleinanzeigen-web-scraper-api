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
package ebay.scraper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.droidsonroids.jspoon.annotation.Selector;

@NoArgsConstructor
@Data
public class Product {
    @Selector(".aditem-main > .text-module-begin > a") private String title;
    @Selector(".aditem-addon") private String addedOn;
    @Selector(".aditem-details > strong") private String price;
    @Selector(value = ".aditem-main > .text-module-begin > a",
              converter = PrefixHrefClassConverter.class) private String link;
}

