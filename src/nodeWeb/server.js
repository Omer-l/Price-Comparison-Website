//Import the express and url modules
var express = require('express');
var url = require("url");
var bodyParser = require("body-parser");
//Import the mysql module
var mysql = require('mysql');

//Status codes defined in external file
require('./http_status.js');

//The express module is a function. When it is executed it returns an app object
const app = express();
app.use(bodyParser.json());

//Set up express to serve static files from the directory called 'public'
app.use(express.static('public')); //use public folder to load files
app.use('/css', express.static('node_modules/bootstrap/dist/css')); //bootstrap css
app.use('/js', express.static('node_modules/bootstrap/dist/js')); //boostrap js
app.use('/js', express.static('node_modules/jquery/dist')); //jquery


//Start the app listening on port 8080
app.listen(8080);
//Create a connection object with the user details
var connectionPool = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : 'root',
    database : 'sampple'
});

//Set up the application to handle GET requests sent to the user path
app.get('/products/*', handleGetRequest);//Subfolders
app.get('/products', handleGetRequest);
app.get('/phones', handleGetRequest);



/* Handles GET requests sent to web service.
   Processes path and query string and calls appropriate functions to
   return the data. */
function handleGetRequest(request, response) {
    //Parse the URL
    var urlObj = url.parse(request.url, true);

    //Extract object containing queries from URL object.
    var queries = urlObj.query;

    //Get the pagination properties if they have been set. Will be undefined if not set.
    var numItems = queries['num_items'];
    var offset = queries['offset'];

    //Split the path of the request into its components
    var pathArray = urlObj.pathname.split("/");

    //Get the last part of the path
    var pathEnd = pathArray[pathArray.length - 1];

    //If path ends with 'products' we return all products
    if(pathEnd === 'products'){
        getTotalProductsCount(response, numItems, offset);//This function calls the getAllProducts function in its callback
        return;
    }

    if(pathEnd == 'phones') {
        console.log("GETTING PHONES");
        getTotalPhonesCount(response, numItems, offset);//This function calls the getAllProducts function in its callback
        return;
    }

    //If path ends with products/, we return all products
    if (pathEnd === '' && pathArray[pathArray.length - 2] === 'products'){
        getTotalProductsCount(response, numItems, offset);//This function calls the getAllProducts function in its callback
        return;
    }

    //If the last part of the path is a valid user id, return data about that user
    if((pathEnd)){
        getSpecificProduct(response, pathEnd);
        return;
    }

    //The path is not recognized. Return an error message
    response.status(HTTP_STATUS.NOT_FOUND);
    response.send("{error: 'Path not recognized', url: " + request.url + "}");
}


/** Returns all of the products, possibly with a limit on the total number of items returned and the offset (to
 *  enable pagination). This function should be called in the callback of getTotalProductCount  */
function getAllProducts(response, totNumItems, numItems, offset) {
    //Select the products data using JOIN to convert foreign keys into useful data.
    var sql = "SELECT products.name, products.url, products.price, phones.model, phones.brand, phones.display_size, phones.color, phones.url_image, phones.storage, products.id, products.phone_id FROM ( ( products INNER JOIN phones ON phones.id = products.phone_id  ) ) ";

    //Limit the number of results returned, if this has been specified in the query string
    if(numItems !== undefined && offset !== undefined ){
        sql += "ORDER BY products.id LIMIT " + numItems + " OFFSET " + offset;
    }

    //Execute the query
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err){
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': + err});
            return;
        }

        //Create JavaScript object that combines total number of items with data
        var returnObj = {totNumItems: totNumItems};
        returnObj.products = result; //Array of data from database
        //Return results in JSON format
        response.json(returnObj);
    });
}


/** When retrieving all products we start by retrieving the total number of products
    The database callback function will then call the function to get the product data
    with pagination */
function getTotalProductsCount(response, numItems, offset){
    var sql = "SELECT COUNT(*) FROM products";

    //Execute the query and call the anonymous callback function.
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err){
            console.error("Error executing query: " + JSON.stringify(err)); // del?
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': + err});
            return;
        }

        //Get the total number of items from the result
        var totNumItems = result[0]['COUNT(*)'];

        //Call the function that retrieves all products
        getAllProducts(response, totNumItems, numItems, offset);
    });
}

/** Returns all of the phones, possibly with a limit on the total number of items returned and the offset (to
 *  enable pagination). This function should be called in the callback of getTotalProductCount  */
function getAllPhones(response, totNumItems, numItems, offset) {
    //Select the phones data using JOIN to convert foreign keys into useful data.
    // var sql = "SELECT * FROM phones";
    var sql = "SELECT model, brand, color, STORAGE, display_size, url_image FROM phones WHERE url_image NOT LIKE '.gif' GROUP BY model;"

    //Limit the number of results returned, if this has been specified in the query string
    if(numItems !== undefined && offset !== undefined ){
        sql += "ORDER BY phones.id LIMIT " + numItems + " OFFSET " + offset;
    }

    //Execute the query
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err){
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': + err});
            return;
        }

        //Create JavaScript object that combines total number of items with data
        var returnObj = {totNumItems: totNumItems};
        returnObj.phones = result; //Array of data from database
        //Return results in JSON format
        response.json(returnObj);
    });
}

/** When retrieving all phones we start by retrieving the total number of phones
 The database callback function will then call the function to get the phones data
 with pagination */
function getTotalPhonesCount(response, numItems, offset){
    var sql = "SELECT COUNT(*) FROM phones";

    //Execute the query and call the anonymous callback function.
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err){
            console.error("Error executing query: " + JSON.stringify(err)); // del?
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': + err});
            return;
        }

        //Get the total number of items from the result
        var totNumItems = result[0]['COUNT(*)'];

        //Call the function that retrieves all phones
        getAllPhones(response, totNumItems, numItems, offset);
    });
}


/** Returns the phones with the specified ID */
function getSpecificProduct(response, phoneModel){
    //Build SQL query to select product with specified id.
    phoneModel = phoneModel.replaceAll("%20", " "); //turn the url into a regular readable string for mysql
    var sql = "SELECT products.name, products.url, phones.brand, phones.display_size, products.price, phones.model, phones.color, phones.storage FROM ( ( products INNER JOIN phones ON phones.id = products.phone_id  ) ) WHERE phones.model LIKE '%" + phoneModel + "%'";

    //Execute the query
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err){
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': + err});
            return;
        }
        var returnObj = {
            products: result};
        console.log(returnObj.products);
        //Output results in JSON format
        response.json(returnObj);
    });
}
