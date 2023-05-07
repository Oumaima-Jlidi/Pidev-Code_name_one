/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Specialite;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HP
 */
public class ServiceSpec {
    public static ServiceSpec instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Specialite> listCategory=new ArrayList<>();

    public ServiceSpec() {
        req = new ConnectionRequest();
    }

    public static ServiceSpec getInstance() {
        if (instance == null) {
            instance = new ServiceSpec();
        }
        return instance;
    }
//ajout
    public boolean addSpecialite(Specialite t) {
        String nom=t.getNom();
        String Description = t.getDescription();
    
     
       
    //  String url =  "http://127.0.0.1:8000/"+ "addArtMobile/" +  "/" + description;
String url= "http://127.0.0.1:8000/"+"specialites/spec?nom="+t.getNom()+"&description="+t.getDescription();
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
   
      
     public ArrayList<Specialite> affichageArticle()
    {

        ArrayList<Specialite> result = new ArrayList<>();
        String url ="http://127.0.0.1:8000/"+"sujet/listetest";
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
                       Specialite c = new Specialite();
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

    public boolean modifierSpec(Specialite specialite) {
    try {
        String url = "http://127.0.0.1:8000/specialites/spec/modifier/" + specialite.getId();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("PUT");
        request.addArgument("nom", specialite.getNom());
        request.addArgument("description", specialite.getDescription());

        NetworkManager.getInstance().addToQueueAndWait(request);
        int responseCode = request.getResponseCode();
        return responseCode == 200; // Réponse HTTP 200 OK
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean deleteArticle(int id ) {
        String url = "http://127.0.0.1:8000/" +"specialites/spec/?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
   
}

    
   

