var jsonfile = require('jsonfile');
var express = require("express");
var mongo = require('mongodb');

var Server = mongo.Server,
    Db = mongo.Db,
    BSON = mongo.BSONPure;

var server = new Server('localhost', 27017, {
    auto_reconnect: true
});
db = new Db('SSV', server);

db.open(function (err, db) {
    if (!err) {
        console.log('Connected to "SSV" database');
        /*var db2 = db.db('Lecheria'),
            db3 = db.db('Laboratory');
        lecheriaApp.setDB(db2);
        laboratoryApp.setDB(db3);*/
    } else {
        console.log(404, 'Error Connecting to "SSV" database');
    }
});


//------------MODULOS

exports.insertModulo = function (req, res) {
    
    var resource = req.body;
    db.collection('index').findAndModify({
        _id: 0
    }, {}, {
        $inc: {
            modulo: 1
        }
    }, function (req2, res2) {
        var newId = res2.value.modulo;
        resource['_id'] = newId;
        db.collection('modulo').insert(resource, function (err, doc_res) {
            if (err) throw err;
            res.send(200, resource);
        });
    });
    
}

exports.getModulos = function (req, res) {
    db.collection('modulo').find().toArray(function (err, doc_res) {
        if (err) throw err;
        if (!doc_res) console.log("No document found");
        res.send(200, doc_res);
    });
}
exports.getModulo = function (req, res) {
    var resource = req.body;
    db.collection('modulo').findOne({
        _id: parseInt(req.body._id)
    }, function (err, resource) {

        if (err) throw err;
        res.send(200, resource);
    });
}


//------------INVERSORES

exports.insertInversor = function (req, res) {
    
    var resource = req.body;
    db.collection('index').findAndModify({
        _id: 0
    }, {}, {
        $inc: {
            inversor: 1
        }
    }, function (req2, res2) {
        var newId = res2.value.inversor;

        resource['_id'] = newId;
        db.collection('inversor').insert(resource, function (err, doc_res) {
            if (err) throw err;
            res.send(200, resource);
        });
    });
    
}

exports.getInversores = function (req, res) {
    db.collection('inversor').find().toArray(function (err, doc_res) {
        if (err) throw err;
        if (!doc_res) console.log("No document found");
        res.send(200, doc_res);
    });
}
exports.getInversor = function (req, res) {
    var resource = req.body;
    console.log("id inversor "+typeof(parseInt(req.body._id)));
    db.collection('inversor').findOne({
        _id: parseInt(req.body._id)
    }, function (err, resource) {

        if (err) throw err;
        res.send(200, resource);
    });
}


//------------ARREGLOS
exports.insertArreglo = function (req, res) {
    
    var resource = req.body;
    db.collection('index').findAndModify({
        _id: 0
    }, {}, {
        $inc: {
            arreglo: 1
        }
    }, function (req2, res2) {
        var newId = res2.value.arreglo;

        resource['_id'] = newId;
                
        db.collection('arreglo').insert(resource, function (err, doc_res) {
            if (err) throw err;
            res.send(200, resource);
        });
    });
    
}


exports.getArreglos = function (req, res) {

    db.collection('arreglo').find().toArray(function (err, doc_res) {
        if (err) throw err;
        if (!doc_res) console.log("No document found");
        res.send(200, doc_res);
    });

}
exports.getArreglo = function (req, res) {

        var resource = req.body;
        console.log(req.body);
        db.collection('arreglo').findOne({
            _id: parseInt(req.body._id)
        }, function (err, resource) {

            if (err) throw err;
            res.send(200, resource);
        });

    }
    /*const pg = require("pg");
    const connectionString = process.env.DATABASE_URL || 'postgres://postgres:postgres@localhost:5433/SSV';
    const client = new pg.Client(connectionString);
    client.connect();

    const query = client.query(
      'CREATE TABLE items(id SERIAL PRIMARY KEY, text VARCHAR(40) not null, complete BOOLEAN)');
    query.on('end', () => { client.end(); });*/
