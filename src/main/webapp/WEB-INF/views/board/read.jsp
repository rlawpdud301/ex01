<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ include file="../include/header.jsp" %>
<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Board List All</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${vo.title }" readonly="readonly">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" class="form-control" name="content" placeholder="Enter Content" readonly="readonly">${vo.content }</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${vo.writer }" readonly="readonly">
					</div>
				</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary" id="bntList">Go List</button>
						<button type="submit" class="btn btn-warning" id="bntModify">Modify</button>
						<button type="submit" class="btn btn-danger" id="bntRemove">Remove</button>
					</div>	
					<form id="f1" action="" method="post">
						<input type="hidden" name="bno" value="${vo.bno }">
					</form>
			</div>
		</div>
	</div>
</section>

<script>
	$(function(){
		$("#bntList").click(function(){
			location.href = "${pageContext.request.contextPath}/board/listAll";
		})
		$("#bntRemove").click(function(){
			$("#f1").attr("action","remove");
			$("#f1").submit();
		})
		$("#bntModify").click(function(){
			$("#f1").attr("method","get");
			$("#f1").attr("action","modify");
			$("#f1").submit();
		})
	})
</script>


<%@ include file="../include/footer.jsp" %>