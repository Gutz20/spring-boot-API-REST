$(document).ready(function () {
  cargarCliente();
});

async function cargarCliente() {
  const request = await fetch(
    "http://localhost:8080/api/cliente/" + localStorage.idEdit,
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }
  );

  const cliente = await request.json();

  document.getElementById("txtNombre").value = cliente.nombre;
  document.getElementById("emlMail").value = cliente.email;
  document.getElementById("pwdPassword").innerHTML = cliente.categoria;
  document.getElementById("telPhone").value = cliente.telefono;
  document.getElementById("nmrDni").value = cliente.dni;
  document.getElementById("txtDireccion").value = cliente.direccion;
  document.getElementById("txtPais").value = cliente.pais;
  document.getElementById("estado").value = cliente.estado;

  let imagen = document.createElement("img");
  imagen.src = cliente.foto;
  document.getElementById("preview").innerHTML = "";
  document.getElementById("preview").appendChild(imagen);

  document.getElementById("nombre1").innerHTML = cliente.nombre;
  document.getElementById("nombre2").innerHTML = cliente.nombre;
}

async function editarCliente() {
  let datos = {};

  datos.nombre = document.getElementById("txtNombre").value;
  datos.email = document.getElementById("emlMail").value;
  datos.password = document.getElementById("pwdPassword").innerHTML;
  datos.telefono = document.getElementById("telPhone").value;
  datos.dni = document.getElementById("nmrDni").value;
  datos.direccion = document.getElementById("txtDireccion").value;
  datos.pais = document.getElementById("txtPais").value;
  datos.estado = document.getElementById("estado").value;
  datos.foto = document.getElementById("file").value;


  window.location.href = "clientes.html";

  const request = await fetch(
    "http://localhost:8080/api/clientes/" + localStorage.idEdit,
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
