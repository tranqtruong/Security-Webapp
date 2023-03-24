package Security;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.*;

import javax.net.ssl.HttpsURLConnection;

public class RecaptchaVeritication {
	public static final String verificationurl = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secretKey = "6Lc3eEUdAAAAAFiEx_QlmSSKVGvkXr8hNSZdiqHk";
	public static final String USER_AGENT = "Mozilla/5.0";
	
	public static boolean verify(String gRecaptchaRespone) throws IOException {
		if(gRecaptchaRespone == null || "".equals(gRecaptchaRespone)) {
			return false;
		}
		
		try {
			URL obj = new URL(verificationurl);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String postParams = "secret=" + secretKey + "&response=" + gRecaptchaRespone;
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			return jsonObject.getBoolean("success");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
}
