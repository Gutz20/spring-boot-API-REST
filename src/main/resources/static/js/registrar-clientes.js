async function registrarClientes() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.email = document.getElementById("emlMail").value;
  datos.telefono = document.getElementById("telPhone").value;
  datos.dni = document.getElementById("nmrDni").value;
  datos.direccion = document.getElementById("txtDireccion").value;
  datos.pais = document.getElementById("txtPais").value;
  datos.foto = document.getElementById("file").value;
  datos.estado = document.getElementById("estado").innerHTML;

  window.location.href = "clientes.html";

  const request = await fetch("http://localhost:8080/api/clientes", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
}
