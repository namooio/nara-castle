package nara.castle.sp.play.shared;

import com.fasterxml.jackson.databind.JsonNode;
import namoo.nara.share.util.json.JsonUtil;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.util.List;
import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.fail;
import static play.mvc.Http.Status.OK;

public class ResourceTestClient {
    //
    private int serverPort;

    public ResourceTestClient(int serverPort) {
        // Do not instantiation
        this.serverPort = serverPort;
    }

    public <T> T get(String url, Class<T> responseType) {
        JsonNode jsonNode = requestJson(url, "GET");
        return JsonUtil.fromJson(jsonNode.toString(), responseType);
    }
    public <T> List<T> getList(String url, Class<T> responseType) {
        //
        JsonNode jsonNode = requestJson(url, "GET");
        return JsonUtil.fromJsonList(jsonNode.toString(), responseType);
    }

    public String post(String url, Object body) {
        JsonNode jsonNode = requestJson(url, "POST", Json.toJson(body));
        return jsonNode.toString();
    }
    public <T> T post(String url, Object body, Class<T> responseType) {
        JsonNode jsonNode = requestJson(url, "POST", Json.toJson(body));
        return JsonUtil.fromJson(jsonNode.toString(), responseType);
    }
    public <T> List<T> postAndGetList(String url, Object body, Class<T> responseType) {
        //
        JsonNode jsonNode = requestJson(url, "POST", Json.toJson(body));
        return JsonUtil.fromJsonList(jsonNode.toString(), responseType);
    }


    private JsonNode requestJson(String url, String httpMethod) {
        //
        return requestJson(url, httpMethod, Json.newObject());
    }
    private JsonNode requestJson(String url, String httpMethod, JsonNode body) {
        //
        try ( WSClient ws = play.test.WSTestClient.newClient(this.serverPort) ) {

            CompletionStage<WSResponse> stage = ws.url(url).setMethod(httpMethod).setBody(body).execute();
            WSResponse response = stage.toCompletableFuture().get();

            if (response.getStatus() != OK) {
                fail(response.getStatusText());
            }
            return response.asJson();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
