app.controller("loginController", function($scope, $http, Data) {
    if (Data.isLogged == true) {
        window.location.href = "#/home";
    } else {
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

        $scope.setNavigationBarOut = $scope.templates[0];
        $scope.setNavigationBarIn = $scope.templates[2];

        function checkIfUserIsLoggedIn() {
            document.getElementById("inDiv").style.display = "block";
            document.getElementById("outDiv").style.display = "none";
            document.getElementById("loginDiv").style.display = "none";
            document.getElementById("errorDiv").style.display = "none";
            document.getElementById("successDiv").style.display = "block";
            document.getElementById("brDiv").style.display = "block";

            if (Data.isLogged == true) {
                window.onbeforeunload = function() {
                    return "Data will be lost if you leave the page, are you sure?";
                };
            }
            ;
        }
        ;

        $scope.logIn = function() {
            $http.post('logIn', this.Model).success(function(o) {
                Data.email = o.userEmail;
                Data.isLogged = o.loginSuccess;
                if (o.loginSuccess == true) {
                    checkIfUserIsLoggedIn();
                } else {
                    showErrorDiv();
                }
            }).error(function(o) {
                showErrorDiv()
            });
        };

        $scope.logOut = function() {
            Data.isLogged = false;
            window.location.reload();
        };

        function showErrorDiv() {
            document.getElementById("loginDiv").style.display = "block";
            document.getElementById("errorDiv").style.display = "block";
            document.getElementById("brDiv").style.display = "none";
        }
    }

});
