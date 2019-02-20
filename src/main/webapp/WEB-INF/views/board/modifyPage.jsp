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
				<form action="modifyPage" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${vo.title }">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" class="form-control" name="content" placeholder="Enter Content">${vo.content }</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${vo.writer }" readonly="readonly">
						</div>
					</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-warning" id="bntModify">Modify</button>
						</div>			
					<input type="hidden" name="bno" value="${vo.bno }">
					<input type="hidden" name="page" value="${cri.page }">
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp" %>