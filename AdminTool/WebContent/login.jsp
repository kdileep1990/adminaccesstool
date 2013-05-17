<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Investor Portal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Investor Portal Site">
	    <link rel="shortcut icon" href="SemanticBits/ico/favicon.ico">
        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="span12"><br/><br/>
					<div class="span9" style="border:5px solid #6082B6; border-radius:6px;">
                    <form:form id="signup" class="form-horizontal" method="post" action="login.html" commandName="user" style="text-align:left;">
                        <fieldset><legend>Sign-In</legend><br/>	
                            <div class="span6">         
                            <span style="color:red">${requestScope.msg}</span>                      
                                <div class="control-group">
                                    <label class="control-label">Email-Id</label>
                                    <div class="controls">
                                        <div class="input-prepend">
                                            <span class="add-on"><i class="icon-envelope"></i></span>
                                            <form:input path="emailid" class="input-large" id="email"  placeholder="Email Id"/>
                                        </div>
                                    </div>	
                                </div>                             
                                <div class="control-group">
                                    <label class="control-label">Password</label>
                                    <div class="controls">
                                        <div class="input-prepend">
                                            <span class="add-on"><i class="icon-lock"></i></span>
                                            <form:password path="password" id="password" class="input-large" placeholder="Password"/>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="control-group">
                                        <label class="control-label offset5" for="input01"></label>
                                        <div class="controls">
                                            <button type="submit" id="submit" class="btn btn-success" rel="tooltip" title="Create My Account">Sign-In</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form:form>
                    </div>
                </div>
            </div><br/><br/>
        </div>
<!-- Javascript -->
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/jquery-ui.js"></script>
</body>
</html>

