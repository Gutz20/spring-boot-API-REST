const clickIcon = document.querySelector(".icon i");
const menus = document.querySelectorAll(".menu ul");

clickIcon.addEventListener("click", () => {
  menus.forEach((menu, indice) => {
    menu.classList.toggle("active");

    if (menu.classList.contains("active")) {
      clickIcon.setAttribute("class", "fa-solid fa-xmark");
    } else {
      clickIcon.setAttribute("class", "fa-solid fa-bars");
    }
  });
});
