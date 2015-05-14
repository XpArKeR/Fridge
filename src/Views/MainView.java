/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Views;

import Core.Globals;
import Core.Settings;
import Enumerables.Enumerables.ItemMode;
import Model.Article.Barcode;
import Model.Configuration.Setting;
import Model.Views.BaseView;
import Views.Article.AddArticleDialog;
import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;


/**
 *
 * @author noldi
 */
public class MainView extends JFrame implements BaseView
{
    private final String[] ValidCommands = {"ping"};
    
    private ItemMode CurrentItemMode = ItemMode.OUT;
    private Setting HeightSetting;
    private Setting WidthSetting;
    private Setting PosXSetting;
    private Setting PosYSetting;
    
    public MainView() 
    {
        initComponents();
        
        ModeButtonGroup.add(OutModeRadioButton);
        ModeButtonGroup.add(InModeRadioButton);
        
        this.SetMode(CurrentItemMode);
        OutModeRadioButton.setSelected(true);
        
        this.BarcodeInputField.requestFocus();
    }
        
    @Override
    public void LoadSettings()
    {
        PosXSetting = Settings.GetProperty("MainWindow.Position.X");
        
        if (PosXSetting == null)
        {
            PosXSetting = new Setting("MainWindow.Position.X", 300);
            Settings.SetProperty(PosXSetting);
        }
                
        PosYSetting = Settings.GetProperty("MainWindow.Position.Y");
        
        if (PosYSetting == null)
        {
            PosYSetting = new Setting("MainWindow.Position.Y", 300);
            Settings.SetProperty(PosYSetting);
        } 
        
        WidthSetting = Settings.GetProperty("MainWindow.Size.Width");
        
        if (WidthSetting == null)
        {
            WidthSetting = new Setting("MainWindow.Size.Width",this.getBounds().width);
            Settings.SetProperty(WidthSetting);
        }       
                        
        HeightSetting = Settings.GetProperty("MainWindow.Size.Height");
        
        if (HeightSetting == null)
        {
            HeightSetting = new Setting("MainWindow.Size.Height",this.getBounds().height);
            Settings.SetProperty(HeightSetting);
        }
        
        int positionX = Integer.parseInt(PosXSetting.Value.toString());   // X
        int positionY = Integer.parseInt(PosYSetting.Value.toString());   // Y
        int uiWidth = Integer.parseInt(WidthSetting.Value.toString());  // Width
        int uiHeight = Integer.parseInt(HeightSetting.Value.toString());  // Height
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        if ((positionX + uiWidth) > width)
        {
            positionX = 300;
        }
        
        if ((positionY + uiHeight) > height)
        {
            positionY = 300;
        }
        
        this.setBounds
        (
                positionX,   // X
                positionY,   // Y
                uiWidth,  // Width
                uiHeight  // Height
        );
    }
    
    @Override
    public void LoadLocalization()
    {
        /***** Mode Box *****/
        // Title
        ((TitledBorder)this.ModePanel.getBorder()).setTitle(Globals.LanguageEngine.Translate("WORDS_MODE"));
        
        //Other Translations
        this.OutModeRadioButton.setText(Globals.LanguageEngine.Translate("MAINVIEW_ITEMMODE_OUT"));
        this.InModeRadioButton.setText(Globals.LanguageEngine.Translate("MAINVIEW_ITEMMODE_IN"));        
        
        /***** Article Box *****/
        // Title
        ((TitledBorder)this.BarcodePanel.getBorder()).setTitle(Globals.LanguageEngine.Translate("MAINVIEW_CONTROLS_ARTICLEBOX_TITLE"));
        
        // Other Translations
        this.BarcodeLabel.setText(Globals.LanguageEngine.Translate("SYSTEM_CONTROLS_BARCODE"));
        
        
    }
    
    private void SetMode(ItemMode newMode)
    {
        System.out.println("Setting item mode"  + newMode);
        
        switch(newMode)
        {
            case IN:
                this.LoadInMode();
                break;
                
            case OUT:
                this.LoadOutMode();
                break;
        }
        
        this.CurrentItemMode = newMode;
        this.BarcodeInputField.requestFocus();
    }
    
    private void LoadInMode()
    {
        this.setTitle(Globals.LanguageEngine.Translate("MAINVIEW_TITLE_ITEMMODE_IN"));
    }
    
    private void LoadOutMode()
    {
        this.setTitle(Globals.LanguageEngine.Translate("MAINVIEW_TITLE_ITEMMODE_OUT"));
    }
    
    private void DecideAction(String input)
    {        
        if (IsCommand(input))
        {
        //    handleCommand(arguments)            
        }
        else
        {        
            SearchArticle(input);
        }        
    }   
    
    private void SearchArticle(String input)
    {
        Barcode barcode = new Barcode();
                
        // Checks the Database if the Barcode is known. If it is, it will load the related article.
        barcode.Exists(input, true);
                    
        switch(this.CurrentItemMode)
        {
            case IN:
                AddArticleDialog addArticleDialog = new AddArticleDialog(this, true, barcode);

                addArticleDialog.setVisible(true);
                break;
                
            case OUT:
                
                break;
        }
    }
    
