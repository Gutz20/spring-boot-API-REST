$(document).ready(function () {
  cargarClientes();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  document.getElementById("txt-email-usuario").innerHTML = localStorage.email;
}

async function cargarClientes() {
  const request = await fetch("http://localhost:8080/api/clientes", {
    method: "GET",
    headers: getHeaders(),
  });

  const clientes = await request.json();

  let listadoHtml = ``;
  for (let cliente of clientes) {
    let fotoTexto =
      cliente.foto === "" ? "../images/ico/user.png" : cliente.foto;

    let clienteHtml = `
      <tr>
      <td>
        <label class="container-check">
          <input type="checkbox" />
          <div class="checkmark"></div>
        </label>
      </td>
      <td>${cliente.id}</td>
      <td class="display-img">
        <div class="profile-photo-data">
          <img
            src="${fotoTexto}"
            class="profile-photo-img"
            alt="client"
          />
        </div>
      </td>
      <td>${cliente.nombre}</td>
      <td>${cliente.email}</td>
      <td>${cliente.telefono}</td>
      <td class="${cliente.estado == "Activo" ? "success" : "danger"}">${
      cliente.estado
    }</td>
      <td>
        <a onclick="editarCliente(${cliente.id})" href="detail-clientes.html" class="primary">Detalle</a>
      </td>
      <td><a onclick="eliminarCliente(${
        cliente.id
      })" class="danger cur-pointer">Eliminar</a></td>
    </tr>
        `;
    listadoHtml += clienteHtml;
  }

  document.querySelector("#clientes tbody").outerHTML = listadoHtml;
}

async function editarCliente(id) {
  localStorage.idEdit = id;
}

async function eliminarCliente(id) {
  if (!confirm("¿Desea eliminar este cliente?")) {
    return;
  }

  const request = await fetch("http://localhost:8080/api/clientes/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}
