<%-- 
    Document   : notes
    Created on : 28-Oct-2017, 1:14:53 PM
    Author     : dan-romero-mancia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes</h1>

        <table>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Content</th>
            </tr>

            <c:forEach var="note" items="${noteTable}">
                <tr>
                    <td>${note.noteId}</td>
                    <td>${note.date}</td>
                    <td>${note.contents}</td>
                    <td>
                        <form action="notes" method="POST" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete"> 
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="POST">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
