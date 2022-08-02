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
  // const checkoutTotal = item.querySelector("[checkout--total]");
  item.querySelector("input#qty.qty").addEventListener("change", (e) => {
    const quantity = e.target.value;
    total.textContent = parseInt(price.textContent) * parseInt(quantity);
    // actualTotal -= parseFloat(total.textContent);
    // checkoutTotal.innerHTML = actualTotal;
    document.querySelector('.cost').querySelector('span:nth-child(2)').textContent = [...document.querySelectorAll('.items, .py-5')].reduce((acc, item) => {
      const total = parseInt(item.querySelector('[total]').textContent)
      return acc + total;
    }, 0)
  });
});