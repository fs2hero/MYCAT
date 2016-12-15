/**********
Copyright © 2010-2012 Olanto Foundation Geneva

This file is part of myCAT.

myCAT is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

myCAT is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with myCAT.  If not, see <http://www.gnu.org/licenses/>.

 **********/
package org.olanto.zahir.run.bitext;

import org.olanto.senseos.SenseOS;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import static org.olanto.zahir.run.bitext.AlignBiTextConstant.*;
import static org.olanto.util.Messages.*;

/**
 * Une classe pour initialiser les constantes.
 */
public class ConfigurationAlignBiTextFromFile implements BiTextInit {

    String fileName = "to be initialised";
    Properties prop;

    /** cr�e l'attache de cette classe.
     */
    public ConfigurationAlignBiTextFromFile() {
    }

    /**
     * charge la configuration depuis un fichier de properties
     * @param fileName nom du fichier
     */
    public ConfigurationAlignBiTextFromFile(String fileName) {
        this.fileName = fileName;
        FileInputStream f = null;
        try {
            f = new FileInputStream(fileName);
        } catch (Exception e) {
            error_fatal("cannot find properties file:" + fileName);
        }
        try {
            prop = new Properties();
            prop.loadFromXML(f);
        } catch (Exception e) {
            error("errors in properties file:" + fileName, e);
            System.exit(0);
        }
        msg("properties from: " + fileName);
        prop.list(System.out);
    }

    /** initialisation permanante des constantes.
     * Ces constantes choisies d�finitivement pour toute la dur�e de la vie de l'index.
     */
    public void InitPermanent() {
    }

    /** initialisation des constantes de configuration (modifiable).
     * Ces constantes choisies d�finitivement pour toute la dur�e de la vie du processus.
     */
    public void InitConfiguration() {
        // les directoire
        FOLDER_SEGMENTED = prop.getProperty("FOLDER_SEGMENTED", SenseOS.getMYCAT_HOME() + "/corpus/txt");
        FOLDER_TMX = prop.getProperty("FOLDER_TMX", SenseOS.getMYPREP_HOME() + "/TMX");
        IDX_DONTINDEXTHIS = prop.getProperty("IDX_DONTINDEXTHIS", SenseOS.getMYCAT_HOME() + "/config/dontindexthiswords.txt");
        LIST_OF_BITEXT_LANG = prop.getProperty("LIST_OF_BITEXT_LANG", "XXYY XXZZ");

        Pattern ps = Pattern.compile("[\\s]");  // le blanc

        BITEXT = ps.split(LIST_OF_BITEXT_LANG);
        MIN_DICT_LEVEL = Float.parseFloat(prop.getProperty("MIN_DICT_LEVEL", "0.1"));
      MIN_SIM_LEVEL = Float.parseFloat(prop.getProperty("MIN_SIM_LEVEL", "0.2"));
      DONT_CHECK_LENGTH = Boolean.parseBoolean(prop.getProperty("DONT_CHECK_LENGTH", "false"));
      
    }
}
