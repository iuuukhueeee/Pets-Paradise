let getSelector = () => {
  let val = document.getElementById("list").value;
  console.log(val);
  if (val === "paypal") {
    console.log("hello world");
    let display = (document.getElementById("payment_method").style.display =
      "block");
  } else {
    let display = (document.getElementById("payment_method").style.display =
      "none");
  }
};
[...document.querySelectorAll(".item")].forEach((item) => {
  const price = item.querySelector("[price]");
  const total = item.querySelector("[total]");
  item.querySelector("input").addEventListener("change", (e) => {
    const quantity = e.target.value;
    total.textContent = parseInt(price.textContent) * parseInt(quantity);
  });
});