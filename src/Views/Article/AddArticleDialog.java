/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Views.Article;

import Core.Globals;
import Core.Helper.DecimalInputVerifier;
import Model.Article.Article;
import Model.Article.Barcode;
import Model.Article.Category;
import Model.Article.Shop;
import Model.Views.BaseView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;

/**
 *
 * @author noldi
 */
public class AddArticleDialog extends javax.swing.JDialog implements BaseView
{
    private List<Category> Categories = new ArrayList<>();
    private List<Shop> Shops = new ArrayList<>();
    private Barcode Barcode;
    private Article Article;
    /**
     * Creates new form AddArticleDialog
     */
    public AddArticleDialog(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(parent);
        
        this.LoadLocalization();
        this.LoadSettings();
    }
    
    public AddArticleDialog(java.awt.Frame parent, boolean modal, Barcode barcode) 
    {
        this(parent, modal);
        
        this.SetBarcode(barcode);
        
        this.FillValues();        
    }
    
    private void SetBarcode(Barcode barcode)
    {
        this.Barcode = barcode;
        
        if ((this.Barcode != null) && (this.Barcode.ParentArticle != null))
        {
            this.Article = this.Barcode.ParentArticle;
        }
    }

    @Override
    public  void LoadSettings()
    {
    
    }
    
