package com.epsi.gosecuri.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class threadAgent extends Thread {
    private String Agent;
    private List listIdentity;

    threadAgent(String Agent, ArrayList<String> listIdentity)
    {
        this.Agent = Agent;
        this.listIdentity = listIdentity;
    }

    @Override
    public void run() {
        File agentHtml = new File("C:\\Users\\Lele\\IdeaProjects\\gosecurilocal\\src\\com\\epsi\\gosecuri\\web\\"+Agent+".html");

        if(!agentHtml.exists())
        {
            try {
                agentHtml.createNewFile();
                run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            List<String> htmlIdent = Arrays.asList(
                    "<html lang=\"fr\">",
                    "<head>",
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />",
                    "<title>Identification</title>",
                    "<link rel=\"shortcut icon\" href=\"assets/images/logo.png\">\n" +
                            "    <link rel=\"stylesheet\" href=\"assets/css/bootstrap.min.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"assets/css/font-awesome.min.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"assets/css/bootstrap-theme.css\" media=\"screen\" >\n" +
                            "    <link rel=\"stylesheet\" href=\"assets/css/go-securi.css\">",
                    "</head>",
                    "<body>",
                    "<div class=\"navbar navbar-inverse navbar-fixed-top headroom\" >\n" +
                            "    <div class=\"container\">\n" +
                            "        <div class=\"navbar-header\">\n" +
                            "            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"><span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> </button>\n" +
                            "            <a class=\"navbar-brand\" href=\"index.html\"><img src=\"assets/images/logo.png\" alt=\"Logo\" width=\"100\" height=\"70\"></a>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>",
                    "<header id=\"head\" class=\"secondary\"></header>",
                   "<div class=\"container\">\n" +
                           "    <ol class=\"breadcrumb\">\n" +
                           "        <li><a href=\"index.html\">Accueil</a></li>\n" +
                           "        <li class=\"active\" id=\"employe_actif\"></li>\n" +
                           "    </ol>\n" +
                           "    <div class=\"row\">\n" +
                           "        <header class=\"page-header\">\n"+
                           " <h1 class=\"page-title\"> Fiche Agent</h1>\n" +
                           "        </header>"+
                           "<div class=\"row\">\n" +
                           "            <div class=\" col-md-3\">\n" +
                           "                <button type=\"button\" class=\"button\">"+  listIdentity.get(0)+" " + listIdentity.get(1) +"</button>\n" +
                           "            </div>\n" +
                           "            <div class=\" col-md-7\"><h3>"+ listIdentity.get(2)+"</h3></div>\n" +
                           "            <div class=\" col-md-2\">\n" +
                           "                <img src=\"https://raw.githubusercontent.com/Adigbonon/GoSecuri/files/"+Agent+".png\" alt=\"avatar\" style=\"width:150px;height:150px;\">\n" +
                           "            </div>\n" +
                           "        </div>"+
                           "        <div class=\"row\">\n" +
                           "            <div class=\" col-md-3\"></div>\n" +
                           "            <div class=\" col-md-3\" style=\"text-align: right\">\n" +
                           "                <ul style=\"list-style-type:none\">"
            );


            List<String> headerHtmlLines  = new ArrayList<String>(htmlIdent);

            for(int t = 0; t < listIdentity.size()-5 ; t++)
            {
                headerHtmlLines.add(" <li><label for=\"1\">"+ listIdentity.get(5+t)+"</label>\n" + "<input type=\"checkbox\" name=\"question\" id=\"1\" checked disabled/></li>");
            }
            headerHtmlLines.add("</ul>\n" +
                    "            </div>\n" +
                    "            <div class=\" col-md-6\"></div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>");
            headerHtmlLines.add("<footer id=\"footer\" class=\"top-space\">\n" +
                    "    <div class=\"footer1\">\n" +
                    "        Copyright &copy;Go Securi\n" +
                    "    </div>\n" +
                    "</footer>");
            headerHtmlLines.add("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                    "<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script>\n" +
                    "<script src=\"assets/js/headroom.min.js\"></script>\n" +
                    "<script src=\"assets/js/jQuery.headroom.min.js\"></script>\n" +
                    "<script src=\"assets/js/template.js\"></script>");
            headerHtmlLines.add("</body>");
            headerHtmlLines.add("</html>");


            try {

                FileWriter writer = new FileWriter(agentHtml);
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
            System.out.println("Fiche de l'agent : " + Agent);
        }
    }

}


