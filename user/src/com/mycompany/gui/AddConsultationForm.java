/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.consultation;
import com.mycompany.myapp.entities.services.ServiceConsultation;
import com.mycompany.myapp.entities.services.UserServices;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USER
 */
public class AddConsultationForm extends BaseForm {
UserServices us;
    public AddConsultationForm(Resources res) {
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

        // Create input fields and button
        Button btnAdd = new Button("Ajouter");
         TextField url =new TextField("", "lien meet");
          url.setUIID("TextFieldBlack");
        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            
                
                    // Create new consultation object and add it to the server
                    
                    if (ServiceConsultation.getInstance().addConsultation(url.getText())) {
                        Dialog.show("Succès", "Consultation ajoutée avec succès", new Command("OK"));
                    } else {
                        Dialog.show("Erreur", "Erreur serveur", new Command("OK"));
                    }
              
            
        });

        // Add input fields and button to the form
        addAll( btnAdd,url);

        // Add back button to the toolbar
        
    }
}
