<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	div.item{
		width: 100px;
		float: left;
		margin: 3px;
	}
</style>

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
					<div class="form-group">
						<!-- <label>Writer</label> -->
						<c:forEach var="file" items="${vo.files }">
							<div class="item">
								<img alt="" src="displayFile?filename=${file }">
							</div>
						</c:forEach>
						<%-- <input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${vo.writer }" readonly="readonly"> --%>
					</div>
					<script>
						$(".item").each(function(i,obj) {
							var img = new Image();
							img.onload = function (){
								$(obj).css("width", this.width);
							}
							img.src = $(obj).find("img").attr("src");
						})
					</script>
				</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary" id="bntList">Go List</button>
						<button type="submit" class="btn btn-warning" id="bntModify">Modify</button>
						<button type="submit" class="btn btn-danger" id="bntRemove">Remove</button>
					</div>	
					<form id="f1" action="" method="post">
						<input type="hidden" name="bno" value="${vo.bno }">
						<input type="hidden" name="page" value="${cri.page }">
						<input type="hidden" name="searchType" value="${cri.searchType }">
						<input type="hidden" name="keyword" value="${cri.keyword }">
					</form>
			</div>
		</div>
	</div>
	
	  <div class="row">
      <div class="col-xs-12">
         <div class="box box-success">
            <div class="box-header">
               <h3 class="box-title">ADD NEW REPLY</h3>
            </div>
            <div class="box-body">
               <label>Writer</label>
               <input type="text" class="form-control" placeholder="User Id" id="newReplyWriter">
               <label>Reply Text</label>
               <input type="text" class="form-control" placeholder="Reply Text" id="newReplyText">
            </div>
            <div class="box-footer">
               <button class="btn btn-primary" id="btnReplyAdd">ADD REPLY</button>
            </div>
         </div>
         <ul class="timeline">
            <li class="time-label" id="repliesDiv">
               <span class="bg-green">Replies List <span id="repCnt"></span> </span>
            </li>
            
            
            
         </ul>
         <div class="text-center">
            <ul id="pagination" class="pagination pagination-sm no-margin">
            </ul>
         </div>
      </div>
   </div>
	
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" data-rno=""><!-- 번호너음 -->
					<p><input type="text" id="replytext" class="form-control"></p><!-- 내용들어감 -->
				</div>
				<div class="modal-footer">
              		<button type="button" class="btn btn-info" id="btnReplyMod"  data-rno="">Modify</button>
               		<button type="button" class="btn btn-default" data-dismiss=modal>Close</button>
            	</div>
			</div>
		</div>
		
	</div>
</section>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template1" type="text/x-handlebars-template">
{{#each.}}
<li class="replyLi" data-rno="{{rno}}">
<i class="fa fa-comments bg-blue"></i>
<div class="timeline-item">
   <span class="item">
      <i class="fa fa-clock-o"></i>{{tempDate regdate}}
   </span>
   <h3 class="timeline-header">
      <strong>{{rno}}</strong> - {{replyer}}
   </h3>
   <div class="timeline-body">
      {{replytext}}
   </div>
   <div class="timeline-footer">
      <a class="btn btn-primary btn-xs btnModify" data-toggle="modal" data-target="#modifyModal" data-rno="{{rno}}" data-text="{{replytext}}">Modify</a>
      <a class="btn btn-danger btn-xs btnDelete">Delete</a>
   </div>
</div>
</li>
{{/each}}
</script>
<script>
	Handlebars.registerHelper("tempDate",function(value){
		var date =new Date(value);
		var year =date.getFullYear();
		var month =date.getMonth()+1;
		var day = date.getDate();
		
		return year + "/" + month + "/" + day;
	}) 

	var bno = ${vo.bno };
	
	function getPageList(page) {
		$.ajax({
					url : "${pageContext.request.contextPath}/replies/" + bno + "/" + page,
					type : "get",
					dataType : "json",
					success : function(json) {
						
						$("#repCnt").text("["+json.replycnt+"]");
						console.log(json)
						
						$(".replyLi").remove();
						
						var source = $("#template1").html();

						var f = Handlebars.compile(source);

						var result = f(json.list);

						$(".timeline").append(result);
						
						$("#pagination").empty();
						for (var i = json.pageMaker.startPage; i <= json.pageMaker.endPage; i++) {
							var liTag = $("<li>")
							if (i == json.pageMaker.cri.page) {
								liTag.addClass("active");
							}
							var aTag = $("<a>").append(i).attr("href","#");
							liTag.append(aTag);
							$("#pagination").append(liTag);
						}
					}
				})

	}
	
	$(function () {
		getPageList(1);
		
		$("#btnReplyAdd").click(function () {
			
			var replyer = $("#newReplyWriter").val();
			
			var replytext = $("#newReplyText").val();
			
			var jsonBody = {bno:bno,replyer:replyer,replytext:replytext};
			
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/",
				type:"post",
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"POST"		
				},
				data:JSON.stringify(jsonBody),//" {bno:bno,replyer:replyer,replytext:replytext}"스트링으로변환
				dataType:"text",
				success : function(json) {
					console.log(json)
					alert(json);
					getPageList(1); 
					$("#newReplyWriter").val("");
					$("#newReplyText").val("");
				}
				
			})
		})
		
		$(document).on("click", ".pagination a", function(e) {
			e.preventDefault();
			
			var num = $(this).text();
			getPageList(num);
		})
		
		$(document).on("click", ".btnModify", function() {
			
			var rno = $(this).parents(".timeline-item").find("strong").text();
			
			var text = $(this).attr("data-text");
			
			$("#replytext").attr("data-text",rno);
			$("#replytext").val(text);
			
			
		})
		
		$(document).on("click", "#btnReplyMod", function() {
			var replyText = $("#replytext").val();
			
			var rno = $("#replytext").attr("data-text");
		
			
			var jsonBody = {replytext:replyText};
			
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+rno,
				type:"put",
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"PUT"		
				},
				data:JSON.stringify(jsonBody),
				dataType:"text",
				success: function (json) {
					console.log(json);
					if (json == "success") {
						alert(rno+"가 수정되었습니다.")
					}
					$("#modifyModal").modal("hide");
					getPageList(1); 
				}
			})
			
		})
		
		$(document).on("click", ".btnDelete", function() {
			
			var rno = $(this).parents(".timeline-item").find("strong").text();
			
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+rno,
				type:"delete",			
				dataType:"text",
				success: function (json) {
					console.log(json);
					if (json == "success") {
						alert(rno+"가 삭제되었습니다.")
					}
					getPageList(1); 
				}
			})
			
		})
		
		
		
	})
</script>

<script>
	$(function(){
		$("#bntList").click(function(){
			//location.href = "${pageContext.request.contextPath}/board/listPage";
			$("#f1").attr("action","list");
			$("#f1").attr("method","get");
			$("#f1").submit();
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