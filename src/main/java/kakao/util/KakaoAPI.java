package kakao.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class KakaoAPI {
    private static final String API_KEY = "d2835d16cae64fb90955434f3775a5c0";
    private static final String API_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public static double[] getAddressCoordinate(String address) throws IOException {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String requestUrl = API_URL + "?query=" + encodedAddress;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("Authorization", "KakaoAK " + API_KEY);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray documents = jsonObject.getAsJsonArray("documents");

            if (documents.size() > 0) {
                JsonObject document = documents.get(0).getAsJsonObject();
                double latitude = document.get("y").getAsDouble();
                double longitude = document.get("x").getAsDouble();
                return new double[] {latitude, longitude};
            } else {
                return null;
            }
        }
    }
}
