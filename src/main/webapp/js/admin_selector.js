let getSelector = () => {
    let val = document.getElementById("category").value;
    console.log(val);
    if (val === "CATEGORY-001" || val === "CATEGORY-002") {
        console.log("hello world");
        let display = (document.getElementById("expire_date").style.display =
            "block");
        let display1 = (document.getElementById("import_date").style.display =
            "block");
    } else if (val === "CATEGORY-005" || val === "CATEGORY-003" || val ==="CATEGORY-004"){
        let display = (document.getElementById("import_date").style.display =
            "block");
        let display1 = (document.getElementById("expire_date").style.display =
            "none");
    }
};
