app.controller("registerController", function($scope, $http, Data) {
    if (Data.isLogged == true) {
        window.location.href = "#/home";
    } else {

        $scope.templates = [ {
            name : 'mutualNavigationBar.html',
            url : 'mutualNavigationBar.html'
        }, {
            name : 'mutualFooter.html',
            url : 'mutualFooter.html'
        } ];
        $scope.navigationBar = $scope.templates[0];
        $scope.footer = $scope.templates[1];

        $scope.addNewUser = function() {
            $http.post('addNewUser', this.Model).success(function(o) {
                showDiv();
            }).error(function(o) {
                showErrorDiv();
            });
        };

        $scope.doesEmailExist = function() {
            $http.post('doesEmailExist', this.Model).success(function(o) {
                chnageInputInfo(o);
            }).error(function(o) {
                showErrorDiv();
            });
        };

        function chnageInputInfo(o) {
            if (o == true) {
                document.getElementById("label").innerHTML = " * Email exists. Chose another one";
                document.getElementById("emailLabelDiv").style.display = "block";
            } else if (o == false) {
                document.getElementById("label").innerHTML = "";
                document.getElementById("emailLabelDiv").style.display = "none";
            }

        }

        function showDiv() {
            document.getElementById("registrationDiv").style.display = "none";
            document.getElementById("errorDiv").style.display = "none";
            document.getElementById("successDiv").style.display = "block";
            document.getElementById("brDiv").style.display = "block";
        }

        function showErrorDiv() {
            document.getElementById("registrationDiv").style.display = "block";
            document.getElementById("errorDiv").style.display = "block";
            document.getElementById("brDiv").style.display = "none";
        }
    }
});