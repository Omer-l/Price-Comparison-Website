//File containing the functions to test
require('../server.js');

//Node.js built in assertions
const assert = require("assert");

//Chai library for HTTP requests and more sophisticated assertions
let chai = require('chai');
let chaiHttp = require('chai-http');
let should = chai.should();
chai.use(chaiHttp);

//Import server
let server = require('../server.js');

//Wrapper for all server tests
describe('Server', function() {

   //Mocha test for getTotalProductsCount method in database
   describe('#getTotalProductsCount', function() {
      it('should return the total number of phones (or products) in the database', function(done) {
         //Data and dummy objects for test
         let response = {};
         response.status = () => {}; //Empty function in case of error
         response.json = () => {}; //Empty function in case of error
         let numItems = 4;
         let offset = 0;

         ///use database code to create a database object testing
         server.getTotalProductsCount = function (response, totNumItems, numItems, offset) {
            assert.equal(totNumItems, 6);
            done();
         }

         //Call function being tested
         server.getTotalProductsCount(response, numItems, offset);
      });
   });

   //Mocha/Chai test of RESTful Web Service
   describe('/GET products', () => {
      it('should GET all the products', (done) => {
         chai.request(server)
             .get('/products')
            .end((err, res) => {
            res.should.have.status(200);
            res.body.should.have.property('totNumItems');
            res.body.products.should.be.a('array');
            done();
         });
      });
   });
});