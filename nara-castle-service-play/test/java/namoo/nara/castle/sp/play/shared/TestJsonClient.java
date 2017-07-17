package namoo.nara.castle.sp.play.shared;

import com.fasterxml.jackson.databind.JsonNode;
import namoo.nara.share.util.json.JsonUtil;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.util.List;
import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.*;
import static play.mvc.Http.Status.OK;

public class TestJsonClient {
    //
    private TestJsonClient() {
        // Do not instantiation
    }

    public static <T> T getOne(String url, int serverPort, Class<T> type) {
        return requestOne(url, serverPort, "GET", type);
    }
    public static <T> List<T> getList(String url, int serverPort, Class<T> type) {
        //
        return requestList(url, serverPort, "GET", type);
    }

    public static <T> T postOne(String url, int serverPort, Class<T> type) {
        return requestOne(url, serverPort, "POST", type);
    }
    public static <T> List<T> postList(String url, int serverPort, Class<T> type) {
        //
        return requestList(url, serverPort, "POST", type);
    }


    private static <T> T requestOne(String url, int serverPort, String httpMethod, Class<T> type) {
        //
        JsonNode jsonNode = requestJson(url, serverPort, httpMethod);
        return JsonUtil.fromJson(jsonNode.toString(), type);
    }

    private static <T> List<T> requestList(String url, int serverPort, String httpMethod, Class<T> type) {
        //
        JsonNode jsonNode = requestJson(url, serverPort, httpMethod);
        return JsonUtil.fromJsonList(jsonNode.toString(), type);
    }

    private static JsonNode requestJson(String url, int serverPort, String httpMethod) {
        //
        try ( WSClient ws = play.test.WSTestClient.newClient(serverPort) ) {

            CompletionStage<WSResponse> stage = ws.url(url).execute(httpMethod);
            WSResponse response = stage.toCompletableFuture().get();

            assertThat(response.getStatus()).isEqualTo(OK);

            return response.asJson();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
