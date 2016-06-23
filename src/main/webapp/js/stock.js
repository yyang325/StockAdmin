var app = angular.module('mainApp', []);

app.controller('mainCtrl', function($scope, $http){
	$scope.addStock = function(){
		if($scope.stockSymbol){
			$http({
				type:"GET",
				url: "stock/"+$scope.stockSymbol,
			}).success(function(data){
				console.log("Ajax Success!");
				$scope.showList();
			});
		}
		
		
	}
	
	$scope.showList = function(){
		$http({
			type: "GET",
			url: "stock"
		}).success(function(data){
			console.log(data);
		});
	}
});