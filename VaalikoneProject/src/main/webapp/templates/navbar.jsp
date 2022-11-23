<header>

    <nav class="navbar navbar-inverse">
        <ul class="nav navbar-nav">
            <li><a href="/">Etusivu</a></li>
            <li><a href="/ehdokas-sivu">Selaa ehdokkaita</a></li>
            <li><a href="/puolueet">Selaa puolueita</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
        	<li>
        	<
        	<%
			    if(session.getAttribute("username") == null){
			        out.println("<p class='navbar-text'>not logged in</p>");
			    }else{
			        out.println("<p class='navbar-text'>"+session.getAttribute("username")+"</p>");
			    }
			%>
        	</li>
        	<li>
        	<%
			    if(session.getAttribute("username") == null){
			 
			    }else{
			    	int role = (int)session.getAttribute("role");
			    	if(role == 1){
			    		out.println("<a href='/ehdokas-sivu/me'>Profile</a>");
			    	}else if(role == 3){
				    	out.println("<a href='/dashboard'>Dashboard</a>");
			    	}
			    }
			%>
        	</li>
 	       	<li>
        	<%
			    if(session.getAttribute("username") == null){
			    	out.println("<a href='/login'>login</a>");
			    }else{
			    	out.println("<a href='/logout'>Logout</a>");
			    }
			%>
        	</li>
        </ul>
    </nav>

</header>