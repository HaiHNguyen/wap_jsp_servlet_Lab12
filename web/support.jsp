<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSTechSupport</title>
</head>
<body>
<form method="POST" action="support">
    <div>
        <label for="name">Name:</label>
        <input id="name" name="name"/>
    </div>

    <div>
        <label for="email">Email:</label>
        <input id="email" name="email" />
    </div>

    <div>
        <label for="problem">Problem:</label>
        <input id="problem" name="problem" />
    </div>

    <div>
        <label for="description">Description:</label>
        <textarea id="description" name="description" cols="80" rows="20"></textarea>
    </div>

    <div>
        <button type="submit">Help</button>
    </div>
</form>
</body>
</html>
