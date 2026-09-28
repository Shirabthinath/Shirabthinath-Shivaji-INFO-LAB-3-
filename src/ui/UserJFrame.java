package ui;


import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


/**
 *
 * @author shirabthinath
 */
public class UserJFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserJFrame.class.getName());

    // === Email field settings ===
    private static final String EMAIL_SUFFIX = ".com";
    private static final String EMAIL_PATTERN = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.com$";
    private boolean emailFilterBypass = false; // lets our own code clear the field

   
    public UserJFrame() {
        initComponents();
        setupEmailField();
    }

    /**
     * Makes the Email field always end in ".com".
     * - Clicking/tabbing into an empty field shows ".com" with the cursor in front of it.
     * - The ".com" part can't be deleted or typed over.
     * - Pasting a full address like "john@gmail.com" won't double the suffix.
     * - Leaving the field without typing anything makes it empty again.
     */
    private void setupEmailField() {
        jFormattedTextField1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jFormattedTextField1.setToolTipText("name@company.com");

        ((AbstractDocument) jFormattedTextField1.getDocument()).setDocumentFilter(new DocumentFilter() {

            // Last position the user may edit (just before ".com")
            private int editLimit(FilterBypass fb) throws BadLocationException {
                int len = fb.getDocument().getLength();
                if (len >= EMAIL_SUFFIX.length()
                        && fb.getDocument().getText(len - EMAIL_SUFFIX.length(), EMAIL_SUFFIX.length()).equals(EMAIL_SUFFIX)) {
                    return len - EMAIL_SUFFIX.length();
                }
                return len;
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                replace(fb, offset, 0, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (emailFilterBypass) { super.replace(fb, offset, length, text, attrs); return; }
                int limit = editLimit(fb);
                if (offset > limit) { offset = limit; length = 0; }        // typing after ".com" goes before it
                if (offset + length > limit) { length = limit - offset; }  // can't overwrite ".com"
                if (fb.getDocument().getLength() >= EMAIL_SUFFIX.length() && text != null && text.endsWith(EMAIL_SUFFIX)) {
                    text = text.substring(0, text.length() - EMAIL_SUFFIX.length()); // pasted a full address
                }
                super.replace(fb, offset, length, text, attrs);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (emailFilterBypass) { super.remove(fb, offset, length); return; }
                int limit = editLimit(fb);
                if (offset + length > limit) { length = limit - offset; }  // protect ".com"
                if (length > 0) { super.remove(fb, offset, length); }
            }
        });

        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (jFormattedTextField1.getText().isEmpty()) {
                    jFormattedTextField1.setText(EMAIL_SUFFIX);
                }
                // invokeLater so this caret position wins over the mouse click's
                javax.swing.SwingUtilities.invokeLater(() ->
                    jFormattedTextField1.setCaretPosition(jFormattedTextField1.getText().length() - EMAIL_SUFFIX.length()));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (jFormattedTextField1.getText().equals(EMAIL_SUFFIX)) {
                    emailFilterBypass = true;
                    jFormattedTextField1.setText(""); // nothing typed, don't leave a lone ".com"
                    emailFilterBypass = false;
                }
            }
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Age = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        textName = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        Name = new javax.swing.JLabel();
        College = new javax.swing.JLabel();
        PhoneNumber = new javax.swing.JLabel();
        textPhoneNumber = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Photo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        UploadPhoto = new javax.swing.JButton();
        photoUpload = new javax.swing.JLabel();
        textName1 = new javax.swing.JTextField();
        Name1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Age.setText("Age:");

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel());

        Name.setText("First Name:");

        College.setText("Gender");

        PhoneNumber.setText("Phone:");

        try {
            textPhoneNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textPhoneNumber.setToolTipText("123-456-7890");
        textPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPhoneNumberActionPerformed(evt);
            }
        });

        jLabel1.setText("Continent:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asia", "Africa", "Antarctica", "Europe", "North America", "South America", "Australia" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Experience:");

        Photo.setText("Photo:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        UploadPhoto.setText("Upload Photo");
        UploadPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadPhotoActionPerformed(evt);
            }
        });

        Name1.setText("Last Name:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Email");

        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(Submit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Photo)
                            .addComponent(Name1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photoUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addComponent(textName1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(UploadPhoto)))
                        .addGap(129, 129, 129))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Age)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(College)
                                    .addComponent(PhoneNumber)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(textPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Name))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Age)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(College)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Photo)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UploadPhoto))
                .addGap(18, 18, 18)
                .addComponent(photoUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Submit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed

    String errorTitle = "Ooops";

    try {
        String firstName = textName.getText();
        String lastName = textName1.getText();
        String email = jFormattedTextField1.getText().trim();
        int age = (int) jSpinner1.getValue();
        String gender = (String) jComboBox2.getSelectedItem();
        String phone = textPhoneNumber.getText();
        String continent = (String) jComboBox1.getSelectedItem();
        String experience = jTextArea1.getText();
        String photoPath = jTextField1.getText();

        if (firstName == null || firstName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid First Name Provided", errorTitle, ERROR_MESSAGE);
            return;
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Last Name Provided", errorTitle, ERROR_MESSAGE);
            return;
        }

        if (!email.matches(EMAIL_PATTERN)) {
            JOptionPane.showMessageDialog(this, "Invalid Email (must be like name@company.com)", errorTitle, ERROR_MESSAGE);
            jFormattedTextField1.requestFocus();
            return;
        }

        if (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Invalid Phone Number", errorTitle, ERROR_MESSAGE);
            return;
        }

        Student student = new Student();
        student.setName(firstName.trim() + " " + lastName.trim());
        student.setAge(age);
        student.setGender(gender);
        student.setPhone(phone);
        student.setContinent(continent);
        student.setExperience(experience);
        student.setPhotoPath(photoPath);

        String successMessage = "First Name: " + firstName.trim()
            + "\nLast Name: " + lastName.trim()
            + "\nAge: " + age
            + "\nGender: " + (gender == null ? "Not selected" : gender)
            + "\nEmail: " + email
            + "\nPhone: " + phone
            + "\nContinent: " + (continent == null ? "Not selected" : continent)
            + "\nExperience: " + (experience == null || experience.trim().isEmpty() ? "None" : experience)
            + "\nPhoto Path: " + (photoPath == null || photoPath.trim().isEmpty() ? "Not uploaded" : photoPath);
      
        ImageIcon photoIcon = null;
        if (photoPath != null && !photoPath.trim().isEmpty()) {
            ImageIcon rawIcon = new ImageIcon(photoPath);
            Image scaled = rawIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            photoIcon = new ImageIcon(scaled);
        }

        JOptionPane.showMessageDialog(
            this,
            successMessage,
            "Success",
            JOptionPane.INFORMATION_MESSAGE,
            photoIcon
        );

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Age Provided", errorTitle, ERROR_MESSAGE);
    }  

    }//GEN-LAST:event_SubmitActionPerformed

    private void textPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPhoneNumberActionPerformed

        String phone = textPhoneNumber.getText();
        if (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number (e.g., 123-456-7890)",
                "Invalid Phone", ERROR_MESSAGE);
            textPhoneNumber.requestFocus();
        }
    }//GEN-LAST:event_textPhoneNumberActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
   
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void UploadPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadPhotoActionPerformed

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            jTextField1.setText(selectedFile.getAbsolutePath());

            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaledImage = icon.getImage().getScaledInstance(photoUpload.getWidth(), photoUpload.getHeight(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            photoUpload.setIcon(icon);
            photoUpload.setText("");
        }

    }//GEN-LAST:event_UploadPhotoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // Pressing Enter in the Email field checks it right away
        String email = jFormattedTextField1.getText().trim();
        if (!email.matches(EMAIL_PATTERN)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email (e.g., name@company.com)",
                "Invalid Email", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    // === Student model (kept inside this file so everything is in one place) ===
    static class Student {

        private String name;
        private int age;
        private String gender;
        private String phone;
        private String continent;
        private String experience;
        private String photoPath;

        void setName(String name) { this.name = name; }
        void setAge(int age) { this.age = age; }
        void setGender(String gender) { this.gender = gender; }
        void setPhone(String phone) { this.phone = phone; }
        void setContinent(String continent) { this.continent = continent; }
        void setExperience(String experience) { this.experience = experience; }
        void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

        String getName() { return name; }
        int getAge() { return age; }
        String getGender() { return gender; }
        String getPhone() { return phone; }
        String getContinent() { return continent; }
        String getExperience() { return experience; }
        String getPhotoPath() { return photoPath; }

        @Override
        public String toString() {
            return "Name: " + name
                + "\nAge: " + age
                + "\nGender: " + (gender == null ? "Not selected" : gender)
                + "\nPhone: " + phone
                + "\nContinent: " + (continent == null ? "Not selected" : continent)
                + "\nExperience: " + (experience == null || experience.trim().isEmpty() ? "None" : experience)
                + "\nPhoto Path: " + (photoPath == null || photoPath.trim().isEmpty() ? "Not uploaded" : photoPath);
        }
    }

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new UserJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Age;
    private javax.swing.JLabel College;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Name1;
    private javax.swing.JLabel PhoneNumber;
    private javax.swing.JLabel Photo;
    private javax.swing.JButton Submit;
    private javax.swing.JButton UploadPhoto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel photoUpload;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textName1;
    private javax.swing.JFormattedTextField textPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
