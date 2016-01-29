app.controller("bookController", function($scope, $http, Data) {
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

        var haveBook = false;
        (function() {
            $http.get('getBooksList').success(function(o) {
                $scope.books = [];
                for (var i = 0; i < o.length; i++) {
                    if (o[i].ownerEmail == Data.email) {
                        haveBook = true;
                    }
                    $scope.books[i] = {
                        value : o[i].id,
                        label : o[i].title
                    }
                }
                $scope.selectedItem = $scope.books[0];
                $scope.getBookInfo();
            });
        })();

        $scope.reserveBook = function() {
            var model = {
                bookId : $scope.selectedItem.value,
                userEmail : Data.email
            }

            $http.post('reserveBook', model).success(function(o) {
                if (o == true) {
                    haveBook = true;
                    $scope.getBookInfo();
                }
            }).error(function(o) {

            });
        };

        $scope.returnBook = function() {
            $http.post('returnBook', Data.email).success(function(o) {
                if (o == true) {
                    haveBook = false;
                    $scope.getBookInfo();
                }
            }).error(function(o) {

            });
        };

        $scope.getBookInfo = function() {
            getBookCover($scope.selectedItem.value);
            $http.post('getBookInfo', $scope.selectedItem.value).success(function(o) {
                $scope.title = o.title;
                $scope.author = o.author;
                if (o.free == true && haveBook == true) {
                    $scope.isFree = "This book is available!";
                    document.getElementById("reserveButtonDiv").style.display = "none";
                    document.getElementById("buttonBlockedDiv").style.display = "block";
                } else if (o.free == false && haveBook == true) {
                    $scope.isFree = "This book is borrowed!";
                    document.getElementById("reserveButtonDiv").style.display = "none";
                    document.getElementById("buttonBlockedDiv").style.display = "block";
                } else if (o.free == true && haveBook == false) {
                    $scope.isFree = "This book is available!";
                    document.getElementById("reserveButtonDiv").style.display = "block";
                    document.getElementById("buttonBlockedDiv").style.display = "none";
                } else if (o.free == false && haveBook == false) {
                    $scope.isFree = "This book is borrowed!";
                    document.getElementById("reserveButtonDiv").style.display = "none";
                    document.getElementById("buttonBlockedDiv").style.display = "none";
                }

                document.getElementById("bookInfoDiv").style.display = "block";
                document.getElementById('cover').src = getBookCover($scope.selectedItem.value);
            }).error(function(o) {

            });
        };

        var getBookCover = function(imageId) {
            document.getElementById("bookCoverDiv").style.display = "block";
            return "assets/images/BookCovers/" + imageId + ".jpg";
        }

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
