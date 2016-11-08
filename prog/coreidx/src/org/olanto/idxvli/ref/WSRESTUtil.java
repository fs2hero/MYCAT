/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.olanto.idxvli.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.olanto.convsrv.server.ConvertService;
import org.olanto.idxvli.IdxConstant;
import org.olanto.idxvli.server.Reference;
import org.olanto.idxvli.server.Server_MyCat;
import org.olanto.idxvli.util.BytesAndFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author simple
 */
public class WSRESTUtil {

    static String organisationTemplate = null;

    public static void main(String[] args) {
//        byte[] bytes = null;
//        System.out.println(convertFileWithRMI("C:\\MYCAT\\corpus\\docs\\small-collection\\UNO\\A_RES_53_144_EN.pdf"));

        String mergedRefDoc = "";
        String file1 = "C:\\MYCAT\\doc2process\\A_RES_53_144_EN_1.xml";
        String file2 = "C:\\MYCAT\\doc2process\\A_RES_53_144_EN_2.xml";
        File fXmlFile = new File(file1);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        File fXmlFile1 = new File(file2);
        DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
            Document doc1 = dBuilder1.parse(fXmlFile1);
            doc1.getDocumentElement().normalize();

            // merge params
            mergedRefDoc += WSRESTUtil.mergeXMLParameters(doc, doc1);
            // merge statistics
            mergedRefDoc += WSRESTUtil.mergeXMLStatistics(doc, doc1);
            // merge HTML
            int totalRefs = doc.getElementsByTagName("reference").getLength() + doc1.getElementsByTagName("reference").getLength();
            mergedRefDoc += WSRESTUtil.mergeHTMLContent(file1, file2, "T", "J", "red", doc.getElementsByTagName("reference").getLength(), totalRefs);
            // merge details
            mergedRefDoc += WSRESTUtil.mergeInfo(doc, doc1);
            System.out.println(mergedRefDoc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Server_MyCat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(WSRESTUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSRESTUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String unCommentRefDoc(String s) {
        s = s.replace("<!--", "</htmlstartcomment>");
        s = s.replace("-->", "</htmlendcomment>");
        return s;
    }

    public static String reCommentRefDoc(String s) {
        s = s.replace("</htmlstartcomment>", "<!--");
        s = s.replace("</htmlendcomment>", "-->");
        return s;
    }

    /*
     * (@DefaultValue("") @QueryParam("TxtSrc") String TxtSrc,
     @DefaultValue("") @QueryParam("TxtTgt") String DocSrc,
     @DefaultValue("") @QueryParam("TxtTgt") String DocTgt,
     @DefaultValue("EN") @QueryParam("LngSrc") String LngSrc,
     @DefaultValue("FR") @QueryParam("LngTgt") String LngTgt,
     @DefaultValue("") @QueryParam("Filter") String Filter,
     @DefaultValue("3") @QueryParam("MinLen") Integer MinLen,
     @DefaultValue("FALSE") @QueryParam("RemFirst") Boolean RemFirst,
     @DefaultValue("FALSE") @QueryParam("Fast") Boolean Fast) 
     */
    public static String niceXMLParameters(String msg, String TxtSrc, String RefType, String DocSrc, String DocTgt,
            String LngSrc, String LngTgt, String[] Filter, Integer MinLen, Boolean RemFirst, Boolean Fast) {

        String collections = "";
        if (Filter != null) {
            for (int i = 0; i < Filter.length; i++) {
                collections += Filter[i] + ";";
            }
        }
        return "<parameters>\n"
                + "   <msg>" + msg + "</msg>\n"
                + "   <TxtSrc>" + TxtSrc + "</TxtSrc>\n"
                + "   <RefType>" + RefType + "</RefType>\n"
                + "   <DocSrc>" + DocSrc + "</DocSrc>\n"
                + "   <DocTgt>" + DocTgt + "</DocTgt>\n"
                + "   <LngSrc>" + LngSrc + "</LngSrc>\n"
                + "   <LngTgt>" + LngTgt + "</LngTgt>\n"
                + "   <Filter>" + collections + "</Filter>\n"
                + "   <MinLen>" + MinLen + "</MinLen>\n"
                + "   <RemFirst>" + RemFirst + "</RemFirst>\n"
                + "   <Fast>" + Fast + "</Fast>\n"
                + "</parameters>\n";

    }

    public static String niceXMLInfo(String RefDocFullName, String RefDocType,
            String RefDocLng, String RefDocPerCent, String RefDocOccurences) {

        if (organisationTemplate == null) {
            organisationTemplate = BytesAndFiles.file2String(IdxConstant.IDX_XML_ORGANISATION_TEMPLATE, "UTF-8");
            if (organisationTemplate == null) {
                organisationTemplate = "<!-- Error impossible to load: "
                        + IdxConstant.IDX_XML_ORGANISATION_TEMPLATE + "-->";
            }
        }

        return "<statistics>\n"
                + "  <mycat>\n"
                + "    <RefDocFullName>" + RefDocFullName + "</RefDocFullName>\n"
                + "    <RefDocType>" + RefDocType + "</RefDocType>\n"
                + "    <RefDocLng>" + RefDocLng + "</RefDocLng>\n"
                + "    <RefDocPerCent>" + RefDocPerCent + "</RefDocPerCent>\n"
                + "    <RefDocOccurences>" + RefDocOccurences + "</RefDocOccurences>\n"
                + "  </mycat>\n"
                + "  <organisation>\n"
                + "     <!-- imported template from /config -->"
                + organisationTemplate
                + "  </organisation>\n"
                + "</statistics>\n";

    }

    public static String convertFileWithRMI(String fileName) {
        String ret = "Conversion Error";
        System.out.println("Request to convert file from WebService: " + fileName);
        try {
            Remote r = Naming.lookup("rmi://localhost/CONVSRV");
            if (r instanceof ConvertService) {
                ConvertService is = (ConvertService) r;
                // ret = is.getInformation();


                ret = is.File2Txt(fileName);

                //System.out.println("DEBUG Converted file content: " + ret);

            } else {
                return "Error: CONVSRV Service not found or not compatible.";
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return ret;
    }

    public static String mergeXMLParameters(Document doc1, Document doc2) {
        return "<parameters>\n"
                + "   <msg>" + doc1.getDocumentElement().getElementsByTagName("msg").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("msg").item(0).getTextContent() + "</msg>\n"
                + "   <TxtSrc>" + doc1.getDocumentElement().getElementsByTagName("TxtSrc").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("TxtSrc").item(0).getTextContent() + "</TxtSrc>\n"
                + "   <RefType>" + doc1.getDocumentElement().getElementsByTagName("RefType").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefType").item(0).getTextContent() + "</RefType>\n"
                + "   <DocSrc>" + doc1.getDocumentElement().getElementsByTagName("DocSrc").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("DocSrc").item(0).getTextContent() + "</DocSrc>\n"
                + "   <DocTgt>" + doc1.getDocumentElement().getElementsByTagName("DocTgt").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("DocTgt").item(0).getTextContent() + "</DocTgt>\n"
                + "   <LngSrc>" + doc1.getDocumentElement().getElementsByTagName("LngSrc").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("LngSrc").item(0).getTextContent() + "</LngSrc>\n"
                + "   <LngTgt>" + doc1.getDocumentElement().getElementsByTagName("LngTgt").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("LngTgt").item(0).getTextContent() + "</LngTgt>\n"
                + "   <Filter>" + doc1.getDocumentElement().getElementsByTagName("Filter").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("Filter").item(0).getTextContent() + "</Filter>\n"
                + "   <MinLen>" + doc1.getDocumentElement().getElementsByTagName("MinLen").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("MinLen").item(0).getTextContent() + "</MinLen>\n"
                + "   <Fast>" + doc1.getDocumentElement().getElementsByTagName("Fast").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("Fast").item(0).getTextContent() + "</Fast>\n"
                + "</parameters>\n";
    }

    public static String mergeXMLStatistics(Document doc1, Document doc2) {
        String organization = "";
        NodeList nodes = doc1.getDocumentElement().getElementsByTagName("organisation");
        NodeList nodes1 = doc2.getDocumentElement().getElementsByTagName("organisation");
        for (int i = 0; i < nodes.item(0).getChildNodes().getLength(); i++) {
            if (nodes.item(0).getChildNodes().item(i).getNodeName().contains("text")) {
                organization += "";
            } else if (nodes.item(0).getChildNodes().item(i).getNodeName().contains("comment")) {
                organization += "<!--" + nodes.item(0).getChildNodes().item(i).getTextContent() + "-->\n";
            } else {
                organization += "<" + nodes.item(0).getChildNodes().item(i).getNodeName() + ">" + nodes.item(0).getChildNodes().item(i).getTextContent() + "</" + nodes.item(0).getChildNodes().item(i).getNodeName() + ">\n";
            }
        }
        for (int i = 0; i < nodes1.item(0).getChildNodes().getLength(); i++) {
            if (nodes1.item(0).getChildNodes().item(i).getNodeName().contains("text")) {
                organization += "";
            } else if (nodes1.item(0).getChildNodes().item(i).getNodeName().contains("comment")) {
                organization += "<!--" + nodes1.item(0).getChildNodes().item(i).getTextContent() + "-->\n";
            } else {
                organization += "<" + nodes1.item(0).getChildNodes().item(i).getNodeName() + ">" + nodes1.item(0).getChildNodes().item(i).getTextContent() + "</" + nodes1.item(0).getChildNodes().item(i).getNodeName() + ">\n";
            }
        }
        return "<statistics>\n"
                + "  <mycat>\n"
                + "    <RefDocFullName>" + doc1.getDocumentElement().getElementsByTagName("RefDocFullName").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefDocFullName").item(0).getTextContent() + "</RefDocFullName>\n"
                + "    <RefDocType>" + doc1.getDocumentElement().getElementsByTagName("RefDocType").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefDocType").item(0).getTextContent() + "</RefDocType>\n"
                + "    <RefDocLng>" + doc1.getDocumentElement().getElementsByTagName("RefDocLng").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefDocLng").item(0).getTextContent() + "</RefDocLng>\n"
                + "    <RefDocPerCent>" + doc1.getDocumentElement().getElementsByTagName("RefDocPerCent").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefDocPerCent").item(0).getTextContent() + "</RefDocPerCent>\n"
                + "    <RefDocOccurences>" + doc1.getDocumentElement().getElementsByTagName("RefDocOccurences").item(0).getTextContent() + "|" + doc2.getDocumentElement().getElementsByTagName("RefDocOccurences").item(0).getTextContent() + "</RefDocOccurences>\n"
                + "  </mycat>\n"
                + "  <organisation>\n"
                + organization
                + "  </organisation>\n"
                + "</statistics>\n";
    }

    public static String mergeHTMLContent(String docSource1, String docSource2, String repTag1, String repTag2, String color2, int start, int totalRefs) {
        String[] content1 = parseHtmlAndUpdateTagsAndColor(docSource1, repTag1, "", 0);
        String[] content2 = parseHtmlAndUpdateTagsAndColor(docSource2, repTag2, color2, start);

        return "<htmlRefDoc>\n"
                + "<!-- <html> <head> <meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"> <title>myQuote</title></head> <body> <A NAME=\"TOP\"></A><A HREF=\"#STATISTIC\">STATISTICS</A>"
                + " TO DO"
                + "<table BORDER=\"1\"> <caption><b>Statistics on referenced documents</b></caption> <tr> <th>Reference</br>Document</th> <th>%</th> <th>References</th> </tr>"
                + content1[0]
                + content2[0]
                + "</table> <hr/> </p><table BORDER=\"1\"> <caption><b>Statistics by referenced texts</b></caption> <tr> <th>Reference</br>Number</th> <th>Referenced Text</th> <th>Reference File</th> </tr> <tr>"
                + content1[1]
                + content2[1]
                + "</table> <hr/>"
                + "</htmlstartcomment>MYQUOTEREF "
                + totalRefs
                + content1[2]
                + content2[2]
                + "MYQUOTEREF</htmlendcomment></P> </body> </html> -->\n"
                + "</htmlRefDoc>";
    }

    public static String mergeInfo(Document doc1, Document doc2) {
        return "<Info>\n"
                + "<references>"
                + getReferencesFromDocument(doc1)
                + getReferencesFromDocument(doc2)
                + "</references>"
                + "</Info>";
    }

    public static String getReferencesFromDocument(Document doc) {
        String references = "";
        NodeList referencesList = doc.getElementsByTagName("reference");
        for (int j = 0; j < referencesList.getLength(); ++j) {
            Element reference = (Element) referencesList.item(j);
            references += "<reference>\n"
                    + "<id>" + reference.getElementsByTagName("id").item(0).getTextContent() + "</id>\n"
                    + "<quote>" + reference.getElementsByTagName("quote").item(0).getTextContent() + "</quote>\n"
                    + "<documents>\n";
            Element documents = (Element) reference.getElementsByTagName("documents").item(0);
            NodeList documentsList = documents.getElementsByTagName("document");
            for (int i = 0; i < documentsList.getLength(); ++i) {
                references += "<document>" + documentsList.item(i).getTextContent() + "</document>\n";
            }
            references += "</documents>\n";
            if (reference.getElementsByTagName("color") != null && (reference.getElementsByTagName("color").getLength() > 0)) {
                references += "<color>" + reference.getElementsByTagName("color").item(0).getTextContent() + "</color>\n";
            } else {
                references += "<color>yellow</color>\n";
            }
            references += "</reference>\n";
        }
        return references;
    }

    public static String getOriginalTextFromDocument(Document doc) {
        return "<origText>\n"
                + doc.getElementsByTagName("origText").item(0).getTextContent()
                + "<origText>";
    }

    private static Integer getReferenceNumber(String refId) {
        int refNumber = 0;
        if (refId.matches("\\d+")) {
            refNumber = Integer.parseInt(refId);
        }
        return refNumber;
    }

    private static String[] parseHtmlAndUpdateTagsAndColor(String docSource, String repTag, String targetColor, int start) {
        FileInputStream in = null;
        String[] content = new String[4];
        content[0] = "";
        content[1] = "";
        content[2] = "";
        try {
            in = new FileInputStream(docSource);
            String xmlContent = UtilsFiles.file2String(in, "UTF-8");
            String html = xmlContent.substring(xmlContent.indexOf("<html>"), xmlContent.indexOf("</html>"));
            if (html.contains("<body>")) {
                String stats1 = html.substring(html.indexOf("<th>References</th>") + 25, html.indexOf("</table>"));
                String stats2 = html.substring(html.indexOf("<th>Reference File</th>") + 29, html.lastIndexOf("</table>"));
                String comments = html.substring(html.indexOf("MYQUOTEREF") + 12, html.lastIndexOf("MYQUOTEREF"));
                int number = 0, newNum = 0;
                if (start > 0) {
                    String regex = "<td>(.*, )+</td>";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(stats1);
                    number = 0;
                    newNum = 0;
                    String res = "";
                    while (matcher.find()) {
                        String[] refs = matcher.group().replace("<td>", "").replace("</td>", "").split(",");
                        for (int i = 0; i < refs.length - 1; i++) {
                            number = Integer.parseInt(refs[i].replace(" ", ""));
                            newNum = number + start;
                            res += newNum + ", ";
                        }
                        stats1 = stats1.replace(matcher.group(), "<td>" + res + "</td>");
                    }
                }
                content[0] = stats1;
                if (start > 0) {
                    String regex = "<td>(\\d+)</td>";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(stats2);
                    number = 0;
                    newNum = 0;
                    while (matcher.find()) {
                        number++;
                        newNum = number + start;
                        stats2 = stats2.replace("<td>" + number + "</td>", "<td>" + newNum + "</td>");
                    }
                }
                content[1] = stats2;
                if (start > 0) {
                    String regex = "([0-9]+)(\\|)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(comments);
                    number = 0;
                    newNum = 0;
                    while (matcher.find()) {
                        newNum = number + start;
                        comments = comments.replace(number + "|", newNum + "|");
                        number++;
                    }
                }
                content[2] = comments;
            }
            return content;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WSRESTUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(WSRESTUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return content;
    }

    // this method gets all the references and locally manages their tags, colors etc.
    private Reference[] getReferences(Document doc, int start, String repTag, String targetColor) {
        NodeList referencesList = doc.getElementsByTagName("reference");
        String originalText = doc.getElementsByTagName("origText").item(0).getTextContent();
        int lastIdx = 0, idx = 0;
        String remainingText = originalText;
        if (referencesList.getLength() > 0) {
            Reference[] references = new Reference[referencesList.getLength()];
            for (int j = 0; j < referencesList.getLength(); ++j) {
                Element reference = (Element) referencesList.item(j);
                Reference ref = new Reference();
                ref.setTextOfRef(reference.getElementsByTagName("quote").item(0).getTextContent());
                ref.setLocalIDX(getReferenceNumber(reference.getElementsByTagName("id").item(0).getTextContent()));
                ref.setGlobalIDX(ref.getLocalIDX() + start);
                if (!targetColor.isEmpty()) {
                    ref.setColor(targetColor);
                } else {
                    if (reference.getElementsByTagName("color") != null && (reference.getElementsByTagName("color").getLength() > 0)) {
                        ref.setColor(reference.getElementsByTagName("color").item(0).getTextContent());
                    } else {
                        ref.setColor("yellow");
                    }
                }
                ref.setTag(repTag);
                idx = remainingText.indexOf(ref.getTextOfRef());
                if (idx >= 0) {
                    ref.setStartIDX(idx + lastIdx);
                    lastIdx = idx + 1;
                    ref.setEndIDX(idx + lastIdx + ref.getTextOfRef().length());
                    remainingText = originalText.substring(ref.getStartIDX() + 1);
                }
                ref.setOpeningText("<a href=\"#" + ref.getGlobalIDX() + "\" id=\"ref" + ref.getGlobalIDX() + "\" onClick=\"return gwtnav(this);\"><FONT style=\"BACKGROUND-COLOR: " + ref.getColor() + "\">[R" + ref.getTag() + ref.getLocalIDX() + "]");
                ref.setClosingText("[E" + ref.getTag() + ref.getLocalIDX() + "]</FONT></a>");
                references[j] = ref;
            }
            return references;
        }
        return null;
    }
}
