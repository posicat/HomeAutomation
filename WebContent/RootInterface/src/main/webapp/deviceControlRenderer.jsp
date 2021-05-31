<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<jsp:useBean id="devControl" class="org.cattech.HomeAutomation.RootInterface.servlets.DeviceControlRenderer" scope="page" />
<% devControl.setupServletState(request,response); %>

<% 
String type = devControl.getDeviceType();
boolean rendered=false;

if ( type.equals("lamp_dimbri") ) { 
	// For now!  TODO MB
	type="lamp_dimbri_d16";
}
%>

<table class="controlBlock">
<caption class="deviceTitle"><%= devControl.getDeviceTitle() %></caption>
<% if (type.equals("lamp_dimbri_d16")) { %> 
		<tr>
			<td> <button onClick="<%= devControl.generateAction("on") %>">ON</button></td>
			<td> <button onClick="<%= devControl.generateAction("off") %>">OFF</button></td>
		</tr><tr>
			<td colspan=2>
				<button onClick="<%= devControl.generateAction("dim:15") %>">-15</button>
				<button onClick="<%= devControl.generateAction("dim:10") %>">-10</button>
				<button onClick="<%= devControl.generateAction("dim:5") %>">-5</button>
				<button onClick="<%= devControl.generateAction("dim:1") %>">-1</button>
				<button onClick="<%= devControl.generateAction("dim:1") %>">+1</button>
				<button onClick="<%= devControl.generateAction("dim:5") %>">+5</button>
				<button onClick="<%= devControl.generateAction("dim:10") %>">+10</button>
				<button onClick="<%= devControl.generateAction("dim:15") %>">+15</button>
			</td>
		</tr>
<% 
	rendered=true;
} 
%>
<% if (type.equals("lamp_dimbri_cmww")) { %> 
		<tr>
			<td> <button onClick="<%= devControl.generateAction("on") %>">ON</button></td>
			<td> <button onClick="<%= devControl.generateAction("off") %>">OFF</button></td>
		</tr><tr>
				
		</tr><tr>
			<td colspan=2>
				<button onClick="<%= devControl.generateAction("dim:15") %>">-15</button>
				<button onClick="<%= devControl.generateAction("dim:10") %>">-10</button>
				<button onClick="<%= devControl.generateAction("dim:5") %>">-5</button>
				<button onClick="<%= devControl.generateAction("dim:1") %>">-1</button>
				<button onClick="<%= devControl.generateAction("dim:1") %>">+1</button>
				<button onClick="<%= devControl.generateAction("dim:5") %>">+5</button>
				<button onClick="<%= devControl.generateAction("dim:10") %>">+10</button>
				<button onClick="<%= devControl.generateAction("dim:15") %>">+15</button>
			</td>
		</tr>
<% 
	rendered=true;
} 
%>
</table>
<% if (! rendered) { %>
	Couldn't find device <%= type %><br>
<% } %>

<!-- <%= type %> -->