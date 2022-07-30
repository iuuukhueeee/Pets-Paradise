let getSelector = () => {
  let val = document.getElementById("category").value;
  console.log(val);
  if (val === "food" || val === "gel") {
    console.log("hello world");
    let display = (document.getElementById("expire_date").style.display =
      "block");
	  let display1 = (document.getElementById("import_date").style.display =
      "block");
  } else if (val === "toy" || val === "acc" || val ==="stuff"){
	let display = (document.getElementById("import_date").style.display =
	"block");
	let display1 = (document.getElementById("expire_date").style.display =
	"none");
  }
};
