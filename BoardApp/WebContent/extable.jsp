<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
a{
	text-decoration:none;
}

.pageNum{
	font-size:14pt;
	color:red;
	font-weight:bold;
}
</style>
<script>
</script>
</head>
<body>
	<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
    <th>조회수</th>
  </tr>
    

	<tr>
    <td>26</td>
    <td></td>
    <td></td>
	<td></td>
	<td></td>	
  </tr>
	
	 <tr>
		<td colspan="5" style="text-align:center">
		
		</td>
	</tr>
  
  	<tr>
		<td colspan="5" style="text-align:center">
			<button>글등록</button>
		</td>
	</tr>
	
	  	<tr>
		<td colspan="5" style="text-align:center">
			<%@ include file="/inc/footer.jsp"%>
		</td>
	</tr>

</table>
</body>
</html>