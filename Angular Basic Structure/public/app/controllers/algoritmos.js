(function () {
    var app = angular.module('algoritmos', []);

    /*
        Convierte un numero 'id' a su equivalente alfanumerico dedicado al label.
        n = id a convertir.
    */
    this.toLabel = function(n) {
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

    /*
        Se necesita un grafo bidireccional para hacer el recorrido, esta función transforma el formato de grafo de vis.js (unidireccional) a bidireccional.
        Este grafo inserta el peso de los arcos donde hay un camino, de tomar este caso no se permitiria asignar un peso de 0.    
        nodes = lista de nodos (arreglo de JSON usado en vis.js)
        edges = lista de arcos (arreglo de JSON usado en vis.js)
    */
    this.buildGraph = function (nodes, edges) {

        var graph = [];
        for (var i = 0; i < nodes.length; i++) {
            let node = [];
            for (j = 0; j < nodes.length; j++) {
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
            if (tree.nodes.length>1) {
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
        return {
            nodes: [{
                id: 0,
                label: "No se encontró solución"
            }],
            edges: []
        };
    };

})();
