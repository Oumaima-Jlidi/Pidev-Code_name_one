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
import com.mycompany.myapp.entities.consultation;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceConsultation {
    public static ServiceConsultation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<consultation> listConsultation=new ArrayList<>();

    public ServiceConsultation() {
        req = new ConnectionRequest();
    }

    public static ServiceConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceConsultation();
        }
        return instance;
    }
    //++++++++++++++++++++++Add consultation++++++++++++++++++++
    public boolean addConsultation(String meet) {
    String url = "http://127.0.0.1:8000/"+"consultation/addconsultJson/"+meet+"/"+UserSession.instance.getU().getId_user();
    req.setUrl(url);
    req.setPost(true);
    
    req.addResponseListener((evt) -> {
        String response = new String(req.getResponseData());
        System.out.println("response: " + response);
        resultOK = true;
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    //++++++++++++++++++++++Affiche consultation++++++++++++++++++++
   public ArrayList<consultation> affichageConsultation() {
    ArrayList<consultation> result = new ArrayList<>();
    String url = "http://127.0.0.1:8000/consultation/allconsulationsjson";
    req.setUrl(url);
req.setPost(true);
    req.addResponseListener((NetworkEvent evt) -> {
        JSONParser jsonp;
                jsonp = new JSONParser();
        try (
                
             CharArrayReader reader = new CharArrayReader(new String(req.getResponseData()).toCharArray())) {
            Map<String, Object> mapMaison = jsonp.parseJSON(reader);
            List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapMaison.get("root");
            for (Map<String, Object> obj : ListOfMaps) {
                
                consultation c = new consultation();
                String dateString = (String) obj.get("date");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(dateString);

                String dateString1 = (String) obj.get("heuredebut");
                Date datedebut = format.parse(dateString1);

                String dateString2 = (String) obj.get("heurefin");
                Date datefin = format.parse(dateString2);

                float id = Float.parseFloat(obj.get("id").toString());

                c.setId((int) id);
                c.setDate(date);
                c.setHeuredebut(datedebut);
                c.setHeurefin(datefin);

                result.add(c);
            }
        } catch (IOException | NumberFormatException | ParseException ex) {
    System.out.println(ex.getMessage());
}
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}
////////////////////////////////////////////
   public boolean deletConsultation(int id)
    {
        String url="http://127.0.0.1:8000/consultation/deleteConsultationJSON"+"/"+id;
        req.setUrl(url);
        req.setPost(false);
       
         
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>()
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK=req.getResponseCode()==200;
              req.removeResponseListener(this);
            }
            
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
