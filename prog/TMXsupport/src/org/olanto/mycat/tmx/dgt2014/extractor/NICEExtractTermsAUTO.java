/**
 * ********
 * Copyright © 2010-2012 Olanto Foundation Geneva
 *
 * This file is part of myCAT.
 *
 * myCAT is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * myCAT is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with myCAT. If not, see <http://www.gnu.org/licenses/>.
 *
 *********
 */
package org.olanto.mycat.tmx.dgt2014.extractor;

import org.olanto.mycat.tmx.common.NgramAndCorrelation;
import org.olanto.mycat.tmx.common.CorrelateAll;
import org.olanto.mycat.tmx.common.ItemsCorrelation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.olanto.mycat.tmx.common.LangMap;

import org.olanto.mysqd.util.Ref;
import org.olanto.util.Timer;

/**
 *
 * @author simple
 */
public class NICEExtractTermsAUTO extends javax.swing.JFrame {

    private StringBuilder htmlResult;

    /**
     * Creates new form ProtoExtractTerms
     */
    public NICEExtractTermsAUTO() {
        initComponents();
        LangMap.init("BG "+"CS "+"DA "+"DE "+"EL "+"EN "+"ES "+"ET "+"FI "+"FR "+"GA "+"HU "+"IT "+"LT "+"LV "+"MT "+"NL "+"PL "+"PT "+"RO "+"SH "+"SK "+"SL "+"SV");
   }

    public void setHtmlParam() {
        HTMLEditorKit kit = new HTMLEditorKit();
        result.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {margin-left:22px; margin-top:22px; margin-right:22px;}");
        styleSheet.addRule("h1 {color: blue;}");
        styleSheet.addRule("body {color:#000000; font-family:Verdana,sans-serif;}");
        styleSheet.addRule("table {width:100%}");
        styleSheet.addRule("caption {border:1px dotted #6495ed;padding:5px;background-color:#EFF6FF;width:25%}");
        styleSheet.addRule("th {border:1px dotted #6495ed;padding:5px;background-color:#EFF6FF;width:25%}");
        styleSheet.addRule("td {border:1px solid #6495ed;padding:5px; text-align:left;}");
        styleSheet.addRule("em {color:blue;}");
        Document doc = kit.createDefaultDocument();
        result.setDocument(doc);
    }

    public void setHtmlHeader(String title) {
        htmlResult = new StringBuilder();
        result.setContentType("text/html");
        htmlResult.append("<html><h1>"
                + title
                + "</h1>" + "<hr/>");
    }

    public void setHtmlFooter() {
        htmlResult.append("</html>");
    }

    public void addhtml(String s) {
        htmlResult.append(s);
    }

    public void showHtml() {
        setHtmlParam();
        result.setContentType("text/html");
        result.setText(htmlResult.toString());
    }

    public String emphase_old(String s, String toEmphase) {
        return s.replace(toEmphase, "<em>" + toEmphase + "</em>");
    }

   public String emphase(String s, String toEmphase) {
    String REGEX_BEFORE_TOKEN = "([^\\p{L}\\p{N}]|^)";
    String REGEX_AFTER_TOKEN = "([^\\p{L}\\p{N}]|$)";

        int start, length = toEmphase.length();
        String regex, newtoEmphase = "";
        Pattern p;
        Matcher m;
//        regex = REGEX_BEFORE_TOKEN + Pattern.quote(toEmphase) + REGEX_AFTER_TOKEN;
        p = Pattern.compile(toEmphase, Pattern.CASE_INSENSITIVE);
        m = p.matcher(s);
        if (m.find()) {
            start = m.start();
            newtoEmphase = s.substring(start, start+length);
            return s.replace(newtoEmphase, "<em>" + newtoEmphase + "</em>");
        }
        return s.replace(toEmphase, "<em>" + toEmphase + "</em>");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        result = new javax.swing.JTextPane();

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

        termso.setText("pommes de terre");
        termso.setToolTipText("");
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
                .addContainerGap(496, Short.MAX_VALUE))
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

