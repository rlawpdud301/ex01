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
					<h3 class="box-title">Board List All</h3>
				</div>
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
									<td><a href="${pageContext.request.contextPath}/board/read?bno=${board.bno}">${board.title }</a></td>
									<td>${board.writer }</td>
									<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/></td>
									<td><span class="badge bg-red">${board.viewcnt }</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div>
					
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>