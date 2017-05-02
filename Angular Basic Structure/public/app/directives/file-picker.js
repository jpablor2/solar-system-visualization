(function () {
    var app = angular.module('file-picker', []);
/*
    app.controller('LoadController', function ($scope) {

    });*/

    app.directive('filePicker', function () {
        return {
            restrict: 'E', //It means element
            templateUrl: 'views/file-picker.html',
            controller: function () {

                $(document).on('change', '.btn-file :file', function () {
                    var input = $(this),
                        numFiles = input.get(0).files ? input.get(0).files.length : 1,
                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                    input.trigger('fileselect', [numFiles, label]);
                });

                $(document).ready(function () {
                    $('.btn-file :file').on('fileselect', function (event, numFiles, label) {

                        var input = $(this).parents('.input-group').find(':text'),
                            log = numFiles > 1 ? numFiles + ' files selected' : label;

                        if (input.length) {
                            input.val(log);
                        } else {
                            if (log) alert(log);
                        }
                        this.tab=0;

                    });
                });

                this.tab = 1;

                this.isSetFile = function (checkTab) {
                    return this.tab === checkTab;
                };

                this.setTabFile = function (activeTab) {
                    this.tab = activeTab;
                    if(activeTab===0)
                        console.log("Llamando la promesa para que decodifique el archivo y se cree el grafo dinamicamente");
                };


            },
            controllerAs: 'tab'
        };
    });


})();
