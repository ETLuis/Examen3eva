import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parseoJASON {
    static String json = "{\n" +
            "    \"pageInfo\": {\n" +
            "            \"pageName\": \"Homepage\",\n" +
            "            \"logo\": \"https://www.example.com/logo.jpg\"\n" +
            "    },\n" +
            "    \"posts\": [\n" +

            "            {\n" +
            "                \"client_id\": \"840715067852-afcp9897424tute3uh7fdam8upe3kcb4.apps.googleusercontent.com\",\n" +
            "                \"project_id\": \"examen3eva\",\n" +
            "                \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "                \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "                \"comments\": [{\"comment\":\"GENIAL\"},{\"comment\":\"No me gusta este examen\"}],\n" +
            "            },\n" +

            "    ]\n" +
            "}";
    public static void main(String[] args) throws JSONException {
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("project_id");
            String comments = arr.getJSONObject(i).getString("comments");
            System.out.println(post_id);
            System.out.println(comments);
        }
    }
}