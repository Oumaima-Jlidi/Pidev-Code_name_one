/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;


import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.services.UserServices;

import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Islem
 */
public class AddArticleForm extends Form {
  
    public AddArticleForm(Form previous) {
        
    setTitle("Ajouter une Article"); 
    setLayout(BoxLayout.y());
    
    // Create input fields and button
    TextField tfNom = new TextField("", "Nom");
    TextField tfDescription = new TextField("", "Description");
    Button btnAdd = new Button("Ajouter");
    
    // Add action listener to button
    btnAdd.addActionListener(evt -> {
        if (tfDescription.getText().length() == 0 || tfNom.getText().length() == 0) {
            Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
        } else {
            try {
                // Create new Article object and add it to the server
                ConnectionRequest connectionRequest = new ConnectionRequest() {
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        JSONParser parser = new JSONParser();
                        String response = Util.readToString(input);
                            if (response.equals("success")) {
                                Dialog.show("Succès", "Article ajoutée avec succès", new Command("OK"));
                                // Clear input fields
                                tfNom.setText("");
                                tfDescription.setText("");
                            } else {
                                Dialog.show("Succès", "Article a été ajoutée avec succès", new Command("OK"));
                            }
                            }
                    };
                connectionRequest.setPost(true);
                connectionRequest.setHttpMethod("POST");
                connectionRequest.setUrl("http://127.0.0.1:8000/article/add/articleJson?");
                connectionRequest.addArgument("nom", tfNom.getText());
                connectionRequest.addArgument("description", tfDescription.getText());
                NetworkManager.getInstance().addToQueue(connectionRequest);
            } catch (NumberFormatException e) {
                Dialog.show("Error", "Invalid input", new Command("OK"));
            }
        }
        
    });
    
    // Add input fields and button to the form
    addAll(tfNom, tfDescription, btnAdd);
    
    // Add back button to the toolbar
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

}
   

    

