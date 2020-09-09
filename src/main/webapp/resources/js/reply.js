console.log("hello reply");

var replyService = (
		function() {
			
			function add(reply, callback, error) {
				
				$.ajax({
					type: 'post',
					url: '/board/replies/new',
					data: JSON.stringify(reply),
					contentType: "application/json; charset=utf-8",
					success : function (result, status, xhr) {
						if(callback) {
							callback(result);
						}
						
					},
					error : function(xhr, status, err) {
						if(err) {}
						error(err);
					}
				});
			}
			
			function getReplyList(param, callback, error) {
				let bno = param.bno;
				let page = param.page || 1;
				
				$.getJSON(
					"/board/replies/" + bno + "/" + page + ".json",
					function(data) {
						if(callback) {
							callback(data);
						}
					}
				)
				.fail(function(xhr, status, err){
					if(error){
						error();
					}
				});
			}
			
			function remove(rno, callback, error) {
				$.ajax({
					type: 'delete',
					url: '/board/replies/' + rno,
					success: function(result, status, xhr) {
						if(callback) {
							callback(result);
						}
					},
					error: function(xhr, status, err) {
						if(error){
							error(err);
						}
					}
				})
			}
			
			function update(reply, callback, error) {
				console.log(reply.rno);
				$.ajax({
					type: 'put',
					url: '/board/replies/' + reply.rno,
					data: JSON.stringify(reply),
					contentType: "application/json; charset=utf-8",
					success: function(result, status, xhr) {
						if(callback) {
							callback(result)
						}
					},
					error: function(xhr, status, err) {
						console.log("update error");
						if(error){
							error(err);
						}
					}
				})
			}
			
			function read(rno, callback, error) {
				$.get("/board/replies/"+rno + ".json", function(result){
					if(callback) {
						callback(result);
					}
				}).fail(function(xhr, status, err) {
					if(error){
						error();
					}
				});
			}
			
			return {
					add:add,
					getReplyList:getReplyList,
					remove:remove,
					update:update,
					read:read
			}
		}
		
)();