    @Override
    public  void LoadLocalization()
    {
        // Dialog Title
        this.setTitle(Globals.LanguageEngine.Translate(Globals.LanguageEngine.Translate("DIALOG_ADD_TITLE")));
        
        /****** ArticleBox ******/
        // Title
        ((TitledBorder)this.ArticlePanel.getBorder()).setTitle(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_ARTICLE_BOXTITLE"));
        
        // Controls
        this.ArticleIDLabel.setText(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_ARTICLE_ID"));
        this.ArticleNameLabel.setText(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_ARTICLE_NAME"));
        this.ArticleDescriptionLabel.setText(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_ARTICLE_DESCRIPTION"));
        this.ArticleCategoryLabel.setText(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_ARTICLE_CATEGORY"));        
    }
    
    private void FillValues()
    {
        for (Category category : Globals.Database.LoadAll(Category.class))
        {
            this.Categories.add(category);
            this.ArticleCategoryComboBox.addItem(category);
        }
        
        for (Shop shop : Globals.Database.LoadAll(Shop.class))
        {
            this.Shops.add(shop);
            this.ArticleLineShopComboBox.addItem(shop);
        }
        
        if (this.Article != null)
        {
            this.ArticleIDTextField.setText(this.Article.Reference);
            this.ArticleNameTextField.setText(this.Article.Name);
            this.ArticleDescriptionTextField.setText(this.Article.Description);
            this.ArticleCategoryComboBox.setSelectedItem(this.Article.Category);            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollPanel = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        ArticlePanel = new javax.swing.JPanel();
        ArticleIDLabel = new javax.swing.JLabel();
        ArticleIDTextField = new javax.swing.JTextField();
        ArticleNameLabel = new javax.swing.JLabel();
        ArticleNameTextField = new javax.swing.JTextField();
        ArticleDescriptionLabel = new javax.swing.JLabel();
        ArticleDescriptionTextField = new javax.swing.JTextField();
        ArticleCategoryLabel = new javax.swing.JLabel();
        ArticleCategoryComboBox = new javax.swing.JComboBox();
        ArticleLinePanel = new javax.swing.JPanel();
        ArticleLineDescriptionLabel = new javax.swing.JLabel();
        ArticleLineDescriptionTextField = new javax.swing.JTextField();
        ArticleLineAmountLabel = new javax.swing.JLabel();
        ArticleLineAmountTextField = new javax.swing.JTextField();
        ArticleLineWeightLabel = new javax.swing.JLabel();
        ArticleLineWeightTextField = new javax.swing.JTextField();
        ArticleLineExpiryDateLabel = new javax.swing.JLabel();
        ArticleLineExpiryDateTextField = new javax.swing.JTextField();
        ArticleLineShopLabel = new javax.swing.JLabel();
        ArticleLineShopComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        ArticlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Article"));

        ArticleIDLabel.setLabelFor(ArticleIDTextField);
        ArticleIDLabel.setText("ID");

        ArticleIDTextField.setEditable(false);

        ArticleNameLabel.setLabelFor(ArticleNameTextField);
        ArticleNameLabel.setText("Name");

        ArticleDescriptionLabel.setLabelFor(ArticleDescriptionTextField);
        ArticleDescriptionLabel.setText("Description");
        ArticleDescriptionLabel.setToolTipText("");

        ArticleCategoryLabel.setLabelFor(ArticleCategoryComboBox);
        ArticleCategoryLabel.setText("Category");
        ArticleCategoryLabel.setToolTipText("");

        javax.swing.GroupLayout ArticlePanelLayout = new javax.swing.GroupLayout(ArticlePanel);
        ArticlePanel.setLayout(ArticlePanelLayout);
        ArticlePanelLayout.setHorizontalGroup(
            ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArticlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ArticleCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArticleDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ArticleIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ArticleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ArticleCategoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ArticleDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ArticleNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ArticleIDTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        ArticlePanelLayout.setVerticalGroup(
            ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArticlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleIDLabel)
                    .addComponent(ArticleIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleNameLabel)
                    .addComponent(ArticleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleDescriptionLabel)
                    .addComponent(ArticleDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(ArticlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ArticleCategoryLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ArticleLinePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Article Line"));
        ArticleLinePanel.setAlignmentX(0.0F);
        ArticleLinePanel.setAlignmentY(0.0F);

        ArticleLineDescriptionLabel.setLabelFor(ArticleIDTextField);
        ArticleLineDescriptionLabel.setText("Description");

        ArticleLineDescriptionTextField.setEditable(false);

        ArticleLineAmountLabel.setLabelFor(ArticleNameTextField);
        ArticleLineAmountLabel.setText("Amount");

        ArticleLineAmountTextField.setToolTipText("");

        ArticleLineWeightLabel.setLabelFor(ArticleDescriptionTextField);
        ArticleLineWeightLabel.setText("Weight");
        ArticleLineWeightLabel.setToolTipText("");

        ArticleLineWeightTextField.setInputVerifier(new DecimalInputVerifier());

        ArticleLineExpiryDateLabel.setLabelFor(ArticleCategoryComboBox);
        ArticleLineExpiryDateLabel.setText("Expiry date");
        ArticleLineExpiryDateLabel.setToolTipText("");

        ArticleLineShopLabel.setLabelFor(ArticleCategoryComboBox);
        ArticleLineShopLabel.setText("Shop");
        ArticleLineShopLabel.setToolTipText("");
        ArticleLineShopLabel.setPreferredSize(new java.awt.Dimension(104, 26));

        ArticleLineShopComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArticleLineShopComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ArticleLinePanelLayout = new javax.swing.GroupLayout(ArticleLinePanel);
        ArticleLinePanel.setLayout(ArticleLinePanelLayout);
        ArticleLinePanelLayout.setHorizontalGroup(
            ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArticleLinePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ArticleLineExpiryDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArticleLineWeightLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArticleLineShopLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ArticleLineAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ArticleLineDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ArticleLineWeightTextField)
                        .addComponent(ArticleLineShopComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ArticleLineExpiryDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ArticleLineDescriptionTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ArticleLineAmountTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        ArticleLinePanelLayout.setVerticalGroup(
            ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArticleLinePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleLineDescriptionLabel)
                    .addComponent(ArticleLineDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleLineAmountLabel)
                    .addComponent(ArticleLineAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleLineWeightLabel)
                    .addComponent(ArticleLineWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleLineExpiryDateLabel)
                    .addComponent(ArticleLineExpiryDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArticleLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArticleLineShopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ArticleLineShopComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ContentPanelLayout = new javax.swing.GroupLayout(ContentPanel);
        ContentPanel.setLayout(ContentPanelLayout);
        ContentPanelLayout.setHorizontalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPanelLayout.createSequentialGroup()
                .addGroup(ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ArticlePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArticleLinePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ContentPanelLayout.setVerticalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPanelLayout.createSequentialGroup()
                .addComponent(ArticlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ArticleLinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ScrollPanel.setViewportView(ContentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPanel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ArticleLineShopComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArticleLineShopComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ArticleLineShopComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddArticleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddArticleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddArticleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddArticleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddArticleDialog dialog = new AddArticleDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ArticleCategoryComboBox;
    private javax.swing.JLabel ArticleCategoryLabel;
    private javax.swing.JLabel ArticleDescriptionLabel;
    private javax.swing.JTextField ArticleDescriptionTextField;
    private javax.swing.JLabel ArticleIDLabel;
    private javax.swing.JTextField ArticleIDTextField;
    private javax.swing.JLabel ArticleLineAmountLabel;
    private javax.swing.JTextField ArticleLineAmountTextField;
    private javax.swing.JLabel ArticleLineDescriptionLabel;
    private javax.swing.JTextField ArticleLineDescriptionTextField;
    private javax.swing.JLabel ArticleLineExpiryDateLabel;
    private javax.swing.JTextField ArticleLineExpiryDateTextField;
    private javax.swing.JPanel ArticleLinePanel;
    private javax.swing.JComboBox ArticleLineShopComboBox;
    private javax.swing.JLabel ArticleLineShopLabel;
    private javax.swing.JLabel ArticleLineWeightLabel;
    private javax.swing.JTextField ArticleLineWeightTextField;
    private javax.swing.JLabel ArticleNameLabel;
    private javax.swing.JTextField ArticleNameTextField;
    private javax.swing.JPanel ArticlePanel;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JScrollPane ScrollPanel;
    // End of variables declaration//GEN-END:variables
}
