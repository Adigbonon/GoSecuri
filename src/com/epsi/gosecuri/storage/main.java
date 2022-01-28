package com.epsi.gosecuri.storage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main (String[] args) throws Exception{

        //Paramètres
        HashMap<String, ArrayList<String>> agents = new HashMap<String,ArrayList<String>>();

        //Récupère le fichier list.txt et staff.txt
        URL staff = getFileFromGit("staff");

        //Lire chaque ligne de staff.txt (lecture fichier)
        agents = readStaffFromGit(staff); // stocker dans une map chaque ligne

        //boucle cherche le fichier qui porte le même nom que l'élément
        try {
            for(Map.Entry<String, ArrayList<String>> entry : agents.entrySet()) {
                String key = entry.getKey();
                int beginLengthFile = getFileFromGit(key).getFile().length() - (key.length() +4);
                int endLengthFile = getFileFromGit(key).getFile().length() -4;
                String fileNameGit =getFileFromGit(key).getFile().substring(beginLengthFile, endLengthFile) ;

                if (key.equals(fileNameGit)) {
                    URL url = getFileFromGit(key);
                    ArrayList<String> infoAgent = readInfoAgentFromGit(url); //lire chaque ligne et l'ajouter à une liste
                    agents.replace(key, infoAgent); // stocke la liste dans la map à l'emplacement correspondant
                }
            }
        } catch(Exception NullPointerException){
            throw new NullPointerException("Il manque un fichier correspondant au membre du staff " + agents.keySet());
        }

        //System.out.println(agents);
        //réécrire tout ça dans mapAgents.txt, stocké jsp où pour le prochain trigger
        try{
            File file = new File("C:\\Users\\Lele\\IdeaProjects\\gosecurilocal\\mapAgents.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(agents.toString());
            bw.close();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Le chemin d'accès au fichier txt mapAgent est incorrect.");
        }
    }

    private static URL getFileFromGit(String filename) throws Exception{
        try{
            URL address = new URL("https://raw.githubusercontent.com/Adigbonon/GoSecuri/files/"+filename+".txt");
            return address;
        } catch(Exception MalformedURLExpression){
            throw new Exception("L'URL est incorrecte.");
        }
    }

    private static HashMap<String, ArrayList<String>> readStaffFromGit(URL url) throws java.io.IOException{
        String line="";
        HashMap<String, ArrayList<String>> staffList = new HashMap<String, ArrayList<String>>();
        ArrayList<String> listInformations = new ArrayList<String>();

        InputStream isStream = url.openStream();
        BufferedReader br = new BufferedReader((new InputStreamReader(isStream)));
        while((line = br.readLine()) != null){
            staffList.put(line, listInformations);
        }
        return staffList;
    }

    private static ArrayList<String> readInfoAgentFromGit(URL url) throws java.io.IOException{
        String line="";
        ArrayList<String> agentInfosList = new ArrayList<String>();

        InputStream isStream = url.openStream();
        BufferedReader br = new BufferedReader((new InputStreamReader(isStream)));
        while((line = br.readLine()) != null){
            agentInfosList.add(line);
        }
        return agentInfosList;
    }
}
