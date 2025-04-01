<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generated Report</title>
</head>
<body>
    <h1>Report</h1>
    <p><%= (request.getAttribute("message") != null) ? request.getAttribute("message") : "" %></p>
    <% String reportPath = (String) request.getAttribute("reportPath"); if (reportPath != null) { %>
        <% if (reportPath.endsWith(".jpg")) { %>
            <img src="<%= request.getContextPath() %>/<%= reportPath %>" alt="Report Image" />
        <% } else if (reportPath.endsWith(".pdf") || reportPath.endsWith(".xlsx")) { %>
            <a href="<%= request.getContextPath() %>/<%= reportPath %>">Download Report</a>
        <% } %>
    <% } %>
</body>
</html>