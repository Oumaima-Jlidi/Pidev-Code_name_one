package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.services.ArticleService;




public class modifierArticle extends Form {
    public modifierArticle(Form previous, Article articles) {
        setTitle("Modifier Article");
        setLayout(BoxLayout.y());
        TextField tnom = new TextField(articles.getNom(), "Nom");
        TextField tdescription = new TextField(articles.getDescription(), "Description");
        
        Button btnValider = new Button("Modifier");
          
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tdescription.getText().length() == 0) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        int id = articles.getId();
                        Article t = new Article(id, tnom.getText(), tdescription.getText());
                        
                        if (ArticleService.getInstance().modifierArticle(t)) {
                            Dialog.show("Succès", "Article modifiée avec succès", new Command("OK"));
                        } else {
                            Dialog.show("Erreur", "Erreur lors de la modification de l'article", new Command("OK"));
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
