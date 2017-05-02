var jsonfile = require('jsonfile');
var express = require("express");





exports.readJSON = function (req, res) {
    //var file = 'data.json';

    loadJsonFile('data.json').then(json => {
        console.dir('el json es' + json);
        //=> {foo: true} 
    });
    //console.dir('objeto: '+jsonfile.readFileSync(file));
    /*
    jsonfile.readFile(file, function (err, obj) {
        console.dir('Imprimiendo el objeto '+obj);
        res.send(200,obj);
        //eval(data.responseText);
    });*/
    res.send(200);

};
exports.exportJSON = function (req, res) {
    var file = 'data.json';
    var obj = req.body.obj;

    jsonfile.writeFile(file, obj, function (err) {
            console.error(err);
        })
        //res.json({Message: 'Buenisimo'});
        //res.send(200);
    res.download('data.json');
};

exports.getJSON = function (req, res) {
    var json = [
        {
            'id': 1234,
            'name': 'Pedro',
            'age': 21
        },
        {
            'id': 5678,
            'name': 'Evan',
            'age': 23
        },
        {
            'id': 6548,
            'name': 'Rafael',
            'age': 22
        },
        {
            'id': 5821,
            'name': 'Albert',
            'age': 22
        }
    ]
    res.send(200, json);
}

exports.getSchema = function (req, res) {
    var json={
        "id": 1,
        "name": "A green door",
        "price": 12.5,
        "checked": false,
        "tags": [
    "home",
    "green"
  ]
    };
    res.send(200,json);
}
