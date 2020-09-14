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
					"/board/replies/pages/" + bno + "/" + page + ".json",
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
			
			function displayTime(timeValue) {
				let today = new Date();
				//date - milliseconds since January 1, 1970, 00:00:00 GMT not to exceed the milliseconds representation for the year 8099
				let gap = today.getTime() - timeValue;
				
				let dateObj = new Date(timeValue);
				let str = "";
				
				if(gap < (1000 * 60 * 60 * 24)) {
					let hh = dateObj.getHours();
					let mi = dateObj.getMinutes();
					let ss = dateObj.getSeconds();
					
					return [ ( hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss ].join('');
				} else {
					let yy = dateObj.getFullYear();
					let mm = dateObj.getMonth() + 1; // zero-based
					let dd = dateObj.getDate();
					
					return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
				}
				
			}
			
			return {
					add:add,
					getReplyList:getReplyList,
					remove:remove,
					update:update,
					read:read,
					displayTime:displayTime
			}
		}
		
)();