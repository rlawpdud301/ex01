<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>
<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Board List Search</h3>
					</div>
					<div class="box-body">
						<select name="searchType">
							<option value="n">---</option>
							<option value="t" ${cri.searchType =='t' ? 'selected' : '' }>Title</option> 
							<option value="c" ${cri.searchType =='c' ? 'selected' : '' }>Content</option>
							<option value="w" ${cri.searchType =='w' ? 'selected' : '' }>Writer</option>
							<option value="tc" ${cri.searchType =='tc' ? 'selected' : '' }>Title or Content</option>
							<option value="cw" ${cri.searchType =='cw' ? 'selected' : '' }>Content or Writer</option>
							<option value="tcw" ${cri.searchType =='tcw' ? 'selected' : '' }>Title or Content or Writer</option>
						</select>
						<input type="text" name="keyword" id="keywordInput" value="${cri.keyword }">
						<button id="btnSearch">Search</button>
						<button id="btnNewBoard">New Board</button>
					</div>
				</div>
			<div class="box">
				
				<div class="box-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th style="width: 10px;">BNO</th>
								<th>TITLE</th>
								<th>WRITER</th>
								<th>REGDATE</th>
								<th style="width: 40px;">VIEWCNT</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="board">
								<tr>
									<td>${board.bno }</td>
									<td><a href="${pageContext.request.contextPath}/sboard/read?bno=${board.bno}&page=${pageMaker.cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}">${board.title }</a>
										<strong>[${board.replycnt}]</strong>
									</td>
									<td>${board.writer }</td>
									<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/></td> 
									<td><span class="badge bg-red">${board.viewcnt }</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="box-footer">
					<div class="text-center">  
						<ul class="pagination">
							<c:if test="${pageMaker.prev }">
								<li><a href="${pageContext.request.contextPath }/sboard/list?page=${ pageMaker.startPage-1}&searchType=${cri.searchType}&keyword=${cri.keyword}">&laquo;</a></li>
							</c:if>							
							<c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
								<li ${pageMaker.cri.page == idx ? 'class="active"':''}><a href="${pageContext.request.contextPath }/sboard/list?page=${ idx}&searchType=${cri.searchType}&keyword=${cri.keyword}">${ idx}</a></li> 
							</c:forEach>
							<c:if test="${pageMaker.next }">
								<li><a href="${pageContext.request.contextPath }/sboard/list?page=${ pageMaker.endPage+1}&searchType=${cri.searchType}&keyword=${cri.keyword}">&raquo;</a></li>
							</c:if>	
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	$(function name() {
		$("#btnSearch").click(function () {
			var searchType = $("select[name='searchType']").val();
			var keyword = $("#keywordInput").val();
			location.href = "${pageContext.request.contextPath}/sboard/list?searchType="+searchType+"&keyword="+keyword;
		})
		
		$("#btnNewBoard").click(function () {
			location.href = "${pageContext.request.contextPath}/sboard/register";
		})
	})

</script>


<%@ include file="../include/footer.jsp" %>