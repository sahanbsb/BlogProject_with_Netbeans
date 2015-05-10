/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Sahan
 */
public class Post_store {
    
    public static void main(String [] args){
        add_post("test post 1", "this is the \n content of \n post 1.");
        show_post(1);
        addcomment("Hello my comment",1);
        show_post(1);
        
    }
    
    public static void add_post(String title, String content) {
 
	JSONObject obj = new JSONObject();
	obj.put("title", title);
	obj.put("content", content);
        
        JSONArray comments = new JSONArray();
        
	obj.put("comments", comments);
        
        int lastid = getlastid();

        obj.put("id", lastid);
        
	try(FileWriter file = new FileWriter(".\\posts\\"+lastid+".json");) {
            file.write(obj.toJSONString());
            file.flush();
            file.close();
 
	} catch (IOException e) {
            System.out.println(e);
	}
        
        lastid++;
        
        setlastid(lastid);
 
	//System.out.print(obj);
 
     }
    
    public static int getlastid(){
        JSONParser parser = new JSONParser();
        int lastid = 1;
        try {
 
            Object idObj = parser.parse(new FileReader(".\\posts\\lastid.json"));

            JSONObject jsonObject = (JSONObject) idObj;

            String slastid = jsonObject.get("lastid").toString();
            lastid = Integer.parseInt(slastid);
            
            System.out.println(lastid);
 
	} catch (FileNotFoundException e) {
            JSONObject newIdObj = new JSONObject();
            lastid = 1;
            newIdObj.put("lastid", lastid);
            
            try(FileWriter file = new FileWriter(".\\posts\\lastid.json");) {
            file.write(newIdObj.toJSONString());
            file.flush();
            file.close();
 
            } catch (IOException ex) {
                System.out.println(e);
            }
	} catch (IOException e) {
            System.out.println(e);
	} catch (ParseException e) {
            System.out.println(e);
	}
        
        return lastid;
    }
    
    public static void setlastid(int lastid){
        
        JSONObject obj = new JSONObject();
        obj.put("lastid", lastid);
        try(FileWriter file = new FileWriter(".\\posts\\lastid.json");) {
            file.write(obj.toJSONString());
            file.flush();
            file.close();
 
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }
    
    public static String getposttitle(int id) {
 
	JSONParser parser = new JSONParser();
        String title="";
 
	try {
 
            Object obj = parser.parse(new FileReader(".\\posts\\"+id+".json"));

            JSONObject jsonObject = (JSONObject) obj;

            title = (String) jsonObject.get("title");
            //System.out.println(title);
 
	} catch (FileNotFoundException e) {
            System.out.println(e);
	} catch (IOException e) {
            System.out.println(e);
	} catch (ParseException e) {
            System.out.println(e);
	}
        
        return title;
 
     }
    
    public static String getpostcontent(int id) {
 
	JSONParser parser = new JSONParser();
        String content="";
 
	try {
 
            Object obj = parser.parse(new FileReader(".\\posts\\"+id+".json"));

            JSONObject jsonObject = (JSONObject) obj;

            content = (String) jsonObject.get("content");
            //System.out.println(content);
 
	} catch (FileNotFoundException e) {
            System.out.println(e);
	} catch (IOException e) {
            System.out.println(e);
	} catch (ParseException e) {
            System.out.println(e);
	}
        
        return content;
 
     }
    
    public static List<String> getpostcomments(int id) {
 
	JSONParser parser = new JSONParser();
        List<String> comments = new ArrayList<>();
 
	try {
 
            Object obj = parser.parse(new FileReader(".\\posts\\"+id+".json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray Jcomments = (JSONArray) jsonObject.get("comments");
            if(Jcomments!=null){
		Iterator<String> iterator = Jcomments.iterator();
		while (iterator.hasNext()) {
                    comments.add(iterator.next());
		}
            }
 
	} catch (FileNotFoundException e) {
            System.out.println(e);
	} catch (IOException e) {
            System.out.println(e);
	} catch (ParseException e) {
            System.out.println(e);
	}
        
        return comments;
 
     }
    
    public static void addcomment(String comment, int id){
        String title = getposttitle(id);
        String content = getpostcontent(id);
        List<String> comments = getpostcomments(id);
        
        comments.add(comment);
        
        update_post(title, content, comments, id);
    }
    
    public static void update_post(String title, String content, List<String> comments, int id){
        JSONObject obj = new JSONObject();
	obj.put("title", title);
	obj.put("content", content);
        
        JSONArray Jcomments = new JSONArray();
        for(String i : comments){
            Jcomments.add(i);
        }
        obj.put("comments", comments);
        
        try(FileWriter file = new FileWriter(".\\posts\\"+id+".json");) {
            file.write(obj.toJSONString());
            file.flush();
            file.close();
 
	} catch (IOException e) {
            System.out.println(e);
	}
 
	//System.out.print(obj);
    }
    
    public static void show_post(int id){
        String title = getposttitle(id);
        String content = getpostcontent(id);
        List<String> comments = getpostcomments(id);
        
        System.out.println(title);
        System.out.println(content);
        for(String i : comments){
            System.out.println(i);
        }
    }
}