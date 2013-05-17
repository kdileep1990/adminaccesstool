<!DOCTYPE html>
<html lang="en">
  
  <head>
    <meta charset="utf-8">
    <title>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style>
      body { padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */ }
    </style>
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
      </script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
	
	
	<style>
      
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
      
      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
      
    </style>
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
    	<div class="navbar" style="background-color:#009999;">
    		<div class="container" style="height:80px;"><br>
    		<a class="brand" href="#"><font color="#000000" size="5px">Employee Management System</font></a>
    			<div class="nav-collapse collapse">
    				<p class="navbar-text pull-right" style="color:#000000"><img src="assets/img/glyphicons_003_user.png" style="height:15px;"></img>&nbsp;Welcome&nbsp;${sessionScope.user}
    				</p>    				
    			</div>
    			<!--/.nav-collapse -->
    		</div>
		<ul class="nav nav-tabs nav-pills" id="myTab">
  			<li><a href="">Home</a></li>
  			<li><a href="register.html">Create Employee</a></li>
			<li><a href="manage.html">Manage Employee</a></li>
			<li><a href="logout.html">Logout</a></li>
			</ul>
			
    	</div>

    </div>
    <div class="container-fluid">
    	<div class="row-fluid" autocomplete="off" style="height:50px;">
    		
    		<!--/span-->
    		
    		<!--/span-->
    	</div>
    	<!--/row-->
    	<div class="control-group" style="background-color:#F8F8F8;">
		<div class="controls">
		</div>
	</div><hr>
	<div style="height:410px;background-color:#F8F8F8;border-radius:20px">
		<div class="span6">
		</div>
		<div style="height:400px">
			<img src="assets/img/bannerdetail1.png" style="height:395px"/>
		</div>
	</div>
	<hr class="">
    	
	

	<footer>
    		<p class="">
    			© Company 2013
    		</p>
    	</footer>
    </div>

 
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"> -->
<!--     </script> -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="assets/js/bootstrap.js">
    </script>
    
	
  </body>
</html>
