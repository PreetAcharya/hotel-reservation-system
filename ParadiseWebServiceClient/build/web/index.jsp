<%-- 
    Document   : index
    Created on : Apr 9, 2017, 7:38:14 PM
    Author     : Preet
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="restclient.RestClient"%>
<%@page import="ejb.Roomtypes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Paradise RESTfulClient</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<body class="w3-light-grey">


<% 
    RestClient rc = new RestClient();
    //out.println(rc.getRoomTypes());
%>

<!-- Page content -->
<div class="w3-content" style="max-width:1532px;">

  <div class="w3-container w3-margin-top" id="rooms">
    <!-- Navigation Bar -->
    <div class="w3-bar">
      <a href="#" class="w3-bar-item w3-button w3-red w3-mobile"><i class="fa fa-bed w3-margin-right"></i>Paradise Hotel</a>
    </div>
  </div>
  
  <div class="w3-row-padding w3-padding-16">
    <% for(Roomtypes rt : rc.getRoomTypes()) { %>
    <div class="w3-third w3-margin-bottom">
      <form method="post" action="http://localhost:8080/ParadiseHotel-war/webresources/service" enctype="application/x-www-form-urlencoded">  
      <img src="images/<% out.print(rt.getRoomTypeId()); %>.jpg" alt="<% out.println(rt.getRoomtype()); %>" style="width:100%">
      <div class="w3-container w3-white">
        <h3><% out.println(rt.getRoomtype()); %></h3>
        <h4><b>     
        <%
        if(request.getParameter("msg")!=null && Integer.parseInt(request.getParameter("roomtypeid").trim())==rt.getRoomTypeId()){
            if(request.getParameter("msg").equals("true")){
                out.println("<span style='color: green'>Available</span>");
            }else{
                out.println("<span style='color: red'>Not Available</span>");
            }   
        }
        %>
        </b></h4>
        <h6 class="w3-opacity">From $<% out.println(rt.getPrice()); %></h6>
        <p><% out.println(rt.getRoomdetails()); %></p>
        <div class="w3-col">
      		<label><i class="fa fa-calendar-o"></i> Check In</label>
                <input class="w3-input w3-border" type="date" name="startdate" placeholder="DD-MM-YYYY" required />
    	</div> 
    	<div class="w3-col">
      		<label><i class="fa fa-calendar-o"></i> Check Out</label>
      		<input class="w3-input w3-border" type="date" name="enddate" placeholder="DD-MM-YYYY" required />
    	</div>
    	<div class="w3-col">
            <input type="hidden" name="room_type_id" value="<% out.println(rt.getRoomTypeId()); %>" />
      		<label><i class="fa fa-male"></i> Number of rooms</label>
      		<input class="w3-input w3-border" type="number" name="qty" placeholder="0" required />
    	</div>
    	<div class="w3-col">
      		<label><i class="fa fa-search"></i> Search</label>
                <input type="submit" value="Search" class="w3-button w3-block w3-black" />
    	</div>
    </div>
    </form>        
    </div>
    <% } %>
    </div>

  <div class="w3-row-padding" id="about">
    <div class="w3-col">
      <h2>About</h2>
      <h6>Our hotel is one of a kind. It is truely amazing. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</h6>
    </div>
  </div>

  <div class="w3-container" id="contact">
    <h2>Contact</h2>
    <p>If you have any questions, do not hesitate to ask them.</p>
    <i class="fa fa-map-marker w3-text-red" style="width:30px"></i> Toronto, CA<br>
    <i class="fa fa-phone w3-text-red" style="width:30px"></i> Phone: +234567890<br>
    <i class="fa fa-envelope w3-text-red" style="width:30px"> </i> Email: paradise@paradise.com<br>
  </div>
</body>
</html>