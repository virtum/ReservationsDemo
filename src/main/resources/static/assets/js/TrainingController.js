app.controller("trainingController", function($scope, $http, Data) {
    if (Data.isLogged == false) {
        window.location.href = "#/signin";
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

        (function() {
            $http.get('getTrainingsList').success(function(o) {
                var trainingId = 1;

                var liTab = [];

                for ( var i in o) {
                    // creating and setting html li and ul elements
                    var ul = document.getElementById("trainingsList");
                    var li = document.createElement("li");

                    // creating and setting html anchor with attributes and
                    // values
                    var anchor = document.createElement("a");
                    var id = document.createAttribute("id");
                    var elemId = "trainingInfo" + trainingId.toString();
                    var obj = {
                        id : o[i].id,
                        elmId : elemId
                    }
                    liTab[trainingId] = obj;
                    id.value = elemId;

                    anchor.setAttributeNode(id);
                    var anchorAtt = document.createAttribute("href");
                    anchorAtt.value = "#/training";
                    anchor.setAttributeNode(anchorAtt);
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

                    trainingId++;
                }

                for ( var i in liTab) {
                    document.getElementById(liTab[i].elmId).onclick = showTrainingInfo(liTab[i].id);
                }
            })
        })();

        var showTrainingInfo = function(trainingId) {

            return function() {
                var model = {
                    id : trainingId,
                    userEmail : Data.email
                }

                $http.post('getTrainingInfo', model).success(function(o) {
                    $scope.title = o.title;
                    $scope.lecturer = o.lecturer;
                    $scope.description = o.description;
                    $scope.trainingDate = new Date(o.trainingDate).toDateString();
                    $scope.seats = o.seats;

                    document.getElementById('lecturerPhoto').src = getLecturerPhoto(trainingId);
                    document.getElementById("mainDiv").style.display = "none";
                    document.getElementById("trainingDiv").style.display = "block";
                    if (o.seats <= 0 || o.signedIn == true) {
                        document.getElementById("signUpButtonDiv").style.display = "none";
                        document.getElementById("signedInDiv").style.display = "block";
                        return;
                    }
                    document.getElementById("signUpButtonDiv").style.display = "block";
                    document.getElementById("signedInDiv").style.display = "none";

                }).error(function(o) {
                    document.getElementById("mainDiv").style.display = "block";
                    document.getElementById("trainingDiv").style.display = "none";
                });
            }
        }

        var trainId;
        var getLecturerPhoto = function(trainingId) {
            trainId = trainingId;
            return "assets/images/Lecturers/" + trainingId + ".jpg";
        }

        $scope.signUpForTraining = function() {
            var model = {
                userEmail : Data.email,
                id : trainId
            }
            $http.post('signUpForTraining', model).success(function(o) {
                showTrainingInfo(trainId)();
            }).error(function(o) {

            })
        }

        $scope.unsubscribeFromTraining = function() {
            var model = {
                userEmail : Data.email,
                id : trainId
            }
            $http.post('unsubscribeFromTraining', model).success(function(o) {
                showTrainingInfo(trainId)();
            }).error(function(o) {

            })
        }

        if (Data.isLogged == true) {
            window.onbeforeunload = function() {
                return "Data will be lost if you leave the page, are you sure?";
            };
        }
        ;
    }
});
