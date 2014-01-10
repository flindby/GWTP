/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.mvp.rebind.version;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpRequestBase;

//import com.jayway.jsonpath.JsonPath;

/**
 * Gets latest GWTP version from Maven Central.
 * See http://search.maven.org/#api for docs on the Maven Central API.
 */

public class GwtpVersionExtractor {
    public String getLatestVersion() {
        String url = "http://search.maven.org/solrsearch/select?q=g:%22com" +
                ".gwtplatform%22+AND+a:%22gwtp-mvp-client%22";

        String json = getJson(url);
//        try {
//            json = readJsonFrom(new URL(url));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

//        return JsonPath.read(json, "$.response.docs[0].latestVersion");
        return json;
    }

    private String getJson(String url) {
        String response = "";
        try {
            ClassLoader classLoader = HttpRequestBase.class.getClassLoader();
            System.out.println(classLoader.getResource("org/apache/http/client/methods/HttpRequestBase.class"));
            response = Request.Get(url).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String readJsonFrom(URL url) {
        String json = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            json = IOUtils.toString(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
