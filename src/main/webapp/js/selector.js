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
  console.log(item)
  const price = item.querySelector("[price]");
  const total = item.querySelector("[total]");
  const checkoutTotal = document.querySelector(
      "#form > div > div > div > div > div.finish > div > span.bg-indigo-100.text-indigo-800.text-sm.font-medium.mr-2.px-2\\.5.py-0\\.5.rounded.dark\\:bg-indigo-200.dark\\:text-indigo-900"
  );
  item.querySelector("input#qty.qty").addEventListener("change", (e) => {
    const quantity = e.target.value;
    total.textContent = parseInt(price.textContent) * parseInt(quantity);
    actualTotal -= parseFloat(total.textContent);
    checkoutTotal.innerHTML = actualTotal;
  });
});