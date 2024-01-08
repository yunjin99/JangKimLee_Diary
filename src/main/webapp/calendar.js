(function() {
	calendarMaker($("#calendarForm"), new Date());
})();

var nowDate = new Date();
var selectedYear, selectedMonth, selectedDay;

function calendarMaker(target, date) {
	if (date == null || date == undefined) {
		date = new Date();
	}
	nowDate = date;
	if ($(target).length > 0) {
		var year = nowDate.getFullYear();
		var month = nowDate.getMonth() + 1;
		var date = 0;
		$(target).empty().append(assembly(year, month));
	} else {
		console.error("custom_calendar Target is empty!!!");
		return;
	}

	var thisMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
	var thisLastDay = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);

	var tag = "<tr>";
	var cnt = 0;
	//빈 공백 만들어주기
	for (i = 0; i < thisMonth.getDay(); i++) {
		tag += "<td></td>";
		cnt++;
	}

	//날짜 채우기
	for (i = 1; i <= thisLastDay.getDate(); i++) {
		if (cnt % 7 == 0) { tag += "<tr>"; }

		tag += "<td style='height:60px; font-size:20px;'>" + i + "</td>";
		cnt++;
		if (cnt % 7 == 0) {
			tag += "</tr>";
		}
	}
	var custom_calendar_table = document.getElementsByClassName('custom_calendar_table')[0];
	custom_calendar_table.width = '100%';
	custom_calendar_table.height = '100%';
	$(target).find("#custom_set_date").append(tag);
	calMoveEvtFn();


	function assembly(year, month) {
		var calendar_html_code =
			"<table class='custom_calendar_table'>" +
			"<colgroup>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"<col style='width:81px'/>" +
			"</colgroup>" +
			"<thead class='cal_date'>" +
			"<th><button type='button' class='prev'><</button></th>" +
			"<th colspan='5'><p><span>" + year + "</span>년 <span>" + month + "</span>월</p></th>" +
			"<th><button type='button' class='next'>></button></th>" +
			"</thead>" +
			"<thead  class='cal_week' style='height:20px; font-size:20px;'>" +
			"<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>" +
			"</thead>" +
			"<tbody id='custom_set_date'>" +
			"</tbody>" +
			"</table>";
		return calendar_html_code;
	}

	window.fetchMemoByDate = function(year, month, day) {
		console.log("fetchMemoByDate 호출 " + year + '-' + month + '-' + day);
		var apiUrl = 'memoFindByDate?date=' + year + '-' + month + '-' + day;
		// AJAX를 사용하여 API 호출
		$.ajax({
			url: apiUrl,
			type: 'GET',
			contentType: 'application/json',
			cache: false,
			success: function(response) {
				console.log('서버 응답 데이터:', response);
				var memoHTML = "<br><table style='width:100%; font-size: 30px;'>";
				response.forEach(function(memo) {
					let id = memo.memoId;
					let content = memo.memoContents;
					let memoDate = `${year} + "-" + ${month} + "-" + ${day}`;
					// 예시: title과 content를 화면에 추가
					memoHTML += `<tr><td><input id='memoContents` + id + `' value='` + content + `' style='font-size:35px; width:100%'></input></td><td><button value=` + id + `onclick='deleteMemo(` + id + `, ` + year + `, ` + month + `, ` + day + `)'>Delete</button></td> <td><button value=` + id + ` onclick='editMemo(value, ${memoDate})'>Edit</button></td> </tr>`;
				});
				// 최종적으로 모든 메모를 화면에 표시
				memoHTML += `</table> <button onclick="saveMemo(${year}, ${month}, ${day})">다이어리 추가</button> 
			<div id='insertUiMemoView'></div>`;
				updateMemoView(memoHTML);
			},
			error: function(error) {
				console.error('API 호출 에러:', error);
			}
		});
	}

	window.fetchChecklistByDate = function(year, month, day) {
		console.log("fetchMemoByDate 호출 " + year + '-' + month + '-' + day);
		var apiUrl = 'checklistByDate?date=' + year + '-' + month + '-' + day;
		// AJAX를 사용하여 API 호출
		$.ajax({
			url: apiUrl,
			type: 'GET',
			contentType: 'application/json',
			cache: false,
			success: function(response) {
				console.log('서버 응답 데이터:', response);
				var checklistHTML = "<br><table style='width:100%; font-size: 30px;'>";
				response.forEach(function(checklist) {
					let status = checklist.checkStatus;
					let id = checklist.checkId;
					let content = checklist.checkContents;
					let checklistDate = `${year} + "-" + ${month} + "-" + ${day}`;
					checklistHTML += `<tr><td><input id='checkBox` + id + `' type='checkbox' ` + (status ? "checked" : "unchecked") + `></td><td><input id='checklistContents` + id + `' value='` + content + `' style='font-size:35px'></input></td><td><button id=` + id + ` onclick='deleteChecklist(` + id + `, ` + year + `, ` + month + `, ` + day + `)'>Delete</button></td> <td><button id='` + id + `' onclick='editChecklist(id, ${checklistDate})'>Edit</button></td></tr>`;
				})
				// 최종적으로 모든 메모를 화면에 표시
				checklistHTML += `</table> <button onclick="saveChecklist(${year}, ${month}, ${day})">체크리스트 추가</button>
		<div id='insertUiChecklistView'></div>`;
				updateCheckView(checklistHTML);
			},
			error: function(error) {
				console.error('API 호출 에러:', error);
			}
		});
	}

	// 화면 갱신을 담당하는 함수
	function updateMemoView(memoHTML) {
		document.getElementById("memo").innerHTML = memoHTML;
	}

	// 화면 갱신을 담당하는 함수
	function updateCheckView(checklistHTML) {
		document.getElementById("checklist").innerHTML = checklistHTML;
	}

	function calMoveEvtFn() {
		//전달 클릭
		$(".custom_calendar_table").on("click", ".prev", function() {
			nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, 1);
			calendarMaker($(target), nowDate);
		});
		//다음날 클릭
		$(".custom_calendar_table").on("click", ".next", function() {
			nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 1);
			calendarMaker($(target), nowDate);
		});
		//일자 선택 클릭
		$(".custom_calendar_table").on("click", "td", function() {
			$(".custom_calendar_table .select_day").removeClass("select_day");
			$(this).removeClass("select_day").addClass("select_day");
			selectedYear = nowDate.getFullYear();
			selectedMonth = nowDate.getMonth() + 1;
			selectedDay = $(this).text();
			fetchMemoByDate(selectedYear, selectedMonth, selectedDay);
			fetchChecklistByDate(selectedYear, selectedMonth, selectedDay);
		});
	}
}