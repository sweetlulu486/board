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
			
			return {
					add:add,
					getReplyList:getReplyList,
					remove:remove
			};
		}
		
)();