<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
    <h1>Create Account</h1>
    <html:form action="/createAccount.do">
        <p>Account Number:<html:text property="accountNumber" /></p><br>
        <p>Account Holder Name:<html:text property="name" /></p><br>
        <p>Initial Deposit:<html:text property="initialDeposit" /></p><br>
        <html:submit value="Create Account" />
    </html:form>
</body>
</html>