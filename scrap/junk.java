import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

class Junk {
    public static void main(String[] args) {
        try {
            System.out.println(
                    requestBearerToken("https://dev.twitter.com/docs/api/1.1/post/oauth2/token"));
        }     catch (IOException e) {
            System.out.println("Exception");

        }
    }

    // Constructs the request for requesting a bearer token and returns that token as a string
    private static String requestBearerToken(String endPointUrl) throws IOException {
        HttpsURLConnection connection = null;
        String encodedCredentials = "QWpQTWFnSFFGbXF4eHN6YVgxb205UTp2ejRWZWtTYWlKTWV4MHhHaEdPTWZtNDdjcTJWU1c0ekdJVERVZk1Z";	         
        try {
            URL url = new URL(endPointUrl);
            connection = (HttpsURLConnection) url.openConnection();          
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Host", "api.twitter.com");
            connection.setRequestProperty("User-Agent", "roomkey");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Content-Length", "29");
            connection.setUseCaches(false);

            writeRequest(connection, "grant_type=client_credentials");
            return readResponse(connection);     
            // Parse the JSON response into a JSON mapped object to fetch fields from.
            //JSONObject obj = (JSONObject)JSONValue.parse(readResponse(connection));

            //if (obj != null) {
            //    String tokenType = (String)obj.get("token_type");
            //    String token = (String)obj.get("access_token");
            // 
            //    return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            //}
            //return new String();
        }
        catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL specified.", e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    private static boolean writeRequest(HttpsURLConnection connection, String textBody) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();

            return true;
        }
        catch (IOException e) { return false; }
    }


    // Reads a response for a given connection and returns it as a string.
    private static String readResponse(HttpsURLConnection connection) {
        try {
            StringBuilder str = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        }
        catch (IOException e) { return new String(); }
    }
}
