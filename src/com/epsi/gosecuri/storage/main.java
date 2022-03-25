package com.epsi.gosecuri.storage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class main {
    public static void main (String[] args) throws Exception{
        HashMap<String, ArrayList<String>> agents = new HashMap<String,ArrayList<String>>();
        ArrayList<String> infoAgent = new ArrayList<String>();

        //Etape 1 : récupérer les données staff et matériel depuis Git


        URL staff = getFileFromGit("staff"); //Récupérer l'url git de staff.txt
        agents = readStaffFromGit(staff); // Stocker les données dans une hashMap

        try
        {
            for(Map.Entry<String, ArrayList<String>> entry : agents.entrySet()) //Cherche les fichiers git correspondant à chaque agent
            {
                String key = entry.getKey();
                int beginLengthFile = getFileFromGit(key).getFile().length() - (key.length() + 4);
                int endLengthFile = getFileFromGit(key).getFile().length() -4;

                String fileNameGit = getFileFromGit(key).getFile().substring(beginLengthFile, endLengthFile) ;

                if (key.equals(fileNameGit))
                {
                    URL url = getFileFromGit(key);
                    infoAgent = readInfoAgentFromGit(url); //Stocker les données de l'agent dans une liste
                    agents.replace(key, infoAgent); // Ajouter la liste à la hashMap
                }
            }
        } catch(Exception NullPointerException){
            throw new NullPointerException("Il manque un fichier correspondant au membre du staff " + agents.keySet());
        }

        try
        {
            File file = new File("C:\\Users\\Lele\\IdeaProjects\\gosecurilocal\\mapAgents.txt"); //Stocker les données en local
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(agents.toString());
            bw.close();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Le chemin d'accès au fichier txt mapAgent est incorrect.");
        }

        //Etape 2 : Construire la page d'accueil
        createHomePage(infoAgent);

        //Etape 3 : Générer les pages des agents



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

    private static void createHomePage(ArrayList<String> infoAgent){
        //Création ou Update du fichier accueil.html
        File file = new File("C:\\Users\\Lele\\IdeaProjects\\gosecurilocal\\src\\com\\epsi\\gosecuri\\web\\Accueil.html");

        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            } catch (IOException e)  { e.printStackTrace();  }
        }
        else {
            List<String> lignesHtml = Arrays.asList( //Concaténation du squelette html
                    "<html lang=\"fr\">",
                    "<head>",
                    "<title> Accueil GoSecuri</title>",
                    "<link rel=\"shortcut icon\" href=\"assets/images/logo.png\">",
                    "<link rel=\"stylesheet\" href=\"assets/css/bootstrap.min.css\">",
                    "<link rel=\"stylesheet\" href=\"assets/css/font-awesome.min.css\">",
                    "<link rel=\"stylesheet\" href=\"assets/css/bootstrap-theme.css\" media=\"screen\" >",
                    "<link rel=\"stylesheet\" href=\"assets/css/go-securi.css\">",
                    "</head>",
                    "<body>",
                    "<header>",
                    "<div class=\"navbar navbar-inverse navbar-fixed-top headroom\" >\n" +
                            "    <div class=\"container\">\n" +
                            "        <div class=\"navbar-header\">\n" +
                            "            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"><span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> </button>\n" +
                            "            <a class=\"navbar-brand\" href=\"accueil.html\"><img src=\"assets/images/logo.png\" alt=\"Logo\" width=\"100\" height=\"70\"></a>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>",
                    "</header>",
                    "<header id=\"head\" class=\"secondary\"></header>",
                    "<div class=\"container\">\n" +
                            "    <ol class=\"breadcrumb\">\n" +
                            "        <li class=\"active\">Accueil</li>\n" +
                            "    </ol>\n" +
                            "    <div class=\"row\">\n" +
                            "        <header class=\"page-header\">\n" +
                            "            <h1 class=\"page-title\">Liste des agents</h1>\n" +
                            "        </header>\n" +
                            "        <div class=\"liste-agents\">\n" +
                            "<ul>"
            );


            List<String> headerHtmlLines  = new ArrayList<String>(lignesHtml);

            for(int a = 0; a < infoAgent.size(); a++)
            {
                headerHtmlLines.add("<li><a href=\""+infoAgent.get(a)+".html\"style=\"margin: 20px\">"+infoAgent.get(a)+"</a></li>");
            }

            headerHtmlLines.add("</ul>\n"+
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<footer id=\"footer\" class=\"top-space\">\n" +
                    "<div class=\"footer1\">\n" +
                    "Copyright &copy;Go Securi\n" +
                    "</div>\n" +
                    "</footer>");
            headerHtmlLines.add("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                    "<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script>\n" +
                    "<script src=\"assets/js/headroom.min.js\"></script>\n" +
                    "<script src=\"assets/js/jQuery.headroom.min.js\"></script>\n" +
                    "<script src=\"assets/js/template.js\"></script>\n"+ "</body>\n" + "</html>");

            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer);

                for (String line : headerHtmlLines)
                {
                    bw.write(line);
                    bw.newLine();
                    System.out.println(line);
                }
                bw.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




