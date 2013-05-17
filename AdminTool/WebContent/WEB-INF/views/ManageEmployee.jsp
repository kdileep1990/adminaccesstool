<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Le styles -->
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <!--<link href="assets/css/bootstrap.min.css" rel="stylesheet">-->
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
                  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
                  </script>
                <![endif]-->
        <!-- Le fav and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="assets/ico/apple-touch-icon-57-precomposed.png">
        <script src="assets/js/jquery-1.9.1.js"></script>
        <link rel = "stylesheet" href = "http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <style>
            .search {
                display: none;
            }
            .roles {
                display: none;
            }

            .sidebar-nav {
                padding: 9px 0;
            }

            @media ( max-width : 980px) {
                /* Enable use of floated navbar text */
                .navbar-text.pull-right {
                    float: none;
                    padding-left: 5px;
                    padding-right: 5px;
                }
            }
        </style>
        <script>
            $(document).ready(function() {
                $.ajax({
                    type: "get",
                    url: "search.html",
                    cache: false,
                    success: function(response) {
                        var responseJSON = $.parseJSON(response);
                        var firstNames = responseJSON.firstNames
                        $("#firstName").autocomplete({
                            source: responseJSON.firstNames
                        });
                        $("#lastName").autocomplete({
                            source: responseJSON.lastNames
                        });
                    },
                    error: function() {
                        alert('Error while request.');
                    }
                });
                $("body").on("change", "#userType", function() {
                    if ($("#userType").val() != "")
                        $("#searchParameter").parent().parent().parent().show();
                    else
                        $(".search").hide();
                });
                $("body").on("change", "#searchParameter", function() {
                    var selectedParameters = $("#searchParameter").val()
                    if (selectedParameters != null)
                    {
                        $(".search").hide();
                        $("#searchUser").show()
                        $("#searchParameter").parent().parent().parent().show();
                        for (var i = 0; i < selectedParameters.length; i++)
                            $("#" + selectedParameters[i]).parent().parent().parent().show();
                    }
                    else
                    {
                        $(".search").hide();
                        $("#searchParameter").parent().parent().parent().show();
                    }
                });
                $("body").on("click", "#startSearch", function() {
                    var requestData = "";
                    if ($("#firstName").parent().parent().parent().css('display') != 'none')
                        requestData = 'firstName=' + $("#firstName").val();
                    else
                        requestData = 'firstName=';
//                    alert($("#lastName").val())
                    if ($("#lastName").parent().parent().parent().css('display') != 'none')
                        requestData = requestData + '&lastName=' + $("#lastName").val();
                    else
                        requestData = requestData + '&lastName=';
                    if ($("#role").parent().parent().parent().css('display') != 'none')
                        requestData = requestData + '&role=' + $("#role").val();
                    else
                        requestData = requestData + '&role=';
                    requestData = requestData + '&userType=' + $("#userType").val();
//                    alert(requestData)
                    $.ajax({
                        type: "post",
                        url: "search.html",
                        cache: false,
                        data: requestData,
                        success: function(response) {
                            var responseJSON = response;
                            if (responseJSON.length == 0)
                            {
                                alert("no data related to your search")
                                return;
                            }
                            $("table.search").show();
                            $("table.search > tbody").html("")
                            for (var i = 0; i < responseJSON.length; i++)
                                $("table.search > tbody:last").append('<tr><form><td><span class="value" id="firstName">' + responseJSON[i].firstname + '</span><input type="text" required id="firstNameEdit" class="edit input-medium"></td>' +
                                        '<td><span class="value" id="lastName">' + responseJSON[i].lastname + '</span><input required type="text" id="lastNameEdit" class="edit input-medium"></td>' +
                                        '<td><span class="value" id="email">' + responseJSON[i].emailid + '</span><input required type="text" id="emailEdit" class="edit input-medium"></td>' +
                                        '<td><span class="value" id="salary">' + responseJSON[i].salary + '</span><input required type="text" id="salaryEdit" class="edit input-medium">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="save" class="btn btn-info edit" type="submit">Save</button><div class="btn-group" style="float:right;"><button class="btn"><i class="icon-cog"></i></button><button class="btn dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu"><li><a id="delete" href="#">Delete</a></li><li><a id="edit" href="#">Edit</a></li><li><a id="changeRoles" href="#">Change Roles</a></li></ul></div></td></form></tr>');
                            $(".edit").hide();
                        },
                        error: function() {
                            alert('Error while request.');
                        }
                    });
                    return false;
                });
                $("body").on("click", "#delete", function() {
                    var row = $(this).parent().parent().parent().parent().parent().get(0);
//                    alert($(row).find("#email").text())
                    $.ajax({
                        type: "post",
                        url: "delete.html",
                        cache: false,
                        data: 'emailid=' + $(row).find("#email").text(),
                        success: function(response) {
                            if (response == "true")
                            {
                                $(row).remove();
                                if ($("table.search > tbody").text() == "")
                                    $("table.search").hide();
                                alert("deleted successfully")
                            }
                            else
                                alert("unable to delete")
                        },
                        error: function() {
                            alert('Error while request.');
                        }
                    });
                });
                $("body").on("click", "#edit", function() {
                    var row = $(this).parent().parent().parent().parent().parent().get(0);
                    $(row).find("#firstName").hide();
                    $(row).find("#firstNameEdit").val($(row).find("#firstName").text());
                    $(row).find("#lastName").hide();
                    $(row).find("#lastNameEdit").val($(row).find("#lastName").text());
                    $(row).find("#email").hide();
                    $(row).find("#emailEdit").val($(row).find("#email").text());
                    $(row).find("#role").hide();
                    $(row).find("#roleEdit").val($(row).find("#role").text());
                    $(row).find("#salary").hide();
                    $(row).find("#salaryEdit").val($(row).find("#salary").text());
                    $(row).find(".edit").show();
                });
                $("body").on("click", "#save", function() {
                    var row = $(this).parent().parent().get(0);
                    $.ajax({
                        type: "post",
                        url: "edit.html",
                        cache: false,
                        data: 'firstname=' + $(row).find("#firstNameEdit").val() + '&lastname=' + $(row).find("#lastNameEdit").val() + '&emailid=' + $(row).find("#emailEdit").val() + '&salary=' + $(row).find("#salaryEdit").val() + '&oldemail=' + $(row).find("#email").text(),
                        success: function(response) {
                            if (response == "true")
                            {
                                $(row).find("#firstName").show();
                                $(row).find("#firstName").text($(row).find("#firstNameEdit").val());
                                $(row).find("#lastName").show();
                                $(row).find("#lastName").text($(row).find("#lastNameEdit").val());
                                $(row).find("#email").show();
                                $(row).find("#email").text($(row).find("#emailEdit").val());
                                $(row).find("#role").show();
                                $(row).find("#role").text($(row).find("#roleEdit").val());
                                $(row).find("#salary").show();
                                $(row).find("#salary").text($(row).find("#salaryEdit").val());
                                $(row).find(".edit").hide();
                                alert("updated successfully")
                            }
                            else
                                alert(response)
                        },
                        error: function() {
                            alert('Error while request.');
                        }
                    });
                });
                var roleEmailId = ""
                $("body").on("click", "#changeRoles", function() {
                    var row = $(this).parent().parent().parent().parent().parent().get(0);
                    roleEmailId = $(row).find("#email").text();
                    $.ajax({
                        type: "post",
                        url: "roles.html",
                        cache: false,
                        data: 'emailid=' + $(row).find("#email").text(),
                        success: function(response) {
                            var responseJSON = $.parseJSON(response);
                            $("table.search").hide()
                            $(".roles").show()
                            if (responseJSON.length > 0)
                            {
                                for (var i = 0; i < responseJSON.length; i++)
                                {
                                    $("table.roles > tbody > tr:contains('" + responseJSON[i] + "')").find("#check").prop("checked", true)
                                }
                            }
                        },
                        error: function() {
                            alert('Error while request.');
                        }
                    });
                });
                $("body").on("click", "#assignRole", function() {
//                    alert("hi")
//                    var rows = $("table.roles > tbody").find("tr:eq(2)");
                    var roles = ""
                    for (var i = 0; i < $('table.roles >tbody >tr').length; i++)
                    {
                        if ($("table.roles > tbody").find("tr:eq(" + i + ")").find("#check").prop("checked"))
                            roles = roles + $("table.roles > tbody").find("tr:eq(" + i + ")").find("#role").text() + ",";
                    }
//                    alert(roles.substring(0, roles.length - 1))
                    $.ajax({
                        type: "post",
                        url: "assign.html",
                        cache: false,
                        data: 'emailid=' + roleEmailId + '&roles=' + roles,
                        success: function(response) {
                            alert(response)
                            $(".search").hide();
                            $(".roles").hide();
                        },
                        error: function() {
                            alert('Error while request.');
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="navbar navbar-inverse">
            <div class="navbar" style="background-color: #009999;">
                <div class="container" style="height: 80px;">
                    <br> <a class="brand" href="#"><font color="#000000"
                                                         size="5px">Employee Management System</font></a>
                    <div class="nav-collapse collapse">
                        <p class="navbar-text pull-right" style="color: #000000">
                            <img src="assets/img/glyphicons_003_user.png"
                                 style="height: 15px;">&nbsp;Welcome&nbsp;${sessionScope.user}<a href="#"
                                 class="navbar-link" style="color: #000000"></a>
                        </p>

                    </div>
                    <!--/.nav-collapse -->
                </div>
                <ul class="nav nav-tabs nav-pills">
                    <li><a href="adminHome.html">Home</a></li>
                    <li><a href="register.html">Create Employee</a></li>
                    <li><a href="">Manage Employee</a></li>
                    <li><a href="logout.html">Logout</a></li>
                </ul>
            </div>

        </div>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <br />
                    <br />
                    <div class="span9">
                        <form method="post" action="search" class="form-horizontal"
                              style="text-align: left;">
                            <div class="control-group">
                                <label class="control-label">Employee Type</label>
                                <div class="controls">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-user"></i></span> <select
                                            id="userType" class="input-xlarge">
                                            <option value="">Select User Type</option>
                                            <option value="Full Time Employee">Full Time Employee</option>
                                            <option value="Contractor">Contractor</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group search">
                                <label class="control-label">Search Parameter</label>
                                <div class="controls">
                                    <div class="input-prepend">
                                        <select multiple id="searchParameter" class="input-xlarge">
                                            <option value="firstName">First Name</option>
                                            <option value="lastName">Last Name</option>
                                            <option value="role">Role</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group search">
                                <label class="control-label">First Name</label>
                                <div class="controls">
                                    <div class="input-prepend">
                                        <input id="firstName" type="text"
                                               placeholder="Start type Here " class="input-xlarge">
                                    </div>
                                </div>
                            </div>
                            <div class="control-group search">
                                <label class="control-label">Last Name</label>
                                <div class="controls">
                                    <div class="input-prepend">
                                        <input id="lastName" type="text" placeholder="Start type Here "
                                               class="input-xlarge">
                                    </div>
                                </div>
                            </div>
                            <div class="control-group search">
                                <label class="control-label">Select Role</label>
                                <div class="controls">
                                    <div class="input-prepend">
                                        <select multiple id="role" class="input-xlarge">
                                            <option value="System Admin">System Admin</option>
                                            <option value="User Admin">User Admin</option>
                                            <option value="Regular User">Regular User</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group search" id="searchUser">
                                <label class="control-label offset5" for="input01"></label>
                                <div class="controls">
                                    <button type="submit" id="startSearch" class="btn btn-success" rel="tooltip"
                                            title="Search">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="span12">
                    <table class="table table-bordered search">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Salary</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div class="span6 roles offset2">
                    <table class="table table-bordered roles">
                        <thead>
                            <tr>
                                <th></th>
                                <th>roleName</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input id="check" type="checkbox"></td>
                                <td><p id="role">System Admin</p></td>
                            </tr>
                            <tr>
                                <td><input id="check" type="checkbox"></td>
                                <td><p id="role">User Admin</p></td>
                            </tr>
                            <tr>
                                <td><input id="check" type="checkbox"></td>
                                <td><p id="role">Regular User</p></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="control-group assign" style="float:right;">
                        <div class="controls">
                            <button type="submit" id="assignRole" class="btn btn-success" rel="tooltip"
                                    title="Assign">Assign</button>
                        </div>
                    </div>
                </div>
            </div>
            <footer>
                <p class="">Â© Company 2013</p>
            </footer>
        </div>



        <script src="assets/js/bootstrap.js">
        </script>


    </body>
</html>
