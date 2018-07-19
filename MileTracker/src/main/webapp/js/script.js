window.addEventListener('load', function(e) {
	getEventObjects();

	var addRun = document.getElementById('newRun');
	addRun.addEventListener('click', function(e) {
		e.preventDefault();
		var form = document.getElementById('newRunForm');
		var newRun = {};
		newRun.week = form.week.value;
		newRun.miles = form.miles.value;
		newRun.time = form.time.value;
		newRun.comments = form.comments.value;
		createRun(newRun);
		//deleteEventObjects();
	});

});
function deleteEventObjects(){
	var body = document.getElementById('body');
	console.log(body);
	var oldTable = document.getElementById('eventTable');
	console.log(oldTable);
	 body.removeChild(oldTable);
}
function getEventObjects() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/miles');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			// console.log(xhr.responseText);
			var data = JSON.parse(xhr.responseText);
			displayEvents(data);

		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.log(xhr.responseText, xhr.status);
		}
	};
	xhr.send(null);
}

function createRun(run) {
	var xhr3 = new XMLHttpRequest();
	xhr3.open('POST', 'api/miles');
	xhr3.setRequestHeader("Content-type", "application/json");

	xhr3.onreadystatechange = function() {
		if (xhr3.readyState === 4) {
			console.log('in ready state')
			if (xhr3.status == 200 || xhr3.status == 201) {
				var newRun = JSON.parse(xhr3.responseText);
				console.log('status 200')
				deleteEventObjects();
				getEventObjects();

				// displayEvents(newRun);

			} else {
				console.log('Post Request Failed');
				console.error(xhr3.status + " : " + xhr3.responseText);
			}
		}
	}
	console.log(run);
	var newRunStringified = JSON.stringify(run);
	console.log(newRunStringified);
	xhr3.send(newRunStringified);
}

