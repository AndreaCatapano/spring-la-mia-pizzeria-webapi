function redirectToLink(event) {
  event.preventDefault();

  const searchValue = document.getElementById("search").value;
  const url = `/pizzas?name=${encodeURIComponent(searchValue)}`;
  window.location.href = url;
}
