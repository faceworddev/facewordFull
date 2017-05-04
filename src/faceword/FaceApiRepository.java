package faceword;

import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FaceApiRepository 
{
    private static final String key = "b96aec45316b431289e6c0c1d6b288ed";
    
    public static void DeleteFaceList(int id)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/"+id);


            URI uri = builder.build();
            HttpDelete request = new HttpDelete(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", key);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void AddSingleUserImage(int faceListId, int userId)
    {
        HttpClient httpclient = HttpClients.createDefault();
        
        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/"+faceListId+"/persistedFaces");
            builder.setParameter("userData", ""+userId+"");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", key);


            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"http://faceworddev.com/UserImages/"+userId+".png\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void AddUserImagesToFaceList(int faceListId, int userCount)
    {
        while(userCount > 0)
        {
             HttpClient httpclient = HttpClients.createDefault();

            try
            {
                URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/"+faceListId+"/persistedFaces");
                builder.setParameter("userData", ""+userCount+"");
                
                URI uri = builder.build();
                HttpPost request = new HttpPost(uri);
                request.setHeader("Content-Type", "application/json");
                request.setHeader("Ocp-Apim-Subscription-Key", key);


                // Request body
                StringEntity reqEntity = new StringEntity("{\"url\":\"http://faceworddev.com/UserImages/"+userCount+".png\"}");
                request.setEntity(reqEntity);

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                if (entity != null) 
                {
                    System.out.println(EntityUtils.toString(entity));
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            userCount--;
        }
    }
    
    public static ArrayList<PersistedFaceId> PopulatePersistedFaceIdArrayList(int faceListId)
    {
        ArrayList<PersistedFaceId> pfid = new ArrayList<>();
        
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/"+faceListId);


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", key);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            
            

            if (entity != null) 
            {

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(EntityUtils.toString(entity));
                JSONArray array = (JSONArray) json.get("persistedFaces");
                for(int i = 0; i < array.size(); i++)
                {
                    JSONObject ob = (JSONObject) array.get(i);
                    pfid.add(new PersistedFaceId(Integer.valueOf((String)ob.get("userData")), (String) ob.get("persistedFaceId")));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return pfid;
    }
    
    public static String GetCurrentUserFaceId(String imageHashHex)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/detect");

            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", key);
            
            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"http://www.faceworddev.com/CurrentUser/"+imageHashHex+".png\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(EntityUtils.toString(entity));
                JSONObject ob = (JSONObject) array.get(0);
                return (String) ob.get("faceId");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static String AuthenticateFace(int faceListId, String currentUserFaceId)
    {
        //Return the faceId of the match. If no match return null.
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/findsimilars");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", key);


            // Request body
            StringEntity reqEntity = new StringEntity("{    \n" +
            "    \"faceId\":\""+currentUserFaceId+"\",\n" +
            "    \"faceListId\":\""+faceListId+"\",  \n" +
            "    \"maxNumOfCandidatesReturned\":1,\n" +
            "    \"mode\": \"matchPerson\"\n" +
            "}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(EntityUtils.toString(entity));
                JSONObject ob = (JSONObject) array.get(0);
                double con = (double) ob.get("confidence");
                int percentage = (int) (con * 100);
                
                if(percentage > 70)
                {
                    return (String) ob.get("persistedFaceId");
                }
                else
                {
                    return null;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public static int CreateNewFaceList()
    {
        // Generate a random face list id between 0 and 10000
        int id = (int) (Math.random() *10000);
        
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/"+id);


            URI uri = builder.build();
            HttpPut request = new HttpPut(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", key);


            // Request body
            StringEntity reqEntity = new StringEntity("{\"name\":\"face_list\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return id;
    }
}
