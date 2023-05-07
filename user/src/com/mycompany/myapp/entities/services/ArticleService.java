/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HP
 */
public class ArticleService {
    public static ArticleService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Article> listCategory=new ArrayList<>();

    public ArticleService() {
        req = new ConnectionRequest();
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }
//ajout
    public boolean addArticle(Article t) {
        String nom=t.getNom();
        String Description = t.getDescription();
    
   String url = "http://127.0.0.1:8000/article/add/articleJson?" +
            "nom=" + t.getNom() +
            "&description=" + t.getDescription();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
     public ArrayList<Article> affichageArticle()
    {

        ArrayList<Article> result = new ArrayList<>();
         String url = "http://127.0.0.1:8000/article/All";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapMaison = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapMaison.get("root");
                    System.out.println(mapMaison);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                       Article c = new Article();
                         float id = Float.parseFloat(obj.get("id").toString());
                        String Nom = obj.get("nom").toString();
                         String Description = obj.get("description").toString();
                     
                       c.setId((int)id);
                        c.setNom(Nom);
                        
                        c.setDescription(Description); 
                       
                        
                        result.add(c);
                        System.out.println(c.getNom()+c.getDescription());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }    

   public boolean modifierArticle(Article article) {
    try {
        String url = "http://127.0.0.1:8000/article/edit/articleJson/" + article.getId();

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("POST");
        request.addArgument("nom", article.getNom());
        request.addArgument("description", article.getDescription());

        NetworkManager.getInstance().addToQueueAndWait(request);
        int responseCode = request.getResponseCode();
        return responseCode == 200; // Réponse HTTP 200 OK
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
 
         
/*
    public boolean deleteArticle(int id ) {
       
        String url = "http://127.0.0.1:8000/article/delete/articleJson/"+"/"+ id;
        req.setUrl(url);
         ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("POST");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }*/

      
  


public boolean deleteArticle(int id) {
    String url = "http://127.0.0.1:8000/article/delete/articleJson/" + id;

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);

    request.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            int responseCode = evt.getResponseCode();
            resultOK = responseCode == 200;
            request.removeResponseListener(this);
        }
    });

    try {
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
    
    
}

    
   

