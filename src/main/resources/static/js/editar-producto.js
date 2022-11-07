$(document).ready(function () {
  cargarProducto();
});

async function cargarProducto() {
  const request = await fetch(
    "http://localhost:8080/api/producto/" + localStorage.idEdit,
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }
  );

  const producto = await request.json();

  document.getElementById("txtNombre").value = producto.nombre;
  document.getElementById("nmrPrecio").value = producto.precio;
  document.getElementById("txtDescripcion").value = producto.descripcion;
  document.getElementById("categoria").innerHTML = producto.categoria;
  document.getElementById("nmrStock").value = producto.stock;
  document.getElementById("txtEspecificaciones").value =
    producto.especificacion;
  let imagen = document.createElement("img");
  imagen.src = producto.foto;
  imagen.style.width = "150px";
  document.getElementById("preview").innerHTML = "";
  document.getElementById("preview").appendChild(imagen);
}

async function editarProducto() {
  let datos = {};

  datos.nombre = document.getElementById("txtNombre").value;
  datos.precio = document.getElementById("nmrPrecio").value;
  datos.descripcion = document.getElementById("txtDescripcion").value;
  datos.categoria = document.getElementById("categoria").innerHTML;
  datos.especificacion = document.getElementById("txtEspecificaciones").value;
  datos.stock = document.getElementById("nmrStock").value;
  datos.foto = document.getElementById("file").value;

  window.location.href = "productos.html";

  const request = await fetch(
    "http://localhost:8080/api/productos/" + localStorage.idEdit,
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
