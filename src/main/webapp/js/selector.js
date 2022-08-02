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

let actualTotal = 0;

[...document.querySelectorAll(".item")].forEach((item) => {
  const price = item.querySelector("[price]");
  const total = item.querySelector("[total]");
  const checkoutTotal = item.querySelector("[checkout--total]");
  item.querySelector("input#qty.qty").addEventListener("change", (e) => {
    const quantity = e.target.value;
    total.textContent = parseInt(price.textContent) * parseInt(quantity);
    actualTotal = total.textContent;
    checkoutTotal.textContent = actualTotal;
    console.log(actualTotal);
  });
});