function displayEvents(data) {

	var table = document.createElement('table');
	table.id = 'eventTable';
	var head = document.createElement('thead');
	var tr = document.createElement('tr');
	var th = document.createElement('th');
	th.textContent = 'Week';
	var th2 = document.createElement('th');
	th2.textContent = 'Miles Ran';
	var th3 = document.createElement('th');
	th3.textContent = 'Total Time';
	var th4 = document.createElement('th');
	th4.textContent = 'Comments';
	tr.appendChild(th);
	tr.appendChild(th2);
	tr.appendChild(th3);
	tr.appendChild(th4);
	head.appendChild(tr);
	table.appendChild(head);
	var tbody = document.createElement('tbody');
	for (var i = 0; i < data.length; i++) {
		var tr2 = document.createElement('tr');
		var td = document.createElement('td');
		var td2 = document.createElement('td');
		var td3 = document.createElement('td');
		var td4 = document.createElement('td');
		td.textContent = data[i].week;
		tr2.id = data[i].id;
		tr2.appendChild(td);
		td2.textContent = data[i].miles;
		tr2.appendChild(td2);
		td3.textContent = data[i].time;
		tr2.appendChild(td3);
		td4.textContent = data[i].comments;
		tr2.appendChild(td4);
		tbody.appendChild(tr2);
	
		tr2.addEventListener('click', function(e) {
			var row = e.target.parentElement;
			var div = document.getElementById('detailView');
			div.textContent = '';
			console.log(e.target.previousSibling.firstChild);
			var form = document.createElement('form');
			var input = document.createElement('input');
			input.type = 'number';
			input.name = 'week';
			input.value = row.lastChild.previousSibling.previousSibling.previousSibling.firstChild.textContent;
			var input2 = document.createElement('input');
			input2.type = 'number';
			input2.name = 'miles';
			input2.value = row.lastChild.previousSibling.previousSibling.firstChild.textContent;
			var input3 = document.createElement('input');
			input3.type = 'number';
			input3.name = 'time';
			input3.value = row.lastChild.previousSibling.firstChild.textContent;
			var input4 = document.createElement('input');
			input4.type = 'text';
			input4.name = 'comments';
			input4.value = row.lastChild.firstChild.textContent;
			var inputHidden = document.createElement('input');
			inputHidden.type = 'hidden';
			inputHidden.value = row.id;
			inputHidden.name = 'id';
			var header5 = document.createElement('h5');
			var btn = document.createElement('input');
			btn.type = 'button';
			btn.value = 'Delete';
			btn.id = row.id;
			console.log(btn.id);
			btn.addEventListener('click', function(e){
				e.preventDefault();
				var id = btn.id;
				DeleteRun(id);
				
			})
			var btn2 = document.createElement('input');
			btn2.type = 'button';
			btn2.value = 'Update';
			btn2.id = row.id;
			
			header5.textContent = 'Edit Run';
			form.appendChild(document.createElement('hr'));
			form.appendChild(header5);
			form.appendChild(input);
			form.appendChild(input2);
			form.appendChild(input3);
			form.appendChild(input4);
			form.appendChild(inputHidden);
			form.appendChild(document.createElement('br'));
			form.appendChild(btn2);
			form.appendChild(btn);
			var div = document.getElementById('detailView');
			div.appendChild(form);
			btn2.addEventListener('click', function(e){
				e.preventDefault();
				var run = {};
				run.id = btn2.id;
				run.week = e.target.parentElement.childNodes[2].value;
				run.miles = e.target.parentElement.childNodes[3].value;
				run.time = e.target.parentElement.childNodes[4].value;
				run.comments = e.target.parentElement.childNodes[5].value;
				console.log(e.target.parentElement.childNodes[2].value);
				updateRun(run);
			})
		});
	}
	table.appendChild(tbody);
	document.body.appendChild(table);
	var total = document.createElement('form');
	var btn3 = document.createElement('input');
	btn3.type = 'button';
	btn3.value = 'Total Miles Ran';
	var btn4 = document.createElement('input');
	btn4.type = 'button';
	btn4.value = 'Average Miles Ran';
	btn3.addEventListener('click', function(e){
		e.preventDefault();
		totalMiles();
	});
	btn4.addEventListener('click', function(e){
		e.preventDefault();
		averageMiles();
	});
	total.appendChild(btn3);
	total.appendChild(btn4);
	document.body.appendChild(total);
}
function totalMiles(){
	var xhr3 = new XMLHttpRequest();
	xhr3.open('GET', 'api/miles/total');
//	xhr3.setRequestHeader("Content-type", "application/json");

	xhr3.onreadystatechange = function() {
		if (xhr3.readyState === 4) {
			console.log('in ready state')
			if (xhr3.status == 200 || xhr3.status == 201) {
				console.log('status 200')
				var data = JSON.parse(xhr3.responseText);
				displayTotalMiles(data);

				// displayEvents(newRun);

			} else {
				console.log('Delete Request Failed');
				console.error(xhr3.status + " : " + xhr3.responseText);
			}
		}
	}
	xhr3.send(null);
	
	
}
function averageMiles(){
	var xhr3 = new XMLHttpRequest();
	xhr3.open('GET', 'api/miles/average');
//	xhr3.setRequestHeader("Content-type", "application/json");
	
	xhr3.onreadystatechange = function() {
		if (xhr3.readyState === 4) {
			console.log('in ready state')
			if (xhr3.status == 200 || xhr3.status == 201) {
				console.log('status 200')
				var data = JSON.parse(xhr3.responseText);
				averageTotalMiles(data);
				
				// displayEvents(newRun);
				
			} else {
				console.log('Delete Request Failed');
				console.error(xhr3.status + " : " + xhr3.responseText);
			}
		}
	}
	xhr3.send(null);
	
	
}
function displayTotalMiles(data){
	var totalDiv = document.getElementById('totalView');
	totalDiv.textContent = '';
	var miles = data[0];
	var time = data[1];
	totalDiv.textContent = 'You have ran a total of ' + miles + " miles. Your total run time is " + time;
}
function averageTotalMiles(data){
	var totalDiv = document.getElementById('totalView');
	totalDiv.textContent = '';
	var miles = data[0].toFixed(2);
	var time = data[1].toFixed(2);
	totalDiv.textContent = 'You have ran a total of ' + miles + " miles. Your total run time is " + time;
}
function DeleteRun(id){
	var xhr3 = new XMLHttpRequest();
	console.log('in delete');
	console.log(id);
	xhr3.open('DELETE', `api/miles/${id}`);
//	xhr3.setRequestHeader("Content-type", "application/json");

	xhr3.onreadystatechange = function() {
		if (xhr3.readyState === 4) {
			console.log('in ready state')
			if (xhr3.status == 200 || xhr3.status == 201) {
				console.log('status 200')
				deleteEventObjects();
				getEventObjects();

				// displayEvents(newRun);

			} else {
				console.log('Delete Request Failed');
				console.error(xhr3.status + " : " + xhr3.responseText);
			}
		}
	}
	xhr3.send(id);
	
}
function updateRun(run){
	var xhr3 = new XMLHttpRequest();
	console.log('in update');
	console.log(run.id);
	var id = run.id;
	xhr3.open('PATCH', `api/miles/${id}`);
	xhr3.setRequestHeader("Content-type", "application/json");
	
	xhr3.onreadystatechange = function() {
		if (xhr3.readyState === 4) {
			console.log('in ready state')
			if (xhr3.status == 200 || xhr3.status == 201) {
				console.log('status 200')
				deleteEventObjects();
				getEventObjects();
				
				// displayEvents(newRun);
				
			} else {
				console.log('Delete Request Failed');
				console.error(xhr3.status + " : " + xhr3.responseText);
			}
		}
	}
	var newRunStringified = JSON.stringify(run);
	xhr3.send(newRunStringified);
	
}