async function iniciarSesion() {
  let datos = {};
  datos.email = document.getElementById("emlMail").value;
  datos.password = document.getElementById("pwdPassword").value;

  const request = await fetch("http://localhost:8080/api/login", {
    method: "POST",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(datos)
  });

  const response = await request.text();

  if (response != 'FAIL') {
    localStorage.token = response;
    localStorage.email = datos.email;
    window.location.href = 'dashboard.html';
  } else {
    alert("Las credenciales ingresadas son incorrectas. Por favor intentelo nuevamente");
  }

}
