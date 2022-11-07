async function registrarUsuario() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.direccion = document.getElementById("txtDireccion").value;
  datos.email = document.getElementById("emlMail").value;
  datos.dni = document.getElementById("txtDni").value;
  datos.telefono = document.getElementById("telPhone").value;
  datos.cargo = document.getElementById("cargo").innerHTML;
  datos.foto = document.getElementById("file").value;
  datos.password = document.getElementById("pwdPassword").value;

  let repetirPassword = document.getElementById("repeatPwdPassword").value;

  if (repetirPassword != datos.password) {
    alert("La contraseña que se escribio es diferente");
    return;
  } else {
    alert("La cuenta se creo exitosamente!");
    window.location.href = "customizador.html";
  }

  const request = await fetch("http://localhost:8080/api/usuarios", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
}
