<%-- 
    Document   : Adminpage
    Created on : 23-06-2017, 16:21:33
    Author     : marik
--%>

<html>
    <head>
        <title>Company Home Page</title>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Content-Language" content="en-us">
        <meta name="description" content="SIMS">
        <meta name="keywords" content="SIMS">
        <meta name="revisit-after" content="1 Weeks">
        <meta name="distribution" content="global">
        <meta name="robots" content="all">
        <meta name="rating" content="general">
        <meta http-equiv="imagetoolbar" content="no">

        <link type="text/css" rel="stylesheet" href="css.css">

        <link rel="icon" type="image/png" href="favicon.png">

        <script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="scripts/site.js"></script>
        <script type="text/javascript" src="scripts/validator.js"></script>
    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" align="center" id="MainTable">
            <tr>
                <td width="100%">
                    <!--  Header Section Starts Here  -->
                    <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="100%">
                                <div id="Header">
                                    <div><a><img src="resources/bus.png" width="280" height="90" border="0" alt="SIMS" title="SIMS"></a></div>
                                    <div id="Welcome">
                                        <table border="0" cellpadding="0" cellspacing="0" align="right">
                                            <tr>


                                            <div id="authors">Group members: Murched kayed, John Hansen, Lasse Andersen </div>
                                            <div id="class">Class: DAT</div>
                                            <div id= "group"> Group number: 13 DAT </div>
                                        </table>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" height="1"></td>
                        </tr>

                        <tr>
                            <td width="100%" id="MainLinks">
                                <a>Home</a>        </td>
                        </tr>

                        <tr>
                            <td width="100%" height="1" bgcolor="#999999"></td>
                        </tr>
                    </table>
                    <!--  Header Section Ends Here  -->
                </td>
            </tr>
            <tr>
                <td width="100%" height="100%" valign="top">
                    <!-- Body Section Starts Here  -->
                    <fieldset>
                        <legend><img src="resources/home.gif" width="54" height="54" align="left" alt="Website Manager" title="Website Manager"> Website Manager</legend>
                        <div id="AdminHome">

                            <fieldset>
                                <legend><img src="resources/mang.gif" width="54" height="54" align="left" alt="Comapny Management" title="Comapny Management">Comapny Management</legend>
                                <table border="0" cellpadding="0" cellspacing="0" width="100%" id="LogSource">
                                    <tr>
                                        <td width="25%" align="center">
                                            <div class="block" onclick="document.location = '';" onmouseover="setClass(this, 'OVER');" onmouseout="setClass(this, 'OUT');">
                                                <img src="resources/mang.gif" width="54" height="54" vspace="10" hspace="5" alt="Add Log Source" title="Add Log Source" align="left"><br>Get all persons by hobby<br clear="all">
                                            </div>
                                        </td>

                                        <td width="25%" align="center">
                                            <div class="block" onclick="document.location = '';" onmouseover="setClass(this, 'OVER');" onmouseout="setClass(this, 'OUT');">
                                                <img src="resources/mang.gif" width="44" height="54" vspace="10" hspace="15" alt="View Log Source" title="View Log Source" align="left"><br>Get all persons by living city <br clear="all">
                                            </div>
                                        </td>


                                        <td width="25%" align="center">
                                            <div class="block" onclick="document.location = '';" onmouseover="setClass(this, 'OVER');" onmouseout="setClass(this, 'OUT');">
                                                <img src="resources/mang.gif" width="54" height="54" vspace="10" hspace="5" alt="View Log Source" title="View Log Source" align="left"><br>Get the count of people with a given hobby<br clear="all">
                                            </div>
                                        </td>
                                        <td width="25%" align="center">
                                            <div class="block" onclick="document.location = '';" onmouseover="setClass(this, 'OVER');" onmouseout="setClass(this, 'OUT');">
                                                <img src="resources/mang.gif" width="54" height="54" vspace="10" hspace="5" alt="Import Log Source" title="Import Log Source" align="left"><br>Get a list of all zip codes in denmark<br clear="all">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="4" height="10"></td>
                                    </tr>
                                    <tr>


                                        <td width="25%" align="center">
                                            <div class="block" onclick="document.location = '';" onmouseover="setClass(this, 'OVER');" onmouseout="setClass(this, 'OUT');">
                                                <img src="resources/mang.gif" width="54" height="54" vspace="10" hspace="0" alt="Export Log Source" title="Export Log Source" align="left"><br>Get companies list with more than xx employes<br clear="all">
                                            </div>
                                        </td>


                                        <td width="25%" align="center"></td>
                                    </tr>
                                </table>
                            </fieldset>

                        </div>
                    </fieldset>
                    <!--  Body Section Endss Here  -->
                </td>
            </tr>
            <tr>
                <td width="100%">
                    <!--  Footer Section Starts Here  -->
                    <table border="0" width="100%" cellpadding="0" cellspacing="20">
                        <tr>
                            <td width="100%" id="Footer">
                                <table border="0" cellpadding="0" cellspacing="0" width="230%">
                                    <tr>
                                        <td width="">Made by: (Murched kayed, John Hansen, Lasse Andersen)</td>
                                        <td></td>
                                        <td width="">Group Number: 13 Dat</td>
                                        <td></td>
                                        <td width="">Class:  Dat</td>



                                        <td width="52%" align="right"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <!--  Footer Section Ends Here  -->
                </td>
            </tr>
        </table>
    </body>
</html>