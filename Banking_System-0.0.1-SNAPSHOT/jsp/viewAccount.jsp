<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>View Account</title>
</head>
<body>
    <h1>View Account</h1>
    <html:form action="/viewAccount.do">
        <p>Account Number:<html:text property="accountNumber" /></p><br>
        <html:submit value="View Account" />
    </html:form>
</body>
</html>