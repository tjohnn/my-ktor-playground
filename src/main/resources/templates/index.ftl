<#-- @ftlvariable name="data" type="java.util.List<com.tjohnn.blog.domain.models.Person>" -->
<html>
<head>
    <title>Welcome to FreeMarker</title>
    <link rel="stylesheet" href="/static/styles.css" />
</head>
<body>
<div>
    <a class="btn" href="/logout" >Logout</a>
</div>
<table>
    <thead>
    <tr class="table-head">
        <td>First Name</td>
        <td>Last Name</td>
        <td>Email</td>
    </tr>
    </thead>
    <tbody>
<#list data as item>
<tr>
    <td>${item.firstName}</td>
    <td>${item.lastName}</td>
    <td>${item.email}</td>
</tr>
</#list>
    </tbody>
</table>
</body>
</html>