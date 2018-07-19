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
		tr2.appendChild(td);
		td2.textContent = data[i].miles;
		tr2.appendChild(td2);
		td3.textContent = data[i].time;
		tr2.appendChild(td3);
		td4.textContent = data[i].comments;
		tr2.appendChild(td4);
		tbody.appendChild(tr2);
	}
	table.appendChild(tbody);
	document.body.appendChild(table);

}