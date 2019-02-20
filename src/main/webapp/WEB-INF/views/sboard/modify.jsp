<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../include/header.jsp" %>
<style>
	div.item{
		width: 100px;
		float: left;
		margin: 3px;
	}
	div.item{
	   	width: 100px;
	   	float: left;
	   	margin: 3px;
	   	position: relative;
   }
   div.item button {
		position: absolute;
		right: 5px;
		top: 5px;
	}
</style>
<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Board List All</h3>
				</div>
				<form action="modify" method="post"  enctype="multipart/form-data">
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
						<div class="form-group">
							<label>Img File</label>
							<input type="file" name="imageFiles" class="form-control" placeholder="select img" multiple="multiple">
							<div id="hiddendiv">
								
							</div>
						</div>
						<div class="form-group">
							<c:forEach var="file" items="${vo.files }">
								<div class="item">
									<img alt="" src="displayFile?filename=${file }">
									<button class="del" type="button" data-src="${file }">X</button>
								</div>
							</c:forEach>
						</div>
						
						<script>
							$(".item").each(function(i,obj) {
								var img = new Image();
								img.onload = function (){
									$(obj).css("width", this.width);
								}
								img.src = $(obj).find("img").attr("src");
							})
							$(document).on("click", "button.del", function() {								
								$(this).parent().remove();
								var name = $(this).attr("data-src");
								var tginput = $("<input>").attr("type","hidden").attr("name","removeimgs").val(name);
								$("#hiddendiv").append(tginput);
							})
						</script>
					</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-warning" id="bntModify">Modify</button>
						</div>			
					<input type="hidden" name="bno" value="${vo.bno }">
					<input type="hidden" name="page" value="${cri.page }">
					<input type="hidden" name="searchType" value="${cri.searchType }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp" %>