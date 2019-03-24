<html>
<head>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<#if error??>
    <p style="color:red;">${error}</p>
</#if>
<form action="/posts" method="post" >
    <div><label for="title">Title:</label></div>
    <div><input id="title" type="text" name="title" /></div>
    <div><label for="body">Body:</label></div>
    <div>
        <textarea id="body" rows="5"  name="body" ></textarea>
    </div>
    <button >submit</button>
</form>
</body>
</html>