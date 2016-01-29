app.controller("categoriesController", function($scope, $http, Data) {
    if (Data.isLogged == true) {
        window.onbeforeunload = function() {
            return "Data will be lost if you leave the page, are you sure?";
        };
    }
    ;

    $scope.templates = [ {
        name : 'mutualNavigationBar.html',
        url : 'mutualNavigationBar.html'
    }, {
        name : 'mutualFooter.html',
        url : 'mutualFooter.html'
    }, {
        name : 'categoriesBlocked.html',
        url : 'categoriesBlocked.html'
    }, {
        name : 'categoriesEnabled.html',
        url : 'categoriesEnabled.html'
    }, {
        name : 'mutualNavigationBarLoggedIn.html',
        url : 'mutualNavigationBarLoggedIn.html'
    } ];

    $scope.footer = $scope.templates[1];

    $scope.checkIfUserIsLoggedIn = function() {
        if (Data.isLogged == true) {
            $scope.setNavigationBar = $scope.templates[4];
            $scope.isUserLoggedIn = $scope.templates[3];
            return;
        }
        $scope.setNavigationBar = $scope.templates[0];
        $scope.isUserLoggedIn = $scope.templates[2];
        return;
    }

    $scope.logOut = function() {
        Data.isLogged = false;
        window.location.reload();
    }
});