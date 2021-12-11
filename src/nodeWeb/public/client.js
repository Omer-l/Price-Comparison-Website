//div elements where products will be inserted
const axios = require("axios");


//when page is loaded, run init
window.onload = init;

let search = "not changed";

var setSearch = new Vue({
    el: '#searchDiv',
    data: {
        searchInput: ""
    },
    methods: {
        search: function () {
            var localApp = this;
            localApp.searchInput = document.getElementById("searchInput").value;
            console.log("HERE THERE");
            console.log(localApp.searchInput);
        }
    }
})
var search = new Vue({
    el: '#search',
    data: {
        searchInput: ''
    },
    // define methods under the `methods` object
    methods: {
        greet: function (searchInput) {
            this.searchInput = searchInput;
        }
    }
})

// function loadProducts() {
//     //set up XMLHTTPRequest()
//     let xhttp = new XMLHttpRequest();
//
//     xhttp.onreadystatechange = () => {//when data is returned from server, do this.
//         if(xhttp.readyState == 4 && xhttp.status == 200) {
//             //convert the JSON data into a JavaScript object
//             let productsArray = JSON.parse(xhttp.responseText);
//             products = productsArray;
//             //return if no products were in the database
//             if(productsArray.length === 0) {
//                 appleProductsDiv.innerHTML = "";
//                 return;
//             }
//
//             //build string with the data of the product that is received.
//             let htmlStr = "";
//
//             for(let product in productsArray) {
//                 htmlStr += product.name + "<br>";
//             }
//
//             //finally, add the HTML string to the the div element containing the products.
//             appleProductsDiv.innerHTML = htmlStr;
//         }
//     };
//
//     //Request data from all products.
//     xhttp.open("GET", "/products", true);
//     xhttp.send();
// }