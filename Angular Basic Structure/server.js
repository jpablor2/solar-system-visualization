var express = require("express"),  
    app = express(),
    bodyParser  = require("body-parser"),
    methodOverride = require("method-override");
var path = require('path');
var jsonFile = require ('jsonfile');
var serverMethods = require('./serverMethods');

    
    //mongoose = require('mongoose');

app.use(bodyParser.urlencoded({ extended: false }));  
app.use(bodyParser.json());  
app.use(methodOverride());
console.log(__dirname+'esta es la ruta');
//app.use(express.static(path.join(__dirname, 'views')));
app.use(express.static(path.join(__dirname + '/public'))); 
app.use('/server', express.static(__dirname + '/'));
//app.use(express.static(path.join(__dirname + 'public/views'))); 
//app.use(express.static(path.join(__dirname + '/assets'))); 

console.log('la ruta es: '+__dirname);
var router = express.Router();

/*
router.get('/', function(req, res) {    
   res.send("Hello World !");
});*/

//app.use(router);

//Modulos
app.get('/server/getModulos', serverMethods.getModulos);
app.post('/server/getModulo', serverMethods.getModulo);

//Inversores
app.get('/server/getInversores', serverMethods.getInversores);
app.post('/server/getInversor', serverMethods.getInversor);

//Arreglos
app.get('/server/getArreglos', serverMethods.getArreglos);
app.post('/server/getArreglo', serverMethods.getArreglo);




app.listen(3000, function() {
    console.log("Node server running on http://localhost:3000");
  });

