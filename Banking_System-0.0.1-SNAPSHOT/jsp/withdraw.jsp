<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>Withdraw Funds</title>
</head>
<body>
    <h1>Withdraw Funds</h1>
    <html:form action="/withdraw.do">
        <p>Account Number:<html:text property="accountNumber" /></p><br>
        <p>Amount:<html:text property="amount" /></p><br>
        <html:submit value="Withdraw" />
    </html:form>
</body>
</html>