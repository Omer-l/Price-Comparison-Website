//div elements where products will be inserted
let appleProductsDiv;


//when page is loaded, run init
window.onload = init;

//Get the pointers to parts of the DOM after the page has loaded.
function init() {
    appleProductsDiv = document.getElementById("AppleDiv");
    loadProducts();
}

function loadProducts() {
    //set up XMLHTTPRequest()
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = () => {//when data is returned from server, do this.
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            //convert the JSON data into a JavaScript object
            let productsArray = JSON.parse(xhttp.responseText);

            //return if no products were in the database
            if(productsArray.length === 0) {
                appleProductsDiv.innerHTML = "";
                return;
            }

            //build string with the data of the product that is received.
            let htmlStr = "";

            for(let product in productsArray) {
                htmlStr += product.name + "<br>";
            }

            //finally, add the HTML string to the the div element containing the products.
            appleProductsDiv.innerHTML = htmlStr;
        }
    };

    //Request data from all products.
    xhttp.open("GET", "/products", true);
    xhttp.send();
}