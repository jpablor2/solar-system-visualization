(function () {
    var app = angular.module('n-navbar', ['algoritmos']);

    app.controller('StoreController', function ($scope, DriverService) {
        $scope.title = 'Titulo Dashboard 0';
        $scope.message = 'Everyone come and see how good I look!';

    });


    app.controller('ManualController', function ($scope, DriverService) {
        $scope.title = 'Titulo Dashboard 1';
        $scope.message = 'Indicaciones';
        $scope.numeroGrafos = 0;
        $scope.busqueda = 0;
        $scope.numeroNodos = 1;
        $scope.peso = 1;
        $scope.inicio;
        $scope.final;
        $scope.nodo;

        $scope.addArray = function () {
            
            var _id = $scope._id,
                tipo_conexion=$scope.tipo_conexion,
                nPaneles=$scope.nPaneles,
                anguloInclinacion=$scope.anguloInclinacion,
                anguloOrientacion=$scope.anguloOrientacion;

            //Prueba de recoleccion de datos
            //console.log("Kilometros: "+km+" Placa: "+placa+" Litros: "+litros+" Monto: "+monto+" Fecha: "+fecha+" Id del conductor: "+id+" Numero de Factura:  "+idFactura);

            var setHeader = function (req) {
                req.setRequestHeader('content-type', 'application/json');
                req.setRequestHeader('accept', 'application/json');
            };

            $.ajax({
                type: 'POST',
                url: 'http://localhost:3000/server/insertArreglo',
                data: JSON.stringify({
                    '_id': _id,
                    'tipo_conexion':tipo_conexion,
                    'nPaneles':nPaneles,
                    'anguloInclinacion':anguloInclinacion,
                    'anguloOrientacion':anguloOrientacion
                }),
                beforeSend: setHeader,

                complete: function (data) {
                    //alert("Datos Ingresados Exitosamente!! ");
                    //console.log(data.responseText);
                    
                    console.log("se inserto correctamente");
                },
                error: function (e) {
                    //alert("Algo salio mal en la insercion!! :(");
                }
            })
        }


        DriverService.clean();

        $scope.getNodes = function () {
            return DriverService.nodes;
        }
        $scope.agregarNodo = function () {

            for (i = 0; i < $scope.numeroNodos; i++) {
                DriverService.insertNode();
            }

        }
        $scope.agregarArco = function () {
            if ($scope.peso) {
                DriverService.insertArc($scope.inicio, $scope.final, $scope.peso);
            }
        }

        $scope.eliminarNodo = function () {
            if ($scope.nodo) {
                DriverService.eliminarNodo($scope.nodo);
            }
        }
    });

    app.controller('AutomaticController', function ($scope, DriverService) {
        $scope.title = 'Titulo Dashboard 2 ';
        $scope.message = 'Indicaciones: ';
        $scope.busqueda = 0;
        $scope.numeroGrafos = 0;
        $scope.nodo;

        DriverService.clean();


        $scope.addModule = function () {

        }


        $scope.eliminarNodo = function () {
            if ($scope.nodo) {
                DriverService.eliminarNodo($scope.nodo);
            }
        }

        $scope.generarGrafo = function () {
            DriverService.clean();
            DriverService.generado = false;
            if ($scope.numeroGrafos <= 1000) {
                DriverService.generateTree($scope.numeroGrafos);
            } else {
                DriverService.grafo = DriverService.generateGrafoMatriz($scope.numeroGrafos);


            }
        }


    });

    app.controller('LoadController', function ($scope) {
        $scope.title = 'Titulo Dashboard 3';
        $scope.message = 'Indicaciones';
        $scope.busqueda = 0;
        $scope.addInverter = function () {
            
        }

    });


    app.directive('nNavbar', function (DriverService) {
        return {
            restrict: 'E', //It means element
            templateUrl: 'views/n-navbar.html',
            controller: function () {
                this.tab = 0;

                this.clean = function () {
                    location.reload();
                }
                this.setearAlgoritmo = function (algoritmo) {
                    this.askNodes(algoritmo);

                }
                this.isSet = function (checkTab) {
                    return this.tab === checkTab;
                };

                this.setTab = function (activeTab) {
                    this.tab = activeTab;
                };
                this.nodes2 = [];

                this.askNodes = function (algoritmo) {

                    this.nodes = [];
                    this.tam = DriverService.nodes.length;
                    this.nodes = eval(JSON.stringify(DriverService.nodes));
                    if (algoritmo !== 'SA' && algoritmo !== 'TS') {

                        DriverService.nodosSwal = [];
                        for (var i = 0; i < this.tam; i++) {
                            //this.nodes2.push(this.nodes[i].label);
                            DriverService.nodosSwal.push(this.nodes[i].label);
                            //console.log(this.nodes[i].label);
                        }

                        //this.askNodes1('a');
                        swal.setDefaults({
                            input: 'select',
                            inputOptions: DriverService.nodosSwal,
                            confirmButtonText: 'Siguiente &rarr;',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            animation: true,
                            progressSteps: ['1']
                        })

                        var steps = [

                            {
                                title: 'Seleccione',
                                text: 'Opc-1'
                  }
                ]

                        swal.queue(steps).then(function (result1) {
                            swal.setDefaults({
                                input: 'select',
                                inputOptions: DriverService.nodosSwal,
                                confirmButtonText: 'Siguiente &rarr;',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                animation: false,
                                progressSteps: ['2']
                            })
                            var steps = [
                                {
                                    title: 'Seleccione ',
                                    text: 'Opc-2'
                  }
                ]
                            swal.queue(steps).then(function (result2) {
                                swal.resetDefaults()
                                var origen = DriverService.nodosSwal[eval(parseInt(result1))];
                                var destino = DriverService.nodosSwal[eval(parseInt(result2))];
                                swal({
                                    title: "Resolviendo",
                                    text: "Espere por favor",
                                    type: 'success',
                                    animation: true,
                                    timer: 3000,
                                    html: 'De origen: ' + origen + '<br>' +
                                        //JSON.stringify(result1) +
                                        ' A destino: ' + destino,
                                    //JSON.stringify(result2),
                                    showCancelButton: false,
                                    showConfirmButton: false
                                })
                                localStorage.setItem("origen", "");
                                localStorage.setItem("destino", "");

                                localStorage.setItem("origen", origen);
                                localStorage.setItem("destino", destino);
                                DriverService.menuAlgoritmos(algoritmo);
                                /*DriverService.origen=origen;
                                DriverService.destino=destino;*/


                            }, function () {

                                swal.resetDefaults()
                            })
                        }, function () {

                            swal.resetDefaults()

                        });

                    } else {
                        swal(
                            'Oops...',
                            'Modulo en desarrollo!',
                            'error'
                        )
                    }
                }



            },
            controllerAs: 'tab'
        };
    });

    app.service('DriverService', function () {

        this.origen;
        this.destino;
        this.busqueda;
        this.nodosSwal = [];
        this.generado = true;

        this.nodes = [];
        this.edges = [];
        this.setOyD = function (origen, destino) {
            console.log('en setOyD con parametros ' + origen + ' y ' + destino);
            this.origen = origen;
            this.destino = destino;
        };

        this.buildGraph = function (nodes, edges) {

            var graph = [];
            var total = nodes[nodes.length - 1].id;
            if (total)
                for (var i = 0; i <= total; i++) {
                    let node = [];
                    for (j = 0; j < total; j++) {
                        node[j] = 0;
                    }
                    graph[i] = node;
                }

            for (i = 0; i < edges.length; i++) {
                graph[edges[i].from][edges[i].to] = parseInt(edges[i].label);
                graph[edges[i].to][edges[i].from] = parseInt(edges[i].label);
            }
            return graph;
        };
        //this.grafo = this.buildGraph(this.nodes, this.edges);


        this.totalNodes = 0; //total de nodos ya insertados
        this.contabc = 0; //para ir pasando una a una sobre las letras
        this.num = 0; //número de vueltas que ah dado ?(osea cada vez que llega a la Z)
        this.abc = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "u", "v", "w", "x", "y", "z"];
        this.letra;
        this.actual = 0; //variable para contener el id

        this.clean = function () {
            this.nodes = [];
            this.edges = [];
            this.totalNodes = 0; //total de nodos ya insertados
            this.contabc = 0; //para ir pasando una a una sobre las letras
            this.num = 0;
            //this.letra;
            this.actual = 0;
            //localStorage.setItem("origen","");
            //localStorage.setItem("destino","");
            this.generado = true;
            this.nodosSwal = [];
        }

        //--------------------------------------------------------------------------------------

        this.insertNode = function () {
            if (this.contabc == 25) {
                this.contabc = 0;
                this.num++;
            }
            this.letra = this.abc[this.contabc];
            if (this.num == 0) {
                this.actual = this.letra;
            } else {
                this.actual = this.letra + this.num;
            }

            this.nodes.push({
                id: this.totalNodes,
                label: this.actual
            });
            this.totalNodes++;
            this.contabc++;

            var container = document.getElementById('graph');
            var data = {
                nodes: this.nodes,
                edges: this.edges,
            };
            var graph = new vis.Network(container, data, {});

        }

        this.insertArc = function (from, to, peso) {
            //cálculo de labels para sacar los ids
            var cont = 0;
            var origen;
            var destino;
            //cálculo del id para el origen
            while (cont < 25) {
                if (this.abc[cont] == from[0]) {
                    if (from[1] == null) {
                        origen = cont;
                        //console.log(origen);
                        break;
                    } else {
                        origen = cont + (25 * parseInt(from[1]));
                        break;
                    }
                }
                cont++;
            }
            cont = 0;

            //cálculo del id de destino
            while (cont < 25) {
                if (this.abc[cont] == to[0]) {
                    if (to[1] == null) {
                        destino = cont;
                        //console.log(destino);
                        break;
                    } else {
                        destino = cont + (25 * parseInt(to[1]));
                        break;
                    }
                }
                cont++;
            }
            var matriz = this.buildGraph(this.nodes, this.edges);
            var n = 0;
            if (!matriz[origen][destino]) {
                this.edges.push({
                    from: origen,
                    to: destino,
                    label: peso
                });


            } else {
                for (i = 0; i < this.edges.length; i++) {
                    if (((this.edges[i].from === origen) && (this.edges[i].to === destino)) ||
                        ((this.edges[i].to === origen) && (this.edges[i].from === destino))) {
                        this.edges[i].peso = peso;

                    }
                }
            }
            var container = document.getElementById('graph');
            var data = {
                nodes: this.nodes,
                edges: this.edges,
            };

            var graph = new vis.Network(container, data, {});

        }

        this.generateTree = function (n) {
            //n= numero de nodos a generar
            var contnodos = n;
            var nodoActual = 0;
            var m;
            var destinoRand;
            var pesoRand;

            while (contnodos != 0) {
                this.insertNode();
                contnodos--;
            }
            var matriz = this.crearMatriz(n);
            contnodos = n;

            while (contnodos != 0) {
                //número de conecciones que tendrá el nodoActual
                m = Math.floor((Math.random() * n) + 0);
                while (m != 0) {
                    //generar un nodo destino
                    destinoRand = Math.floor((Math.random() * n) + 0);
                    pesoRand = Math.floor((Math.random() * 20) + 1);

                    if (matriz[nodoActual][destinoRand] == 0) {
                        matriz[nodoActual][destinoRand] = pesoRand;
                        matriz[destinoRand][nodoActual] = pesoRand;
                        this.edges.push({
                            from: nodoActual,
                            to: destinoRand,
                            label: pesoRand
                        });

                    }
                    m--;
                }
                nodoActual++;
                contnodos--;
            }
            this.mostrar(false);
        }

        this.generateGrafoMatriz = function (n) {
            //n= numero de nodos a generar
            var contnodos = n;
            var nodoActual = 0;
            var m;
            var destinoRand;
            var pesoRand;

            var matriz = this.crearMatriz(n);

            while (contnodos != 0) {
                //número de conecciones que tendrá el nodoActual
                m = Math.floor((Math.random() * n) + 0);
                while (m != 0) {
                    //generar un nodo destino
                    destinoRand = Math.floor((Math.random() * n) + 0);
                    pesoRand = Math.floor((Math.random() * 20) + 1);

                    if (matriz[nodoActual][destinoRand] == 0) {
                        matriz[nodoActual][destinoRand] = pesoRand;
                        matriz[destinoRand][nodoActual] = pesoRand;
                    }
                    m--;
                }
                nodoActual++;
                contnodos--;
            }
            //console.log("hola");
            return matriz;
        }

        this.eliminarNodo = function (n) {
            for (i = 0; i < this.nodes.length; i++) {
                if (this.nodes[i].label === n) {
                    this.nodes.splice(i, 1);
                }
            }
            for (i = 0; i < this.edges.length; i++) {
                if ((this.edges[i].to === n) || (this.edges[i].from === n)) {
                    this.edges.splice(i, 1);
                }
            }
            this.mostrar(true);
        }

        this.mostrar = function (p) {
            var container = document.getElementById('graph');
            var data = {
                nodes: this.nodes,
                edges: this.edges,
            };
            var graph = new vis.Network(container, data, {
                physics: p,
                layout: {
                    improvedLayout: false
                }
            });
        }

        this.crearMatriz = function (n) {
            var graph = [];
            for (var i = 0; i < n; i++) {
                let node = [];
                for (j = 0; j < n; j++) {
                    node[j] = 0;
                }
                graph[i] = node;
            }
            /*
                for (i = 0; i < edges.length; i++) {
                    graph[edges[i].from][edges[i].to] = parseInt(edges[i].label);
                    graph[edges[i].to][edges[i].from] = parseInt(edges[i].label);
                }
            */
            return graph;
        };

        this.toId = function (label) {
            var letra = label[0];
            if (label.length == 1) {
                return this.abc.indexOf(letra);
            }
            var num = label.slice(1, label.length);
            return this.abc.indexOf(letra) + (parseInt(num) * 25)
        }


        //----------------------------------------------------------------------------


        this.menuAlgoritmos = function (tipo) {
            var origen2 = localStorage.getItem("origen");
            var destino2 = localStorage.getItem("destino");
            var origen = this.toId(origen2);
            var destino = this.toId(destino2);
            this.grafo = this.buildGraph(this.nodes, this.edges);
            var result;
            var container2 = document.getElementById('graph2');
            var container = document.getElementById('graph');
            console.log(tipo);
            switch (tipo) {
                case 'DFS':
                    result = this.DFS(this.grafo, origen, destino);
                    break;
                case 'DLS':
                    result = this.DLS(this.grafo, origen, destino, 3);
                    break;
                case 'IDS':
                    result = this.IS(this.grafo, origen, destino, 3);
                    break;
                case 'BFS':
                    result = this.BFS(this.grafo, origen, destino);
                    break;
                case 'BS':
                    result = this.BS(this.grafo, origen, destino);
                    break;
                case 'UCS':
                    result = this.UCS(this.grafo, origen, destino);
                    break;
                case 'BEST-FS':
                    result = this.Best_FS(this.grafo, origen, destino, (function (a, b) {
                        return b - a
                    }));
                    break;
                case 'AS':
                    console.log(tipo);

                    result = this.astar(this.grafo, origen, destino, (function (a, b) {
                        return b - a
                    }));
                    break;
                case 'HC':
                    console.log(tipo);
                    result = this.hill_climbing(this.grafo, origen, destino, (function (a, b) {
                        return b - a
                    }));
                    break;
                default:
                    console.log("No implementado");
                    break;
            }
            if (tipo == 'BS' && result) {
                var graph_new = new vis.Network(container, result[0], {
                    layout: {
                        hierarchical: {
                            sortMethod: "directed"
                        }
                    }
                });
                var graph_new2 = new vis.Network(container2, result[1], {
                    layout: {
                        hierarchical: {
                            sortMethod: "directed"
                        }
                    }
                });
            } else if (result) {
                var graph_new = new vis.Network(container, result, {
                    layout: {
                        hierarchical: {
                            sortMethod: "directed"
                        }
                    }
                });
                var graph_new2 = new vis.Network(container2, {}, {
                    layout: {
                        hierarchical: {
                            sortMethod: "directed"
                        }
                    }
                });
            }
        }


        this.toLabel = function (n) {
            var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'];
            var label;
            var n1 = Math.floor(n / 25);
            if (n1) {
                label = abc[n % 25] + n1;
            } else {
                label = abc[n];
            }
            return label;
        }

        this.mark = function (nodes, goal) {
            for (i = 0; i < nodes.length; i++) {
                if (nodes[i].id === goal) {
                    nodes[i].color = "#f44336";
                }
            }
        }

        this.printLists = function (abiertos, cerrados) {
            var lista = [];
            for (i = 0; i < abiertos.length; i++) {
                lista.push(this.toLabel(abiertos[i]));
            }
            console.log("Nodos abiertos: " + lista.toString());
            lista = [];
            for (i = 0; i < cerrados.length; i++) {
                if (cerrados[i]) {
                    lista.push(this.toLabel(i));
                }
            }
            console.log("Nodos cerrados: " + lista.toString());
        }

        this.printListsB = function (abiertos, cerrados) {
                var lista = [];
                for (i = 0; i < abiertos.length; i++) {
                    lista.push(this.toLabel(abiertos[i].node));
                }
                console.log("Nodos abiertos: " + lista.toString());
                lista = [];
                for (i = 0; i < cerrados.length; i++) {
                    if (cerrados[i]) {
                        lista.push(this.toLabel(i));
                    }
                }
                console.log("Nodos cerrados: " + lista.toString());
            }
            /*
                Se necesita un grafo bidireccional para hacer el recorrido, esta función transforma el formato de grafo de vis.js (unidireccional) a bidireccional.
                Este grafo inserta el peso de los arcos donde hay un camino, de tomar este caso no se permitiria asignar un peso de 0.    
                nodes = lista de nodos (arreglo de JSON usado en vis.js)
                edges = lista de arcos (arreglo de JSON usado en vis.js)
            */



        ////////////////Uninformed Search

        /*
            Depth First Search (Profundidad)
            graph = matriz de adyacencia (utilizar buildGraph())
            current = nodo inicial (int)
            goal = nodo final (int)
        */
        this.DFS = function (graph, current, goal) {
            //var tree = new Tree(current);
            var nodes = [],
                edges = [];
            var stack = [];
            var visited = [];
            var node;
            stack.push(current);
            nodes.push({
                id: current,
                label: this.toLabel(current)
            });
            visited[current] = true;
            while (stack.length) {
                node = stack.pop();
                if (node === goal) {
                    this.printLists(stack, visited);
                    this.mark(nodes, goal);
                    return {
                        nodes: nodes,
                        edges: edges
                    };
                }
                for (var i = 0; i < graph[node].length; i += 1) {
                    if (graph[node][i] && !visited[i]) {
                        //tree.add(i, node, tree.traverseBF);
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        edges.push({
                            from: node,
                            to: i,
                            label: graph[node][i]
                        });
                        stack.push(i);
                        visited[i] = true;
                    }
                }
            }
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };

        /*
            Breadth First Search (Amplitud)
            graph = matriz de adyacencia (utilizar buildGraph())
            startNode = nodo inicial (int)
            targetNode = nodo final (int)
        */
        this.BFS = function (graph, startNode, targetNode) {

            var nodes = [],
                edges = [];
            var queue = [];
            var visited = [];
            var current;
            queue.push(startNode);
            nodes.push({
                id: startNode,
                label: this.toLabel(startNode)
            });
            visited[startNode] = true;

            while (queue.length) {
                current = queue.shift();
                if (current === targetNode) {
                    this.mark(nodes, targetNode);
                    this.printLists(queue, visited);
                    return {
                        nodes: nodes,
                        edges: edges
                    };

                }
                for (var i = 0; i < graph.length; i += 1) {
                    if (i !== current && graph[current][i] && !visited[i]) {
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        edges.push({
                            from: current,
                            to: i,
                            label: graph[current][i]
                        });
                        visited[i] = true;
                        queue.push(i);
                    }
                }
            }
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
        }],
                edges: []
            };
        };

        /*
            Depth Limited Search (Profundidad limitada)
            graph = matriz de adyacencia (utilizar buildGraph())
            source = nodo inicial (int)
            target = nodo final (int)
            max = nivel máximo de profunidad
        */
        this.DLS = function (graph, source, target, max) {
            var nodes = [],
                edges = [];
            var visited = [];
            var element, destination;
            var depth = 0;
            var stack = [];
            nodes.push({
                id: source,
                label: this.toLabel(source)
            });
            stack.push(source);
            visited[source] = true;
            depth = 0;

            while (stack.length) {
                element = stack[stack.length - 1];
                destination = element;
                while (destination <= graph.length) {
                    if (depth < max) {
                        if (graph[element][destination] && !visited[destination]) {
                            nodes.push({
                                id: destination,
                                label: this.toLabel(destination)
                            });
                            edges.push({
                                from: element,
                                to: destination,
                                label: graph[element][destination]
                            });
                            if (destination === target) {
                                this.mark(nodes, target);
                                this.printLists(stack, visited);
                                return {
                                    nodes: nodes,
                                    edges: edges
                                };
                            }
                            stack.push(destination);
                            visited[destination] = true;
                            depth++;
                            element = destination;
                            destination = 1;
                        }
                    } else {
                        break;
                    }
                    destination++;
                }
                stack.pop();
                depth--;
            }
            this.printLists(stack, visited);
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };

        /*
            Iterative Search (Iterativa)
            graph = matriz de adyacencia (utilizar buildGraph())
            source = nodo inicial (int)
            target = nodo final (int)
            max = nivel máximo de profundidad
        */
        this.IS = function (graph, source, target, max) {
            for (var i = 0; i <= max; i++) {
                var tree = DLS(graph, source, target, i);
                if (tree.nodes.length > 1) {
                    return tree;
                }
            }
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };

        /*
            Bidirectional Search (Bidireccional)
            graph = matriz de adyacencia (utilizar buildGraph())
            source = nodo inicial (int)
            target = nodo final (int)
        */

        this.BS = function (graph, source, target) {
            var nodesA = [],
                nodesB = [],
                edgesA = [],
                edgesB = [];
            var queueA = [];
            var queueB = [];
            var visitedA = [];
            var visitedB = [];

            visitedA[source] = true;
            visitedB[target] = true;
            queueA.push(source);
            nodesA.push({
                id: source,
                label: this.toLabel(source)
            });
            queueB.push(target);
            nodesB.push({
                id: target,
                label: this.toLabel(target)
            });

            while (queueA.length && queueB.length) {
                var result = this.pathExistsBidirectionalHelper(graph, queueA, visitedA, visitedB, nodesA, edgesA);
                if (result) {
                    this.mark(nodesA, result);
                    this.mark(nodesB, result);
                    console.log("Desde el inicio:");
                    this.printLists(queueA, visitedA);
                    console.log("Desde el final:")
                    this.printLists(queueB, visitedB);
                    return [{
                        nodes: nodesA,
                        edges: edgesA
                }, {
                        nodes: nodesB,
                        edges: edgesB
                }];
                }
                result = this.pathExistsBidirectionalHelper(graph, queueB, visitedB, visitedA, nodesB, edgesB);
                if (result) {
                    console.log("Desde el inicio:");
                    this.printLists(queueA, visitedA);
                    console.log("Desde el final:")
                    this.printLists(queueB, visitedB);
                    this.mark(nodesA, result);
                    this.mark(nodesB, result);
                    return [{
                        nodes: nodesA,
                        edges: edgesA
                }, {
                        nodes: nodesB,
                        edges: edgesB
                }];
                }
            }

            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };

        /*
            Busca si existe una coincidencia entre los nodos visitados de las dos listas
            graph = matriz de adyacencia
            queue = cola de nodos 
            visitedFromThisSide = lista de nodos visitados desde un lado
            visitedFromThatSide = lista de nodos visitados desde el otro lado
        */
        this.pathExistsBidirectionalHelper = function (graph, queue, visitedFromThisSide, visitedFromThatSide, nodes, edges) {
            if (queue.length) {
                var next = queue.pop();
                for (var i = 0; i < graph.length; i++) {
                    if (graph[next][i] && visitedFromThatSide[i]) {
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        edges.push({
                            from: next,
                            to: i,
                            label: graph[next][i]
                        });
                        this.mark(nodes, i);
                        return i;
                    } else if (graph[next][i] && !visitedFromThisSide[i]) {
                        visitedFromThisSide[i] = true;
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        edges.push({
                            from: next,
                            to: i,
                            label: graph[next][i]
                        });
                        queue.push(i);
                    }
                }
            }
            return false;
        };

        /*
          Crea un JSON con el nodo y el costo acumulado, lo inserta en la cola y la ordena de menor a mayor (prioridad)
          queue = cola de prioridad
          node = nodo origen (int)
          cost = costo de llegar a ese nodo (int)
        */
        this.enPQueue = function (queue, node, cost) {
            var n = {
                node: node,
                cost: cost
            };
            queue.push(n);
            queue.sort(function (a, b) {
                return a.cost - b.cost
            });
        };

        /*
          Uniform Cost Search (Costo uniforme)
          graph = matriz de adyacencia (utilizar !! buildGraphC() para crear matriz)
          root = nodo inicial
          goal = nodo final
        */
        this.UCS = function (graph, root, goal) {
            var nodes = [],
                edges = [];
            var node, cost, child_cost;
            var to;
            var queue = [];
            var visited = [];
            nodes.push({
                id: root + '-' + 0,
                label: this.toLabel(root) + '-' + 0
            });
            this.enPQueue(queue, root, 0);
            while (queue.length) {
                var temp = queue.shift();
                node = temp.node;
                cost = temp.cost;
                if (node == goal) {
                    this.mark(nodes, goal + '-' + cost);
                    this.printListsB(queue, visited);
                    return {
                        nodes: nodes,
                        edges: edges
                    };
                }
                for (to = graph.length - 1; to >= 0; to--) {
                    child_cost = graph[node][to];
                    if (child_cost) {
                        if ((function () {
                                for (i = 0; i < queue.length; i++) {
                                    if (queue[i].node === to && queue[i].cost < child_cost + cost) {
                                        return false;
                                    }
                                }
                                return true;
                            })()) {
                            nodes.push({
                                id: to + '-' + (child_cost + cost),
                                label: this.toLabel(to) + '-' + (child_cost + cost)
                            });
                            edges.push({
                                from: node + '-' + cost,
                                to: to + '-' + (child_cost + cost),
                                label: graph[node][to]
                            });
                            this.enPQueue(queue, to, (child_cost + cost));
                        }
                    }
                }
            }
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };



        ////////////////Informed Search

        /*
            Best-First Search
            graph = matriz de adyacencia (utilizar !! buildGraphC() para crear matriz)
            current = nodo inicial
            goal = nodo final
        */
        this.Best_FS = function (graph, source, goal, heuristic) {
            var nodes = [],
                edges = [];
            var stack = [];
            var visited = [];
            var node, cost;
            nodes.push({
                id: source,
                label: this.toLabel(source)
            });
            this.enPQueue(stack, source, 0);
            visited[source] = true;
            while (stack.length) {
                node = stack.shift().node;
                if (node === goal) {
                    this.mark(nodes, goal);
                    this.printListsB(stack, visited);
                    return {
                        nodes: nodes,
                        edges: edges
                    };
                }
                let pStack = [];
                for (var i = 0; i < graph[node].length; i += 1) {
                    if (graph[node][i] && !visited[i]) {
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        cost = heuristic(i, goal);
                        edges.push({
                            from: node,
                            to: i,
                            label: graph[node][i] + ", h(n):" + cost
                        });
                        this.enPQueue(pStack, i, cost);
                        visited[i] = true;
                    }
                }
                stack = pStack.concat(stack);
            }
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };


        this.astar = function (graph, start, end, heuristic) {
            var nodes = [],
                edges = [];
            var openList = [];
            var closedList = [];
            var node, cost;
            this.enPQueue(openList, start, heuristic(start, end));
            nodes.push({
                id: start,
                label: this.toLabel(start)
            })
            while (openList.length) {

                var currentNode = openList.shift();
                node = currentNode.node;
                cost = currentNode.cost;
                if (node == end) {
                    this.mark(nodes, end);
                    this.printListsB(openList, closedList);
                    return {
                        nodes: nodes,
                        edges: edges
                    };
                }

                // Normal case -- move currentNode from open to closed, process each of its neighbors

                closedList[node] = true;

                for (i = 0; i < graph.length; i++) {
                    if (graph[node][i] && !closedList[i]) {
                        var gScore = (cost - heuristic(node, end)) + graph[node][i]; // 1 is the distance from a node to it's neighbor

                        var next = this.exist(openList, i);
                        var h = heuristic(i, end);

                        if (next == -1) {
                            nodes.push({
                                id: i,
                                label: this.toLabel(i)
                            });
                            var f = gScore + h;
                            edges.push({
                                from: node,
                                to: i,
                                label: "g(n):" + gScore + "\n h(n):" + h + "\nf(n):" + f
                            })
                            this.enPQueue(openList, i, f);
                        } else {
                            if (gScore < openList[next].cost - h) {
                                openList[next].cost = gScore + h;
                                for (j = 0; j < edges.length; j++) {
                                    if (edges[j].to === i) {
                                        edges[j].from = node;
                                        console.log(node);
                                        console.log(edges[j]);
                                    }
                                }
                                openList.sort(function (a, b) {
                                    return a.cost - b.cost
                                });
                            }
                        }
                    }
                }
            }

            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        }

        /*
            Devuelve la posición de un nodo si este existe dentro de la lista, si no, devuelve -1.
            list = lista de nodos ({nodo,costo})
            n = nodo a buscar
        */
        this.exist = function (list, n) {
            for (j = 0; j < list.length; j++) {
                if (list[j].node === n) {
                    return j;
                }
            }
            return -1;
        };


        /*
            Hill Climbing Search
            graph = matriz de adyacencia (utilizar !! buildGraphC() para crear matriz)
            current = nodo inicial
            goal = nodo final
        */
        this.hill_climbing = function (graph, source, goal, heuristic) {
            var nodes = [],
                edges = [];
            var stack = [];
            var visited = [];
            var node, cost;
            nodes.push({
                id: source,
                label: this.toLabel(source)
            });
            this.enPQueue(stack, source, 0);
            visited[source] = true;
            while (stack.length) {
                node = stack.shift().node;
                if (node === goal) {
                    this.mark(nodes, goal);
                    this.printListsB(stack, visited);
                    return {
                        nodes: nodes,
                        edges: edges
                    };
                }
                let pStack = [];
                for (var i = 0; i < graph[node].length; i += 1) {
                    if (graph[node][i] && !visited[i]) {
                        nodes.push({
                            id: i,
                            label: this.toLabel(i)
                        });
                        cost = heuristic(i, goal);
                        edges.push({
                            from: node,
                            to: i,
                            label: graph[node][i] + ", h(n):" + cost
                        });
                        this.enPQueue(pStack, i, cost);
                        visited[i] = true;
                    }
                }
                stack.push(pStack.shift());
            }
            this.printListsB(stack, visited);
            return {
                nodes: [{
                    id: 0,
                    label: "No se encontró solución"
            }],
                edges: []
            };
        };


    })
})();
