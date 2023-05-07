/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;
import com.mycompany.gui.BaseForm;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.services.UserServices;
import com.mycompany.myapp.utils.UserSession;


/**
 *
 * @author Sondes
 */
public class HomeForm extends BaseForm {
    
    

    private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final String ADD_BUTTON_TEXT = "Ajouter consultation";
    private static final String LIST_BUTTON_TEXT = "Afficher Les consultations";
     private static final String DELETE_BUTTON_TEXT = "supprimer consultations";
UserServices us;

    public HomeForm(Resources res,User user) {
        
        super("Users", BoxLayout.y());
         this.us = UserServices.getInstance();
      
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Users");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        setTitle("Menu");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));

        Button addButton = new Button("");
        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButton.setText(ADD_BUTTON_TEXT);
        addButton.addActionListener(e -> new AddConsultationForm(res).show());

        Button listButton = new Button("");
        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listButton.setText(LIST_BUTTON_TEXT);
        listButton.addActionListener(e -> new listConsultation(res).show());
        
        
        Button delete = new Button("");
        delete.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        delete.setText(DELETE_BUTTON_TEXT);
        delete.addActionListener(e -> new deleteConsultation(res).show());

        addAll(addButton, listButton,delete);
    }
   
}





     


