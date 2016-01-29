app.controller("homeController", function($scope, $http, Data) {
    $scope.templates = [ {
        name : 'mutualNavigationBar.html',
        url : 'mutualNavigationBar.html'
    }, {
        name : 'mutualFooter.html',
        url : 'mutualFooter.html'
    }, {
        name : 'mutualNavigationBarLoggedIn.html',
        url : 'mutualNavigationBarLoggedIn.html'
    } ];

    $scope.footer = $scope.templates[1];

    $scope.checkIfUserIsLoggedIn = function() {
        if (Data.isLogged == true) {
            return $scope.setNavigationBar = $scope.templates[2];
        }
        return $scope.setNavigationBar = $scope.templates[0];
    }

    $scope.logOut = function() {
        window.location.reload();
    };

    if (Data.isLogged == true) {
        window.onbeforeunload = function() {
            return "Data will be lost if you leave the page, are you sure?";
        };
    }
    ;

});
