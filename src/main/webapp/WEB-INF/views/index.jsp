<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
<script type="text/javascript">
    function updatenote(title, content, status)
    {
    alert("updatenote function");
        document.getElementById("noteTitle").value = title;
        document.getElementById("noteContent").value = content;
        document.getElementById("noteStatus").value = status;
    }
</script>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
		 <h1 align="center">Notes Recorder</h1>
              <form action="add" method="post" style="align:center" name="noteRecorderForm">
                 <table summary="Notes Recorder" border="1" align="center">
                    <%
                       String note_title = "";
                       String note_content = "";
                       String note_status = "";

                       if(request.getParameter("note_title")!=null){
                           note_title = (String) request.getParameter("noteTitle");
                       }

                       if(request.getParameter("note_content")!=null){
                           note_content = (String) request.getParameter("noteContent");
                       }

                       if(request.getParameter("note_status")!=null){
                            note_status = (String) request.getParameter("noteStatus");
                       }
                        %>

                    <tr>
                       <td>Note Title: </td>
                       <td><input type="text" name="noteTitle" id="noteTitle" required="required" pattern="[a-zA-Z0-9\s]+" size=100 maxlength=100 value=<%=note_title %> ></td>
                    </tr>
                    <tr>
                       <td>Note Content: </td>
                       <td><input type="text" name="noteContent" id="noteContent" required="required" pattern="[a-zA-Z0-9\s]+" maxlength=100 size=100 value=<%=note_content %> > </td>
                    </tr>
                    <tr>
                       <td>Note Status: </td>
                       <td><input type="text" name="noteStatus" id="noteStatus" required="required" pattern="[a-zA-Z0-9\s]+" maxlength=100 size=100 value=<%=note_status %> > </td>
                    </tr>
                    <tr>
                     <td align="center"><input type="submit" value="ADD" /></td>
                    </tr>
                 </table>

              </form>
	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
          <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
          <h1 align="center">Notes Recorder List</h1>
          <form action="deleteNote" method="GET" id="deleteform" style="align:center">
             <table border="2" width="70%" cellpadding="2" align="center">
                <tr>
                   <th>Note_Id</th>
                   <th>Note_Title</th>
                   <th>Note_Content</th>
                   <th>Note Status</th>
                   <th>Created At</th>
                </tr>
                <c:forEach var="note" items="${list}">
                   <tr>
                      <td>${note.noteId}</td>
                      <td>${note.noteTitle}</td>
                      <td>${note.noteContent}</td>
                      <td>${note.noteStatus}</td>
                      <td>${note.createdAt}</td>
                      <td><a href="delete?noteId=${note.noteId}">Delete</a>
                      <td><a href="javascript:void(0)" onclick="updatenote('${note.noteTitle}', '${note.noteContent}', '${note.noteStatus}')">Update</a>
                   </tr>
                </c:forEach>
             </table>
          </form>
</body>

</html>