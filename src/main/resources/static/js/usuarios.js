$(document).ready(function () {
  cargarUsuarios();
  actualizarEmailDelUsuario();
  
});

function actualizarEmailDelUsuario() {
  document.getElementById('txt-email-usuario').innerHTML = localStorage.email;
}

async function cargarUsuarios() {
  const request = await fetch("http://localhost:8080/api/usuarios", {
    method: "GET",
    headers: getHeaders(),
  });

  const usuarios = await request.json();

  let listadoHtml = ``;
  for (let usuario of usuarios) {
    let fotoTexto =
      usuario.foto === "" ? "../images/ico/user.png" : usuario.foto;

    let usuarioHtml = `
      <tr>
        <td>
          <label class="container-check">
            <input type="checkbox" />
            <div class="checkmark"></div>
          </label>
        </td>
        <td>${usuario.id}</td>
        <td class="content-img">
          <img
            src="${fotoTexto}"
            class="img-content-data"
          />${usuario.nombre}
        </td>
        <td>${usuario.email}</td>
        <td>${usuario.telefono}</td>
        <td>${usuario.cargo}</td>
        <td>
          <a href="detail-empleados.html" onclick="editarUsuario(${usuario.id})" class="primary">Editar</a>
        </td>
        <td><a href="#" class="danger" onclick="eliminarUsuario(${usuario.id})">Eliminar</a></td>
      </tr>
      `;
    listadoHtml += usuarioHtml;
  }

  document.querySelector("#usuarios tbody").outerHTML = listadoHtml;
}

async function editarUsuario(id) {
  localStorage.idEdit = id;
}


function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}

async function eliminarUsuario(id) {
  if (!confirm("¿Desea eliminar este usuario?")) {
    return;
  }

  const request = await fetch("http://localhost:8080/api/usuarios/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}
