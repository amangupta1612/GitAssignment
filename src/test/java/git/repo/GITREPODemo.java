package git.repo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GITREPODemo {
public static final String Repo_Name="GitDemo";
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		createReposory();
		starredReposory();
		fetchedReposory();
		
	}
	
	
	public static void createReposory() throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> params = new HashMap<String, Object>();  

		params.put("name", Repo_Name);
		params.put("description", "This is your first repository");
		params.put("private", false);
		String url="https://api.github.com/user/repos?access_token=b8d4112eae1677d9b245cc0e58e252b1ee9bf32a";
		Map res=callWebService( params, url);
		if(res.get("Status_code")!=null && res.get("Status_code").toString().equals("201") ){
			System.out.println("***************Repository Created***********************");
			System.out.println(res);
			
		}else{
			System.out.println("Repo not created ");
			System.out.println(res);
		}

	}
	
	
	public static void starredReposory() throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> params = new HashMap<String, Object>();  

		
		String url="https://api.github.com/user/starred/amangupta1612/"+Repo_Name+"?access_token=b8d4112eae1677d9b245cc0e58e252b1ee9bf32a";
		Map res=starredWebService(  url);

	}
	
	public static void fetchedReposory() throws JsonParseException, JsonMappingException, IOException{
		
		String url="https://api.github.com/user/repos?access_token=b8d4112eae1677d9b245cc0e58e252b1ee9bf32a";
		
		getWebService(url);
	}
	public static Map callWebService( Map<String, Object> params,String url) throws JsonParseException, JsonMappingException, IOException{
		 RestTemplate restTemplate = new RestTemplate();
		  
		
		
		 ObjectMapper mapper = new ObjectMapper();
		
		 HttpEntity<Object> entityReq = new HttpEntity<Object>(params);
		 Map<String,Object>map=new HashMap<String,Object>();
		 try{
		 ResponseEntity<?> result	= restTemplate.exchange(url, HttpMethod.POST, entityReq,Object.class);
		 Map<String,Object>map1=(Map<String, Object>) result.getBody();
		 map1.put("Status_code", result.getStatusCode());
		
		 return map1;
		 }catch(HttpClientErrorException e){
			 System.out.println(e.getStatusCode());
			 System.out.println(e.getStatusText());
			 System.out.println(e.getResponseBodyAsString());
			 map=mapper.readValue(e.getResponseBodyAsString(),Map.class);
			 return map;
		 }
	
	}
	
	public static Map starredWebService( String url) throws JsonParseException, JsonMappingException, IOException{
		 RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Length","0");
		
		 ObjectMapper mapper = new ObjectMapper();
		
		 HttpEntity<Object> entityReq = new HttpEntity<Object>(headers);
		 Map<String,Object>map=new HashMap<String,Object>();
		 try{
		 ResponseEntity<?> result	= restTemplate.exchange(url, HttpMethod.PUT, entityReq,Object.class);
		 if(result.getStatusCode().toString().equals("204")){
				System.out.println("************Starred done***********");
			}
		 Map<String,Object>map1=(Map<String, Object>) result.getBody();
		
		 return map1;
		 }catch(HttpClientErrorException e){
			 System.out.println(e.getStatusCode());
			 System.out.println(e.getStatusText());
			 System.out.println(e.getResponseBodyAsString());
			 map=mapper.readValue(e.getResponseBodyAsString(),Map.class);
			 return map;
		 }
	
	}
	
	public static void getWebService( String url) throws JsonParseException, JsonMappingException, IOException{
		 RestTemplate restTemplate = new RestTemplate();
		  
		
		
		 ObjectMapper mapper = new ObjectMapper();
		
		 Map<String,Object>map=new HashMap<String,Object>();
		 try{
		 List<Map> result	= restTemplate.getForObject(url, List.class);
		
		 
			 System.out.println("***************FETCHING REPO*************");
			 
		for(Map data:result){
			if(Repo_Name.equals(data.get("name")+"")){
				System.out.println("**********repo found**************");
				System.out.println(data);
				break;
			}
		}
		 }catch(HttpClientErrorException e){
			 System.out.println(e.getStatusCode());
			 System.out.println(e.getStatusText());
			 System.out.println(e.getResponseBodyAsString());
			 map=mapper.readValue(e.getResponseBodyAsString(),Map.class);
			 System.out.println("Error ");
			 System.out.println(map);
		 }
	
	}

}
