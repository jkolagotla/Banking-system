<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>Deposit Funds</title>
</head>
<body>
    <h1>Deposit Funds</h1>
    <html:form action="/deposit.do">
        <p>Account Number:<html:text property="accountNumber" /></p><br>
        <p>Amount:<html:text property="amount" /></p><br>
        <html:submit value="Deposit" />
    </html:form>
</body>
</html>