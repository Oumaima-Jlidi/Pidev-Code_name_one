/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.consultation;
import com.mycompany.myapp.entities.services.ServiceConsultation;
import com.mycompany.myapp.entities.services.UserServices;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class listConsultation extends BaseForm{
    UserServices us;

    public listConsultation(Resources res) {
          super("Users", BoxLayout.y());
         this.us = UserServices.getInstance();
      
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Users");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        setTitle("Ajoute une nouvelle consultation");
        setLayout(BoxLayout.y());
       ArrayList<consultation> listConsultation = ServiceConsultation.getInstance().affichageConsultation();
       //  for( Actualite act : actualites) {
        //     if (act.getId()==)
        // }
        int k=0;
         for (consultation cons : listConsultation) {
           k++;
   // créer un label pour les attributs
   Label titre = new Label("Consultation Num "+k );
   Label date = new Label("Date : "+cons.getDate().toString());
   Label heuredebut = new Label("Heure debut : "+cons.getHeuredebut().toString());
   Label heurefin = new Label("Heure fin : "+cons.getHeurefin().toString());
   Label espace = new Label("      ");
   // ajouter le container à la forme
    
   Container container = BoxLayout.encloseY(titre,date, heuredebut ,heurefin,espace);
   add(container);
}
           
       
    }
    
}
