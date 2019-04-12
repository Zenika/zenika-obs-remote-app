package zenika.obs.api;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {

    /**
     * Constructs a string url with base + params
     * @param base base URL
     * @param params params to add to the URL
     * @return the url string with all parameters
     */
    public static String buildCustomUrl(String base, List<NameValuePair> params) {
        String url = base.concat("?")
                .concat(params.get(0).getName().concat("=").concat(params.get(0).getValue()));
        for(int i = 1; i < params.size(); i++) {
            url.concat("&").concat(params.get(i).getName().concat("=").concat(params.get(i).getValue()));
        }

        return url;
    }

    /**
     * Sends a HTTP GET request to url with params using httpClient
     * @param httpClient client sending request
     * @param url request URL
     * @param params request PARAMS
     * @return
     * @throws Exception
     */
    public static CloseableHttpResponse sendGetRequestToNodeAPI(
            CloseableHttpClient httpClient, String url, List<NameValuePair> params) throws Exception {
        HttpGet httpGet = new HttpGet(url);

        if(params != null) {
            httpGet = new HttpGet(buildCustomUrl(url, params));
        }

        return httpClient.execute(httpGet);
    }

    /**
     * Sends a HTTP POST request to url with params using httpClient
     * @param httpClient client sending request
     * @param url request URL
     * @param params request PARAMS
     * @return
     * @throws Exception
     */
    public static CloseableHttpResponse sendPostRequestToNodeAPI(
            CloseableHttpClient httpClient, String url, List<NameValuePair> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        if(params != null) {
            httpPost = new HttpPost(buildCustomUrl(url, params));
        }
        return httpClient.execute(httpPost);
    }

    /**
     * Reads response within httpResponse
     * @param httpResponse
     * @return httResponse content as String object
     * @throws Exception
     */
    public static String readResponseFromNodeAPI(CloseableHttpResponse httpResponse) throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
