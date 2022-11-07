$(document).ready(function () {
  cargarCategorias();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  document.getElementById("txt-email-usuario").innerHTML = localStorage.email;
}

async function cargarCategorias() {
  const request = await fetch("http://localhost:8080/api/categorias", {
    method: "GET",
    headers: getHeaders(),
  });

  const categorias = await request.json();

  let listadoHtml = ``;
  for (let categoria of categorias) {
    let fotoTexto =
      categoria.foto === "" ? "../images/ico/user.png" : categoria.foto;

    let categoriaHtml = `
      <tr>
      <td>
        <label class="container-check">
          <input type="checkbox" />
          <div class="checkmark"></div>
        </label>
      </td>
      <td>${categoria.id}</td>
      <td class="content-img">
        <img
          src="${fotoTexto}"
          class="img-content-data"
        />
      </td>
      <td>${categoria.nombre}</td>
      <td><a onclick="eliminarCategoria(${categoria.id})" class="danger cur-pointer">Eliminar</a></td>
    </tr>
        `;
    listadoHtml += categoriaHtml;
  }

  document.querySelector("#categorias tbody").outerHTML = listadoHtml;
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}

async function eliminarCategoria(id) {
  if (!confirm("¿Desea eliminar esta categoria?")) {
    return;
  }

  const request = await fetch("http://localhost:8080/api/categorias/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}

async function registrarCategoria() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.foto = document.getElementById("fleImg").value;
  
  const request = await fetch("http://localhost:8080/api/categorias", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });

  location.reload();
  onConfirm();
}

function onAdd() {
  let confirmation = document.getElementById("confirmacion");
  if (!confirmation.classList.contains("modal-open")) {
    confirmation.classList.add("modal-open");
  }
}

function onCancel() {
  let confirmation = document.getElementById("confirmacion");
  confirmation.classList.remove("modal-open");
}

function onConfirm() {
  onCancel();
}
