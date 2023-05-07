package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Specialite;
import com.mycompany.myapp.services.ServiceSpec;

public class modifier extends Form {
    public modifier(Form previous,Specialite specialite) {
        setTitle("Modifier Spécialités");
        setLayout(BoxLayout.y());
        TextField tnom = new TextField(specialite.getNom(), "Nom");
        TextField tdescription = new TextField(specialite.getDescription(), "Description");
        
        Button btnValider = new Button("Modifier");
          
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tdescription.getText().length() == 0) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        int id = specialite.getId();
                        Specialite t = new Specialite(id, tnom.getText(), tdescription.getText());
                        
                        if (ServiceSpec.getInstance().modifierSpec(t)) {
                            Dialog.show("Succès", "Spécialité modifiée avec succès", new Command("OK"));
                        } else {
                            Dialog.show("Erreur", "Erreur lors de la modification de la spécialité", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("Erreur", "L'identifiant doit être un nombre", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tnom, tdescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

   
}
