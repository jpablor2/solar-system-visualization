var jsonfile = require('jsonfile');
var express = require("express");


//------------MODULOS
exports.getModulos = function (req, res) {
    var json = [{
        "id_modulo": "0",
        "id_inversor": "0",
        "id_arreglo": "0",
        "p_stc": "a",
        "p_noct": "a",
        "eficiencia": "a",
        "fact_desemp": "a",
        "modelo": "a",
        "descripcion": "a"
    },{
        "id_modulo": "1",
        "id_inversor": "1",
        "id_arreglo": "1",
        "p_stc": "a",
        "p_noct": "a",
        "eficiencia": "a",
        "fact_desemp": "a",
        "modelo": "a",
        "descripcion": "a"
    }];
    res.send(200, json);
}
exports.getModulo = function (req, res) {
    var json = {
        "id_modulo": "0",
        "id_inversor": "0",
        "id_arreglo": "0",
        "p_stc": "a",
        "p_noct": "a",
        "eficiencia": "a",
        "fact_desemp": "a",
        "modelo": "a",
        "descripcion": "a"
    };
    res.send(200, json);
}

//------------INVERSORES
exports.getInversores = function (req, res) {
    var json = [{
        "id_inversor": "0",
        "max_strings": "a",
        "modelo": "a",
        "descripcion": "a",
        "micro": "a"
    },{
        "id_inversor": "1",
        "max_strings": "a",
        "modelo": "a",
        "descripcion": "a",
        "micro": "a"
    }];
    res.send(200, json);
}
exports.getInversor = function (req, res) {
    var json = {
        "id_inversor": "0",
        "max_strings": "a",
        "modelo": "a",
        "descripcion": "a",
        "micro": "a"
    };
    res.send(200, json);
}

//------------ARREGLOS

exports.getArreglos = function (req, res) {
    
    var json = [
        {
            "id_arreglo": "0",
            "tipoConexion": "a",
            "nPaneles": "a",
            "anguloInclinacion": "a",
            "anguloOrientacion": "a"

    }, {
            "id_arreglo": "1",
            "tipoConexion": "a",
            "nPaneles": "a",
            "anguloInclinacion": "a",
            "anguloOrientacion": "a"

    }
    ];

    res.send(200, json);
}
exports.getArreglo = function (req, res) {
    var json2 ={};
    var result=req.query;
    var id_arreglo=result.id_arreglo;
    console.log(id_arreglo);
    
    var json = [
        {
            "id_arreglo": "0",
            "tipoConexion": "a",
            "nPaneles": "a",
            "anguloInclinacion": "a",
            "anguloOrientacion": "a"

    }, {
            "id_arreglo": "1",
            "tipoConexion": "Paralela",
            "nPaneles": "a",
            "anguloInclinacion": "a",
            "anguloOrientacion": "a"

    }
    ];
    
      
    for(var i=0;i<json.length;i++) {
        if(json[i].id_arreglo==id_arreglo){
            json2=json[i];
            res.send(200, json2);
        }
        
    }
    
    res.send(200, json2);
}
