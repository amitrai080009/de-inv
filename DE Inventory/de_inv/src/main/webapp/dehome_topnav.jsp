<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>

     <div class="top_nav">
          <div class="nav_menu">
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars">&nbsp;&nbsp;AIF</i></a>
              </div>
              <nav class="nav navbar-nav">
              <ul class=" navbar-right">
                <li class="nav-item dropdown open" style="padding-left: 15px;">
                  <a href="javascript:;"  aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="true">
                    <img src="images/AIF_Logo.jpg" alt="AIF" width="70px" height="30px"> 
                    
                    <!-- Session User name appears here -->

				Welcome:- <i class="fa fa-circle green small"></i><s:property value="%{#session['loggedUserName']}" />
						
			
                  
                  </a><a  href="logout"><i class="fa fa-sign-out pull-right"></i> LogOut</a>
                 
                </li>

                
              </ul>
            </nav>
          </div>
        </div>
    