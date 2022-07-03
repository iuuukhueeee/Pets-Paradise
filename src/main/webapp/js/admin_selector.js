let getSelector = () => {
	let val = document.getElementById("category").value;
	console.log(val);
	if (val === "food" || val ==='gel') {
	  console.log("hello world");
	  let display = (document.getElementById("date_form").style.display =
		"block");
	} else {
	  let display = (document.getElementById("date_form").style.display =
		"none");
	}
  };
  