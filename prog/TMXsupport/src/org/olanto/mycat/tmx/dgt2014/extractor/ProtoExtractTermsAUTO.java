/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.olanto.mycat.tmx.dgt2014.extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.olanto.mycat.tmx.dgt2014.extract.LangMap;

import org.olanto.mysqd.util.Ref;
import org.olanto.util.Timer;

/**
 *
 * @author simple
 */
public class ProtoExtractTermsAUTO extends javax.swing.JFrame {

    /**
     * Creates new form ProtoExtractTerms
     */
    public ProtoExtractTermsAUTO() {
        initComponents();
LangMap.init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        top = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        termso = new javax.swing.JTextField();
        langso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        langta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        minfreq = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        minNgram = new javax.swing.JTextField();
        extract = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        minTerm = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        correlationLimit = new javax.swing.JTextField();
        bottom = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel1.setText("Prototype for terms extraction");

        jLabel3.setText("source term");

        jLabel4.setText("source language");

        termso.setText("océan atlantique");
        termso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termsoActionPerformed(evt);
            }
        });

        langso.setText("FR");
        langso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langsoActionPerformed(evt);
            }
        });

        jLabel5.setText("target language");

        langta.setText("EN");
        langta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langtaActionPerformed(evt);
            }
        });

        jLabel6.setText("min frequency");

        minfreq.setText("10");

        jLabel7.setText("min NGram");

        minNgram.setText("1");
        minNgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minNgramActionPerformed(evt);
            }
        });

        extract.setText("Extract");
        extract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extractActionPerformed(evt);
            }
        });

        jLabel8.setText("min Terms");

        minTerm.setText("3");
        minTerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minTermActionPerformed(evt);
            }
        });

        jLabel9.setText("correlation limit");

        correlationLimit.setText("0.1");
        correlationLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correlationLimitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(termso, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(topLayout.createSequentialGroup()
                        .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(topLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(langso, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(langta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(minTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(topLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(minfreq, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minNgram, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(correlationLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addComponent(extract, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(termso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(langso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(langta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(minTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(minfreq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(minNgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extract)
                    .addComponent(jLabel9)
                    .addComponent(correlationLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSplitPane1.setTopComponent(top);

        jLabel2.setText("InfoLog");

        log.setColumns(20);
        log.setRows(5);
        jScrollPane1.setViewportView(log);

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(bottom);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void termsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_termsoActionPerformed

    private void minNgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minNgramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minNgramActionPerformed

    private void langsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_langsoActionPerformed

    private void langtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langtaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_langtaActionPerformed

    private void extractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extractActionPerformed
        // TODO add your handling code here:
        Timer t1=new Timer("total time");
        log.setText("________________________________________\n");
        log.append(termso.getText() + ", " + langso.getText() + ", " + langta.getText() + ", " + minfreq.getText() + ", " + minNgram.getText() + "\n");
        int freqso = TestClientGetTargetTxt.getFrequency(termso.getText(), langso.getText(),langta.getText());
        log.append("Frequency: " + freqso + "\n");
        if (freqso != 0) {
            minfreq.setText(""+TestClientGetTargetTxt.fixMinFreq(freqso));
            minTerm.setText(""+TestClientGetTargetTxt.fixMinTerm(termso.getText()));
            String source = TestClientGetTargetTxt.getSource(termso.getText(), langso.getText(),langta.getText());
            List<Ref> refComposite = TestClientGetTargetTxt.getNGramIncluded(source, Integer.parseInt(minfreq.getText()), Integer.parseInt(minTerm.getText()), termso.getText());
            log.append("------ composite terms for: " + termso.getText() + "\n");
            for (Ref r : refComposite) { // pour chaque n-gram
                log.append(r.ngram + " (" + r.nbocc + ")\n");
            }


            String target = TestClientGetTargetTxt.getTarget(termso.getText(), langso.getText(), langta.getText());
            List<Ref> ref = TestClientGetTargetTxt.getNGram(target, Integer.parseInt(minfreq.getText()), Integer.parseInt(minNgram.getText()));
            List<ItemsCorrelation> list = new ArrayList<>();

            for (int i = 0; i < ref.size(); i++) { // pour chaque n-gram
                list.add(TestClientGetTargetTxt.correlationObj(termso.getText(), ref.get(i).ngram, langso.getText(), langta.getText()));
            }
            Collections.sort(list);
            float corlimit = Float.parseFloat(correlationLimit.getText());
            log.append("------ candidate translations for: " + termso.getText() + ", correlation limit=" + corlimit + "\n");
            int countskip = 0;
            int count=0;
            for (ItemsCorrelation item : list) { // pour chaque n-gram
                if (corlimit <= item.cor&&count<5) {
                    log.append(item.msg + "\n");
                } else {
                    countskip++;
                }
                count++;
            }
            log.append("skip terms: " + countskip + "\n");
            log.append("total time: " +t1.getstop() + " millisec\n");
        }
    }//GEN-LAST:event_extractActionPerformed

    private void minTermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minTermActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minTermActionPerformed

    private void correlationLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correlationLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correlationLimitActionPerformed

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
            java.util.logging.Logger.getLogger(ProtoExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProtoExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProtoExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProtoExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProtoExtractTermsAUTO().setVisible(true);
                TestClientGetTargetTxt.initIS();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom;
    private javax.swing.JTextField correlationLimit;
    private javax.swing.JButton extract;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField langso;
    private javax.swing.JTextField langta;
    private javax.swing.JTextArea log;
    private javax.swing.JTextField minNgram;
    private javax.swing.JTextField minTerm;
    private javax.swing.JTextField minfreq;
    private javax.swing.JTextField termso;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables
}
