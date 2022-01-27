package com.epsi.gosecuri.web;

import java.io.*;
import java.net.URL;


public class main {
    public static void main (String[] args){
        int nbStaff = 0;
        //Récup le fichier Recap depuis le git
        //Sortir les listes avec les clés-nom

        //Boucle pour chaque liste <= nbStaff
            //créer un html avec la classe htmlFile
            //stocker le html dans le git (wsh comment ??) ==> un dossier bien rangé et tout

    }

    public static void readFileInConsole(URL url) throws java.io.IOException {
        String line = "";
        InputStream isStream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(isStream));
        while ((line = br.readLine()) !=null)
        {
            System.out.println(line);
        }
    }
}

