$(document).ready(function () {
  cargarOrdenes();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  document.getElementById("txt-email-usuario").innerHTML = localStorage.email;
}

async function cargarOrdenes() {
  const request = await fetch("http://localhost:8080/api/ordenes", {
    method: "GET",
    headers: getHeaders(),
  });

  const ordenes = await request.json();

  let listadoHtml = ``;
  for (let orden of ordenes) {
    let fotoTexto = orden.foto === "" ? "../images/ico/user.png" : orden.foto;

    let ordenHtml = `
      <tr>
      <td>
        <label class="container-check">
          <input type="checkbox" />
          <div class="checkmark"></div>
        </label>
      </td>
      <td>${orden.id}</td>
      <td>${orden.orden}</td>
      <td class="content-img">
        ${orden.nombre}
      </td>
      <td>${orden.fecha}</td>
      <td>${orden.pago}</td>
      <td class="${
        orden.estado == "Pendiente"
          ? "warning"
          : orden.estado == "Entregado"
          ? "primary"
          : "danger"
      }">${orden.estado}</td>
      <td><a href="detail-ordenes.html" class="primary">Detalle</a></td>
    </tr>
        `;
    listadoHtml += ordenHtml;
  }

  document.querySelector("#ordenes tbody").outerHTML = listadoHtml;
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}
