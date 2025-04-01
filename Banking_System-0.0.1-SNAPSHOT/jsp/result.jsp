<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>Operation Result</title>
</head>
<body>
    <h1>Operation Result</h1>
    <p><bean:write name="message" /></p>

     <%-- Check if account not found --%>
    <logic:present name="accountNotFound">
        <p><font color="red">Account not found</font></p>
    </logic:present>

    <%-- Display account details if available --%>
    <logic:notPresent name="accountNotFound">
    
        <logic:notEmpty name="account">
            Account Number: <bean:write name="account" property="accountNumber" /><br/>
            Name: <bean:write name="account" property="accountHolderName" /><br/>
            Balance: <bean:write name="account" property="balance" /><br/>
        </logic:notEmpty>

        <%-- Display transactions if available --%>
        <logic:present name="transactions">
            <table border="1">
                <tr>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Date</th>
                </tr>
                <logic:iterate name="transactions" id="transaction">
                    <tr>
                        <td><bean:write name="transaction" property="type" /></td>
                        <td><bean:write name="transaction" property="amount" /></td>
                        <td><bean:write name="transaction" property="description" /></td>
                        <td><bean:write name="transaction" property="timestamp" /></td>
                    </tr>
                </logic:iterate>
            </table>
        </logic:present>
    </logic:notPresent>
</body>
</html>