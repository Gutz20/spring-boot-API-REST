$(document).ready(function () {
  // listarCategorias();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  document.getElementById("txt-email-usuario").innerHTML = localStorage.email;
}

document.getElementById("file").onchange = (e) => {
  let leer = new FileReader();

  leer.readAsDataURL(e.target.files[0]);
  leer.onload = () => {
    let previsualizar = document.getElementById("preview");
    let imagen = document.createElement("img");
    imagen.src = leer.result;
    imagen.style.width = "150px";
    previsualizar.innerHTML = "";
    previsualizar.appendChild(imagen);
  };
};

let selected = document.querySelector(".selected");
let optionsContainer = document.querySelector(".options-container");

let optionsList = document.querySelectorAll(".option");

selected.addEventListener("click", () => {
  optionsContainer.classList.toggle("active-options");
});

optionsList.forEach(o => {
  o.addEventListener("click", () => {
    selected.innerHTML = o.querySelector("label").innerHTML;
    optionsContainer.classList.remove("active-options");
  });
});

// Consultas

async function registrarProducto() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.categoria = document.getElementById("categoria").innerHTML;
  datos.descripcion = document.getElementById("txtDescripcion").value;
  datos.especificacion = document.getElementById("txtEspecificacion").value;
  datos.foto = document.getElementById("file").value;
  datos.precio = document.getElementById("nmrPrecio").value;
  datos.stock = document.getElementById("nmrStock").value;

  window.location.href = "productos.html";

  const request = await fetch("http://localhost:8080/api/productos", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
}
