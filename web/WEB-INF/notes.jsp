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
                            <input type="hidden" name="selectedNote" value="${note.noteId}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="GET">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedNote" value="${note.noteId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedNote == null}">  
            <h3>Add Note</h3>
            <form action="notes" method="POST">
                <input type="text" name="contents">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>

        <c:if test="${selectedNote != null}">
            <h3>Edit Note</h3>
            <form action="notes" method="POST">
                <input type="text" name="contents" value="${selectedNote.contents}">
                <input type="hidden" name="noteId" value="${selectedNote.noteId}">
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>


    </body>
</html>
