$(document).ready(function () {
  cargarProductos();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  document.getElementById("txt-email-usuario").innerHTML = localStorage.email;
}

async function cargarProductos() {
  const request = await fetch("http://localhost:8080/api/productos", {
    method: "GET",
    headers: getHeaders(),
  });

  const productos = await request.json();

  let listadoHtml = ``;
  for (let producto of productos) {
    let fotoTexto =
      producto.foto === "" ? "../images/ico/user.png" : producto.foto;

    let productosHtml = `
      <tr>
      <td>
        <label class="container-check">
          <input type="checkbox" />
          <div class="checkmark"></div>
        </label>
      </td>
      <td>${producto.id}</td>
      <td class="display-img">
        <div class="content-img">
          <img
            src="${fotoTexto}"
            class="img-content-data"
            alt="client"
          />
          ${producto.nombre}
        </div>
      </td>
      <td>S/${producto.precio}</td>
      <td>${producto.stock}</td>
      <td>${producto.categoria}</td>
      <td><a onclick="editarProducto(${producto.id})" href="detail-productos.html" class="primary">Detalle</a></td>
      <td><a onclick="eliminarProducto(${producto.id})" class="danger cur-pointer">Eliminar</a></td>
    </tr>
        `;
    listadoHtml += productosHtml;
  }

  document.querySelector("#productos tbody").outerHTML = listadoHtml;
}

function editarProducto(id) {
  localStorage.idEdit = id;
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
  };
}

async function eliminarProducto(id) {

  if (!confirm("¿Desea eliminar este producto?")) {
    return;
  }

  const request = await fetch("http://localhost:8080/api/productos/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}