    private boolean IsCommand(String input)
    {
        String[] arguments = input.split(" ");
        
        if (arguments.length > 1)
        {
            return true;
        }
        else if (Arrays.asList(ValidCommands).contains(input))
        {
           return true;
        }
        
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModeButtonGroup = new javax.swing.ButtonGroup();
        ScrollPanel = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        ModePanel = new javax.swing.JPanel();
        InModeRadioButton = new javax.swing.JRadioButton();
        OutModeRadioButton = new javax.swing.JRadioButton();
        BarcodePanel = new javax.swing.JPanel();
        BarcodeInputField = new javax.swing.JTextField();
        BarcodeLabel = new javax.swing.JLabel();
        StatusPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fridge V3");
        setName("BaseView"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        ScrollPanel.setAutoscrolls(true);

        ContentPanel.setPreferredSize(new java.awt.Dimension(250, 250));

        ModePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Mode"));
        ModePanel.setName("ModePanel"); // NOI18N

        InModeRadioButton.setText("In-Mode");
        InModeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InModeRadioButtonActionPerformed(evt);
            }
        });

        OutModeRadioButton.setText("Out-Mode");
        OutModeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OutModeRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModePanelLayout = new javax.swing.GroupLayout(ModePanel);
        ModePanel.setLayout(ModePanelLayout);
        ModePanelLayout.setHorizontalGroup(
            ModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModePanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(InModeRadioButton)
                .addGap(114, 114, 114)
                .addComponent(OutModeRadioButton)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        ModePanelLayout.setVerticalGroup(
            ModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InModeRadioButton)
            .addComponent(OutModeRadioButton)
        );

        BarcodePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add / Remove Article"));

        BarcodeInputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarcodeInputFieldActionPerformed(evt);
            }
        });

        BarcodeLabel.setLabelFor(BarcodeInputField);
        BarcodeLabel.setText("Enter Barcode:");
        BarcodeLabel.setName("BarcodeLabel"); // NOI18N

        javax.swing.GroupLayout BarcodePanelLayout = new javax.swing.GroupLayout(BarcodePanel);
        BarcodePanel.setLayout(BarcodePanelLayout);
        BarcodePanelLayout.setHorizontalGroup(
            BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarcodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BarcodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BarcodeInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BarcodePanelLayout.setVerticalGroup(
            BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarcodePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BarcodeInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BarcodeLabel))
                .addGap(20, 20, 20))
        );

        StatusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        StatusPanel.setFocusable(false);
        StatusPanel.setName("StatusPanel"); // NOI18N

        javax.swing.GroupLayout StatusPanelLayout = new javax.swing.GroupLayout(StatusPanel);
        StatusPanel.setLayout(StatusPanelLayout);
        StatusPanelLayout.setHorizontalGroup(
            StatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        StatusPanelLayout.setVerticalGroup(
            StatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ContentPanelLayout = new javax.swing.GroupLayout(ContentPanel);
        ContentPanel.setLayout(ContentPanelLayout);
        ContentPanelLayout.setHorizontalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BarcodePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ModePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(StatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContentPanelLayout.setVerticalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPanelLayout.createSequentialGroup()
                .addComponent(ModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BarcodePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(StatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ScrollPanel.setViewportView(ContentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Rectangle bounds = this.getBounds();
        
        this.HeightSetting.Value = bounds.height;
        this.WidthSetting.Value = bounds.width;
        this.PosXSetting.Value = bounds.x;
        this.PosYSetting.Value = bounds.y;       
        
        Settings.StoreSettings();
    }//GEN-LAST:event_formWindowClosing

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
//        Rectangle bounds = this.getBounds();
//                
//        this.PosXSetting.Value = bounds.x;
//        this.PosYSetting.Value = bounds.y;       
    }//GEN-LAST:event_formComponentMoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//        Rectangle bounds = this.getBounds();
//        
//        this.HeightSetting.Value = bounds.height;
//        this.WidthSetting.Value = bounds.width;
    }//GEN-LAST:event_formComponentResized

    private void BarcodeInputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarcodeInputFieldActionPerformed
        String input = this.BarcodeInputField.getText().trim();

        if (!input.isEmpty())
        {
            this.DecideAction(input);
        }
    }//GEN-LAST:event_BarcodeInputFieldActionPerformed

    private void OutModeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OutModeRadioButtonActionPerformed
        if (this.CurrentItemMode != ItemMode.OUT)
        {
            this.SetMode(ItemMode.OUT);
        }
    }//GEN-LAST:event_OutModeRadioButtonActionPerformed

    private void InModeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InModeRadioButtonActionPerformed
        if (this.CurrentItemMode != ItemMode.IN)
        {
            this.SetMode(ItemMode.IN);
        }
    }//GEN-LAST:event_InModeRadioButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BarcodeInputField;
    private javax.swing.JLabel BarcodeLabel;
    private javax.swing.JPanel BarcodePanel;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JRadioButton InModeRadioButton;
    private javax.swing.ButtonGroup ModeButtonGroup;
    private javax.swing.JPanel ModePanel;
    private javax.swing.JRadioButton OutModeRadioButton;
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JPanel StatusPanel;
    // End of variables declaration//GEN-END:variables
}
