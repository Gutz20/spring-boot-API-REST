$(document).ready(function () {
  cargarUsuario();
});

async function cargarUsuario() {
  const request = await fetch(
    "http://localhost:8080/api/usuario/" + localStorage.idEdit,
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }
  );

  const usuario = await request.json();

  document.getElementById("txtNombre").value = usuario.nombre;
  document.getElementById("txtDireccion").value = usuario.direccion;
  document.getElementById("emlMail").value = usuario.email;
  document.getElementById("txtDni").value = usuario.dni;
  document.getElementById("telPhone").value = usuario.telefono;
  document.getElementById("cargo").innerHTML = usuario.cargo;
  document.getElementById("imagen-cliente").src = usuario.foto;
}

async function editarUsuario() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.direccion = document.getElementById("txtDireccion").value;
  datos.email = document.getElementById("emlMail").value;
  datos.dni = document.getElementById("txtDni").value;
  datos.telefono = document.getElementById("telPhone").value;
  datos.cargo = document.getElementById("cargo").innerHTML;
  datos.foto = document.getElementById("file").value;

  window.location.href = "customizador.html";

  const request = await fetch(
    "http://localhost:8080/api/usuarios/" + localStorage.idEdit,
    {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    }
  );
}
