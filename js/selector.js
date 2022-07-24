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

// getTotal = () => {
//   let qty = document.getElementById("qty").value;
//   let price = document.getElementById("price").value;
//   let total = price * qty;
//   let totalPrice = document.getElementById("total").innerHTML = total + ".000 VND";

// };

// function getTotal() {
//   let content = document.getElementsByClassName("content")[0];
//   let items = content.getElementsByClassName("content");
//   let total=0;
//   for (let i = 0; i < items.length; i++) {
//     let item = item[i];
//     let priceElement = item.getElementById("price")[0];
//     let quantityElement = item.getElementById("qty")[0];
//     let price=parseFloat(priceElement.innerText.replace("$",""));

//     let quantity=quantityElement.value;
//     total=total+(price)*quantity;
//     document.getElementsById('total')[0].innerText=total;
//   }
// }
[...document.querySelectorAll(".item")].forEach((item) => {
  const price = item.querySelector("[price]");
  const total = item.querySelector("[total]");
  item.querySelector("input").addEventListener("change", (e) => {
    const quantity = e.target.value;
    total.textContent = parseInt(price.textContent) * parseInt(quantity);
  });
});