function home() {
	document.getElementById('content').innerHTML = 'CARRITO'
}

function showModal(name) {

	var modal = "";

	if (name == "ayuda")
		modal = document.getElementById('modal-Ayuda');
	else if (name == "acerca")
		modal = document.getElementById('modal-Acercade');
	else if (name == "sesion")
		modal = document.getElementById('modal-CerrarSesion');
	else if (name == "producto")
		modal = document.getElementById('modal-producto');
	else if (name == "categoria")
		modal = document.getElementById('modal-categoria');

	modal.style.display = 'block';
}

function closeModal(name) {

	var modal = "";

	if (name == "ayuda")
		modal = document.getElementById('modal-Ayuda');
	else if (name == "acerca")
		modal = document.getElementById('modal-Acercade');
	else if (name == "sesion")
		modal = document.getElementById('modal-CerrarSesion');
	else if (name == "producto")
		modal = document.getElementById('modal-producto');
	else if (name == "categoria")
		modal = document.getElementById('modal-categoria');

	modal.style.display = 'none';
}