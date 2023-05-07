/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.services.ServiceConsultation;
import com.mycompany.myapp.entities.services.UserServices;

/**
 *
 * @author USER
 */
public class deleteConsultation extends BaseForm {
        UserServices us;

     public deleteConsultation(Resources res) {
         
         
          super("Users", BoxLayout.y());
          
         this.us = UserServices.getInstance();
      
       Toolbar tb = new Toolbar(true);
setToolbar(tb);

// Get the style of the toolbar
Style style = tb.getUnselectedStyle();

// Set the background color to black
style.setBgColor(0x000000);

        getTitleArea().setUIID("Container");
        setTitle("Users");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
     
        setTitle("supprimer une consultation");
        setLayout(BoxLayout.yBottom());
        TextField id =new TextField("", "consultation id");
        id.setUIID("TextFieldBlack");
        
          Button btnAdd = new Button("Delet");
          btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                if(ServiceConsultation.getInstance().deletConsultation(id.getAsInt(BASELINE)))
                {
                    Dialog.show("Success", "Activite Deleted", "ok",null);
                }
                else
                {
                    Dialog.show("Error", "Activite Error", "ok",null);
                }
            }
          }); 
           
          
         
           addAll(id,btnAdd);
     }
    
}
