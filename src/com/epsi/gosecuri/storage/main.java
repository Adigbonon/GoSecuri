package com.epsi.gosecuri.storage;

import java.io.*;
import java.net.URL;

public class main {
    public static void main (String[] args){
        //TODO : Récupérer les fichiers distants du Git
        //TODO : Stocker dans un format JSON
        //TODO : Réinjecter dans le HTML

        String html = "<div><h1>C'est un fichier HTML</h1><p>Petit paragraphe</p></div>";
        File file = new File("C:\\Users\\Lele\\IdeaProjects\\GoSecuri\\com.epsi.javahtmlfiles\\test.html");

        try
        {
            URL materiel = new URL("https://raw.githubusercontent.com/Adigbonon/GoSecuri/files/liste.txt");
            URL agents = new URL("https://raw.githubusercontent.com/Adigbonon/GoSecuri/files/liste_agents.txt");

            //Cloner le fichier ou lire ligne par ligne ??? GitHubAPI
            readFileInConsole(materiel);
            readFileInConsole(agents);

            //Stocker les données dans un format JSON


            //Injecter dans le HTML
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(html);
            bw.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
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
