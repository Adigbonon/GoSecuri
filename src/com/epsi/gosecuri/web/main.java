package com.epsi.gosecuri.web;

import jdk.nashorn.internal.parser.JSONParser;
//import org.json.simple.JSONObject;
import java.io.*;

public class main {
    public static void main (String[] args) throws java.lang.Exception{
        String htmlfile="",cssStyle="",javaScript="",inputCheckbox,header, body, footer;
        String mapText="";
        int nbStaff = 0;

        //Récup le fichier mapAgents.txt en local
        File file = new File("C:\\Users\\Lele\\IdeaProjects\\gosecurilocal\\mapAgents.txt");
        //Sortir les listes avec les clés-nom
        getMapFromTextFile(file,mapText);

        //Boucle pour chaque liste <= nbStaff
            //créer un html avec la classe htmlFile
            //stocker le html dans le git (wsh comment ??) ==> un dossier bien rangé et tout

    }

    public static void readFileInConsole(File file) throws java.io.IOException {
        try(FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader)) {
            System.out.println(file.getAbsolutePath());
            boolean read = true;
            while (read) {
                String line = buffer.readLine();
                if (line == null) {
                    read = false;
                    continue;
                }
                System.out.println(line);
            }
        }
    }

    public static void getMapFromTextFile(File file, String mapText) throws IOException {
        try(FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader)) {
            System.out.println(file.getAbsolutePath());
            boolean read = true;
            while (read) {
                String line = buffer.readLine();
                if (line == null) {
                    read = false;
                    continue;
                }
                mapText+=line;
            }
            System.out.println(mapText);
        }

        //Transformer String to String JSON
        String jsonString ="";
        //JSONParser parser = new JSONParser(jsonString);
        //JSONObject json = (JSONObject) parser.parse(stringToParse);
    }

    private void addButton(String content, String css){

    }

    private void addImage(){

    }
    private void addPersonCheckbox(String name){

    }
}

