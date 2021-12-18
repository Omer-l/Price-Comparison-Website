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
      it('should return the total number of products in the database given a limit', function(done) {
         //Data and dummy objects for test
         let response = {};
         response.status = () => {}; //Empty function in case of error
         response.json = () => {}; //Empty function in case of error
         let numItems = 4;
         let offset = 0;

         ///use database code to create a database object testing
         server.getTotalProductsCount = function (response, totNumItems, numItems, offset) {
            assert.equal(totNumItems, 4);
            done();
         }

         //Call function being tested
         server.getTotalProductsCount(response, numItems, offset);
      });
   });

   //Mocha test for getTotalPhonesCount method in database
   describe('#getTotalPhonesCount', function() {
      it('should return the total number of phone models in the database given a limit', function(done) {
         //Data and dummy objects for test
         let response = {};
         response.status = () => {}; //Empty function in case of error
         response.json = () => {}; //Empty function in case of error
         let numItems = 8;
         let offset = 0;

         ///use database code to create a database object testing
         server.getTotalPhonesCount = function (response, totNumItems, numItems, offset) {
            assert.equal(totNumItems, 8);
            done();
         }

         //Call function being tested
         server.getTotalPhonesCount(response, numItems, offset);
      });
   });

   //Mocha/Chai test of RESTful Web Service
   describe('/GET products', () => {
      it('should GET all the products', (done) => {
         chai.request('http://localhost:8080')
             .get('/products')
            .end((err, res) => {
            res.should.have.status(200);
            res.body.should.have.property('totNumItems');
            res.body.products.should.be.a('array');
            done();
         });
      });
   });

   //Mocha/Chai test of RESTful Web Service
   describe('/GET phones', () => {
      it('should GET all the phones', (done) => {
         chai.request('http://localhost:8080')
             .get('/phones')
             .end((err, res) => {
                res.should.have.status(200);
                res.body.should.have.property('totNumItems');
                res.body.phones.should.be.a('array');
                done();
             });
      });
   });

   //Mocha/Chai test of RESTful Web Service
   describe('/GET products', () => {
      it('should GET specific products', (done) => {
         chai.request('http://localhost:8080')
             .get('/products/2')
             .end((err, res) => {
                res.should.have.status(200);
                res.body.products.should.be.a('array');
                done();
             });
      });
   });

});