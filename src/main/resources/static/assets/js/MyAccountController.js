app.controller("myAccountController", function($scope, $http, Data) {
    if (Data.isLogged == false) {
        window.location.href = "#/signin";
    } else {

        (function() {
            $http.post('getUserData', Data.email).success(function(o) {
                $scope.firstName = o.firstName;
                $scope.lastName = o.lastName;
                $scope.email = o.mail;
                $scope.password = o.password;
            }).error(function(o) {

            });
        })();

        (function() {
            $http.post('getUserParkingData', Data.email).success(function(o) {
                if (o.ownerEmail == Data.email) {
                    $scope.parkingName = o.parkingName.toUpperCase();
                    $scope.placeNumber = o.placeNumber;
                    document.getElementById("haveParkingDiv").style.display = "block";
                    document.getElementById("withoutParkingDiv").style.display = "none";
                } else {
                    document.getElementById("haveParkingDiv").style.display = "none";
                    document.getElementById("withoutParkingDiv").style.display = "block";
                }
            }).error(function(o) {

            });
        })();

        (function() {
            $http.post('getUserBookData', Data.email).success(function(o) {
                if (o.ownerEmail == Data.email) {
                    $scope.title = o.title;
                    $scope.author = o.author;
                    document.getElementById("haveBookDiv").style.display = "block";
                    document.getElementById('noBookDiv').style.display = "none";
                } else {
                    document.getElementById("haveBookDiv").style.display = "none";
                    document.getElementById('noBookDiv').style.display = "block";
                }
            }).error(function(o) {

            });
        })();

        (function() {
            $http.post('getUserTrainingsList', Data.email).success(function(o) {
                if (o.length <= 0) {
                    document.getElementById('userTrainingsDiv').style.display = "none";
                    document.getElementById('noTrainingsDiv').style.display = "block";
                } else {
                    for ( var i in o) {
                        document.getElementById('userTrainingsDiv').style.display = "block";
                        document.getElementById('noTrainingsDiv').style.display = "none";

                        // creating and setting html li and ul elements
                        var ul = document.getElementById("trainingsList");
                        var li = document.createElement("li");

                        // creating and setting html anchor with attributes and
                        // values
                        var anchor = document.createElement("a");
                        var id = document.createAttribute("id");

                        anchor.setAttributeNode(id);
                        anchor.appendChild(document.createTextNode(o[i].title));
                        li.appendChild(anchor);

                        // creating html br element
                        var firstBreak = document.createElement("br");
                        li.appendChild(firstBreak);

                        // creating and setting html span element
                        var span = document.createElement("span");
                        var spanClassAtt = document.createAttribute("class");
                        spanClassAtt.value = "small text-muted";
                        span.setAttributeNode(spanClassAtt);
                        span.appendChild(document.createTextNode("by " + o[i].lecturer));
                        li.appendChild(span);

                        // creating html br element
                        var secondBreak = document.createElement("br");
                        li.appendChild(secondBreak);

                        // creating and setting html span element
                        var secondSpan = document.createElement("span");
                        var secondSpanClassAtt = document.createAttribute("class");
                        secondSpanClassAtt.value = "small text-muted";
                        secondSpan.setAttributeNode(secondSpanClassAtt);
                        secondSpan.appendChild(document.createTextNode(new Date(o[i].trainingDate).toDateString()));
                        li.appendChild(secondSpan);

                        ul.appendChild(li);
                    }
                }
            })
        })();

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
        $scope.userEmail = Data.email;

        $scope.checkIfUserIsLoggedIn = function() {
            if (Data.isLogged == true) {
                return $scope.setNavigationBar = $scope.templates[2];
            }
            return $scope.setNavigationBar = $scope.templates[0];
        }

        $scope.logOut = function() {
            Data.isLogged = false;
            window.location.reload();
        };

        if (Data.isLogged == true) {
            window.onbeforeunload = function() {
                return "Data will be lost if you leave the page, are you sure?";
            };
        }
        ;
    }
});
