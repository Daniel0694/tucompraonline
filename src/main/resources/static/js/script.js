function home() {
		document.getElementById('content').innerHTML = 'CARRITO'
	}


function showModal(name) {

	var modal = "";

	if (name == "ayuda")
		modal = document.getElementById('modal-Ayuda');
	else if (name == "acerca")
		modal = document.getElementById('modal-Acercade');

	modal.style.display = 'block';
}

function closeModal(name) {

	var modal = "";

	if (name == "ayuda")
		modal = document.getElementById('modal-Ayuda');
	else if (name == "acerca")
		modal = document.getElementById('modal-Acercade');

	modal.style.display = 'none';
}