        jScrollPane2.setViewportView(result);

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
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
        setHtmlHeader("Result for: \"" + termso.getText() + "\" from " + langso.getText() + " to " + langta.getText());
        Timer t1 = new Timer("total time");
        log.setText("________________________________________\n");
        log.append(termso.getText() + ", " + langso.getText() + ", " + langta.getText() + ", " + minfreq.getText() + ", " + minNgram.getText() + "\n");
        int freqso = NgramAndCorrelation.getFrequency(termso.getText(), langso.getText(), langta.getText());
        log.append("Term Frequency: " + freqso + "\n");
        addhtml("<h2>Term frequency: " + freqso + "</h2>\n");
        if (freqso != 0) {
            minfreq.setText("" + NgramAndCorrelation.fixMinFreq(freqso));
            minTerm.setText("" + NgramAndCorrelation.fixMinTerm(termso.getText()));
            String source = NgramAndCorrelation.getSource(termso.getText(), langso.getText(), langta.getText());
            List<Ref> refComposite = NgramAndCorrelation.getNGramIncluded(source, Integer.parseInt(minfreq.getText()), Integer.parseInt(minTerm.getText()), termso.getText());
            log.append("------ composite terms for: " + termso.getText() + "\n");
            addhtml("<h2>Expressions with the source term</h2>\n");
            addhtml("<table>\n");
            //    addhtml("<caption>Expressions with the source term</caption>\n");
            addhtml("<tr><th>Expressions containing the term: " + termso.getText() + "</th><th>Occurrences</th></tr>\n");
            for (Ref r : refComposite) { // pour chaque n-gram
                addhtml("<tr>\n");
                log.append(r.ngram + " (" + r.nbocc + ")\n");
                addhtml("<td>" + r.ngram + "</td>"
                        + "<td>" + r.nbocc + "</td>\n");
                addhtml("</tr>\n");
            }
            addhtml("</table>\n");


            String target = NgramAndCorrelation.getTarget(termso.getText(), langso.getText(), langta.getText());
            List<Ref> ref = NgramAndCorrelation.getNGram(target, Integer.parseInt(minfreq.getText()), Integer.parseInt(minNgram.getText()), Integer.parseInt(minfreq.getText()) + 2);

            CorrelateAll corAll=new CorrelateAll(ref, termso.getText(), langso.getText(), langta.getText());
            List<ItemsCorrelation> list=corAll.computePAR();
            //            List<ItemsCorrelation> list = new ArrayList<>();
//
//            for (int i = 0; i < ref.size(); i++) { // pour chaque n-gram
//                list.add(NgramAndCorrelation.correlationObj(termso.getText(), ref.get(i).ngram, langso.getText(), langta.getText()));
//            }
            try {
                Collections.sort(list);
            } catch (Exception ex) {
                Logger.getLogger(NICEExtractTermsAUTO.class.getName()).log(Level.SEVERE, null, ex);
            }


            float corlimit = Float.parseFloat(correlationLimit.getText());
            log.append("------ candidate translations for: " + termso.getText() + ", correlation limit=" + corlimit + "\n");
            int countskip = 0;
            int count = 0;
            addhtml("<h2>Translations</h2>\n");
            addhtml("<table>\n");
            //         addhtml("<caption>possible translation for the source term</caption>\n");
            addhtml("<tr><th>Expressions containing the term: " + termso.getText()
                    + "</th><th>Correlation</th>"
                    + "</th><th>In " + langso.getText() + "</th>"
                    + "</th><th>In " + langta.getText() + "</th>"
                    + "</th><th>In both</th></tr>\n");
            for (ItemsCorrelation item : list) { // pour chaque n-gram
                if (corlimit <= item.cor && count < 5) {
                    addhtml("<tr>\n");
                    log.append(item.msg + "\n");
                    addhtml("<td>" + item.termta + "</td>"
                            + "<td color=red>" + item.cor + "</td>\n"
                            + "<td>" + item.n1 + "</td>\n"
                            + "<td>" + item.n2 + "</td>\n"
                            + "<td>" + item.n12 + "</td>\n");
                    addhtml("</tr>\n");
                    addhtml("</table>\n");
                    addhtml("<table>\n");
                    for (int i = 0; i < item.examples.length; i++) {
                        addhtml("<tr>\n");
                        addhtml("<td width=50%>" + emphase(item.examples[i][0], termso.getText()) + "</td>"
                                + "<td width=50%>" + emphase(item.examples[i][1], item.termta) + "</td>\n");
                        addhtml("</tr>\n");
                        addhtml("</table>\n");
                        addhtml("<table>\n");
                    }

                } else {
                    countskip++;
                }
                count++;
            }
            addhtml("</table>\n");
            log.append("skip terms: " + countskip + "\n");
            log.append("total time: " + t1.getstop() + " millisec\n");
            addhtml("<p>total time: " + t1.getstop() + "millisec</p>\n");

            setHtmlFooter();
            showHtml();
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
            java.util.logging.Logger.getLogger(NICEExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NICEExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NICEExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NICEExtractTermsAUTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NICEExtractTermsAUTO().setVisible(true);
                NgramAndCorrelation.initIS();
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField langso;
    private javax.swing.JTextField langta;
    private javax.swing.JTextArea log;
    private javax.swing.JTextField minNgram;
    private javax.swing.JTextField minTerm;
    private javax.swing.JTextField minfreq;
    private javax.swing.JTextPane result;
    private javax.swing.JTextField termso;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables
}
