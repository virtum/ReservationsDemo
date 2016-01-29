app.controller("parkingController", function($scope, $http, Data) {
    if (Data.isLogged == false) {
        window.location.href = "#/signin";
    } else {

        $scope.templates = [ {
            name : 'mutualFooter.html',
            url : 'mutualFooter.html'
        }, {
            name : 'mutualNavigationBarLoggedIn.html',
            url : 'mutualNavigationBarLoggedIn.html'
        } ];

        $scope.footer = $scope.templates[0];
        $scope.setNavigationBarIn = $scope.templates[1];

        $scope.logOut = function() {
            Data.isLogged = false;
            window.location.reload();
        };

        $scope.parkings = [ {
            value : 'a',
            label : 'Parking A'
        }, {
            value : 'b',
            label : 'Parking B'
        } ];

        $scope.getParkingInfo = function() {
            var model = {
                parkingName : $scope.selectedItem.value,
                userEmail : Data.email
            }

            $http.post('getParginkInfo', model).success(function(o) {
                if (o.hasAlreadyParkingPlace == false && o.placesTaken < 20) {
                    document.getElementById("successDiv").style.display = "block";
                    document.getElementById("fullDiv").style.display = "none";
                    document.getElementById("hasPlaceDiv").style.display = "none";
                    $scope.parkingName = o.parkingName.toUpperCase();
                    $scope.placesTaken = o.placesTaken;
                    $scope.placesFree = o.placesFree;
                } else if (o.hasAlreadyParkingPlace == true && o.placesTaken < 20) {
                    $scope.parkingName = o.parkingName.toUpperCase();
                    $scope.placesTaken = o.placesTaken;
                    $scope.placesFree = o.placesFree;
                    document.getElementById("hasPlaceDiv").style.display = "block";
                    document.getElementById("successDiv").style.display = "none";
                    document.getElementById("fullDiv").style.display = "none";
                } else if (o.placesTaken <= 20) {
                    document.getElementById("hasPlaceDiv").style.display = "block";
                    document.getElementById("successDiv").style.display = "none";
                    document.getElementById("fullDiv").style.display = "none";
                }

            }).error(function(o) {

            });
        };

        $scope.leaveParkingPlace = function() {
            $http.post('leaveParkingPlace', Data.email).success(function(o) {
                if (o == true) {
                    $scope.getParkingInfo();
                    document.getElementById("successDiv").style.display = "block";
                    document.getElementById("fullDiv").style.display = "none";
                    document.getElementById("hasPlaceDiv").style.display = "none";
                }
            }).error(function(o) {

            });
        };

        $scope.reserveParkingPlace = function() {
            var model = {
                parkingName : $scope.selectedItem.value,
                userEmail : Data.email
            }

            $http.post('reserveParkingPlace', model).success(function(o) {
                document.getElementById("hasPlaceDiv").style.display = "block";
                document.getElementById("successDiv").style.display = "none"
                document.getElementById("fullDiv").style.display = "none"
                document.getElementById("errorDiv").style.display = "none"
            }).error(function(o) {
                document.getElementById("hasPlaceDiv").style.display = "none";
                document.getElementById("successDiv").style.display = "none"
                document.getElementById("fullDiv").style.display = "none"
                document.getElementById("errorDiv").style.display = "block"
            });
        };

        if (Data.isLogged == true) {
            window.onbeforeunload = function() {
                return "Data will be lost if you leave the page, are you sure?";
            };
        }
        ;
    }

});