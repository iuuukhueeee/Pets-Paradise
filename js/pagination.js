function myFunction() {
  var dots = document.getElementById("dots");
  var moreText = document.getElementById("more");
  var btnText = document.getElementById("myBtn");
  if (dots.style.display === "none") {
    dots.style.display = "inline";
    btnText.innerHTML = "Read more";
    moreText.style.display = "none";
    btnText.style.display="block";

  } else {
    dots.style.display = "none";
    btnText.innerHTML = "Read less";
    moreText.style.display="grid";
    moreText.style.gridTemplateColumns = "1fr 1fr 1fr";
    btnText.style.display="flex";

  }
  
}
