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
    var resource = req.query;
    db.collection('modulo').findOne({
        _id: parseInt(req.query._id)
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
    var resource = req.query;
    //console.log("id inversor " + typeof (parseInt(req.query._id)));
    db.collection('inversor').findOne({
        _id: parseInt(req.query._id)
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

    var resource = req.query;
    //console.log(req.query);
    db.collection('arreglo').findOne({
        _id: parseInt(req.query._id)
    }, function (err, resource) {

        if (err) throw err;
        res.send(200, resource);
    });

}

exports.getConjunto = function (req, res) {

    var resource = req.body;

    var id = req.body._id;

    db.collection('arreglo').findOne({
        _id: parseInt(id)
    }, function (err, resource) {

        if (err) throw err;
        //res.send(200, resource);

        db.collection('modulo').find({
            id_arreglo: req.body._id
        }).toArray(function (err, doc_res) {
            if (err) throw err;
            if (!doc_res) console.log("No document found");
            //res.send(200, doc_res);
            
            var id_inversor=doc_res[0].id_inversor;
            
            
            db.collection('inversor').findOne({
                _id: parseInt(id_inversor)
            }, function (err, inversor) {

                if (err) throw err;
                console.log(resource);
                console.log(doc_res);
                console.log(inversor);
                var info={
                    'id_arreglo':resource._id,
                    'tipo_conexion':resource.tipo_conexion,
                    'nPaneles':resource.nPaneles,
                    'anguloInclinacion':resource.anguloInclinacion,
                    'anguloOrientacion':resource.anguloOrientacion,
                    'id_inversor':inversor._id,
                    'max_strings':inversor.max_strings,
                    'modelo':inversor.modelo,
                    'descripcion_inv':inversor.descripcion,
                    'micro':inversor.micro,
                    'l_modulos':doc_res
                }
                res.send(200, info);
            });

        });

    });

    /*
    var param = req.body.filtro;
    //res.send(200,param);

    db.collection('Dispositivos').find({}, {
        'ID': 1,
        'description': 1,
        '_id': 0
    }).toArray(function (err, doc_resD) {

        if (err) throw err;

        if (!doc_resD) {
            console.log("No document found");

        } else {
            //res.send(200, doc_res);
                db.collection('MetricasAutomoviles').aggregate([

                        {
                            '$match': {
                                'year': parseInt(param)
                            }
    },
                    {
                        '$project': {
                            'year': 1,
                            'month': 1,
                            'amount': 1,
                            'km': 1,
                            'date': 1,
                            'ID': 1,
                            'liters':1
                        }
},
                    {
                        '$group': {
                            '_id': {
                                'month': '$month',
                                'year': '$year',
                                'ID': '$ID',
                                'km': '$km',
                                'total_litros':'$liters'

                            },
                            'total_costo': {
                                '$sum': '$amount'
                            },
                            'min_km': {
                                '$min': '$km'
                            },
                            'max_km': {
                                '$max': '$km'
                            },
                            

                        }
},
                    {
                        '$sort': {
                            '_id.month': 1,
                            '_id.ID':1,
                            '_id.km':1
                        }
}
],
                function (err, doc_res) {
                    if (err) throw err;

                    if (!doc_res) {
                        res.send(400);

                    } else {
                        var l_reporte = [];

                        for (var i = 0; i < doc_res.length; i++) {

                            for (var j = 0; j < doc_resD.length; j++) {
                                if (doc_res[i]._id.ID == doc_resD[j].ID) {

                                    var reporte = {
                                        'tipo_reporte': 'mensual',
                                        '_id': i,
                                        'month': doc_res[i]._id.month,
                                        'year': doc_res[i]._id.year,
                                        'description': doc_resD[j].description,
                                        'ID': doc_res[i]._id.ID,
                                        'total_costo': doc_res[i].total_costo,
                                        'min_km': doc_res[i].min_km,
                                        'max_km': doc_res[i].max_km,
                                        'km':doc_res[i]._id.km, // esto se lo agregue nuevo 
                                        'total_litros':doc_res[i]._id.total_litros
                                    };
                                    break;
                                }

                            }

                            l_reporte.push(reporte);
                        }
                        res.send(200, l_reporte);

                    }
                });
        }
    });
    */



    /*
    var resource = req.body;
    db.collection('modulo').find().toArray(function (err, doc_res) {
        if (err) throw err;
        if (!doc_res) console.log("No document found");
        res.send(200, doc_res);
        db.collection('arreglo').findOne({
            _id: parseInt(req.body._id)
        }, function (err, resource) {
            console.log("buscando relaciones del arreglo " + req.body._id);
            if (err) throw err;
            res.send(200, resource);

        });
    });*/


}









/*const pg = require("pg");
const connectionString = process.env.DATABASE_URL || 'postgres://postgres:postgres@localhost:5433/SSV';
const client = new pg.Client(connectionString);
client.connect();

const query = client.query(
  'CREATE TABLE items(id SERIAL PRIMARY KEY, text VARCHAR(40) not null, complete BOOLEAN)');
query.on('end', () => { client.end(); });*/
