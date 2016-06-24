var app = angular.module('mainApp', []);

app.controller('mainCtrl', function($scope, $http, $interval){
	$scope.stocks = [];
	
	$scope.addStock = function(){
		if($scope.stockSymbol){
			$http({
				method:"GET",
				url: "stock/"+$scope.stockSymbol,
			}).success(function(data){
				$scope.stockSymbol = "";
				$scope.showList();
			});
		}
	}
	
	
	$scope.deleteStock = function(){
		if($scope.stockSymbol){
			$http({
				method:"DELETE",
				url: "stock/delete/"+$scope.stockSymbol,
			}).success(function(data){
				$scope.stockSymbol = "";
				$scope.showList();
			});
		}
	}
	
	$scope.showList = function(){
		$http({
			method: "GET",
			url: "stock"
		}).success(function(data){
			$scope.stocks = data;
		});
	}
	
	$scope.showList();
	$interval($scope.showList, 5000);
	
	
	$scope.stockHistory = [];
	$scope.stockNameHistory = "";
	$scope.stockSymbolHistory = "";
	$scope.showHistory = function(stock){
		$http({
			method: "GET",
			url: "stock/history/" + stock.symbol
		}).success(function(data){
			$scope.stockHistory = [];
			$scope.stockNameHistory = stock.name;
			$scope.stockSymbolHistory = stock.symbol;
			for(var i = 1; i < data.records.length; i++){
				$scope.stockHistory.push(data.records[i].split(','));
			}
			generate($scope.stockHistory);
		});
	}
	
	
	var generate = function(data){
		console.log(data[40][0])
		var len = data.length;
		var base = Math.floor(len/12);
		
		var ctx = document.getElementById("c").getContext("2d");
		var lab = [];
		var val = [];
		for(var i = 11; i >= 0; i--){
			lab.push(data[base*i][0]);
			val.push(data[base*i][4]);
		}
		console.log(val)
	    var data = {
	      labels: lab,
	      datasets: [{
	        label: "Stock Price Chart",
	        fillColor: "rgba(220,220,220,0.2)",
	        strokeColor: "rgba(220,220,220,1)",
	        pointColor: "rgba(220,220,220,1)",
	        pointStrokeColor: "#fff",
	        pointHighlightFill: "#fff",
	        pointHighlightStroke: "rgba(220,220,220,1)",
	        data: val
	      }]
	    };
	    var MyNewChart = new Chart(ctx).Line(data);
	}
	
});