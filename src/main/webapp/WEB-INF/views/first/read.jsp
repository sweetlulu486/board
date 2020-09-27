<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<div class="form-group">
					<label>Bno</label>
					<input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Title</label>
					<input class="form-control" name='title' value='<c:out value="${board.title }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>

				<div class="form-group">
					<label>Writer</label>
					<input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
				</div>

				<button data-oper='modify' class="btn btn-default">Modify</button>
				<button data-oper='list' class="btn btn-info">List</button>

				<form id="operForm" action="/board/first/list" method="get">
					<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum }'/>">
					<input type="hidden" name="amount" value="<c:out value='${cri.amount }'/>">
					<input type="hidden" id="bno" name ="bno" value="<c:out value='${board.bno }'/>">
					<input type="hidden" name="keyword" value="<c:out value='${cri.keyword }'/>">
					<input type="hidden" name="type" value="<c:out value='${cri.type }'/>">
				</form>
				<!-- end form -->
			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<div class='row'>

  <div class="col-lg-12">

    <!-- /.panel -->
    <div class="panel panel-default">
      
      <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
      </div>      
      
      
      <!-- /.panel-heading -->
      <div class="panel-body">        
      
        <ul class="chat">
        
			<!-- start reply -->
			<!--  example -->
			<!-- 
			<li class="left clearfix" data-rno='12'>
				<div>
					<strong class="primary-font">user000</strong>
					<small class="pull-right text-muted">1212-01-01 13:13</small>
				</div>
				<p>asdfasdf</p>
			</li>
			 -->
        </ul>
        <!-- ./ end ul -->
      </div>
      <!-- /.panel .chat-panel -->

	<div class="panel-footer">
		
	</div>


		</div>
  </div>
  <!-- ./ end row -->
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			
			<div class="modal-body">
				<div class="form-group">
					<label>ReplyContent</label> 
					<input class="form-control" name='replyContent' value='New Reply!!!!'>
				</div>      
				<div class="form-group">
					<label>Replyer</label> 
					<input class="form-control" name='replyer' value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> 
					<input class="form-control" name='replyDate' value='2018-01-01 13:13'>
				</div>
				
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- Reply Service JavaScript -->
<script src="/board/resources/js/reply.js"></script>

<script type="text/javascript">
$(document).ready(function() {

	var operForm = $("#operForm");
	$("button[data-oper='modify']").click("on", function(e){
		operForm.attr("action", "/board/first/modify").submit();
	});
	
	$("button[data-oper='list']").click("on", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/first/list").submit();
	});

	var bnoValue = '<c:out value="${board.bno}"/>'; //'${board.bno}';
	var replyUL = $(".chat");
	function showList(page) {
		replyService.getReplyList({bno: bnoValue, page : page || 1}, function(replyCnt, list){

			if(page == -1) {
				pageNum = Math.ceil( replyCnt / 10.0 );
				showList(pageNum);
				return;
			}
			
			let str = "";
			if(list == null || list.length == 0) {
				return;
			}
			
			for(let i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
				str += "	<div>";
				str += "		<div class='header'>";
				str += "			<strong class='primary-font'>" + list[i].replyer + "</strong>";
				str += "			<small class='pull-right text-muted'>" + replyService.displayTime( list[i].replyDate ) + "</small>";
				str += "		</div>";
				str += "	<p>" + list[i].replyContent + "</p>";
				str += "	</div>";
				str += "</li>";
			}
			
			replyUL.html(str);
			showReplyPage(replyCnt);
		});	
	}
	
	var pageNum = 1;
	let replyPageFooter = $(".panel-footer");
	function showReplyPage(replyCnt) {
		
		let endNum = Math.ceil( pageNum / 10.0 ) * 10;
		let startNum = endNum - 9;

		let prev = startNum != 1;
		let next = false;
		
		if(endNum * 10 >= replyCnt) {
			endNum = Math.ceil(replyCnt / 10.0);
		}
		
		if(endNum * 10 < replyCnt) {
			next = true;
		}
		
		let str = "<ul class='pagination pull-right'>";
		if(prev === true) {
			str += "<li class='page-item'>"
			str += "	<a class='page-link' href='" + (startNum - 1) + "'>Previous</a>"
			str += "</li>";
		}
		
		for(let i = startNum; i <= endNum; i++) {
			let activeAttr = pageNum == i? "active" : "";
			str += "<li class='page-item " + activeAttr +" '>"
			str += "	<a class='page-link' href='" + i + "'>" + i + "</a>"
			str += "</li>"; 
		}
		
		if(next) {
			str += "<li class='page-item'>"
			str += "	<a class='page-link' href='" + (endNum + 1) + "'>Next</a>"
			str += "</li>";
		}
		str += "</ul></div>";
		
		replyPageFooter.html(str);
	}
	
	showList(1);
	
	let modal = $(".modal");
	let modalInputReplyContent = modal.find("input[name='replyContent']");
	let modalInputReplyer = modal.find("input[name='replyer']");
	let modalInputReplyDate = modal.find("input[name='replyDate']");
	
	let modalModBtn = modal.find("#modalModBtn");
	let modalRemoveBtn = modal.find("#modalRemoveBtn");
	let modalRegisterBtn = modal.find("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		modalRegisterBtn.show();
		
		$(".modal").modal("show");	
	});
	
	modalRegisterBtn.on("click", function(e) {
		
		let reply = {
				replyContent : modalInputReplyContent.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue		
		};
		
		replyService.add(reply, function(result){
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
			showList(-1); // 댓글 입력시 댓글 페이지 갱신
		});
	})

	modalModBtn.on("click", function(e){
		// 태그의 data-rno 속성에서 data() 로 rno를 하면 data-rno의 값이 가져와 짐
		let reply = {rno : modal.data("rno"), replyContent: modalInputReplyContent.val()};
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});

	});
	
	modalRemoveBtn.on("click", function(e){
		
		replyService.remove(modal.data("rno"), function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		})
				
	})
		
	 $("#modalCloseBtn").on("click", function(e){
    	modal.modal('hide');
    });
	
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
		replyService.read(rno, function(reply){
			modalInputReplyContent.val(reply.replyContent);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");	
		});
	})
	
	replyPageFooter.on("click", "li a", function(e){
		e.preventDefault();
		
		let targetPageNum = $(this).attr("href");
		pageNum = targetPageNum;
		showList(pageNum);
		 
	})
	
	/*
	replyService.add(
		{replyContent:"aa", replyer: "bb", bno: bnoValue},
		function(result) {
			alert("hello");
		}
	)
	replyService.getReplyList(
		{bno: bnoValue, page: 1},
		function(list) {
			for(let i = 0, len = list.length; i < len; i++) {
				console.log(list[i]);	
			}
		}
	)
	replyService.remove(
		3,
		function(count){
			if(count ==="success"){
				alert("REMOVED");
			} else {
				alert("NOT REMOVED");
			}
		},
		function(err){
			alert("ERROR");
		}
	
	)
	
	replyService.update(
		{rno: 1, bno: bnov, replyContent: " modify reply asdfasdf"},
		function(result) {
			alert("수정완료 ... ");
		}
	);
	
	replyService.read(1, function(data){
		console.log(data);
	});
	*/
});
</script>

<%@include file="../includes/footer.jsp"%>
