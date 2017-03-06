(function () {
    var app = angular.module('notes', []);

    app.controller('NotesController', ['$http', '$filter', function ($http, $filter) {
        var self = this;

        this.list = [];
        this.newNote = {};

        this.updateList = function () {
            $http.get('/rest/notes').then(function (response) {
                    var data = response.data;
                    for (i = 0; i < data.length; i++) {
                        data[i]['creationDate'] = new Date(data[i]['creationDate']).toDateString();
                    }

                    console.log(data);

                    self.list = data;
                },
                this.errorHandler);
        };

        this.add = function () {
            $http.post('/rest/notes', this.newNote).then(function () {
                    self.updateList();
                    self.newNote = {};
                },
                this.errorHandler);
        };

        this.remove = function (id) {
            $http.delete('/rest/notes/' + id).then(function () {
                    self.updateList();
                },
                this.errorHandler);
        };

        this.errorHandler = function (error) {
            console.error(error);
        };

        this.updateList();

    }]);
})();
