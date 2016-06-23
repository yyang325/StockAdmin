<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script>
	var app = angular.module('mainApp', []);
	app.controller('mainCtrl', function($scope, $http){
		$scope.add = function(symbol){
			console.log(symbol);
			$http({
		        method : "GET",
		        url : "stock/"+$scope.symbol,
		    }).then(function mySucces(response) {
		        console.log("success!");
		    }, function myError(response) {
		    	console.log("Error!");
		    });
		}
		
		
		$scope.deleteStock = function(delete_symbol){
			console.log(delete_symbol);
			$http({
		        method : "DELETE",
		        url : "stock/delete/"+$scope.delete_symbol,
		    }).then(function mySucces(response) {
		        console.log("success!");
		    }, function myError(response) {
		    	console.log("Error!");
		    });
		}
	});
</script>

</head>
<body ng-app="mainApp" ng-controller="mainCtrl">
<h2>Stock Monitor</h2>
<table>
	<tr>
		<td>ADD STOCK</td>
		<td>
		<input type="text" placeholder="Stock Symbol" ng-model="symbol"><button id="addStock" ng-click="add(symbol)">Add</button>
		</td>
	</tr>
	<tr>
		<td>DELETE STOCK</td>
		<td>
		<input type="text" placeholder="Stock Symbol" ng-model="delete_symbol"><button id="deleteStock" ng-click="deleteStock(delete_symbol)">Delete</button>
		</td>
	</tr>
</table>
</body>
</html>